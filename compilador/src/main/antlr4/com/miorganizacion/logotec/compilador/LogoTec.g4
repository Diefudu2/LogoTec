grammar LogoTec;

@parser::header{
    import java.util.*;
    import com.miorganizacion.logotec.compilador.ast.*;
}
@parser::members{
    Map<String, Symbol> symbols = new HashMap<>(); 
    List<String> errors = new ArrayList<>();
    boolean firstLineHasComment = false;
    boolean atLeastOneVariable = false;

    // Nuevo: llamadas a procedimientos pendientes para validar al final
    static class PendingCall {
        final String name;
        final int argCount;
        final int line;
        PendingCall(String name, int argCount, int line) {
            this.name = name;
            this.argCount = argCount;
            this.line = line;
        }
    }
    List<PendingCall> pendingCalls = new ArrayList<>();

    void ensureProgramConstraints() {
        // Ya no fallar por falta de comentario en la primera línea.
        if (!atLeastOneVariable) {
            errors.add("Error: el programa debe definir al menos una variable con 'Haz' o 'INIC'.");
        }
        // Validar llamadas pendientes a procedimientos (soporta referencias adelantadas)
        for (PendingCall pc : pendingCalls) {
            Symbol s = symbols.get(pc.name);
            if (s == null) {
                errors.add("Error semántico: procedimiento '" + pc.name + "' no está definido.");
            } else if (s.type != ValueType.PROCEDURE) {
                errors.add("Error semántico: '" + pc.name + "' no es un procedimiento.");
            } else {
                int expectedParams = (Integer) s.value;
                if (pc.argCount != expectedParams) {
                    errors.add("Error semántico: procedimiento '" + pc.name + "' espera " + expectedParams + " parámetros, pero se llamó con " + pc.argCount + ".");
                }
            }
        }
        pendingCalls.clear();

        if (!errors.isEmpty()) {
            throw new RuntimeException(String.join("\n", errors));
        }
    }

    boolean isValidVarName(String id) {
        if (id.length() == 0 || id.length() > 10) return false;
        if (!Character.isLowerCase(id.charAt(0))) return false;
        for (char c : id.toCharArray()) {
            if (!(Character.isLetterOrDigit(c) || c=='_' || c=='&' || c=='@')) return false;
        }
        return true;
    }

    void declareOrAssign(String name, ValueType type, Object value) {
        if (!isValidVarName(name)) {
            errors.add("Error léxico: identificador inválido '" + name + "'.");
            return;
        }
        Symbol s = symbols.get(name);
        if (s == null) {
            symbols.put(name, new Symbol(name, type, value));
            atLeastOneVariable = true;
        } else {
            if (s.type != type) {
                errors.add("Error semántico: intento de asignar " + type + " a variable '" + name + "' de tipo " + s.type + ".");
            } else {
                s.value = value;
            }
        }
    }

    void predeclareProcedure(String name) {
        Symbol s = symbols.get(name);
        if (s == null) {
            symbols.put(name, new Symbol(name, ValueType.PROCEDURE, null));
        } else if (s.type == ValueType.PROCEDURE && s.value == null) {
            errors.add("Error semántico: procedimiento '" + name + "' ya está en proceso de definición.");
        } else {
            errors.add("Error semántico: símbolo '" + name + "' ya está definido y no puede volver a declararse como procedimiento.");
        }
    }

    void declareProcedure(String name, int paramCount) {
        Symbol s = symbols.get(name);
        if (s == null) {
            symbols.put(name, new Symbol(name, ValueType.PROCEDURE, paramCount));
        } else if (s.type != ValueType.PROCEDURE) {
            errors.add("Error semántico: símbolo '" + name + "' ya está definido y no es un procedimiento.");
        } else if (s.value == null) {
            s.value = paramCount;
        } else {
            int expectedParams = (Integer) s.value;
            if (expectedParams != paramCount) {
                errors.add("Error semántico: procedimiento '" + name + "' ya está definido con " + expectedParams + " parámetros.");
            }
        }
    }

    void validateProcedureCall(String name, int argCount) {
        Symbol s = symbols.get(name);
        // Si no existe aún o está predeclarado sin aridad, difiere la validación
        if (s == null || (s.type == ValueType.PROCEDURE && s.value == null)) {
            pendingCalls.add(new PendingCall(name, argCount, getCurrentToken()!=null ? getCurrentToken().getLine() : -1));
            return;
        }
        if (s.type != ValueType.PROCEDURE) {
            errors.add("Error semántico: '" + name + "' no es un procedimiento.");
        } else {
            int expectedParams = (Integer) s.value;
            if (argCount != expectedParams) {
                errors.add("Error semántico: procedimiento '" + name + "' espera " + expectedParams + " parámetros, pero se llamó con " + argCount + ".");
            }
        }
    }
}

/* ------------------- Programa ------------------- 
Parsea la unidad completa de un fichero LogoTec y construye el nodo raíz 
del AST (ProgramNode).

Comportamiento:
Acepta opcionalmente un comentario inicial (cmtFirstLine).
Procesa un proceduresBlock que puede contener declaraciones de procedimiento y 
sentencias principales mezcladas.
Acumula ProcDeclNode en la lista decls y StmtNode en la lista mainBody.

Llama a ensureProgramConstraints() para validar restricciones globales antes 
de devolver el ProgramNode
*/

program returns [ProgramNode node]
    : cmtFirstLine?                // el comentario inicial es OPCIONAL para parsear,
                                   // pero se valida luego en ensureProgramConstraints()
      p=proceduresBlock EOF
      {
        ensureProgramConstraints();
        $node = $p.node;
      }
    ;

proceduresBlock returns [ProgramNode node]
    @init {
        List<ProcDeclNode> decls = new ArrayList<>();
        List<StmtNode> mainBody = new ArrayList<>();
    }
    : (  procedureDecl { decls.add($procedureDecl.node); }
       | sentence      { mainBody.add($sentence.node); }
      )*
      {
        $node = new ProgramNode(decls, mainBody);
      }
    ;

/* ------------------- Procedimientos ------------------- 
Parsear una declaración de procedimiento y construir su nodo AST (ProcDeclNode).
Sintaxis: PARA <procName> (lista de parámetros entre corchetes) 
(cuerpo de sentencias entre corchetes) FIN.

Durante el parseo acumula los nombres de parámetros en params y las sentencias 
en body.

Marca atLeastOneVariable = true si el procedimiento declara parámetros, 
contando éstos como variables válidas para las comprobaciones globales.

Al finalizar crea y devuelve new ProcDeclNode(procName, params, body).
*/

procedureDecl returns [ProcDeclNode node]
@init { 
    List<String> params = new ArrayList<>(); 
    List<StmtNode> body = new ArrayList<>(); 
}
: PARA procName=ID
  { predeclareProcedure($procName.text); }
  BRACKET_OPEN (param=ID { 
      params.add($param.getText()); 
      declareOrAssign($param.text, ValueType.UNKNOWN, null);
  })* BRACKET_CLOSE
  { declareProcedure($procName.text, params.size()); }
  BRACKET_OPEN ( sentence {body.add($sentence.node);} )* BRACKET_CLOSE
  FIN
  {
    if (!params.isEmpty()) atLeastOneVariable = true;
    $node = new ProcDeclNode($procName.text, params, body);
  }
;


/* ------------------- Sentencias ------------------- 
Define las formas válidas de sentencia en el lenguaje y construir los nodos AST 
correspondientes (StmtNode).

sentence:
Alternativa que agrupa todas las formas de sentencia: declaración de variable, asignación, comando de tortuga, control de flujo, bloque de ejecución o llamada a procedimiento.
Devuelve el nodo específico producido por la subregla seleccionada.

varDecl (HAZ):
Sintaxis: HAZ <name> <literal|string> [;]
Infiera el tipo del literal con ValueType.infer(...), registra la variable
mediante declareOrAssign(...), y devuelve VarDeclNode(name, value).
Nota: permite punto y coma opcional al final.

varInit (INIC):
Sintaxis: INIC <name> ASSIGN <expression> ;
Infiera el tipo de la expresión, registra la asignación con declareOrAssign(...), 
y devuelve VarAssignNode(name, expression).
Requiere el punto y coma final para terminar la sentencia.

callProc:
Sintaxis flexible: <procName> (args entre corchetes) | procName arg1 arg2 ...
Acumula expresiones de argumento en una lista args y construye 
ProcCallNode(procName, args).
Soporta cero o más argumentos; corchetes opcionales según la forma usada.
*/

sentence returns [StmtNode node]
    : hazHastaStmt      { $node = $hazHastaStmt.node; }
    | hazMientrasStmt   { $node = $hazMientrasStmt.node; }
    | varDecl           { $node = $varDecl.node; }
    | varInit           { $node = $varInit.node; }
    | callProc          { $node = $callProc.node; }
    | turtleCmd         { $node = $turtleCmd.node; }
    | siStmt            { $node = $siStmt.node; }
    | hastaStmt         { $node = $hastaStmt.node; }
    | mientrasStmt      { $node = $mientrasStmt.node; }
    | repiteBlock       { $node = $repiteBlock.node; }
    | execBlock         { $node = $execBlock.node; }
 ;

/* Declaración de variables */
varDecl returns [StmtNode node]
    : HAZ name=ID value=literalOrString (SEMICOLON)?
      {
        declareOrAssign($name.text, ValueType.infer($value.node), $value.jval);
        $node = new VarDeclNode($name.text, $value.node);
      }
    ;

varInit returns [StmtNode node]
    : INIC name=ID ASSIGN expression SEMICOLON
      {
        ValueType t = ValueType.infer($expression.node);
        declareOrAssign($name.text, t, null);
        $node = new VarAssignNode($name.text, $expression.node);
      }
    ;

/* Llamada a procedimiento */
callProc returns [StmtNode node]
    @init { List<ExprNode> args = new ArrayList<>(); }
    : proc=ID
      (
        // Forma 1: con corchetes (tradicional LogoTec)
        BRACKET_OPEN
            ( expression { args.add($expression.node); }
              ( (COMMA)? expression { args.add($expression.node); } )*
            )?
        BRACKET_CLOSE
        |
        // Forma 2: argumentos directos sin corchetes
        // SOLO acepta primarios simples (números, variables, expresiones entre paréntesis)
        ( primaryArg { args.add($primaryArg.node); } )*
      )
      {
        validateProcedureCall($proc.text, args.size());
        $node = new ProcCallNode($proc.text, args);
      }
   ;

// Argumento primario simple (para llamadas sin corchetes)
primaryArg returns [ExprNode node]
    : NUMBER { $node = new ConstNode(Integer.parseInt($NUMBER.text)); }
    | ID { $node = new VarRefNode($ID.text); }
    | PAR_OPEN expression PAR_CLOSE { $node = $expression.node; }
    ;

expressionSeries returns [List<ExprNode> list]
    @init { $list = new ArrayList<>(); }
    : ( expression { $list.add($expression.node); }
        ( (COMMA)? expression { $list.add($expression.node); } )*
      )?
    ;

/* ------------------- Bloques ------------------- 
Parsear bloques compuestos de sentencias y convertirlos en nodos AST que 
representan ejecución secuencial y bucles.
*/
execBlock returns [StmtNode node]
    @init { List<StmtNode> body = new ArrayList<>(); }
    : EJECUTA BRACKET_OPEN ( sentence {body.add($sentence.node);} )* BRACKET_CLOSE
      { $node = new ExecBlockNode(body); }
    ;

repiteBlock returns [StmtNode node]
    @init { List<StmtNode> body = new ArrayList<>(); }
    : REPITE times=expression BRACKET_OPEN ( sentence {body.add($sentence.node);} )* BRACKET_CLOSE
      { $node = new RepeatNode($times.node, body); }
    ;

/* ------------------- Control de flujo ------------------- 
Parsear las construcciones de control del lenguaje y construir los nodos 
AST correspondientes (IfNode, WhileNode, DoWhileNode, UntilNode, DoUntilNode, 
RepeatNode).

flowStmt: 
regla agregadora que delega en las subreglas específicas de control 
(si, haz/hasta, hasta, haz/mientras, mientras, repite).

siStmt (SI): 
evalúa la condición, acumula sentencias del bloque "then" y opcionalmente del 
bloque "else", devuelve IfNode(cond, thenBody, elseBody|null).

hazHastaStmt (HAZ . HASTA): 
ejecuta el cuerpo al menos una vez y luego evalúa la condición; 
devuelve DoUntilNode(body, cond).

hastaStmt (HASTA): 
evalúa la condición antes o después según la semántica; construye 
UntilNode(cond, body).

hazMientrasStmt (HAZ . MIENTRAS): 
ejecuta el cuerpo al menos
devuelve DoWhileNode(body, cond).

mientrasStmt (MIENTRAS): 
evalúa la condición y, si es verdadera, ejecuta el cuerpo repetidamente; devuelve WhileNode(cond, body).
repiteBlock: 
bucle con contador fijo (ya documentado en la sección de bloques).
*/

// La regla 'flowStmt' ya no es necesaria, sus alternativas se han movido a 'sentence'

siStmt returns [StmtNode node]
    @init { List<StmtNode> thenBody = new ArrayList<>(); List<StmtNode> elseBody = new ArrayList<>(); }
    : SI PAR_OPEN cond=expression PAR_CLOSE
      BRACKET_OPEN ( sentence {thenBody.add($sentence.node);} )* BRACKET_CLOSE
      ( BRACKET_OPEN ( sentence {elseBody.add($sentence.node);} )* BRACKET_CLOSE )?
      {
        $node = new IfNode($cond.node, thenBody, elseBody.isEmpty()? null : elseBody);
      }
    ;

hazHastaStmt returns [StmtNode node]
    @init { List<StmtNode> body = new ArrayList<>(); }
    : HAZ (DOT)?
      BRACKET_OPEN ( sentence {body.add($sentence.node);} )* BRACKET_CLOSE
      HASTA PAR_OPEN cond=expression PAR_CLOSE
      {
        $node = new DoUntilNode(body, $cond.node);
      }
    ;

hazMientrasStmt returns [StmtNode node]
    @init { List<StmtNode> body = new ArrayList<>(); }
    : HAZ (DOT)?
      BRACKET_OPEN ( sentence {body.add($sentence.node);} )* BRACKET_CLOSE
      MIENTRAS PAR_OPEN cond=expression PAR_CLOSE
      {
        $node = new DoWhileNode(body, $cond.node);
      }
    ;

mientrasStmt returns [StmtNode node]
    @init { List<StmtNode> body = new ArrayList<>(); }
    : MIENTRAS PAR_OPEN cond=expression PAR_CLOSE
      BRACKET_OPEN ( sentence {body.add($sentence.node);} )* BRACKET_CLOSE
      {
        $node = new WhileNode($cond.node, body);
      }
    ;

hastaStmt returns [StmtNode node]
    @init { List<StmtNode> body = new ArrayList<>(); }
    : HASTA PAR_OPEN cond=expression PAR_CLOSE
      BRACKET_OPEN ( sentence {body.add($sentence.node);} )* BRACKET_CLOSE
      {
        $node = new UntilNode($cond.node, body);
      }
    ;

/* ------------------- Comandos de tortuga ------------------- 
Parsear todas las instrucciones que controlan la "tortuga" 
(movimiento, orientación, lápiz, posición y utilidades) y convertirlas en nodos 
AST específicos de sentencia.

Soporta formas largos y abreviadas del mismo comando 
(por ejemplo AVANZA / AV, GIRADERECHA / GD).

Para comandos con argumentos visita la(s) expresión(es) correspondiente(s) y 
crea el nodo con esos ExprNode.

Para comandos sin argumentos construye nodos simples sin expresión 
(HideTurtleNode, CenterNode, ShowHeadingNode, PenUpNode, PenDownNode).

PONPOS admite versión con corchetes para separar X e Y; PONXY acepta ambos 
argumentos en secuencia sin corchetes.

INC admite incremento por 1 implícito o incremento por expresión explícita, 
construyendo un IncNode con VarRefNode y ConstNode/ExprNode.

Nodos generados (ejemplos):
ForwardNode, BackwardNode, TurnRightNode, TurnLeftNode
SetPosNode, SetHeadingNode, ShowHeadingNode, SetXNode, SetYNode
PenDownNode, PenUpNode, CenterNode, WaitNode
IncNode con VarRefNode + ExprNode
*/

turtleCmd returns [StmtNode node]
    : AVANZA e=expression        { $node = new ForwardNode($e.node); }
    | AV e=expression            { $node = new ForwardNode($e.node); }
    | RETROCEDE e=expression     { $node = new BackwardNode($e.node); }
    | RE e=expression            { $node = new BackwardNode($e.node); }
    | GIRADERECHA e=expression   { $node = new TurnRightNode($e.node); }
    | GD e=expression            { $node = new TurnRightNode($e.node); }
    | GIRAIZQUIERDA e=expression { $node = new TurnLeftNode($e.node); } // corregido
    | GI e=expression            { $node = new TurnLeftNode($e.node); }
    | OCULTATORTUGA              { $node = new HideTurtleNode(); }
    | OT                         { $node = new HideTurtleNode(); }
    | APARECETORTUGA             { $node = new ShowTurtleNode(); }
    | AT                         { $node = new ShowTurtleNode(); }
    | PONPOS BRACKET_OPEN coords=expressionSeries BRACKET_CLOSE
      {
        List<ExprNode> coordsList = $coords.list;
        if (coordsList.size() != 2) {
            errors.add("Error semántico: 'PONPOS' requiere exactamente dos expresiones para X e Y.");
        }
        ExprNode xNode = coordsList.size() > 0 ? coordsList.get(0) : new ConstNode(0);
        ExprNode yNode = coordsList.size() > 1 ? coordsList.get(1) : new ConstNode(0);
        $node = new SetPosNode(xNode, yNode, true);
      }
    | PONXY x=expression y=expression { $node = new SetPosNode($x.node, $y.node, false); }
    | PONRUMBO e=expression      { $node = new SetHeadingNode($e.node); }
    | RUMBO                      { $node = new ShowHeadingNode(); }
    | PONX e=expression          { $node = new SetXNode($e.node); }
    | PONY e=expression          { $node = new SetYNode($e.node); }
    | BAJALAPIZ                  { $node = new PenDownNode(); }
    | BL                         { $node = new PenDownNode(); }
    | SUBELAPIZ                  { $node = new PenUpNode(); }
    | SB                         { $node = new PenUpNode(); }
    | CENTRO                     { $node = new CenterNode(); }
    | ESPERA e=expression        { $node = new WaitNode($e.node); }
    | INC BRACKET_OPEN id=ID BRACKET_CLOSE
      { $node = new IncNode(new VarRefNode($id.text), new ConstNode(1)); }
    | INC BRACKET_OPEN id=ID n=expression BRACKET_CLOSE
      { $node = new IncNode(new VarRefNode($id.text), $n.node); }
    // NUEVO: Comandos de color
    | PONCOLORLAPIZ BRACKET_OPEN coords=expressionSeries BRACKET_CLOSE
      {
        List<ExprNode> colorList = $coords.list;
        if (colorList.size() != 3) {
            errors.add("Error semántico: 'PONCOLORLAPIZ' requiere exactamente tres valores RGB.");
        }
        ExprNode rNode = colorList.size() > 0 ? colorList.get(0) : new ConstNode(0);
        ExprNode gNode = colorList.size() > 1 ? colorList.get(1) : new ConstNode(0);
        ExprNode bNode = colorList.size() > 2 ? colorList.get(2) : new ConstNode(0);
        // Nota: La validación de colores permitidos se hace en SetColorNode.execute()
        $node = new SetColorNode(rNode, gNode, bNode);
      }
    | PONCL BRACKET_OPEN coords=expressionSeries BRACKET_CLOSE
      {
        List<ExprNode> colorList = $coords.list;
        if (colorList.size() != 3) {
            errors.add("Error semántico: 'PONCL' requiere exactamente tres valores RGB.");
        }
        ExprNode rNode = colorList.size() > 0 ? colorList.get(0) : new ConstNode(0);
        ExprNode gNode = colorList.size() > 1 ? colorList.get(1) : new ConstNode(0);
        ExprNode bNode = colorList.size() > 2 ? colorList.get(2) : new ConstNode(0);
        // Nota: La validación de colores permitidos se hace en SetColorNode.execute()
        $node = new SetColorNode(rNode, gNode, bNode);
      }
    | PONCOLORLAPIZ c=colorName
      { $node = new SetPenColorNode($c.value); }
    | PONCL c=colorName
      { $node = new SetPenColorNode($c.value); }
    ;

colorName returns [String value]
    : c=COLOR { $value = $c.getText().toLowerCase(); }
    | id=ID   { $value = $id.getText().toLowerCase(); }
    ;

/* ------------------- Expresiones ------------------- 
Parsear expresiones aritméticas, lógicas y de funciones, construir su 
jerarquía de ExprNode y realizar inferencia de valor parcial usando el 
atributo val.

Estructura y precedencia
Nivel lógico: OR (expression) → AND (logicTerm) → NOT / relacional (logicFactor).
Comparaciones: relational aplica GT, LT, GEQ, LEQ, EQ, NEQ sobre arithExpr.
Aritmética: arithExpr maneja PLUS/MINUS; term maneja MULT/DIV; factor y exponente 
manejan EXP (exponenciación).
primary cubre literales (NUMBER, BOOLEAN, STRING), identificadores (ID) y 
paréntesis.

Funciones y formas especiales
funcCall define funciones por palabra (IGUALESQ, YFUNC, OFUNC, MAYORQ, MENORQ, 
AZAR, PRODUCTO, POTENCIA, DIVISION, SUMA, DIFERENCIA).
Algunas funciones permiten múltiples argumentos acumulados (PRODUCTO, SUMA, 
DIFERENCIA) y construyen nodos compuestos con listas auxiliares.

Variantes de función mappean a nodos reutilizando operadores
lógicos/relacionales cuando aplica (por ejemplo YFUNC → LogicalAndNode).

Atributos semánticos
Cada regla devuelve ExprNode node y Value val cuando aplica; val se usa para 
inferencia temprana de tipos/valores y debe tratar unknown y errores 
explícitamente.

primary asigna valores concretos para literales y unknown para ID.
*/

expression returns [ExprNode node, Value val]
    : t1=logicTerm { $node = $t1.node; $val = $t1.val; }
      ( OR t2=logicTerm { $node = new LogicalOrNode($node, $t2.node); } )*
    ;

logicTerm returns [ExprNode node, Value val]
    : f1=logicFactor { $node = $f1.node; $val = $f1.val; }
      ( AND f2=logicFactor { $node = new LogicalAndNode($node, $f2.node); } )*
    ;

logicFactor returns [ExprNode node, Value val]
    : NOT lf=logicFactor { $node = new LogicalNotNode($lf.node);}
    | r=relational { $node = $r.node; $val = $r.val; }
    ;

relational returns [ExprNode node, Value val]
    : a1=arithExpr { $node = $a1.node; $val = $a1.val; }
      ( GT a2=arithExpr { $node = new GreaterThanNode($node, $a2.node); }
      | LT a2=arithExpr { $node = new LessThanNode($node, $a2.node); }
      | GEQ a2=arithExpr { $node = new GreaterEqualNode($node, $a2.node); }
      | LEQ a2=arithExpr { $node = new LessEqualNode($node, $a2.node); }
      | EQ a2=arithExpr { $node = new EqualsNode($node, $a2.node); }
      | NEQ a2=arithExpr { $node = new NotEqualsNode($node, $a2.node); }
      )*
    ;

arithExpr returns [ExprNode node, Value val]
    : t1=term { $node = $t1.node; $val = $t1.val; }
      ( PLUS t2=term { $node = new AdditionNode($node, $t2.node); }
      | MINUS t2=term { $node = new SubtractionNode($node, $t2.node);  }
      )*
    ;

term returns [ExprNode node, Value val]
    : f1=factor { $node = $f1.node; $val = $f1.val; }
      ( MULT f2=factor { $node = new MultiplicationNode($node, $f2.node);  }
      | DIV f2=factor { $node = new DivisionNode($node, $f2.node); }
      )*
    ;

factor returns [ExprNode node, Value val]
    : e1=exponent { $node = $e1.node; $val = $e1.val; }
      ( EXP e2=exponent { $node = new ExponentiationNode($node, $e2.node);  } )*
    ;

exponent returns [ExprNode node, Value val]
    : unary { $node = $unary.node; $val = $unary.val; }
    ;

unary returns [ExprNode node, Value val]
    : MINUS u=unary { $node = new SubtractionNode(new ConstNode(0), $u.node); $val = Value.unknown(); }
    | PLUS u=unary  { $node = $u.node; $val = $u.val; }
    | funcCall      { $node = $funcCall.node; $val = $funcCall.val; }
    | primary       { $node = $primary.node; $val = $primary.val; }
 ;

/* Funciones */
funcCall returns [ExprNode node, Value val]
    : IGUALESQ PAR_OPEN e1=expression COMMA e2=expression PAR_CLOSE
      { $node = new EqualsFuncNode($e1.node, $e2.node); }
    | YFUNC PAR_OPEN e1=expression PAR_CLOSE PAR_OPEN e2=expression PAR_CLOSE
      { $node = new LogicalAndNode($e1.node,$e2.node); }
    | YFUNC PAR_OPEN e1=expression COMMA e2=expression PAR_CLOSE
      { $node = new LogicalAndNode($e1.node,$e2.node); }
    | OFUNC PAR_OPEN e1=expression PAR_CLOSE PAR_OPEN e2=expression PAR_CLOSE
      { $node = new LogicalOrNode($e1.node,$e2.node); }
    | OFUNC PAR_OPEN e1=expression COMMA e2=expression PAR_CLOSE
      { $node = new LogicalOrNode($e1.node,$e2.node); }
    | MAYORQ PAR_OPEN e1=expression COMMA e2=expression PAR_CLOSE
      { $node = new GreaterThanNode($e1.node,$e2.node);  }
    | MENORQ PAR_OPEN e1=expression COMMA e2=expression PAR_CLOSE
      { $node = new LessThanNode($e1.node,$e2.node);  }
    | AZAR PAR_OPEN e=expression PAR_CLOSE
      { $node = new RandomNode($e.node); }
    | PRODUCTO PAR_OPEN e1=expression (COMMA rest+=expression)* PAR_CLOSE
      {
        List<ExprNode> restNodes = new ArrayList<>();
        if ($rest != null) {
            for (ExpressionContext ctx : $rest) {
                restNodes.add(ctx.node);
            }
        }
        $node = new ProductNode($e1.node, restNodes);
      }
    | POTENCIA PAR_OPEN e1=expression COMMA e2=expression PAR_CLOSE
      { $node = new ExponentiationNode($e1.node, $e2.node); }
    | DIVISION PAR_OPEN e1=expression COMMA e2=expression PAR_CLOSE
      { $node = new DivisionNode($e1.node, $e2.node); }
    | SUMA PAR_OPEN e1=expression (COMMA rest+=expression)* PAR_CLOSE
      {
        List<ExprNode> restNodes = new ArrayList<>();
        if ($rest != null) {
            for (ExpressionContext ctx : $rest) {
                restNodes.add(ctx.node);
            }
        }
        $node = new SumNode($e1.node, restNodes);
      }
    | DIFERENCIA PAR_OPEN e1=expression (COMMA rest+=expression)* PAR_CLOSE
      {
        List<ExprNode> restNodes = new ArrayList<>();
        if ($rest != null) {
            for (ExpressionContext ctx : $rest) {
                restNodes.add(ctx.node);
            }
        }
        $node = new DifferenceNode($e1.node, restNodes);
      }
    ;

/* Primarios */
primary returns [ExprNode node, Value val]
    : DECIMAL
      {
        double v = Double.parseDouble($DECIMAL.text.replace(',', '.'));
        $node = new ConstNode(v);
        $val = Value.unknown(); // se mantiene inferencia desconocida si no hay soporte de double en Value
      }
    | NUMBER
      { 
        int v = Integer.parseInt($NUMBER.text);
        $node = new ConstNode(v);
        $val = Value.integer(v);
      }
    | BOOLEAN
      { 
        boolean b = Boolean.parseBoolean($BOOLEAN.text);
        $node = new ConstNode(b);
        $val = Value.bool(b);
      }
    | ID
      { 
        $node = new VarRefNode($ID.text);
        $val = Value.unknown();
      }
    | STRING
      {
        String s = $STRING.text.substring(1,$STRING.text.length()-1);
        $node = new ConstNode(s);
        $val = Value.string(s);
      }
    | PAR_OPEN expression PAR_CLOSE
      { 
        $node = $expression.node; 
        $val = $expression.val; 
      }
    ;


literalOrString returns [ExprNode node, Object jval]
    : MINUS DECIMAL
      {
        double v = -Double.parseDouble($DECIMAL.text.replace(',', '.'));
        $node = new ConstNode(v);
        $jval = Double.valueOf(v);
      }
    | DECIMAL
      {
        double v = Double.parseDouble($DECIMAL.text.replace(',', '.'));
        $node = new ConstNode(v);
        $jval = Double.valueOf(v);
      }
    | MINUS NUMBER
      {
        int v = -Integer.parseInt($NUMBER.text);
        $node = new ConstNode(v);
        $jval = Integer.valueOf(v);
      }
    | NUMBER
      {
        int v = Integer.parseInt($NUMBER.text);
        $node = new ConstNode(v);
        $jval = Integer.valueOf(v);
      }
    | BOOLEAN
      {
        boolean v = Boolean.parseBoolean($BOOLEAN.text);
        $node = new ConstNode(v);
        $jval = Boolean.valueOf(v);
      }
    | STRING
      {
        String s = $STRING.text.substring(1, $STRING.text.length()-1);
        $node = new ConstNode(s);
        $jval = s;
      }
    ;


/* ------------------- Tokens ------------------- */
// Make key keywords case-tolerant
PARA: 'Para' | 'para' | 'PARA';
HAZ: 'Haz' | 'haz' | 'HAZ';
FIN: 'fin' | 'Fin' | 'FIN';
EJECUTA: 'Ejecuta' | 'ejecuta' | 'EJECUTA';
REPITE: 'repite' | 'Repite' | 'REPITE';
APARECETORTUGA: 'aparecetortuga' | 'APARECETORTUGA' | 'ApareceTortuga';
AT: 'at' | 'AT';
PONPOS: 'ponpos' | 'PONPOS' | 'PonPos';
PONXY: 'ponxy' | 'PONXY' | 'PonXY';
PONRUMBO: 'ponrumbo' | 'PONRUMBO' | 'PonRumbo';
RUMBO: 'rumbo' | 'RUMBO';
PONX: 'ponx' | 'PONX' | 'PonX';
PONY: 'pony' | 'PONY' | 'PonY';
BAJALAPIZ: 'bajalapiz' | 'BAJALAPIZ' | 'BajaLapiz';
BL: 'bl' | 'BL';
SUBELAPIZ: 'subelapiz' | 'SUBELAPIZ' | 'SubeLapiz';
SB: 'sb' | 'SB';
CENTRO: 'centro' | 'CENTRO' | 'Centro';
ESPERA: 'espera' | 'ESPERA' | 'Espera';
INC: 'inc' | 'INC';
SI: 'SI';
HASTA: 'HASTA';
MIENTRAS: 'MIENTRAS';
INIC: 'INIC';
AVANZA: 'avanza';
AV: 'av';
RETROCEDE: 'retrocede';
RE: 're';
ATRAS: 'atras';
GIRADERECHA: 'giraderecha';
GD: 'gd';
GIRAIZQUIERDA: 'giraizquierda';
GI: 'gi';
OCULTATORTUGA: 'ocultatortuga';
OT: 'ot';
PONCOLORLAPIZ: 'poncolorlapiz' | 'PONCOLORLAPIZ' | 'PonColorLapiz';
PONCL: 'poncl' | 'PONCL' | 'PonCL';

/* Funciones (case-tolerant) */
IGUALESQ: 'iguales?' | 'Iguales?' | 'IGUALES?';
YFUNC: 'Y';      // mantener solo mayúscula para no chocar con ID
OFUNC: 'O';      // idem
MAYORQ: 'mayorque?' | 'Mayorque?' | 'MAYORQUE?';
MENORQ: 'menorque?' | 'Menorque?' | 'MENORQUE?';
DIFERENCIA: 'diferencia' | 'Diferencia' | 'DIFERENCIA';
AZAR: 'azar' | 'Azar' | 'AZAR';
PRODUCTO: 'producto' | 'Producto' | 'PRODUCTO';
POTENCIA: 'potencia' | 'Potencia' | 'POTENCIA';
DIVISION: 'division' | 'Division' | 'DIVISION';
SUMA: 'suma' | 'Suma' | 'SUMA';

/* Operadores y símbolos */
PLUS: '+';
MINUS: '-';
MULT: '*';
DIV: '/';
EXP: '^';
AND: '&&';
OR: '||';
NOT: '!';
GT: '>';
LT: '<';
GEQ: '>=';
LEQ: '<=';
EQ: '==';
NEQ: '!=';
ASSIGN: '=';
BRACKET_OPEN: '[' | '{';
BRACKET_CLOSE: ']' | '}';
PAR_OPEN: '(';
PAR_CLOSE: ')';
SEMICOLON: ';';
/* Evita que la coma entre dígitos sea tratada como separador de argumentos */
COMMA: { !(Character.isDigit((char)_input.LA(-1)) && Character.isDigit((char)_input.LA(1)) ) }? ',';
DOT: '.';

/* Literales (números decimales con coma) */
BOOLEAN: 'TRUE' | 'FALSE' | 'true' | 'false';
/* Permite 3,14 y 3.14 (sin espacios) */
DECIMAL: [0-9]+ (',' | '.') [0-9]+; // usar 3,14 o 3.14 (no 3, 14)
NUMBER: [0-9]+;
STRING: '"' (~["\r\n])* '"' ;

/* Identificadores */
ID: [a-z] [a-zA-Z0-9_&@]*;

// ------------------ Comentarios ------------------

// Comentario en primera línea (no se salta) para poder validarlo en 'program'
cmtFirstLine
    : FIRSTLINE_COMMENT { firstLineHasComment = true; }
    ;

FIRSTLINE_COMMENT
    : {getLine()==1}? '//' ~[\n\r]*
    ;

COMMENT_LINE
    : '//' ~[\n\r]* -> skip
    ;

/* Espacios */
WS: [ \t\r\n]+ -> skip;

/* Colores */
COLOR: 'Negro' | 'Azul' | 'Rojo' | 'negro' | 'azul' | 'rojo' | 'NEGRO' | 'AZUL' | 'ROJO';

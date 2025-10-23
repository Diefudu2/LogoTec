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

    void ensureProgramConstraints() {
        if (!firstLineHasComment) {
            errors.add("Error: debe existir al menos un comentario en la primera línea del programa.");
        }
        if (!atLeastOneVariable) {
            errors.add("Error: el programa debe definir al menos una variable con 'Haz' o 'INIC'.");
        }
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
@init { List<String> params = new ArrayList<>(); List<StmtNode> body = new ArrayList<>(); }
: PARA procName=ID
  BRACKET_OPEN (param=ID { params.add($param.getText()); })* BRACKET_CLOSE
  BRACKET_OPEN ( sentence {body.add($sentence.node);} )* BRACKET_CLOSE
  FIN
  {
    if (!params.isEmpty()) atLeastOneVariable = true; // contar parámetros como variables válidas
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
    : varDecl           { $node = $varDecl.node; }
    | varInit           { $node = $varInit.node; }
    | turtleCmd         { $node = $turtleCmd.node; }
    | flowStmt          { $node = $flowStmt.node; }
    | execBlock         { $node = $execBlock.node; }
    | callProc          { $node = $callProc.node; }
    ;

/* Declaración de variables */
varDecl returns [StmtNode node]
    : HAZ name=ID ( value=literalOrString { 
                        declareOrAssign($name.text, ValueType.infer($value.node), null);
                        $node = new VarDeclNode($name.text, $value.node);
                      }
                    | expr=expression { 
                        declareOrAssign($name.text, ValueType.infer($expr.node), null);
                        $node = new VarDeclNode($name.text, $expr.node);
                      }
                    ) (SEMICOLON)?
    ;


varInit returns [StmtNode node]
    : INIC name=ID ASSIGN expression (SEMICOLON)?
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
        BRACKET_OPEN (expression {args.add($expression.node);} )* BRACKET_CLOSE
      | (expression {args.add($expression.node);} )+ 
      )?
      {
        $node = new ProcCallNode($proc.text, args);
      }
    ;

/* ------------------- Bloques ------------------- 
Parsear bloques compuestos de sentencias y convertirlos en nodos AST que 
representan ejecución secuencial y bucles.

execBlock
Sintaxis: EJECUTA [ <sentences> ]
Acumula sentencias en la lista body y devuelve ExecBlockNode(body) que representa un bloque de ejecución secuencial.

repiteBlock
Sintaxis: REPITE <times> [ <sentences> ]
Visita la expresión times para obtener el contador (ExprNode),
acumula las sentencias en body y devuelve RepeatNode(times, body) que representa un bucle con número fijo de repeticiones.
*/

execBlock returns [StmtNode node]
    @init { List<StmtNode> body = new ArrayList<>(); }
    : EJECUTA BRACKET_OPEN ( sentence {body.add($sentence.node);} )* BRACKET_CLOSE
      {
        $node = new ExecBlockNode(body);
      }
    ;

repiteBlock returns [StmtNode node]
    @init { List<StmtNode> body = new ArrayList<>(); }
    : REPITE times=expression BRACKET_OPEN ( sentence {body.add($sentence.node);} )* BRACKET_CLOSE
      {
        $node = new RepeatNode($times.node, body);
      }
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
ejecuta el cuerpo al menos una vez y repite mientras la condición sea verdadera; 
devuelve DoWhileNode(body, cond).

mientrasStmt (MIENTRAS): 
evalúa la condición y, si es verdadera, ejecuta el cuerpo repetidamente; devuelve WhileNode(cond, body).

repiteBlock: 
bucle con contador fijo (ya documentado en la sección de bloques).
*/

flowStmt returns [StmtNode node]
    : siStmt            { $node = $siStmt.node; }
    | hazHastaStmt      { $node = $hazHastaStmt.node; }
    | hastaStmt         { $node = $hastaStmt.node; }
    | hazMientrasStmt   { $node = $hazMientrasStmt.node; }
    | mientrasStmt      { $node = $mientrasStmt.node; }
    | repiteBlock       { $node = $repiteBlock.node; }
    ;

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
    : HAZ DOT HASTA BRACKET_OPEN ( sentence {body.add($sentence.node);} )* BRACKET_CLOSE
      PAR_OPEN cond=expression PAR_CLOSE
      {
        $node = new DoUntilNode(body, $cond.node);
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

hazMientrasStmt returns [StmtNode node]
    @init { List<StmtNode> body = new ArrayList<>(); }
    : HAZ DOT MIENTRAS BRACKET_OPEN ( sentence {body.add($sentence.node);} )* BRACKET_CLOSE
      PAR_OPEN cond=expression PAR_CLOSE
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

/* ------------------- Comandos de tortuga ------------------- 
Parsear todas las instrucciones que controlan la "tortuga" 
(movimiento, orientación, lápiz, posición y utilidades) y convertirlas en nodos 
AST específicos de sentencia.

Soporta formas largas y abreviadas del mismo comando 
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
    | GIRAIzQUIERDA e=expression { $node = new TurnLeftNode($e.node); }
    | GI e=expression            { $node = new TurnLeftNode($e.node); }
    | OCULTATORTUGA              { $node = new HideTurtleNode(); }
    | OT                         { $node = new HideTurtleNode(); }
    | PONPOS BRACKET_OPEN x=expression y=expression BRACKET_CLOSE { $node = new SetPosNode($x.node, $y.node, true); }
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
      ( GT a2=arithExpr { $node = new GreaterThanNode($node, $a2.node);}
      | LT a2=arithExpr { $node = new LessThanNode($node, $a2.node); }
      | GEQ a2=arithExpr { $node = new GreaterEqualNode($node, $a2.node);  }
      | LEQ a2=arithExpr { $node = new LessEqualNode($node, $a2.node);}
      | EQ a2=arithExpr { $node = new EqualsNode($node, $a2.node);  }
      | NEQ a2=arithExpr { $node = new NotEqualsNode($node, $a2.node); ; }
      )*
    ;

/* Operaciones aritméticas y funciones */
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
    : funcCall { $node = $funcCall.node; $val = $funcCall.val; }
    | primary  { $node = $primary.node; $val = $primary.val; }
    ;

/* Funciones */
funcCall returns [ExprNode node, Value val]
    : IGUALESQ e1=expression e2=expression { $node = new EqualsFuncNode($e1.node, $e2.node); }
    | YFUNC PAR_OPEN e1=expression PAR_CLOSE PAR_OPEN e2=expression PAR_CLOSE { $node = new LogicalAndNode($e1.node,$e2.node); }
    | OFUNC PAR_OPEN e1=expression PAR_CLOSE PAR_OPEN e2=expression PAR_CLOSE { $node = new LogicalOrNode($e1.node,$e2.node);  }
    | MAYORQ e1=expression e2=expression { $node = new GreaterThanNode($e1.node,$e2.node);  }
    | MENORQ e1=expression e2=expression { $node = new LessThanNode($e1.node,$e2.node);  }
	| AZAR e=expression { $node = new RandomNode($e.node); }
    | PRODUCTO e1=expression (eMore+=expression)*
  {
    List<ExprNode> rest = new ArrayList<>();
    for (ExpressionContext ctx : $eMore) {
        rest.add(ctx.node);
    }
    $node = new ProductNode($e1.node, rest);
  }
	| POTENCIA e1=expression e2=expression { $node = new ExponentiationNode($e1.node, $e2.node); }
    | DIVISION e1=expression e2=expression{ $node = new DivisionNode($e1.node, $e2.node); }
	| SUMA e1=expression (eMore+=expression)*
  {
    List<ExprNode> rest = new ArrayList<>();
    for (ExpressionContext ctx : $eMore) {
        rest.add(ctx.node);
    }
    $node = new SumNode($e1.node, rest);
  }

| DIFERENCIA e1=expression (eMore+=expression)*
  {
    List<ExprNode> rest = new ArrayList<>();
    for (ExpressionContext ctx : $eMore) {
        rest.add(ctx.node);
    }
    $node = new DifferenceNode($e1.node, rest);
  }
;

/* Primarios */
primary returns [ExprNode node, Value val]
    : NUMBER  { $node = new ConstNode(Integer.parseInt($NUMBER.text));
                $val = Value.integer(Integer.parseInt($NUMBER.text)); }
    | BOOLEAN { $node = new ConstNode(Boolean.parseBoolean($BOOLEAN.text));
                $val = Value.bool(Boolean.parseBoolean($BOOLEAN.text)); }
    | ID      { $node = new VarRefNode($ID.text);
                $val = Value.unknown(); }
    | STRING  { $node = new ConstNode($STRING.text.substring(1,$STRING.text.length()-1));
                $val = Value.string($STRING.text.substring(1,$STRING.text.length()-1)); }
    | PAR_OPEN expression PAR_CLOSE { $node = $expression.node; $val = $expression.val; }
    ;


literalOrString returns [ExprNode node]
    : NUMBER     { $node = new ConstNode(Integer.parseInt($NUMBER.text)); }
    | BOOLEAN    { $node = new ConstNode(Boolean.parseBoolean($BOOLEAN.text)); }
    // quitar comillas como en 'primary'
    | STRING     { $node = new ConstNode($STRING.text.substring(1,$STRING.text.length()-1)); }
    ;

/* ------------------- Tokens ------------------- */

PARA: 'Para';
FIN: 'fin';
EJECUTA: 'Ejecuta';
REPITE : 'Repite' | 'repite';
SI: 'SI';
HASTA: 'HASTA';
MIENTRAS: 'MIENTRAS';
HAZ: 'Haz' | 'haz';
INIC: 'INIC';

AVANZA: 'avanza';
AV: 'av';
RETROCEDE: 'retrocede';
RE: 're';
GIRADERECHA: 'giraderecha';
GD: 'gd';
GIRAIzQUIERDA: 'giraizquierda';
GI: 'gi';
OCULTATORTUGA: 'ocultatortuga';
OT: 'ot';
PONPOS: 'ponpos';
PONXY: 'ponxy';
PONRUMBO: 'ponrumbo';
RUMBO: 'rumbo';
PONX: 'ponx';
PONY: 'pony';
BAJALAPIZ: 'bajalapiz';
BL: 'bl';
SUBELAPIZ: 'subelapiz';
SB: 'sb';
PONCOLORLAPIZ: 'poncolorlapiz';
PONCL: 'poncl';
CENTRO: 'centro';
ESPERA: 'espera';
INC: 'inc';

IGUALESQ: 'iguales?';
YFUNC: 'Y';
OFUNC: 'O';
MAYORQ: 'mayorque?';
MENORQ: 'menorque?';
DIFERENCIA: 'Diferencia';
AZAR: 'azar';
PRODUCTO: 'producto';
POTENCIA: 'potencia';
DIVISION: 'división';
SUMA: 'suma';

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
COMMA: ',';
DOT: '.';

/* Literales */
BOOLEAN: 'TRUE' | 'FALSE' | 'true' | 'false';
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
COLOR: 'Negro' | 'Azul' | 'Rojo';

/* Regla parser para colores */
colorName
    : c=COLOR
    ;

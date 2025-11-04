grammar LogoTec;
/* 1 ------------------- Encabezado del parser ------------------- 
Importa librerías estándar (java.util) y clases propias (Symbol, ValueType, etc.) 
que se usarán para manejar la tabla de símbolos y el AST.
*/
@parser::header{
    import java.util.*;
    import com.miorganizacion.logotec.compilador.ast.*;
}
@parser::members{
/* 2 ------------------- Variables de estado ------------------- 
symbols: tabla de símbolos, guarda variables y procedimientos.
errors: lista acumulativa de errores semánticos.
firstLineHasComment: bandera para validar que el programa empiece con comentario.
atLeastOneVariable: asegura que haya al menos una variable definida.
*/
    Map<String, Symbol> symbols = new HashMap<>();
    List<String> errors = new ArrayList<>();
    boolean firstLineHasComment = false;
    boolean atLeastOneVariable = false;

/* 3 ------------------- Utilidad para línea actual ------------------- 
Devuelve la línea actual del token, útil para reportar errores con contexto.
*/
    int currentLine() {
        return getCurrentToken() != null ? getCurrentToken().getLine() : -1;
    }

/* 4 ------------------- Manejo de llamadas pendientes ------------------- 
Permite referencias adelantadas: si se llama a un procedimiento antes de definirlo, 
se guarda en pendingCalls y se valida al final
*/
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

/* 5 ------------------- Validación de restricciones del programa ------------------- 
Comprueba:
Que la primera línea sea comentario.
Que exista al menos una variable.
Que todas las llamadas pendientes a procedimientos sean válidas.
Si hay errores, lanza RuntimeException.
*/
    void ensureProgramConstraints() {
        if (!firstLineHasComment) {
            errors.add("Error en línea 1: el programa debe iniciar con un comentario (// ...).");
        }
        if (!atLeastOneVariable) {
            errors.add("Error: el programa debe definir al menos una variable con 'Haz' o 'INIC'.");
        }
        // Validar llamadas pendientes a procedimientos (soporta referencias adelantadas)
        for (PendingCall pc : pendingCalls) {
            Symbol s = symbols.get(pc.name);
            if (s == null) {
                errors.add("Error en línea " + pc.line + ": procedimiento '" + pc.name + "' no está definido.");
            } else if (s.type != ValueType.PROCEDURE) {
                errors.add("Error en línea " + pc.line + ": '" + pc.name + "' no es un procedimiento.");
            } else {
                int expectedParams = (Integer) s.value;
                if (pc.argCount != expectedParams) {
                    errors.add("Error en línea " + pc.line + ": procedimiento '" + pc.name + "' espera " + expectedParams +
                               " parámetros, pero se llamó con " + pc.argCount + ".");
                }
            }
        }
        pendingCalls.clear();

        if (!errors.isEmpty()) {
            throw new RuntimeException(String.join("\n", errors));
        }
    }
/* 6 ------------------- Validación de nombres de variables ------------------- 
Reglas:
Longitud entre 1 y 10.
Empieza en minúscula.
Solo letras, dígitos, _, &, @.
Si no cumple, se rechaza.
*/
    boolean isValidVarName(String id) {
        if (id.length() == 0 || id.length() > 10) return false;
        if (!Character.isLowerCase(id.charAt(0))) return false;
        for (char c : id.toCharArray()) {
            if (!(Character.isLetterOrDigit(c) || c=='_' || c=='&' || c=='@')) return false;
        }
        return true;
    }
/* 7 ------------------- Declaración y asignación de variables ------------------- 
Si el nombre es válido:
Si no existe, se crea y marca que hay al menos una variable.
Si existe, valida que el tipo coincida antes de asignar.
Si no es válido, se agrega error
*/
    void declareOrAssign(String name, ValueType type, Object value) {
        if (!isValidVarName(name)) {
            errors.add("Error en línea " + currentLine() + ": identificador inválido '" + name + "'.");
            return;
        }
        Symbol s = symbols.get(name);
        if (s == null) {
            symbols.put(name, new Symbol(name, type, value));
            atLeastOneVariable = true;
        } else {
            if (s.type != type) {
                errors.add("Error en línea " + currentLine() + ": intento de asignar " + type +
                           " a variable '" + name + "' de tipo " + s.type + ".");
            } else {
                s.value = value;
            }
        }
    }
/*  ------------------- Restriccion ------------------- 
*/
    void assignInitOnlyIfDeclared(String name, ValueType valueType, ExprNode exprNode) {
    int line = currentLine();

    if (!isValidVarName(name)) {
        errors.add("Error en línea " + line + ": identificador inválido '" + name + "'.");
        return;
    }

    Symbol s = symbols.get(name);
    if (s == null) {
        errors.add("Error en línea " + line +
                   ": variable '" + name + "' no ha sido declarada antes de inicializar.");
        return;
    }

    if (s.type != valueType) {
        errors.add("Error en línea " + line +
                   ": intento de asignar " + valueType + " a variable '" + name +
                   "' de tipo " + s.type + ".");
        return;
    }

    // No evalúes aquí, solo guarda el nodo para runtime
    s.value = exprNode;
}



/* 8 ------------------- Declaración de procedimientos ------------------- 
Predeclare: reserva el nombre antes de definir parámetros.
Declare: asigna número de parámetros y valida consistencia.
Evita redefiniciones inconsistentes.
*/
    void predeclareProcedure(String name) {
        Symbol s = symbols.get(name);
        if (s == null) {
            symbols.put(name, new Symbol(name, ValueType.PROCEDURE, null));
        } else if (s.type == ValueType.PROCEDURE && s.value == null) {
            errors.add("Error en línea " + currentLine() + ": procedimiento '" + name + "' ya está en proceso de definición.");
        } else {
            errors.add("Error en línea " + currentLine() + ": símbolo '" + name + "' ya está definido y no puede declararse como procedimiento.");
        }
    }

    void declareProcedure(String name, int paramCount) {
        Symbol s = symbols.get(name);
        if (s == null) {
            symbols.put(name, new Symbol(name, ValueType.PROCEDURE, paramCount));
        } else if (s.type != ValueType.PROCEDURE) {
            errors.add("Error en línea " + currentLine() + ": símbolo '" + name + "' ya está definido y no es un procedimiento.");
        } else if (s.value == null) {
            s.value = paramCount;
        } else {
            int expectedParams = (Integer) s.value;
            if (expectedParams != paramCount) {
                errors.add("Error en línea " + currentLine() + ": procedimiento '" + name +
                           "' ya está definido con " + expectedParams + " parámetros.");
            }
        }
    }
/* 9 ------------------- Validación de llamadas a procedimientos ------------------- 
Si el procedimiento no está definido aún, se guarda en pendingCalls.
Si está definido, valida que sea de tipo procedimiento y que el número de parámetros coincida.
*/
    void validateProcedureCall(String name, int argCount) {
        Symbol s = symbols.get(name);
        int line = currentLine();
        if (s == null || (s.type == ValueType.PROCEDURE && s.value == null)) {
            pendingCalls.add(new PendingCall(name, argCount, line));
            return;
        }
        if (s.type != ValueType.PROCEDURE) {
            errors.add("Error en línea " + line + ": '" + name + "' no es un procedimiento.");
        } else {
            int expectedParams = (Integer) s.value;
            if (argCount != expectedParams) {
                errors.add("Error en línea " + line + ": procedimiento '" + name + "' espera " +
                           expectedParams + " parámetros, pero se llamó con " + argCount + ".");
            }
        }
    }

/* 10 ------------------- GRUPO 1: Validación de tipos en expresiones ------------------- */

    /**
     * Valida que una expresión sea de tipo numérico (INT).
     * Usado en comandos de tortuga (AVANZA, GD, PONRUMBO, etc.)
     */
    void requireNumericType(ExprNode expr, String commandName) {
        ValueType t = ValueType.infer(expr, symbols);
        if (t != ValueType.INT && t != ValueType.UNKNOWN) {
            errors.add("Error en línea " + currentLine() + 
                       ": comando '" + commandName + "' requiere argumento numérico, recibió " + t + ".");
        }
    }

    /**
     * Valida que una expresión sea de tipo booleano (BOOL).
     * Usado en condiciones de SI, MIENTRAS, HASTA.
     */
    void requireBooleanType(ExprNode expr, String context) {
        ValueType t = ValueType.infer(expr, symbols);
        if (t != ValueType.BOOL && t != ValueType.UNKNOWN) {
            errors.add("Error en línea " + currentLine() + 
                       ": " + context + " requiere expresión booleana, recibió " + t + ".");
        }
    }

    /**
     * Valida el comando INC (incremento de variables).
     * Restricciones:
     * - La variable debe existir (#13)
     * - La variable debe ser numérica (#14, #15)
     * - El incremento debe ser numérico (#16a, #16b, #17, #18)
     */
    void validateIncrement(String varName, ExprNode incrementExpr) {
        int line = currentLine();
        Symbol s = symbols.get(varName);
        
        // Error #13: variable no declarada
        if (s == null) {
            errors.add("Error en línea " + line + 
                       ": variable '" + varName + "' no declarada.");
            return;
        }
        
        // Error #14, #15: variable no numérica
        if (s.type != ValueType.INT) {
            errors.add("Error en línea " + line + 
                       ": INC solo puede incrementar variables numéricas, '" + varName + 
                       "' es de tipo " + s.type + ".");
        }
        
        // Error #16a, #16b, #17, #18: incremento no numérico
        ValueType incType = ValueType.infer(incrementExpr, symbols);
        if (incType != ValueType.INT && incType != ValueType.UNKNOWN) {
            errors.add("Error en línea " + line + 
                       ": INC requiere incremento numérico, recibió " + incType + ".");
        }
    }

    /**
     * Valida argumentos de funciones que requieren valores numéricos.
     * Usado en AZAR, MAYORQ, MENORQ, DIFERENCIA.
     */
    void requireNumericArgs(ExprNode arg1, ExprNode arg2, String funcName) {
        int line = currentLine();
        ValueType t1 = ValueType.infer(arg1, symbols);
        ValueType t2 = arg2 != null ? ValueType.infer(arg2, symbols) : ValueType.INT;
        
        if (t1 != ValueType.INT && t1 != ValueType.UNKNOWN) {
            errors.add("Error en línea " + line + 
                       ": función '" + funcName + "' requiere argumentos numéricos, primer argumento es " + t1 + ".");
        }
        if (arg2 != null && t2 != ValueType.INT && t2 != ValueType.UNKNOWN) {
            errors.add("Error en línea " + line + 
                       ": función '" + funcName + "' requiere argumentos numéricos, segundo argumento es " + t2 + ".");
        }
    }
}

/* 
Fin seccion
*/

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

/* 10 ------------------- program ------------------- 
Define la raíz del programa.
El comentario inicial es opcional en el parseo, pero se valida después con ensureProgramConstraints().
Al final, construye un ProgramNode con el bloque de procedimientos.
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

/* 11 ------------------- proceduresBlock ------------------- 
Contiene tanto declaraciones de procedimientos como sentencias sueltas (el cuerpo principal).
Se acumulan en listas separadas (decls y mainBody).
*/
proceduresBlock returns [ProgramNode node]
    @init {
        List<ProcDeclNode> decls = new ArrayList<>();
        List<StmtNode> mainBody = new ArrayList<>();
    }
    : (  procedureDecl { decls.add($procedureDecl.node); }
       | sentence      { mainBody.add($sentence.node); }
       | invalidComment // registra error y continúa
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

/* 12 ------------------- procedureDecl ------------------- 
Define un procedimiento con nombre y parámetros.
Usa predeclareProcedure y luego declareProcedure para registrar en la tabla de símbolos.
Cada parámetro se declara como variable (declareOrAssign).
El cuerpo se compone de sentencias.
Al final, construye un ProcDeclNode.
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

/* 13 ------------------- sentence ------------------- 
Es una sentencia genérica.
Puede ser declaración de variable, inicialización, llamada a procedimiento, comandos de tortuga, 
estructuras de control, etc.
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

/* 14 ------------------- varDecl ------------------- 
Declara una variable con HAZ.
Usa declareOrAssign para registrar en la tabla de símbolos y validar tipos.
*/
varDecl returns [StmtNode node]
    : HAZ name=ID value=literalOrString (SEMICOLON)?
      {
        declareOrAssign($name.text, ValueType.infer($value.node), $value.jval);
        $node = new VarDeclNode($name.text, $value.node);
      }
 ;

/* 15 ------------------- varInit ------------------- 
Inicializa una variable con INIC.
Valida tipo con ValueType.infer.
IMPORTANTE: INIC solo INICIALIZA variables ya declaradas con HAZ, NO crea variables nuevas.
*/
varInit returns [StmtNode node]
    : INIC name=ID ASSIGN expression SEMICOLON
      {
        ValueType t = ValueType.infer($expression.node, symbols);  // ← CAMBIO: pasar symbols
        assignInitOnlyIfDeclared($name.text, t, $expression.node);
        $node = new VarAssignNode($name.text, $expression.node);
      }
    ;

/* 16 ------------------- callProc ------------------- 
Llamada a procedimiento.
Dos formas: con corchetes (estilo LogoTec tradicional) 
o sin corchetes (solo argumentos simples).
Valida la llamada con validateProcedureCall.
*/
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

/* 17 ------------------- primaryArg ------------------- 
Define qué se acepta como argumento primario simple en llamadas sin corchetes
*/
primaryArg returns [ExprNode node]
    : NUMBER { $node = new ConstNode(Integer.parseInt($NUMBER.text)); }
    | ID { $node = new VarRefNode($ID.text); }
    | PAR_OPEN expression PAR_CLOSE { $node = $expression.node; }
    ;

/* 18 ------------------- expressionSeries ------------------- 
Lista de expresiones separadas por comas.
Se usa para agrupar argumentos o valores.
*/
expressionSeries returns [List<ExprNode> list]
    @init { $list = new ArrayList<>(); }
    : ( expression { $list.add($expression.node); }
        ( (COMMA)? expression { $list.add($expression.node); } )*
      )?
    ;

/* 
Fin seccion
*/

/* ------------------- Bloques ------------------- 
Parsear bloques compuestos de sentencias y convertirlos en nodos AST que 
representan ejecución secuencial y bucles.
*/

/* 19 ------------------- execBlock ------------------- 
Define un bloque de ejecución directa (EJECUTA …).
Contiene una lista de sentencias que se ejecutan secuencialmente.
Se encapsula en un ExecBloc
kNode.dmite versión con corchetes para separar X e Y; PONXY acepta ambos 
argumentos en secuencia sin corchetes.

INC a
*/ VarRefNode y ConstNode/ExprNode.

Nodos generados (ejemplos):
ForwardNo TurnLeftNode
SetPo
execBlock returns [StmtNode node]e, WaitNode
IncNode con VarRefNode + ExprNode
*/

turtl
    @init { List<StmtNode>       
body = n
        requireNumericType($e.node, "AVANZA");
        $w ArrayList<>(); }
     
    : EJECUTA BRACKET_OPEN ( sent
      { 
        requireNumericType($e.node, "AV");
        $e {body.add($sentence.node);} )* B
     RACKET_CLOSE
      { $node = new ExecBlon     
ckNode(b
        requireNumericType($e.node, "RETROCEDE");
        $dy); }
     
    ;
      { 
        requireNumericType($e.node, "RE");
        $
      }
ion   

        requireNumericType($e.node, "GIRADERECHA");
        $; 
     
/* 20 ------------------- repiteBlock ------------------- 
Repite un bloque de sentencias un número de veces (times).
Se construye un RepeatNode con la expresión que indica cuántas veces repetir.
*/
repiteBlock returns [StmtNode node]
    @init { List<StmtNode> body = new ArrayList<>(); }
    : REPITE times=expression 
      {
        requireNumericType($times.node, "REPITE");
      }
      BRACKET_OPEN ( sentence {body.add($sentence.node);} )* BRACKET_CLOSE
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

/* 21 ------------------- siStmt ------------------- 
Condicional SI.
Tiene un bloque then obligatorio y un bloque else opcional.
Se construye un IfNode.
*/
siStmt returns [StmtNode node]
    @init { List<StmtNode> thenBody = new ArrayList<>(); List<StmtNode> elseBody = new ArrayList<>(); }
    : SI PAR_OPEN cond=expression PAR_CLOSE
      {
        requireBooleanType($cond.node, "condición de SI");
      }
      BRACKET_OPEN ( sentence {thenBody.add($sentence.node);} )* BRACKET_CLOSE
      ( BRACKET_OPEN ( sentence {elseBody.add($sentence.node);} )* BRACKET_CLOSE )?
      {
        $node = new IfNode($cond.node, thenBody, elseBody.isEmpty()? null : elseBody);
      }
    ;

/* 22 ------------------- hazHastaStmt ------------------- 
Ciclo HAZ ... HASTA.
Ejecuta primero el cuerpo y luego evalúa la condición de salida.
Se construye un DoUntilNode.
*/
hazHastaStmt returns [StmtNode node]
    @init { List<StmtNode> body = new ArrayList<>(); }
    : HAZ (DOT)?
      BRACKET_OPEN ( sentence {body.add($sentence.node);} )* BRACKET_CLOSE
      HASTA PAR_OPEN cond=expression PAR_CLOSE
      {
        requireBooleanType($cond.node, "condición de HAZ.HASTA");
        $node = new DoUntilNode(body, $cond.node);
      }
    ;

/* 23 ------------------- hazMientrasStmt ------------------- 
Ciclo HAZ ... MIENTRAS.
Ejecuta primero el cuerpo y luego evalúa la condición de continuación.
Se construye un DoWhileNode.
*/
hazMientrasStmt returns [StmtNode node]
    @init { List<StmtNode> body = new ArrayList<>(); }
    : HAZ (DOT)?
      BRACKET_OPEN ( sentence {body.add($sentence.node);} )* BRACKET_CLOSE
      MIENTRAS PAR_OPEN cond=expression PAR_CLOSE
      {
        requireBooleanType($cond.node, "condición de HAZ.MIENTRAS");
        $node = new DoWhileNode(body, $cond.node);
      }
    ;

/* 24 ------------------- mientrasStmt ------------------- 
Ciclo clásico MIENTRAS.
Evalúa condición antes de entrar al cuerpo.
Se construye un WhileNode.
*/
mientrasStmt returns [StmtNode node]
    @init { List<StmtNode> body = new ArrayList<>(); }
    : MIENTRAS PAR_OPEN cond=expression PAR_CLOSE
      {
        requireBooleanType($cond.node, "condición de MIENTRAS");
      }
      BRACKET_OPEN ( sentence {body.add($sentence.node);} )* BRACKET_CLOSE
      {
        $node = new WhileNode($cond.node, body);
      }
    ;

/* 25 ------------------- hastaStmt ------------------- 
Ciclo HASTA.
Ejecuta cuerpo hasta que la condición se cumpla.
Se construye un UntilNode.
*/
hastaStmt returns [StmtNode node]
    @init { List<StmtNode> body = new ArrayList<>(); }
    : HASTA PAR_OPEN cond=expression PAR_CLOSE
      {
        requireBooleanType($cond.node, "condición de HASTA");
      }
      BRACKET_OPEN ( sentence {body.add($sentence.node);} )* BRACKET_CLOSE
      {
        $node = new UntilNode($cond.node, body);
      }
    ;

/* 
Fin seccion
*/

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
    : AVANZA e=expression        
      { 
        requireNumericType($e.node, "AVANZA");
        $node = new ForwardNode($e.node); 
      }
    | AV e=expression            
      { 
        requireNumericType($e.node, "AV");
        $node = new ForwardNode($e.node); 
      }
    | RETROCEDE e=expression     
      { 
        requireNumericType($e.node, "RETROCEDE");
        $node = new BackwardNode($e.node); 
      }
    | RE e=expression            
      { 
        requireNumericType($e.node, "RE");
        $node = new BackwardNode($e.node); 
      }
    | GIRADERECHA e=expression   
      { 
        requireNumericType($e.node, "GIRADERECHA");
        $node = new TurnRightNode($e.node); 
      }
    | GD e=expression            
      { 
        requireNumericType($e.node, "GD");
        $node = new TurnRightNode($e.node); 
      }
    | GIRAIZQUIERDA e=expression 
      { 
        requireNumericType($e.node, "GIRAIZQUIERDA");
        $node = new TurnLeftNode($e.node); 
      }
    | GI e=expression            
      { 
        requireNumericType($e.node, "GI");
        $node = new TurnLeftNode($e.node); 
      }
    | OCULTATORTUGA              { $node = new HideTurtleNode(); }
    | OT                         { $node = new HideTurtleNode(); }
    | APARECETORTUGA             { $node = new ShowTurtleNode(); }
    | AT                         { $node = new ShowTurtleNode(); }
    | PONPOS BRACKET_OPEN coords=expressionSeries BRACKET_CLOSE
      {
        List<ExprNode> coordsList = $coords.list;
        if (coordsList.size() != 2) {
            errors.add("Error en línea " + currentLine() + ": 'PONPOS' requiere exactamente dos expresiones para X e Y.");
        }
        ExprNode xNode = coordsList.size() > 0 ? coordsList.get(0) : new ConstNode(0);
        ExprNode yNode = coordsList.size() > 1 ? coordsList.get(1) : new ConstNode(0);
        requireNumericType(xNode, "PONPOS (coordenada X)");
        requireNumericType(yNode, "PONPOS (coordenada Y)");
        $node = new SetPosNode(xNode, yNode, true);
      }
    | PONXY x=expression y=expression 
      { 
        requireNumericType($x.node, "PONXY (coordenada X)");
        requireNumericType($y.node, "PONXY (coordenada Y)");
        $node = new SetPosNode($x.node, $y.node, false); 
      }
    | PONRUMBO e=expression      
      { 
        requireNumericType($e.node, "PONRUMBO");
        $node = new SetHeadingNode($e.node); 
      }
    | RUMBO                      { $node = new ShowHeadingNode(); }
    | PONX e=expression          
      { 
        requireNumericType($e.node, "PONX");
        $node = new SetXNode($e.node); 
      }
    | PONY e=expression          
      { 
        requireNumericType($e.node, "PONY");
        $node = new SetYNode($e.node); 
      }
    | BAJALAPIZ                  { $node = new PenDownNode(); }
    | BL                         { $node = new PenDownNode(); }
    | SUBELAPIZ                  { $node = new PenUpNode(); }
    | SB                         { $node = new PenUpNode(); }
    | CENTRO                     { $node = new CenterNode(); }
    | ESPERA e=expression        
      { 
        requireNumericType($e.node, "ESPERA");
        $node = new WaitNode($e.node); 
      }
    | INC BRACKET_OPEN id=ID BRACKET_CLOSE
      { 
        validateIncrement($id.text, new ConstNode(1));
        $node = new IncNode(new VarRefNode($id.text), new ConstNode(1)); 
      }
    | INC BRACKET_OPEN id=ID n=expression BRACKET_CLOSE
      { 
        validateIncrement($id.text, $n.node);
        $node = new IncNode(new VarRefNode($id.text), $n.node); 
      }
    | PONCOLORLAPIZ BRACKET_OPEN coords=expressionSeries BRACKET_CLOSE
      {
        List<ExprNode> colorList = $coords.list;
        if (colorList.size() != 3) {
            errors.add("Error en línea " + currentLine() + ": 'PONCOLORLAPIZ' requiere exactamente tres valores RGB.");
        }
        ExprNode rNode = colorList.size() > 0 ? colorList.get(0) : new ConstNode(0);
        ExprNode gNode = colorList.size() > 1 ? colorList.get(1) : new ConstNode(0);
        ExprNode bNode = colorList.size() > 2 ? colorList.get(2) : new ConstNode(0);
        requireNumericType(rNode, "PONCOLORLAPIZ (valor R)");
        requireNumericType(gNode, "PONCOLORLAPIZ (valor G)");
        requireNumericType(bNode, "PONCOLORLAPIZ (valor B)");
        $node = new SetColorNode(rNode, gNode, bNode);
      }
    | PONCL BRACKET_OPEN coords=expressionSeries BRACKET_CLOSE
      {
        List<ExprNode> colorList = $coords.list;
        if (colorList.size() != 3) {
            errors.add("Error en línea " + currentLine() + ": 'PONCL' requiere exactamente tres valores RGB.");
        }
        ExprNode rNode = colorList.size() > 0 ? colorList.get(0) : new ConstNode(0);
        ExprNode gNode = colorList.size() > 1 ? colorList.get(1) : new ConstNode(0);
        ExprNode bNode = colorList.size() > 2 ? colorList.get(2) : new ConstNode(0);
        requireNumericType(rNode, "PONCL (valor R)");
        requireNumericType(gNode, "PONCL (valor G)");
        requireNumericType(bNode, "PONCL (valor B)");
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

/* ------------------- Expresiones -------------------     : FIRSTLINE_COMMENT { firstLineHasComment = true; }    ;// Comentario inválido: se registra como error semánticoinvalidComment    : INVALID_COMMENT      {        errors.add("Error en línea " + currentLine() + ": los comentarios deben empezar con //");      }    ;FIRSTLINE_COMMENT    : {getLine()==1}? '//' ~[\n\r]*    ;COMMENT_LINE    : '//' ~[\n\r]* -> skip    ;// Comentario inválido con '--' (no se salta; se reporta en parser)INVALID_COMMENT    : '--' ~[\n\r]*    ;/* Espacios */WS: [ \t\r\n]+ -> skip;/* Colores */COLOR: 'Negro' | 'Azul' | 'Rojo' | 'negro' | 'azul' | 'rojo' | 'NEGRO' | 'AZUL' | 'ROJO';
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
/* 29 ------------------- funcCall ------------------- own y errores 
Igualdad (IGUALESQ), conjunción (YFUNC), disyunción (OFUNC), comparaciones (MAYORQ, MENORQ).
Aleatorio (AZAR).
Operaciones agregadas: PRODUCTO, SUMA, DIFERENCIA.unknown para ID.
Potencia y división explícitas (POTENCIA, DIVISION).
*/
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
      { 
        requireNumericArgs($e1.node, $e2.node, "MayorQue?");
        $node = new GreaterThanNode($e1.node,$e2.node);  
      }
    | MENORQ PAR_OPEN e1=expression COMMA e2=expression PAR_CLOSE
      { 
        requireNumericArgs($e1.node, $e2.node, "MenorQue?");
        $node = new LessThanNode($e1.node,$e2.node);  
      }
    | AZAR PAR_OPEN e=expression PAR_CLOSE
      { 
        requireNumericType($e.node, "AZAR");
        $node = new RandomNode($e.node); 
      }
    | PRODUCTO PAR_OPEN e1=expression (COMMA rest+=expression)* PAR_CLOSE
      {
        requireNumericType($e1.node, "PRODUCTO");
        List<ExprNode> restNodes = new ArrayList<>();
        if ($rest != null) {
            for (ExpressionContext ctx : $rest) {
                requireNumericType(ctx.node, "PRODUCTO");
                restNodes.add(ctx.node);
            }
        }
        $node = new ProductNode($e1.node, restNodes);
      }
    | POTENCIA PAR_OPEN e1=expression COMMA e2=expression PAR_CLOSE
      { 
        requireNumericArgs($e1.node, $e2.node, "POTENCIA");
        $node = new ExponentiationNode($e1.node, $e2.node); 
      }
    | DIVISION PAR_OPEN e1=expression COMMA e2=expression PAR_CLOSE
      { 
        requireNumericArgs($e1.node, $e2.node, "DIVISION");
        $node = new DivisionNode($e1.node, $e2.node); 
      }
    | SUMA PAR_OPEN e1=expression (COMMA rest+=expression)* PAR_CLOSE
      {
        requireNumericType($e1.node, "SUMA");
        List<ExprNode> restNodes = new ArrayList<>();
        if ($rest != null) {
            for (ExpressionContext ctx : $rest) {
                requireNumericType(ctx.node, "SUMA");
                restNodes.add(ctx.node);
            }
        }
        $node = new SumNode($e1.node, restNodes);
      }
    | DIFERENCIA PAR_OPEN e1=expression (COMMA rest+=expression)* PAR_CLOSE
      {
        requireNumericType($e1.node, "DIFERENCIA");
        List<ExprNode> restNodes = new ArrayList<>();
        if ($rest != null) {
            for (ExpressionContext ctx : $rest) {
                requireNumericType(ctx.node, "DIFERENCIA");
                restNodes.add(ctx.node);
            }
        }
        $node = new DifferenceNode($e1.node, restNodes);
      }
    ;

/* 30 ------------------- primary ------------------- 
Constantes: DECIMAL, NUMBER, BOOLEAN, STRING.
Variables (ID).
Expresiones entre paréntesis.
*/
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

/* 31 ------------------- literalOrString ------------------- 
Similar a primary, pero pensado para inicialización de variables (HAZ, INIC).
Devuelve tanto nodo (ExprNode) como valor Java (jval).
*/
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

/* 
Fin seccion
*/

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
/* ID: [a-z] [a-zA-Z0-9_&@]*;*/
ID: [a-z] [a-zA-Z0-9&@]*;


// ------------------ Comentarios ------------------

// Comentario en primera línea (no se salta) para poder validarlo en 'program'
cmtFirstLine
    : FIRSTLINE_COMMENT { firstLineHasComment = true; }
    ;
// Comentario inválido: se registra como error semántico
invalidComment
    : INVALID_COMMENT
      {
        errors.add("Error en línea " + currentLine() + ": los comentarios deben empezar con //");
      }
    ;

FIRSTLINE_COMMENT
    : {getLine()==1}? '//' ~[\n\r]*
    ;
VALID_COMMENTT    : '//' ~[\n\r]* -> skip
COMMENT_LINE
    : '//' ~[\n\r]* -> skip
    ;

// Comentario inválido con '--' (no se salta; se reporta en parser)
INVALID_COMMENT
    : '--' ~[\n\r]*
    ;


/* Colores */
COLOR: 'Negro' | 'Azul' | 'Rojo' | 'negro' | 'azul' | 'rojo' | 'NEGRO' | 'AZUL' | 'ROJO';

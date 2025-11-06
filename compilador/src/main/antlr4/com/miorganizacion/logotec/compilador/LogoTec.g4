grammar LogoTec;

/* 1 ------------------- Encabezado del parser ------------------- */
@parser::header{
    import java.util.*;
    import com.miorganizacion.logotec.compilador.ast.*;
}

@parser::members{
/* 2 ------------------- Variables de estado ------------------- */
    Map<String, Symbol> symbols = new HashMap<>();
    List<String> errors = new ArrayList<>();
    boolean firstLineHasComment = false;
    boolean atLeastOneVariable = false;

/* 3 ------------------- Utilidad para línea actual ------------------- */
    int currentLine() {
        return getCurrentToken() != null ? getCurrentToken().getLine() : -1;
    }

/* 4 ------------------- Manejo de llamadas pendientes ------------------- */
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

/* 5 ------------------- Validación de restricciones del programa ------------------- */
    void ensureProgramConstraints() {
        if (!firstLineHasComment) {
            errors.add("Error en línea 1: el programa debe iniciar con un comentario (// ...).");
        }
        if (!atLeastOneVariable) {
            errors.add("Error: el programa debe definir al menos una variable con 'Haz' o 'INIC'.");
        }
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

/* 6 ------------------- Validación de nombres de variables ------------------- */
    boolean isValidVarName(String id) {
        if (id.length() == 0 || id.length() > 10) return false;
        if (!Character.isLowerCase(id.charAt(0))) return false;
        for (char c : id.toCharArray()) {
            if (!(Character.isLetterOrDigit(c) || c=='_' || c=='&' || c=='@')) return false;
        }
        return true;
    }
    
    /**
     * Valida longitud de identificador en cualquier contexto.
     * Restricción #3
     */
    void validateIdentifierLength(String id, int line) {
        if (id.length() > 10) {
            errors.add("Error en línea " + line + 
                       ": identificador '" + id + "' excede el límite de 10 caracteres (tiene " + id.length() + ").");
        }
    }

/* 7 ------------------- Declaración y asignación de variables ------------------- */
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

/* 8 ------------------- GRUPO 2: INIC solo inicializa ------------------- */
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
        s.value = exprNode;
    }

/* 9 ------------------- Declaración de procedimientos ------------------- */
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

/* 10 ------------------- Validación de llamadas a procedimientos ------------------- */
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

/* 11 ------------------- GRUPO 1: Validación de tipos en expresiones ------------------- */
    void requireNumericType(ExprNode expr, String commandName) {
        ValueType t = ValueType.infer(expr, symbols);
        if (t != ValueType.INT && t != ValueType.UNKNOWN) {
            errors.add("Error en línea " + currentLine() + 
                       ": comando '" + commandName + "' requiere argumento numérico, recibió " + t + ".");
        }
    }

    void requireBooleanType(ExprNode expr, String context) {
        ValueType t = ValueType.infer(expr, symbols);
        if (t != ValueType.BOOL && t != ValueType.UNKNOWN) {
            errors.add("Error en línea " + currentLine() + 
                       ": " + context + " requiere expresión booleana, recibió " + t + ".");
        }
    }

    void validateIncrement(String varName, ExprNode incrementExpr) {
        int line = currentLine();
        Symbol s = symbols.get(varName);
        if (s == null) {
            errors.add("Error en línea " + line + 
                       ": variable '" + varName + "' no declarada.");
            return;
        }
        if (s.type != ValueType.INT) {
            errors.add("Error en línea " + line + 
                       ": INC solo puede incrementar variables numéricas, '" + varName + 
                       "' es de tipo " + s.type + ".");
        }
        ValueType incType = ValueType.infer(incrementExpr, symbols);
        if (incType != ValueType.INT && incType != ValueType.UNKNOWN) {
            errors.add("Error en línea " + line + 
                       ": INC requiere incremento numérico, recibió " + incType + ".");
        }
    }

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
    
/* GRUPO 3: Validación de bloques de control de flujo */
    
    /**
     * Valida que un bloque de sentencias no esté vacío.
     * Restricciones #37, #39
     */
    void requireNonEmptyBlock(List<StmtNode> body, String blockType) {
        if (body == null || body.isEmpty()) {
            errors.add("Error en línea " + currentLine() + 
                       ": bloque de '" + blockType + "' no puede estar vacío.");
        }
    }

    /**
     * Valida que un SI solo tenga un bloque ELSE (máximo).
     * Restricción #36
     */
    void validateSingleElse(int elseBlockCount, String context) {
        if (elseBlockCount > 1) {
            errors.add("Error en línea " + currentLine() + 
                       ": " + context + " solo puede tener un bloque ELSE, se encontraron " + elseBlockCount + ".");
        }
    }

    /**
     * Valida que HAZ.HASTA/HAZ.MIENTRAS tenga condición.
     * Restricción #38, #40
     */
    void requireCondition(ExprNode condition, String loopType) {
        if (condition == null) {
            errors.add("Error en línea " + currentLine() + 
                       ": '" + loopType + "' requiere una condición entre paréntesis.");
        }
    }

    /**
     * Valida que funciones binarias tengan exactamente 2 argumentos.
     * Restricción #44
     */
    void requireTwoArguments(int argCount, String funcName, int line) {
        if (argCount != 2) {
            errors.add("Error en línea " + line + 
                       ": función '" + funcName + "' requiere exactamente 2 argumentos, recibió " + argCount + ".");
        }
    }

    /**
     * Valida que funciones unarias tengan exactamente 1 argumento.
     */
    void requireOneArgument(int argCount, String funcName, int line) {
        if (argCount != 1) {
            errors.add("Error en línea " + line + 
                       ": función '" + funcName + "' requiere exactamente 1 argumento, recibió " + argCount + ".");
        }
    }
}

/* ------------------- Programa ------------------- */
program returns [ProgramNode node]
    : cmtFirstLine? p=proceduresBlock EOF
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
       | invalidComment
      )*
      { $node = new ProgramNode(decls, mainBody); }
 ;

/* ------------------- Procedimientos ------------------- */
procedureDecl returns [ProcDeclNode node]
    @init { 
        List<String> params = new ArrayList<>(); 
        List<StmtNode> body = new ArrayList<>(); 
    }
    : PARA procName=ID
      { 
        validateIdentifierLength($procName.text, $procName.line);
        predeclareProcedure($procName.text); 
      }
      // Primer bloque: parámetros
      BRACKET_OPEN 
        (param=ID { 
          validateIdentifierLength($param.text, $param.line);
          params.add($param.getText()); 
          declareOrAssign($param.text, ValueType.UNKNOWN, null);
        })* 
      BRACKET_CLOSE
      { declareProcedure($procName.text, params.size()); }
      // Segundo bloque: cuerpo
      BRACKET_OPEN 
        ( sentence {body.add($sentence.node);} )* 
      BRACKET_CLOSE
      FIN
      {
        if (!params.isEmpty()) atLeastOneVariable = true;
        $node = new ProcDeclNode($procName.text, params, body);
      }
 ;

/* ------------------- Sentencias ------------------- */
sentence returns [StmtNode node]
    : varDecl           { $node = $varDecl.node; }
    | varInit           { $node = $varInit.node; }
    | hazDoStmt         { $node = $hazDoStmt.node; }
    | siStmt            { $node = $siStmt.node; }
    | hastaStmt         { $node = $hastaStmt.node; }
    | mientrasStmt      { $node = $mientrasStmt.node; }
    | repiteBlock       { $node = $repiteBlock.node; }
    | execBlock         { $node = $execBlock.node; }
    | turtleCmd         { $node = $turtleCmd.node; }
    | callProc          { $node = $callProc.node; }
    ;

varDecl returns [StmtNode node]
    : HAZ name=ID value=literalOrString (SEMICOLON)?
      {
        validateIdentifierLength($name.text, $name.line);
        declareOrAssign($name.text, ValueType.infer($value.node), $value.jval);
        $node = new VarDeclNode($name.text, $value.node);
      }
    ;

varInit returns [StmtNode node]
    : INIC name=ID ASSIGN expression SEMICOLON
      {
        validateIdentifierLength($name.text, $name.line);
        ValueType t = ValueType.infer($expression.node, symbols);
        assignInitOnlyIfDeclared($name.text, t, $expression.node);
        $node = new VarAssignNode($name.text, $expression.node);
      }
    ;

callProc returns [StmtNode node]
    @init { List<ExprNode> args = new ArrayList<>(); }
    : proc=ID
      {
        validateIdentifierLength($proc.text, $proc.line);
      }
      (
        // Sintaxis con corchetes: proc[arg1, arg2]
        BRACKET_OPEN
            ( expression { args.add($expression.node); }
              ( (COMMA)? expression { args.add($expression.node); } )*
            )?
        BRACKET_CLOSE
        |
        // Sintaxis sin corchetes: proc arg1 arg2 (solo para argumentos simples)
        // Usar lookahead para evitar capturar comandos turtle
        { _input.LA(1) != BRACKET_OPEN && 
          (_input.LA(1) == NUMBER || _input.LA(1) == ID || _input.LA(1) == PAR_OPEN) }?
        ( primaryArg { args.add($primaryArg.node); } )+
      )?
      {
        validateProcedureCall($proc.text, args.size());
        $node = new ProcCallNode($proc.text, args);
      }
    ;

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

/* ------------------- Bloques ------------------- */
execBlock returns [StmtNode node]
    @init { List<StmtNode> body = new ArrayList<>(); }
    : EJECUTA BRACKET_OPEN ( sentence {body.add($sentence.node);} )* BRACKET_CLOSE
      { $node = new ExecBlockNode(body); }
    ;

repiteBlock returns [StmtNode node]
    @init { List<StmtNode> body = new ArrayList<>(); }
    : REPITE times=expression 
      {
        requireNumericType($times.node, "REPITE");
      }
      BRACKET_OPEN ( sentence {body.add($sentence.node);} )* BRACKET_CLOSE
      { $node = new RepeatNode($times.node, body); }
 ;

/* ------------------- Control de flujo ------------------- */
siStmt returns [StmtNode node]
    @init { 
        List<StmtNode> thenBody = new ArrayList<>(); 
        List<StmtNode> elseBody = new ArrayList<>();
        int elseCount = 0;
    }
    : SI PAR_OPEN cond=expression PAR_CLOSE
      {
        requireBooleanType($cond.node, "condición de SI");
      }
      BRACKET_OPEN ( sentence {thenBody.add($sentence.node);} )* BRACKET_CLOSE
      ( 
        BRACKET_OPEN 
        { elseCount++; }
        ( sentence {elseBody.add($sentence.node);} )* 
        BRACKET_CLOSE 
      )*
      {
        validateSingleElse(elseCount, "SI");
        $node = new IfNode($cond.node, thenBody, elseBody.isEmpty()? null : elseBody);
      }
    ;

hazDoStmt returns [StmtNode node]
    @init { 
        List<StmtNode> body = new ArrayList<>(); 
        ExprNode condition = null;
        boolean isUntil = false;
        boolean isWhile = false;
    }
    : HAZ DOT                                            // ← DOT obligatorio
      BRACKET_OPEN ( sentence {body.add($sentence.node);} )* BRACKET_CLOSE
      ( HASTA PAR_OPEN cond=expression PAR_CLOSE 
        { 
          condition = $cond.node; 
          isUntil = true; 
        }
      | MIENTRAS PAR_OPEN cond=expression PAR_CLOSE 
        { 
          condition = $cond.node; 
          isWhile = true; 
        }
      )
      {
        if (condition == null) {
            errors.add("Error en línea " + currentLine() + 
                       ": HAZ requiere HASTA o MIENTRAS con condición.");
        } else {
            requireNonEmptyBlock(body, isUntil ? "HAZ.HASTA" : "HAZ.MIENTRAS");
            requireBooleanType(condition, isUntil ? "condición de HAZ.HASTA" : "condición de HAZ.MIENTRAS");
            
            if (isUntil) {
                $node = new DoUntilNode(body, condition);
            } else {
                $node = new DoWhileNode(body, condition);
            }
        }
      }
    ;

mientrasStmt returns [StmtNode node]
    @init { List<StmtNode> body = new ArrayList<>(); }
    : MIENTRAS PAR_OPEN cond=expression PAR_CLOSE
      {
        requireBooleanType($cond.node, "condición de MIENTRAS");
      }
      BRACKET_OPEN ( sentence {body.add($sentence.node);} )* BRACKET_CLOSE
      {
        requireNonEmptyBlock(body, "MIENTRAS");
        $node = new WhileNode($cond.node, body);
      }
    ;

hastaStmt returns [StmtNode node]
    @init { List<StmtNode> body = new ArrayList<>(); }
    : HASTA PAR_OPEN cond=expression PAR_CLOSE
      {
        requireBooleanType($cond.node, "condición de HASTA");
      }
      BRACKET_OPEN ( sentence {body.add($sentence.node);} )* BRACKET_CLOSE
      {
        requireNonEmptyBlock(body, "HASTA");
        $node = new UntilNode($cond.node, body);
      }
    ;

/* ------------------- Comandos de tortuga ------------------- */
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

/* ------------------- Expresiones ------------------- */
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

funcCall returns [ExprNode node, Value val]
    : IGUALESQ PAR_OPEN args=expressionSeries PAR_CLOSE
      { 
        requireTwoArguments($args.list.size(), "Iguales?", currentLine());
        ExprNode e1 = $args.list.size() > 0 ? $args.list.get(0) : new ConstNode(0);
        ExprNode e2 = $args.list.size() > 1 ? $args.list.get(1) : new ConstNode(0);
        $node = new EqualsFuncNode(e1, e2); 
      }
    | YFUNC PAR_OPEN args=expressionSeries PAR_CLOSE
      { 
        requireTwoArguments($args.list.size(), "Y", currentLine());
        ExprNode e1 = $args.list.size() > 0 ? $args.list.get(0) : new ConstNode(false);
        ExprNode e2 = $args.list.size() > 1 ? $args.list.get(1) : new ConstNode(false);
        $node = new LogicalAndNode(e1, e2);
      }
    | OFUNC PAR_OPEN args=expressionSeries PAR_CLOSE
      { 
        requireTwoArguments($args.list.size(), "O", currentLine());
        ExprNode e1 = $args.list.size() > 0 ? $args.list.get(0) : new ConstNode(false);
        ExprNode e2 = $args.list.size() > 1 ? $args.list.get(1) : new ConstNode(false);
        $node = new LogicalOrNode(e1, e2);
      }
    | MAYORQ PAR_OPEN args=expressionSeries PAR_CLOSE
      { 
        requireTwoArguments($args.list.size(), "MayorQue?", currentLine());
        ExprNode e1 = $args.list.size() > 0 ? $args.list.get(0) : new ConstNode(0);
        ExprNode e2 = $args.list.size() > 1 ? $args.list.get(1) : new ConstNode(0);
        requireNumericArgs(e1, e2, "MayorQue?");
        $node = new GreaterThanNode(e1, e2);  
      }
    | MENORQ PAR_OPEN args=expressionSeries PAR_CLOSE
      { 
        requireTwoArguments($args.list.size(), "MenorQue?", currentLine());
        ExprNode e1 = $args.list.size() > 0 ? $args.list.get(0) : new ConstNode(0);
        ExprNode e2 = $args.list.size() > 1 ? $args.list.get(1) : new ConstNode(0);
        requireNumericArgs(e1, e2, "MenorQue?");
        $node = new LessThanNode(e1, e2);  
      }
    | AZAR PAR_OPEN args=expressionSeries PAR_CLOSE
      { 
        requireOneArgument($args.list.size(), "AZAR", currentLine());
        ExprNode e = $args.list.size() > 0 ? $args.list.get(0) : new ConstNode(1);
        requireNumericType(e, "AZAR");
        $node = new RandomNode(e); 
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
    | POTENCIA PAR_OPEN args=expressionSeries PAR_CLOSE
      { 
        requireTwoArguments($args.list.size(), "POTENCIA", currentLine());
        ExprNode e1 = $args.list.size() > 0 ? $args.list.get(0) : new ConstNode(0);
        ExprNode e2 = $args.list.size() > 1 ? $args.list.get(1) : new ConstNode(0);
        requireNumericArgs(e1, e2, "POTENCIA");
        $node = new ExponentiationNode(e1, e2); 
      }
    | DIVISION PAR_OPEN args=expressionSeries PAR_CLOSE
      { 
        requireTwoArguments($args.list.size(), "DIVISION", currentLine());
        ExprNode e1 = $args.list.size() > 0 ? $args.list.get(0) : new ConstNode(0);
        ExprNode e2 = $args.list.size() > 1 ? $args.list.get(1) : new ConstNode(1);
        requireNumericArgs(e1, e2, "DIVISION");
        $node = new DivisionNode(e1, e2); 
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

primary returns [ExprNode node, Value val]
    : DECIMAL
      {
        double v = Double.parseDouble($DECIMAL.text.replace(',', '.'));
        $node = new ConstNode(v);
        $val = Value.unknown();
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
        validateIdentifierLength($ID.text, $ID.line);
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
PARA: 'Para' | 'para' | 'PARA';
HAZ: 'Haz' | 'haz' | 'HAZ';
FIN: 'fin' | 'Fin' | 'FIN';
EJECUTA: 'Ejecuta' | 'ejecuta' | 'EJECUTA';
REPITE: 'repite' | 'Repite' | 'REPITE';
APARECETORTUGA: 'aparecetortuga' | 'APARECETORTUGA' | 'ApareceTortuga';
AT: 'at' | 'AT';
PONPOS: 'PonPOS' | 'ponpos' | 'PONPOS'| 'PonPos';
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
AVANZA: 'avanza' | 'AVANZA' | 'Avanza';
AV: 'av' | 'AV' | 'Av';
RETROCEDE: 'retrocede' | 'RETROCEDE' | 'Retrocede';
RE: 'RE' | 're' | 'Re';
ATRAS: 'atras';
GIRADERECHA: 'GiraDerecha' | 'GIRADERECHA' | 'giraderecha';
GD: 'GD' | 'gd' | 'Gd';
GIRAIZQUIERDA: 'GiraIzquierda' | 'GIRAIZQUIERDA' | 'giraizquierda';
GI: 'GI' | 'gi' | 'Gi';
OCULTATORTUGA: 'ocultatortuga';
OT: 'ot';
PONCOLORLAPIZ: 'poncolorlapiz' | 'PONCOLORLAPIZ' | 'PonColorLapiz';
// Alias para compatibilidad con archivo de prueba
PONCL_ALIAS: 'ponCL';  // Se procesa primero que ID
PONCL: 'poncl' | 'ponCL' | 'PONCL' | 'PonCL';

IGUALESQ: 'iguales?' | 'Iguales?' | 'IGUALES?';
YFUNC: 'Y';
OFUNC: 'O';
MAYORQ: 'mayorque?' | 'Mayorque?' | 'MAYORQUE?';
MENORQ: 'menorque?' | 'Menorque?' | 'MENORQUE?';
DIFERENCIA: 'diferencia' | 'Diferencia' | 'DIFERENCIA';
AZAR: 'azar' | 'Azar' | 'AZAR';
PRODUCTO: 'producto' | 'Producto' | 'PRODUCTO';
POTENCIA: 'potencia' | 'Potencia' | 'POTENCIA';
DIVISION: 'division' | 'Division' | 'DIVISION';
SUMA: 'suma' | 'Suma' | 'SUMA';

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
COMMA: { !(Character.isDigit((char)_input.LA(-1)) && Character.isDigit((char)_input.LA(1)) ) }? ',';
DOT: '.';

BOOLEAN: 'TRUE' | 'FALSE' | 'True' | 'False' | 'true' | 'false';
DECIMAL: [0-9]+ (',' | '.') [0-9]+;
NUMBER: [0-9]+;
STRING: ('"' | '"') (~["\r\n""])* ('"' | '"') ;
ID: [a-z] [a-zA-Z0-9&@]*;

/* ------------------- Comentarios -------------------*/
cmtFirstLine
    : FIRSTLINE_COMMENT { firstLineHasComment = true; }
    ;

invalidComment
    : INVALID_COMMENT
      {
        errors.add("Error en línea " + currentLine() + ": los comentarios deben empezar con //");
        //throw new RuntimeException("Error en línea " + currentLine() + ": los comentarios deben empezar con //");
      }
    ;

FIRSTLINE_COMMENT : {getLine()==1}? '//' ~[\n\r]* ;
COMMENT_LINE : '//' ~[\n\r]* -> skip ;
INVALID_COMMENT : '--' ~[\n\r]* ;

WS: [ \t\r\n]+ -> skip;
COLOR: 'Negro' | 'Azul' | 'Rojo' | 'negro' | 'azul' | 'rojo' | 'NEGRO' | 'AZUL' | 'ROJO';

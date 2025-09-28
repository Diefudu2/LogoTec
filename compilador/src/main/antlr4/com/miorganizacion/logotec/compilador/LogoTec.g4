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

/* ------------------- Programa ------------------- */

program returns [ProgramNode node]
    : p=proceduresBlock EOF
      {
        ensureProgramConstraints();
        $node = $p.node;
      }
    ;

proceduresBlock returns [ProgramNode node]
    @init { List<ProcDeclNode> decls = new ArrayList<>(); List<StmtNode> mainBody = new ArrayList<>(); }
    : (cmtFirstLine {firstLineHasComment = true;} )?
      ( procedureDecl {decls.add($procedureDecl.node);} )*
      ( sentence {mainBody.add($sentence.node);} )*
      {
        $node = new ProgramNode(decls, mainBody);
      }
    ;

/* ------------------- Procedimientos ------------------- */

procedureDecl returns [ProcDeclNode node]
    @init { List<String> params = new ArrayList<>(); List<StmtNode> body = new ArrayList<>(); }
    : PARA procName=ID (procParams { for (Token p : $procParams.list) params.add(p.getText()); })?
      BRACKET_OPEN ( sentence {body.add($sentence.node);} )* BRACKET_CLOSE FIN
      {
        $node = new ProcDeclNode($procName.text, params, body);
      }
    ;


procParams returns [List<Token> list]
    @init { $list = new ArrayList<>(); }
    : BRACKET_OPEN (param=ID { $list.add($param); })* BRACKET_CLOSE
    ;


/* ------------------- Sentencias ------------------- */

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
    : HAZ name=ID value=literalOrString (SEMICOLON)?
      {
        $node = new VarDeclNode($name.text, $value.node);
      }
    ;



varInit returns [StmtNode node]
    : INIC name=ID ASSIGN expression SEMICOLON
      {
        Value v = $expression.val;
        declareOrAssign($name.text, v.type, v.raw);
        $node = new VarAssignNode($name.text, $expression.node);
      }
    ;

/* Llamada a procedimiento */
callProc returns [StmtNode node]
    @init { List<ExprNode> args = new ArrayList<>(); }
    : proc=ID (BRACKET_OPEN (expression {args.add($expression.node);} )* BRACKET_CLOSE)?
      {
        $node = new ProcCallNode($proc.text, args);
      }
    ;

/* ------------------- Bloques ------------------- */

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

/* ------------------- Control de flujo ------------------- */

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

/* ------------------- Comandos de tortuga ------------------- */

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
    | BOOLEAN { $node = new ConstNode(Boolean.parseBoolean($BOOLEAN.text)); }
    | STRING  { $node = new ConstNode($STRING.text); }
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
ID: [a-z] [a-zA-Z0-9_]*;

/* Comentarios */
COMMENT_LINE: '//' ~[\r\n]* -> channel(HIDDEN);
fragment CMT_START: '//' ;
cmtFirstLine: COMMENT_LINE;

/* Espacios */
WS: [ \t\r\n]+ -> skip;

/* Colores */
COLOR: 'Negro' | 'Azul' | 'Rojo';

/* Regla parser para colores */
colorName
    : c=COLOR
    ;

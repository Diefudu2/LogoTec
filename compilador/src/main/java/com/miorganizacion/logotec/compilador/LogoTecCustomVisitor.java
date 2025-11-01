package com.miorganizacion.logotec.compilador;

import com.miorganizacion.logotec.compilador.ast.*;
import java.util.*;

import org.antlr.v4.runtime.tree.TerminalNode;

public class LogoTecCustomVisitor extends LogoTecBaseVisitor<ASTNode> {
	
	/*visitProgram
	Este método recorre el contexto raíz devuelto por el parser y construye el 
	nodo raíz del AST. Extrae todas las declaraciones de procedimientos y las 
	sentencias principales llamando recursivamente a visit sobre cada 
	subcontexto, acumula los resultados en dos listas y finalmente crea y 
	devuelve un ProgramNode con esas listas.
	*/
    @Override
    public ASTNode visitProgram(LogoTecParser.ProgramContext ctx) {
        List<ProcDeclNode> decls = new ArrayList<>();
        List<StmtNode> sentences = new ArrayList<>();

        // Procedimientos
        for (LogoTecParser.ProcedureDeclContext p : ctx.proceduresBlock().procedureDecl()) {
            ASTNode node = visit(p);
            if (node instanceof ProcDeclNode) {
                decls.add((ProcDeclNode) node);
            }
        }
        // Sentencias principales
        for (LogoTecParser.SentenceContext s : ctx.proceduresBlock().sentence()) {
            ASTNode node = visit(s);
            if (node instanceof StmtNode) {
                sentences.add((StmtNode) node);
            }
        }
        return new ProgramNode(decls, sentences);
    }

    /*visitVarDecl
	Extrae el identificador de la declaración de variable y crea un 
	VarDeclNode cuyo valor inicial es un ConstNode según el literal

	Si hay NUMBER, convierte el texto a int y devuelve 
	VarDeclNode(name, ConstNode(int)).

	Si hay BOOLEAN, parsea a boolean y devuelve 
	VarDeclNode(name, ConstNode(boolean)).

	Si hay STRING, quita las comillas y devuelve 
	VarDeclNode(name, ConstNode(string)). Si no encuentra ningún literal válido, retorna null.
	*/
    @Override
    public ASTNode visitVarDecl(LogoTecParser.VarDeclContext ctx) {
        String name = ctx.ID().getText();
        if (ctx.literalOrString().NUMBER() != null) {
            int val = Integer.parseInt(ctx.literalOrString().NUMBER().getText());
            return new VarDeclNode(name, new ConstNode(val));
        }
        if (ctx.literalOrString().BOOLEAN() != null) {
            boolean val = Boolean.parseBoolean(ctx.literalOrString().BOOLEAN().getText());
            return new VarDeclNode(name, new ConstNode(val));
        }
        if (ctx.literalOrString().STRING() != null) {
            String raw = ctx.literalOrString().STRING().getText();
            return new VarDeclNode(name, new ConstNode(raw.substring(1, raw.length() - 1)));
        }
        return null;
    }


    /*visitVarInit
    Toma el identificador de la asignación, visita la subregla de expresión para 
    construir su AST y devuelve un VarAssignNode que representa la asignación de
    esa expresión a la variable indicada.
    */
    @Override
    public ASTNode visitVarInit(LogoTecParser.VarInitContext ctx) {
        String name = ctx.ID().getText();
        ASTNode expr = visit(ctx.expression());
        return new VarAssignNode(name, expr);
    }

    /*visitCallProc
    Obtiene el nombre del procedimiento llamado, recorre las expresiones de 
    argumentos llamando recursivamente a visit(...) para construir sus nodos 
    ExprNode, las recopila en una lista y devuelve un ProcCallNode con el nombre 
    y la lista de argumentos.
    */
    @Override
    public ASTNode visitCallProc(LogoTecParser.CallProcContext ctx) {
        String name = ctx.ID().getText();
        List<ExprNode> args = new ArrayList<>();
        if (ctx.expression() != null) {
            for (LogoTecParser.ExpressionContext e : ctx.expression()) {
                args.add((ExprNode) visit(e)); // casteo seguro porque expression devuelve ExprNode
            }
        }
        return new ProcCallNode(name, args);
    }


    /*visitExecBlock
    Recorre las sentencias dentro del bloque de ejecución, visita cada una para
    obtener su nodo AST, verifica que cada resultado sea una sentencia (StmtNode)
    y arroja una excepción si encuentra algo distinto, y finalmente devuelve un 
    ExecBlockNode que contiene la lista de sentencias del cuerpo.
    */
    @Override
    public StmtNode visitExecBlock(LogoTecParser.ExecBlockContext ctx) {
        List<StmtNode> body = new ArrayList<>();
        for (LogoTecParser.SentenceContext s : ctx.sentence()) {
            ASTNode node = visit(s);
            if (!(node instanceof StmtNode)) {
                throw new RuntimeException("ExecBlock solo puede contener sentencias, encontrado: " + node);
            }
            body.add((StmtNode) node);
        }
        return new ExecBlockNode(body);
    }

    /*visitRepiteBlock
    Visita la expresión que indica cuántas veces repetir y la convierte en un 
    ExprNode, recorre las sentencias del cuerpo construyendo una lista de 
    StmtNode, y devuelve un RepeatNode que representa un bloque "repite" con
    su contador y su lista de sentencias.
    */
    @Override
    public ASTNode visitRepiteBlock(LogoTecParser.RepiteBlockContext ctx) {
        ExprNode times = (ExprNode) visit(ctx.expression());
        List<StmtNode> body = new ArrayList<>();
        for (LogoTecParser.SentenceContext s : ctx.sentence()) {
            body.add((StmtNode) visit(s));
        }
        return new RepeatNode(times, body);
    }

    /*visitTurtleCmd
    Detecta qué comando de tortuga aparece en el contexto y construye 
    el nodo AST correspondiente.

	Para comandos con argumento (avanza/retrae/gira/duración) visita la 
	expresión 0 y crea ForwardNode, BackwardNode, TurnRightNode, TurnLeftNode o 
	WaitNode con ese ExprNode.

	Para comandos sin argumento devuelve HideTurtleNode o CenterNode según el 
	token presente.

	Si no coincide ningún token devuelve null.
    */
    @Override
    public ASTNode visitTurtleCmd(LogoTecParser.TurtleCmdContext ctx) {
        if (ctx.AVANZA() != null || ctx.AV() != null) {
            return new ForwardNode((ExprNode) visit(ctx.expression(0)));
        }
        if (ctx.RETROCEDE() != null || ctx.RE() != null) {
            return new BackwardNode((ExprNode) visit(ctx.expression(0)));
        }
        if (ctx.GIRADERECHA() != null || ctx.GD() != null) {
            return new TurnRightNode((ExprNode) visit(ctx.expression(0)));
        }
        if (ctx.GIRAIZQUIERDA() != null || ctx.GI() != null) {
            return new TurnLeftNode((ExprNode) visit(ctx.expression(0)));
        }
        if (ctx.OCULTATORTUGA() != null || ctx.OT() != null) {
            return new HideTurtleNode();
        }
        if (ctx.CENTRO() != null) {
            return new CenterNode();
        }
        if (ctx.ESPERA() != null) {
            return new WaitNode((ExprNode) visit(ctx.expression(0)));
        }
        return null;
    }

    /*visitPrimary
    Convierte un literal o referencia básica en su nodo AST correspondiente: 
    NUMBER → ConstNode(int), BOOLEAN → ConstNode(boolean)
    STRING → ConstNode(String sin comillas)
    ID → VarRefNode(nombre)
    si contiene una expresión anidada delega a visit(expression); 
    si no hay ningún caso válido retorna null
    */
    @Override
    public ASTNode visitPrimary(LogoTecParser.PrimaryContext ctx) {
        if (ctx.NUMBER() != null) {
            return new ConstNode(Integer.parseInt(ctx.NUMBER().getText()));
        }
        if (ctx.BOOLEAN() != null) {
            return new ConstNode(Boolean.parseBoolean(ctx.BOOLEAN().getText()));
        }
        if (ctx.STRING() != null) {
            String raw = ctx.STRING().getText();
            return new ConstNode(raw.substring(1, raw.length() - 1));
        }
        if (ctx.ID() != null) {
            return new VarRefNode(ctx.ID().getText());
        }
        if (ctx.expression() != null) {
            return visit(ctx.expression());
        }
        return null;
    }

    /*visitSentence
    Delega a las subreglas (varDecl, varInit, turtleCmd, etc.)
    para construir el nodo de sentencia apropiado.
    */
    @Override
    public ASTNode visitSentence(LogoTecParser.SentenceContext ctx) {
        // Declaraciones de variables
        if (ctx.varDecl() != null) {
            return visit(ctx.varDecl());
        }
        if (ctx.varInit() != null) {
            return visit(ctx.varInit());
        }
        // Comandos de tortuga
        if (ctx.turtleCmd() != null) {
            return visit(ctx.turtleCmd());
        }
        // Bloques
        if (ctx.execBlock() != null) {
            return visit(ctx.execBlock());
        }
        if (ctx.repiteBlock() != null) {
            return visit(ctx.repiteBlock());
        }
        // Llamada a procedimiento
        if (ctx.callProc() != null) {
            return visit(ctx.callProc());
        }
        // Control de flujo (ahora directamente en sentence, no en flowStmt)
        if (ctx.siStmt() != null) {
            return visit(ctx.siStmt());
        }
        if (ctx.hastaStmt() != null) {
            return visit(ctx.hastaStmt());
        }
        if (ctx.hazHastaStmt() != null) {
            return visit(ctx.hazHastaStmt());
        }
        if (ctx.mientrasStmt() != null) {
            return visit(ctx.mientrasStmt());
        }
        if (ctx.hazMientrasStmt() != null) {
            return visit(ctx.hazMientrasStmt());
        }
        return null;
    }

    @Override
    public ASTNode visitArithExpr(LogoTecParser.ArithExprContext ctx) {
        ASTNode result = visit(ctx.term(0));
        
        for (int i = 1; i < ctx.term().size(); i++) {
            ASTNode right = visit(ctx.term(i));
            // Determinar si es suma o resta basado en el operador
            if (ctx.getChild(2 * i - 1).getText().equals("+")) {
                result = new AdditionNode((ExprNode) result, (ExprNode) right);
            } else {
                result = new SubtractionNode((ExprNode) result, (ExprNode) right);
            }
        }
        return result;
    }

    @Override
    public ASTNode visitTerm(LogoTecParser.TermContext ctx) {
        ASTNode result = visit(ctx.factor(0));
        
        for (int i = 1; i < ctx.factor().size(); i++) {
            ASTNode right = visit(ctx.factor(i));
            // Determinar si es multiplicación o división
            if (ctx.getChild(2 * i - 1).getText().equals("*")) {
                result = new MultiplicationNode((ExprNode) result, (ExprNode) right);
            } else {
                result = new DivisionNode((ExprNode) result, (ExprNode) right);
            }
        }
        return result;
    }

}

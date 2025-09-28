package com.miorganizacion.logotec.compilador;

import com.miorganizacion.logotec.compilador.ast.*;
import java.util.*;

import org.antlr.v4.runtime.tree.TerminalNode;

public class LogoTecCustomVisitor extends LogoTecBaseVisitor<ASTNode> {

    @Override
    public ASTNode visitProgram(LogoTecParser.ProgramContext ctx) {
        List<ProcDeclNode> decls = new ArrayList<>();
        List<StmtNode> sentences = new ArrayList<>();

        // Procedimientos
        for (LogoTecParser.ProcedureDeclContext p : ctx.proceduresBlock().procedureDecl()) {
            decls.add((ProcDeclNode) visit(p));
        }
        // Sentencias principales
        for (LogoTecParser.SentenceContext s : ctx.proceduresBlock().sentence()) {
            sentences.add((StmtNode) visit(s));
        }
        return new ProgramNode(decls, sentences);
    }

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


    @Override
    public ASTNode visitVarInit(LogoTecParser.VarInitContext ctx) {
        String name = ctx.ID().getText();
        ASTNode expr = visit(ctx.expression());
        return new VarAssignNode(name, expr);
    }

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

    @Override
    public ASTNode visitRepiteBlock(LogoTecParser.RepiteBlockContext ctx) {
        ExprNode times = (ExprNode) visit(ctx.expression());
        List<StmtNode> body = new ArrayList<>();
        for (LogoTecParser.SentenceContext s : ctx.sentence()) {
            body.add((StmtNode) visit(s));
        }
        return new RepeatNode(times, body);
    }


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
        if (ctx.GIRAIzQUIERDA() != null || ctx.GI() != null) {
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


}

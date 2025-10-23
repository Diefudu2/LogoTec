package com.miorganizacion.logotec.compilador.ir;

import java.util.*;
import com.miorganizacion.logotec.compilador.ast.*;
import static com.miorganizacion.logotec.compilador.ir.TAC.*;

public class IRGenerator {

    private final TAC tac = new TAC();
    private final Set<String> vars = new LinkedHashSet<>();

    public static final class Result {
        public final TAC tac;
        public final Set<String> vars;
        public Result(TAC t, Set<String> v){this.tac=t; this.vars=v;}
    }

    public Result generate(ProgramNode program) {
        // main
        emit(Instruction.label("main"));
        for (StmtNode s : program.getMainBody()) genStmt(s);
        tac.emit(Op.RET, null, null, null);

        // procedimientos
        for (ProcDeclNode p : program.getProcDecls()) {
            emit(Instruction.label(p.getName()));
            for (StmtNode s : p.getBody()) genStmt(s);
            tac.emit(Op.RET, null, null, null);
        }
        return new Result(tac, vars);
    }

    private void genStmt(StmtNode s) {
        if (s instanceof VarDeclNode v) {
            vars.add(v.getName());
            Operand rhs = genExpr(v.getExpr());
            tac.emit(Op.ASSIGN, rhs, null, new Var(v.getName()));
            return;
        }
        if (s instanceof VarAssignNode v) {
            vars.add(v.getName());
            Operand rhs = genExpr(v.getExpr());
            tac.emit(Op.ASSIGN, rhs, null, new Var(v.getName()));
            return;
        }
        if (s instanceof ExecBlockNode b) {
            for (StmtNode i : b.getBody()) genStmt(i);
            return;
        }
        if (s instanceof IfNode i) {
            Operand c = genCond(i.getCond());
            String lElse = tac.newLabel(), lEnd = tac.newLabel();
            tac.emit(Op.IF_GOTO, c, new Label(lElse), null); // saltar a else si c == 0
            for (StmtNode t : i.getThenBody()) genStmt(t);
            tac.emit(Op.GOTO, new Label(lEnd), null, null);
            emit(Instruction.label(lElse));
            if (i.getElseBody()!=null) for (StmtNode e : i.getElseBody()) genStmt(e);
            emit(Instruction.label(lEnd));
            return;
        }
        if (s instanceof WhileNode w) {
            String lTop = tac.newLabel(), lExit = tac.newLabel();
            emit(Instruction.label(lTop));
            Operand c = genCond(w.getCond());
            tac.emit(Op.IF_GOTO, c, new Label(lExit), null);
            for (StmtNode b : w.getBody()) genStmt(b);
            tac.emit(Op.GOTO, new Label(lTop), null, null);
            emit(Instruction.label(lExit));
            return;
        }
        if (s instanceof DoWhileNode dw) {
            String lTop = tac.newLabel();
            emit(Instruction.label(lTop));
            for (StmtNode b : dw.getBody()) genStmt(b);
            Operand c = genCond(dw.getCond());
            // repetir si c != 0
            TAC.Temp one = tac.newTemp();
            tac.emit(Op.ASSIGN, new Imm(0), null, one);
            tac.emit(Op.CMPNE, c, one, one);
            tac.emit(Op.IF_GOTO, one, new Label(lTop), null);
            return;
        }
        if (s instanceof RepeatNode r) {
            Temp i = tac.newTemp();
            Temp limit = (Temp)genExpr(r.getTimes());
            tac.emit(Op.ASSIGN, new Imm(0), null, i);
            String lTop = tac.newLabel(), lExit = tac.newLabel();
            emit(Instruction.label(lTop));
            Temp cmp = tac.newTemp();
            tac.emit(Op.CMPLT, i, limit, cmp);
            tac.emit(Op.IF_GOTO, cmp, new Label(lExit), null);
            for (StmtNode b : r.getBody()) genStmt(b);
            Temp one = tac.newTemp();
            tac.emit(Op.ASSIGN, new Imm(1), null, one);
            Temp sum = tac.newTemp();
            tac.emit(Op.ADD, i, one, sum);
            tac.emit(Op.ASSIGN, sum, null, i);
            tac.emit(Op.GOTO, new Label(lTop), null, null);
            emit(Instruction.label(lExit));
            return;
        }
        if (s instanceof ProcCallNode pc) {
            // Pasa args como PARAM y CALL
            List<ExprNode> args = pc.getArgs();
            for (int k = args.size()-1; k>=0; k--) {
                tac.emit(Op.PARAM, genExpr(args.get(k)), null, null);
            }
            tac.emit(Op.CALL, new Imm(pc.getName()), new Imm(args.size()), null);
            return;
        }

        // Comandos turtle => llamadas a runtime
        if (s instanceof ForwardNode n) { emitRuntime("rt_forward", List.of(n.getExpr())); return; }
        if (s instanceof BackwardNode n) { emitRuntime("rt_backward", List.of(n.getExpr())); return; }
        if (s instanceof TurnRightNode n) { emitRuntime("rt_turn_right", List.of(n.getExpr())); return; }
        if (s instanceof TurnLeftNode n) { emitRuntime("rt_turn_left", List.of(n.getExpr())); return; }
        if (s instanceof SetPosNode n) { emitRuntime("rt_setpos", List.of(n.getX(), n.getY())); return; }
        if (s instanceof SetHeadingNode n) { emitRuntime("rt_setheading", List.of(n.getExpr())); return; }
        if (s instanceof SetXNode n) { emitRuntime("rt_setx", List.of(n.getExpr())); return; }
        if (s instanceof SetYNode n) { emitRuntime("rt_sety", List.of(n.getExpr())); return; }
        if (s instanceof WaitNode n) { emitRuntime("rt_wait", List.of(n.getExpr())); return; }
        if (s instanceof IncNode n) {
            vars.add(n.getVar().getName());
            Temp sum = tac.newTemp();
            tac.emit(Op.ADD, new Var(n.getVar().getName()), genExpr(n.getDelta()), sum);
            tac.emit(Op.ASSIGN, sum, null, new Var(n.getVar().getName()));
            return;
        }

        tac.emit(Op.NOP, null, null, null, s.getClass().getSimpleName());
    }

    private void emitRuntime(String name, List<ExprNode> args) {
        for (int k = args.size()-1; k>=0; k--) tac.emit(Op.PARAM, genExpr(args.get(k)), null, null);
        tac.emit(Op.CALL, new Imm(name), new Imm(args.size()), null);
    }

    private Operand genCond(ExprNode e) {
        Operand v = genExpr(e);
        // Se asume 0 = false, !=0 = true
        return v;
    }

    private Operand genExpr(ExprNode e) {
        if (e instanceof ConstNode c) {
            Object v = c.getValue();
            if (v instanceof Boolean b) return new Imm(b ? 1 : 0);
            return new Imm(v);
        }
        if (e instanceof VarRefNode v) {
            vars.add(v.getName());
            return new Var(v.getName());
        }
        if (e instanceof AdditionNode n) return bin(n, Op.ADD);
        if (e instanceof SubtractionNode n) return bin(n, Op.SUB);
        if (e instanceof MultiplicationNode n) return bin(n, Op.MUL);
        if (e instanceof DivisionNode n) return bin(n, Op.DIV);
        if (e instanceof ExponentiationNode n) {
            // pow(a,b) naive: call runtime
            Temp tA = (Temp)genExpr(n.getLeft()); Temp tB = (Temp)genExpr(n.getRight());
            tac.emit(Op.PARAM, tB, null, null);
            tac.emit(Op.PARAM, tA, null, null);
            Temp dst = tac.newTemp();
            tac.emit(Op.CALL, new Imm("rt_pow"), new Imm(2), dst);
            return dst;
        }
        if (e instanceof LogicalAndNode n) return bin(n, Op.AND);
        if (e instanceof LogicalOrNode n) return bin(n, Op.OR);
        if (e instanceof LogicalNotNode n) {
            Operand a = genExpr(n.getExpr());
            Temp t = tac.newTemp();
            tac.emit(Op.NOT, a, null, t);
            return t;
        }
        if (e instanceof EqualsNode n) return bin(n, Op.CMPEQ);
        if (e instanceof NotEqualsNode n) return bin(n, Op.CMPNE);
        if (e instanceof GreaterThanNode n) return bin(n, Op.CMPGT);
        if (e instanceof LessThanNode n) return bin(n, Op.CMPLT);
        if (e instanceof GreaterEqualNode n) return bin(n, Op.CMPGE);
        if (e instanceof LessEqualNode n) return bin(n, Op.CMPLE);

        if (e instanceof ProductNode n) return n.getRest().stream().reduce(genExpr(n.getFirst()),
            (acc, x) -> {
                Temp t = tac.newTemp();
                tac.emit(Op.MUL, acc, genExpr(x), t);
                return t;
            }, (a,b)->b);

        if (e instanceof SumNode n) return n.getRest().stream().reduce(genExpr(n.getFirst()),
            (acc, x) -> {
                Temp t = tac.newTemp();
                tac.emit(Op.ADD, acc, genExpr(x), t);
                return t;
            }, (a,b)->b);

        if (e instanceof DifferenceNode n) {
            Iterator<ExprNode> it = n.getRest().iterator();
            Operand acc = genExpr(n.getFirst());
            while (it.hasNext()) {
                Temp t = tac.newTemp();
                tac.emit(Op.SUB, acc, genExpr(it.next()), t);
                acc = t;
            }
            return acc;
        }

        Temp t = tac.newTemp();
        tac.emit(Op.NOP, null, null, t, "expr:" + e.getClass().getSimpleName());
        return t;
    }

    private Operand bin(BinaryNode n, Op op) {
        Operand a = genExpr(n.getLeft());
        Operand b = genExpr(n.getRight());
        Temp t = tac.newTemp();
        tac.emit(op, a, b, t);
        return t;
    }

    private void emit(Instruction i){ tac.emit(i); }
}

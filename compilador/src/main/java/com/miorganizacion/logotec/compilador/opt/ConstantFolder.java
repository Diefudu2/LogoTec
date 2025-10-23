package com.miorganizacion.logotec.compilador.opt;

import java.util.*;
import com.miorganizacion.logotec.compilador.ast.*;

public class ConstantFolder {

    public StmtNode rewriteStmt(StmtNode s) {
        if (s == null) return null;

        if (s instanceof VarDeclNode v) {
            return new VarDeclNode(v.getName(), rewriteExpr(v.getExpr()));
        }
        if (s instanceof VarAssignNode v) {
            return new VarAssignNode(v.getName(), rewriteExpr(v.getExpr()));
        }
        if (s instanceof ExecBlockNode b) {
            List<StmtNode> out = new ArrayList<>();
            for (StmtNode i : b.getBody()) out.add(rewriteStmt(i));
            return new ExecBlockNode(out);
        }
        if (s instanceof IfNode i) {
            ExprNode cond = rewriteExpr(i.getCond());
            List<StmtNode> th = rewriteList(i.getThenBody());
            List<StmtNode> el = i.getElseBody() == null ? null : rewriteList(i.getElseBody());
            // Fold simple si la condición es booleana constante
            if (cond instanceof ConstNode c && c.getValue() instanceof Boolean b) {
                return new ExecBlockNode(b ? th : (el == null ? List.of() : el));
            }
            return new IfNode(cond, th, el);
        }
        if (s instanceof WhileNode w) {
            ExprNode cond = rewriteExpr(w.getCond());
            List<StmtNode> body = rewriteList(w.getBody());
            // while(false) => bloque vacío
            if (cond instanceof ConstNode c && c.getValue() instanceof Boolean b && !b) {
                return new ExecBlockNode(List.of());
            }
            return new WhileNode(cond, body);
        }
        if (s instanceof DoWhileNode dw) {
            ExprNode cond = rewriteExpr(dw.getCond());
            List<StmtNode> body = rewriteList(dw.getBody());
            return new DoWhileNode(body, cond);
        }
        if (s instanceof UntilNode u) {
            ExprNode cond = rewriteExpr(u.getCond());
            List<StmtNode> body = rewriteList(u.getBody());
            // until(true) => no entra
            if (cond instanceof ConstNode c && c.getValue() instanceof Boolean b && b) {
                return new ExecBlockNode(List.of());
            }
            return new UntilNode(cond, body);
        }
        if (s instanceof DoUntilNode du) {
            ExprNode cond = rewriteExpr(du.getCond());
            List<StmtNode> body = rewriteList(du.getBody());
            return new DoUntilNode(body, cond);
        }
        if (s instanceof RepeatNode r) {
            ExprNode times = rewriteExpr(r.getTimes());
            List<StmtNode> body = rewriteList(r.getBody());
            // repite 0 => vacío
            if (times instanceof ConstNode c && c.getValue() instanceof Integer n && n <= 0) {
                return new ExecBlockNode(List.of());
            }
            return new RepeatNode(times, body);
        }
        if (s instanceof ProcCallNode pc) {
            List<ExprNode> args = new ArrayList<>();
            for (ExprNode e : pc.getArgs()) args.add(rewriteExpr(e));
            return new ProcCallNode(pc.getName(), args);
        }

        // Comandos turtle: reescribe sus expresiones
        if (s instanceof ForwardNode n) return new ForwardNode(rewriteExpr(n.getExpr()));
        if (s instanceof BackwardNode n) return new BackwardNode(rewriteExpr(n.getExpr()));
        if (s instanceof TurnRightNode n) return new TurnRightNode(rewriteExpr(n.getExpr()));
        if (s instanceof TurnLeftNode n) return new TurnLeftNode(rewriteExpr(n.getExpr()));
        if (s instanceof SetPosNode n) return new SetPosNode(rewriteExpr(n.getX()), rewriteExpr(n.getY()), n.isBracketed());
        if (s instanceof SetHeadingNode n) return new SetHeadingNode(rewriteExpr(n.getExpr()));
        if (s instanceof SetXNode n) return new SetXNode(rewriteExpr(n.getExpr()));
        if (s instanceof SetYNode n) return new SetYNode(rewriteExpr(n.getExpr()));
        if (s instanceof WaitNode n) return new WaitNode(rewriteExpr(n.getExpr()));
        if (s instanceof IncNode n) return new IncNode(n.getVar(), rewriteExpr(n.getDelta()));

        return s; // nodos sin expr
    }

    private List<StmtNode> rewriteList(List<StmtNode> in) {
        List<StmtNode> out = new ArrayList<>();
        for (StmtNode s : in) out.add(rewriteStmt(s));
        return out;
    }

    public ExprNode rewriteExpr(ExprNode e) {
        if (e == null) return null;

        // Unarios
        if (e instanceof LogicalNotNode n) {
            ExprNode a = rewriteExpr(n.getExpr());
            if (a instanceof ConstNode c && c.getValue() instanceof Boolean b) {
                return new ConstNode(!b);
            }
            return new LogicalNotNode(a);
        }

        // Binarios booleanos
        if (e instanceof LogicalAndNode n) {
            ExprNode l = rewriteExpr(n.getLeft()), r = rewriteExpr(n.getRight());
            if (l instanceof ConstNode lc && lc.getValue() instanceof Boolean lb) {
                if (!lb) return new ConstNode(false);
                if (r instanceof ConstNode rc && rc.getValue() instanceof Boolean rb) return new ConstNode(rb);
            }
            if (r instanceof ConstNode rc && rc.getValue() instanceof Boolean rb) {
                if (!rb) return new ConstNode(false);
            }
            return new LogicalAndNode(l, r);
        }
        if (e instanceof LogicalOrNode n) {
            ExprNode l = rewriteExpr(n.getLeft()), r = rewriteExpr(n.getRight());
            if (l instanceof ConstNode lc && lc.getValue() instanceof Boolean lb) {
                if (lb) return new ConstNode(true);
                if (r instanceof ConstNode rc && rc.getValue() instanceof Boolean rb) return new ConstNode(rb);
            }
            if (r instanceof ConstNode rc && rc.getValue() instanceof Boolean rb) {
                if (rb) return new ConstNode(true);
            }
            return new LogicalOrNode(l, r);
        }

        // Comparaciones
        if (e instanceof EqualsNode n) return foldCmp(n, "==");
        if (e instanceof NotEqualsNode n) return foldCmp(n, "!=");
        if (e instanceof GreaterThanNode n) return foldCmp(n, ">");
        if (e instanceof LessThanNode n) return foldCmp(n, "<");
        if (e instanceof GreaterEqualNode n) return foldCmp(n, ">=");
        if (e instanceof LessEqualNode n) return foldCmp(n, "<=");

        // Aritmética
        if (e instanceof AdditionNode n) return foldArith(n, "+");
        if (e instanceof SubtractionNode n) return foldArith(n, "-");
        if (e instanceof MultiplicationNode n) return foldArith(n, "*");
        if (e instanceof DivisionNode n) return foldArith(n, "/");
        if (e instanceof ExponentiationNode n) return foldArith(n, "^");

        // Random, funciones: intenta plegar si los args son constantes y es determinista
        if (e instanceof ProductNode n) {
            List<ExprNode> all = new ArrayList<>();
            all.add(rewriteExpr(n.getFirst()));
            for (ExprNode x : n.getRest()) all.add(rewriteExpr(x));
            boolean allConst = all.stream().allMatch(a -> a instanceof ConstNode c && c.getValue() instanceof Integer);
            if (allConst) {
                long acc = 1;
                for (ExprNode a : all) acc *= ((Number)((ConstNode)a).getValue()).longValue();
                return new ConstNode((int)acc);
            }
            return new ProductNode(all.get(0), all.subList(1, all.size()));
        }
        if (e instanceof SumNode n) {
            List<ExprNode> all = new ArrayList<>();
            all.add(rewriteExpr(n.getFirst()));
            for (ExprNode x : n.getRest()) all.add(rewriteExpr(x));
            boolean allConst = all.stream().allMatch(a -> a instanceof ConstNode c && c.getValue() instanceof Integer);
            if (allConst) {
                long acc = 0;
                for (ExprNode a : all) acc += ((Number)((ConstNode)a).getValue()).longValue();
                return new ConstNode((int)acc);
            }
            return new SumNode(all.get(0), all.subList(1, all.size()));
        }
        if (e instanceof DifferenceNode n) {
            List<ExprNode> all = new ArrayList<>();
            all.add(rewriteExpr(n.getFirst()));
            for (ExprNode x : n.getRest()) all.add(rewriteExpr(x));
            boolean allConst = all.stream().allMatch(a -> a instanceof ConstNode c && c.getValue() instanceof Integer);
            if (allConst) {
                long acc = ((Number)((ConstNode)all.get(0)).getValue()).longValue();
                for (int i=1;i<all.size();i++) acc -= ((Number)((ConstNode)all.get(i)).getValue()).longValue();
                return new ConstNode((int)acc);
            }
            return new DifferenceNode(all.get(0), all.subList(1, all.size()));
        }

        return e;
    }

    private ExprNode foldArith(BinaryNode n, String op) {
        ExprNode l = rewriteExpr(n.getLeft()), r = rewriteExpr(n.getRight());
        if (l instanceof ConstNode lc && lc.getValue() instanceof Integer
         && r instanceof ConstNode rc && rc.getValue() instanceof Integer) {
            int a = (Integer) lc.getValue(), b = (Integer) rc.getValue();
            return switch (op) {
                case "+" -> new ConstNode(a + b);
                case "-" -> new ConstNode(a - b);
                case "*" -> new ConstNode(a * b);
                case "/" -> new ConstNode(b == 0 ? a : a / b);
                case "^" -> new ConstNode((int)Math.pow(a, b));
                default -> new BinaryDispatch(l, r, op).toNode();
            };
        }
        // Simplificaciones básicas
        if (op.equals("+")) {
            if (l instanceof ConstNode c && c.getValue() instanceof Integer v && v == 0) return r;
            if (r instanceof ConstNode c && c.getValue() instanceof Integer v && v == 0) return l;
        }
        if (op.equals("*")) {
            if ((l instanceof ConstNode c1 && c1.getValue() instanceof Integer v1 && v1 == 0)
             || (r instanceof ConstNode c2 && c2.getValue() instanceof Integer v2 && v2 == 0)) return new ConstNode(0);
            if (l instanceof ConstNode c && c.getValue() instanceof Integer v && v == 1) return r;
            if (r instanceof ConstNode c && c.getValue() instanceof Integer v && v == 1) return l;
        }
        return new BinaryDispatch(l, r, op).toNode();
    }

    private ExprNode foldCmp(BinaryNode n, String op) {
        ExprNode l = rewriteExpr(n.getLeft()), r = rewriteExpr(n.getRight());
        if (l instanceof ConstNode lc && r instanceof ConstNode rc) {
            Object a = lc.getValue(), b = rc.getValue();
            if (a instanceof Integer ai && b instanceof Integer bi) {
                return switch (op) {
                    case "==" -> new ConstNode(ai == bi);
                    case "!=" -> new ConstNode(ai != bi);
                    case ">"  -> new ConstNode(ai >  bi);
                    case "<"  -> new ConstNode(ai <  bi);
                    case ">=" -> new ConstNode(ai >= bi);
                    case "<=" -> new ConstNode(ai <= bi);
                    default -> new EqualsNode(l, r);
                };
            }
            if (a instanceof Boolean ab && b instanceof Boolean bb) {
                return switch (op) {
                    case "==" -> new ConstNode(ab == bb);
                    case "!=" -> new ConstNode(ab != bb);
                    default -> new EqualsNode(l, r);
                };
            }
            if (a instanceof String as && b instanceof String bs) {
                return switch (op) {
                    case "==" -> new ConstNode(as.equals(bs));
                    case "!=" -> new ConstNode(!as.equals(bs));
                    default -> new EqualsNode(l, r);
                };
            }
        }
        return switch (op) {
            case "==" -> new EqualsNode(l, r);
            case "!=" -> new NotEqualsNode(l, r);
            case ">"  -> new GreaterThanNode(l, r);
            case "<"  -> new LessThanNode(l, r);
            case ">=" -> new GreaterEqualNode(l, r);
            case "<=" -> new LessEqualNode(l, r);
            default -> new EqualsNode(l, r);
        };
    }

    // Adaptador mínimo para recrear nodos binarios a partir de operador
    private static class BinaryDispatch {
        final ExprNode l, r; final String op;
        BinaryDispatch(ExprNode l, ExprNode r, String op){this.l=l;this.r=r;this.op=op;}
        ExprNode toNode(){
            return switch (op) {
                case "+" -> new AdditionNode(l, r);
                case "-" -> new SubtractionNode(l, r);
                case "*" -> new MultiplicationNode(l, r);
                case "/" -> new DivisionNode(l, r);
                case "^" -> new ExponentiationNode(l, r);
                case "&&"-> new LogicalAndNode(l, r);
                case "||"-> new LogicalOrNode(l, r);
                case "=="-> new EqualsNode(l, r);
                case "!="-> new NotEqualsNode(l, r);
                case ">" -> new GreaterThanNode(l, r);
                case "<" -> new LessThanNode(l, r);
                case ">="-> new GreaterEqualNode(l, r);
                case "<="-> new LessEqualNode(l, r);
                default -> new AdditionNode(l, r);
            };
        }
    }
}

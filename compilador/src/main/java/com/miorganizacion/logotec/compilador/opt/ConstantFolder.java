package com.miorganizacion.logotec.compilador.opt;

import java.util.*;
import com.miorganizacion.logotec.compilador.ast.*;

/**
 * Constant Folder: pliega expresiones constantes en tiempo de compilación.
 * Compatible con Java 8+.
 */
public class ConstantFolder {

    public StmtNode rewriteStmt(StmtNode s) {
        if (s == null) return null;

        if (s instanceof VarDeclNode) {
            VarDeclNode v = (VarDeclNode) s;
            return new VarDeclNode(v.getName(), rewriteExpr(v.getValue()));
        }
        if (s instanceof VarAssignNode) {
            VarAssignNode v = (VarAssignNode) s;
            return new VarAssignNode(v.getName(), rewriteExpr(v.getExpression()));
        }
        if (s instanceof ExecBlockNode) {
            ExecBlockNode b = (ExecBlockNode) s;
            List<StmtNode> out = new ArrayList<StmtNode>();
            for (StmtNode i : b.getBody()) {
                out.add(rewriteStmt(i));
            }
            return new ExecBlockNode(out);
        }
        if (s instanceof IfNode) {
            IfNode i = (IfNode) s;
            ExprNode cond = rewriteExpr(i.getCond());
            List<StmtNode> th = rewriteList(i.getThenBody());
            List<StmtNode> el = i.getElseBody() == null ? null : rewriteList(i.getElseBody());
            // Fold si la condición es booleana constante
            if (cond instanceof ConstNode) {
                ConstNode c = (ConstNode) cond;
                Object val = c.getValue();
                if (val instanceof Boolean) {
                    boolean b = (Boolean) val;
                    return new ExecBlockNode(b ? th : (el == null ? new ArrayList<StmtNode>() : el));
                }
            }
            return new IfNode(cond, th, el);
        }
        if (s instanceof WhileNode) {
            WhileNode w = (WhileNode) s;
            ExprNode cond = rewriteExpr(w.getCond());
            List<StmtNode> body = rewriteList(w.getBody());
            // while(false) => bloque vacío
            if (cond instanceof ConstNode) {
                ConstNode c = (ConstNode) cond;
                Object val = c.getValue();
                if (val instanceof Boolean && !((Boolean) val)) {
                    return new ExecBlockNode(new ArrayList<StmtNode>());
                }
            }
            return new WhileNode(cond, body);
        }
        if (s instanceof DoWhileNode) {
            DoWhileNode dw = (DoWhileNode) s;
            ExprNode cond = rewriteExpr(dw.getCond());
            List<StmtNode> body = rewriteList(dw.getBody());
            return new DoWhileNode(body, cond);
        }
        if (s instanceof UntilNode) {
            UntilNode u = (UntilNode) s;
            ExprNode cond = rewriteExpr(u.getCondition());
            List<StmtNode> body = rewriteList(u.getBody());
            // until(true) => no entra
            if (cond instanceof ConstNode) {
                ConstNode c = (ConstNode) cond;
                Object val = c.getValue();
                if (val instanceof Boolean && ((Boolean) val)) {
                    return new ExecBlockNode(new ArrayList<StmtNode>());
                }
            }
            return new UntilNode(cond, body);
        }
        if (s instanceof DoUntilNode) {
            DoUntilNode du = (DoUntilNode) s;
            ExprNode cond = rewriteExpr(du.getCond());
            List<StmtNode> body = rewriteList(du.getBody());
            return new DoUntilNode(body, cond);
        }
        if (s instanceof RepeatNode) {
            RepeatNode r = (RepeatNode) s;
            ExprNode times = rewriteExpr(r.getTimes());
            List<StmtNode> body = rewriteList(r.getBody());
            // repite 0 => vacío
            if (times instanceof ConstNode) {
                ConstNode c = (ConstNode) times;
                Object val = c.getValue();
                if (val instanceof Integer && ((Integer) val) <= 0) {
                    return new ExecBlockNode(new ArrayList<StmtNode>());
                }
            }
            return new RepeatNode(times, body);
        }
        if (s instanceof ProcCallNode) {
            ProcCallNode pc = (ProcCallNode) s;
            List<ExprNode> args = new ArrayList<ExprNode>();
            for (ExprNode e : pc.getArgs()) {
                args.add(rewriteExpr(e));
            }
            return new ProcCallNode(pc.getName(), args);
        }

        // Comandos turtle: reescribe sus expresiones
        if (s instanceof ForwardNode) {
            ForwardNode n = (ForwardNode) s;
            return new ForwardNode(rewriteExpr(n.getExpr()));
        }
        if (s instanceof BackwardNode) {
            BackwardNode n = (BackwardNode) s;
            return new BackwardNode(rewriteExpr(n.getExpr()));
        }
        if (s instanceof TurnRightNode) {
            TurnRightNode n = (TurnRightNode) s;
            return new TurnRightNode(rewriteExpr(n.getExpr()));
        }
        if (s instanceof TurnLeftNode) {
            TurnLeftNode n = (TurnLeftNode) s;
            return new TurnLeftNode(rewriteExpr(n.getExpr()));
        }
        if (s instanceof SetPosNode) {
            SetPosNode n = (SetPosNode) s;
            return new SetPosNode(rewriteExpr(n.getX()), rewriteExpr(n.getY()), n.isBracketed());
        }
        if (s instanceof SetHeadingNode) {
            SetHeadingNode n = (SetHeadingNode) s;
            return new SetHeadingNode(rewriteExpr(n.getExpr()));
        }
        if (s instanceof SetXNode) {
            SetXNode n = (SetXNode) s;
            return new SetXNode(rewriteExpr(n.getExpr()));
        }
        if (s instanceof SetYNode) {
            SetYNode n = (SetYNode) s;
            return new SetYNode(rewriteExpr(n.getExpr()));
        }
        if (s instanceof WaitNode) {
            WaitNode n = (WaitNode) s;
            return new WaitNode(rewriteExpr(n.getExpr()));
        }
        if (s instanceof IncNode) {
            IncNode n = (IncNode) s;
            return new IncNode(n.getVar(), rewriteExpr(n.getDelta()));
        }

        return s; // nodos sin expr
    }

    private List<StmtNode> rewriteList(List<StmtNode> in) {
        List<StmtNode> out = new ArrayList<StmtNode>();
        for (StmtNode s : in) {
            out.add(rewriteStmt(s));
        }
        return out;
    }

    public ExprNode rewriteExpr(ExprNode e) {
        if (e == null) return null;

        // Unarios
        if (e instanceof LogicalNotNode) {
            LogicalNotNode n = (LogicalNotNode) e;
            ExprNode a = rewriteExpr(n.getExpr());
            if (a instanceof ConstNode) {
                ConstNode c = (ConstNode) a;
                Object val = c.getValue();
                if (val instanceof Boolean) {
                    return new ConstNode(!((Boolean) val));
                }
            }
            return new LogicalNotNode(a);
        }

        // Binarios booleanos
        if (e instanceof LogicalAndNode) {
            LogicalAndNode n = (LogicalAndNode) e;
            ExprNode l = rewriteExpr(n.getLeft());
            ExprNode r = rewriteExpr(n.getRight());
            
            if (l instanceof ConstNode) {
                ConstNode lc = (ConstNode) l;
                Object lval = lc.getValue();
                if (lval instanceof Boolean) {
                    if (!((Boolean) lval)) return new ConstNode(false);
                    if (r instanceof ConstNode) {
                        ConstNode rc = (ConstNode) r;
                        Object rval = rc.getValue();
                        if (rval instanceof Boolean) return new ConstNode((Boolean) rval);
                    }
                }
            }
            if (r instanceof ConstNode) {
                ConstNode rc = (ConstNode) r;
                Object rval = rc.getValue();
                if (rval instanceof Boolean && !((Boolean) rval)) {
                    return new ConstNode(false);
                }
            }
            return new LogicalAndNode(l, r);
        }
        
        if (e instanceof LogicalOrNode) {
            LogicalOrNode n = (LogicalOrNode) e;
            ExprNode l = rewriteExpr(n.getLeft());
            ExprNode r = rewriteExpr(n.getRight());
            
            if (l instanceof ConstNode) {
                ConstNode lc = (ConstNode) l;
                Object lval = lc.getValue();
                if (lval instanceof Boolean) {
                    if ((Boolean) lval) return new ConstNode(true);
                    if (r instanceof ConstNode) {
                        ConstNode rc = (ConstNode) r;
                        Object rval = rc.getValue();
                        if (rval instanceof Boolean) return new ConstNode((Boolean) rval);
                    }
                }
            }
            if (r instanceof ConstNode) {
                ConstNode rc = (ConstNode) r;
                Object rval = rc.getValue();
                if (rval instanceof Boolean && (Boolean) rval) {
                    return new ConstNode(true);
                }
            }
            return new LogicalOrNode(l, r);
        }

        // Comparaciones
        if (e instanceof EqualsNode) {
            EqualsNode n = (EqualsNode) e;
            return foldComparison(rewriteExpr(n.getLeft()), rewriteExpr(n.getRight()), "==");
        }
        if (e instanceof NotEqualsNode) {
            NotEqualsNode n = (NotEqualsNode) e;
            return foldComparison(rewriteExpr(n.getLeft()), rewriteExpr(n.getRight()), "!=");
        }
        if (e instanceof GreaterThanNode) {
            GreaterThanNode n = (GreaterThanNode) e;
            return foldComparison(rewriteExpr(n.getLeft()), rewriteExpr(n.getRight()), ">");
        }
        if (e instanceof LessThanNode) {
            LessThanNode n = (LessThanNode) e;
            return foldComparison(rewriteExpr(n.getLeft()), rewriteExpr(n.getRight()), "<");
        }
        if (e instanceof GreaterEqualNode) {
            GreaterEqualNode n = (GreaterEqualNode) e;
            return foldComparison(rewriteExpr(n.getLeft()), rewriteExpr(n.getRight()), ">=");
        }
        if (e instanceof LessEqualNode) {
            LessEqualNode n = (LessEqualNode) e;
            return foldComparison(rewriteExpr(n.getLeft()), rewriteExpr(n.getRight()), "<=");
        }

        // Aritmética
        if (e instanceof AdditionNode) {
            AdditionNode n = (AdditionNode) e;
            return foldArithmetic(rewriteExpr(n.getLeft()), rewriteExpr(n.getRight()), "+");
        }
        if (e instanceof SubtractionNode) {
            SubtractionNode n = (SubtractionNode) e;
            return foldArithmetic(rewriteExpr(n.getLeft()), rewriteExpr(n.getRight()), "-");
        }
        if (e instanceof MultiplicationNode) {
            MultiplicationNode n = (MultiplicationNode) e;
            return foldArithmetic(rewriteExpr(n.getLeft()), rewriteExpr(n.getRight()), "*");
        }
        if (e instanceof DivisionNode) {
            DivisionNode n = (DivisionNode) e;
            return foldArithmetic(rewriteExpr(n.getLeft()), rewriteExpr(n.getRight()), "/");
        }
        if (e instanceof ExponentiationNode) {
            ExponentiationNode n = (ExponentiationNode) e;
            return foldArithmetic(rewriteExpr(n.getLeft()), rewriteExpr(n.getRight()), "^");
        }

        // N-ary: Product, Sum, Difference
        if (e instanceof ProductNode) {
            ProductNode n = (ProductNode) e;
            List<ExprNode> all = new ArrayList<ExprNode>();
            all.add(rewriteExpr(n.getFirst()));
            for (ExprNode x : n.getRest()) {
                all.add(rewriteExpr(x));
            }
            boolean allConst = true;
            for (ExprNode a : all) {
                if (!(a instanceof ConstNode && ((ConstNode) a).getValue() instanceof Integer)) {
                    allConst = false;
                    break;
                }
            }
            if (allConst) {
                long acc = 1;
                for (ExprNode a : all) {
                    acc *= ((Number) ((ConstNode) a).getValue()).longValue();
                }
                return new ConstNode((int) acc);
            }
            return new ProductNode(all.get(0), all.subList(1, all.size()));
        }
        
        if (e instanceof SumNode) {
            SumNode n = (SumNode) e;
            List<ExprNode> all = new ArrayList<ExprNode>();
            all.add(rewriteExpr(n.getFirst()));
            for (ExprNode x : n.getRest()) {
                all.add(rewriteExpr(x));
            }
            boolean allConst = true;
            for (ExprNode a : all) {
                if (!(a instanceof ConstNode && ((ConstNode) a).getValue() instanceof Integer)) {
                    allConst = false;
                    break;
                }
            }
            if (allConst) {
                long acc = 0;
                for (ExprNode a : all) {
                    acc += ((Number) ((ConstNode) a).getValue()).longValue();
                }
                return new ConstNode((int) acc);
            }
            return new SumNode(all.get(0), all.subList(1, all.size()));
        }
        
        if (e instanceof DifferenceNode) {
            DifferenceNode n = (DifferenceNode) e;
            List<ExprNode> all = new ArrayList<ExprNode>();
            all.add(rewriteExpr(n.getFirst()));
            for (ExprNode x : n.getRest()) {
                all.add(rewriteExpr(x));
            }
            boolean allConst = true;
            for (ExprNode a : all) {
                if (!(a instanceof ConstNode && ((ConstNode) a).getValue() instanceof Integer)) {
                    allConst = false;
                    break;
                }
            }
            if (allConst) {
                long acc = ((Number) ((ConstNode) all.get(0)).getValue()).longValue();
                for (int i = 1; i < all.size(); i++) {
                    acc -= ((Number) ((ConstNode) all.get(i)).getValue()).longValue();
                }
                return new ConstNode((int) acc);
            }
            return new DifferenceNode(all.get(0), all.subList(1, all.size()));
        }

        return e;
    }

    private ExprNode foldArithmetic(ExprNode l, ExprNode r, String op) {
        // Intentar plegar constantes
        if (l instanceof ConstNode && r instanceof ConstNode) {
            ConstNode lc = (ConstNode) l;
            ConstNode rc = (ConstNode) r;
            Object lval = lc.getValue();
            Object rval = rc.getValue();
            
            if (lval instanceof Integer && rval instanceof Integer) {
                int a = (Integer) lval;
                int b = (Integer) rval;
                
                if (op.equals("+")) return new ConstNode(a + b);
                if (op.equals("-")) return new ConstNode(a - b);
                if (op.equals("*")) return new ConstNode(a * b);
                if (op.equals("/") && b != 0) return new ConstNode(a / b);
                if (op.equals("^")) return new ConstNode((int) Math.pow(a, b));
            }
        }
        
        // Simplificaciones básicas
        if (op.equals("+")) {
            if (isZero(l)) return r;
            if (isZero(r)) return l;
            return new AdditionNode(l, r);
        }
        if (op.equals("-")) {
            if (isZero(r)) return l;
            return new SubtractionNode(l, r);
        }
        if (op.equals("*")) {
            if (isZero(l) || isZero(r)) return new ConstNode(0);
            if (isOne(l)) return r;
            if (isOne(r)) return l;
            return new MultiplicationNode(l, r);
        }
        if (op.equals("/")) {
            if (isOne(r)) return l;
            return new DivisionNode(l, r);
        }
        if (op.equals("^")) {
            if (isZero(r)) return new ConstNode(1);
            if (isOne(r)) return l;
            return new ExponentiationNode(l, r);
        }
        
        return new AdditionNode(l, r); // fallback
    }

    private ExprNode foldComparison(ExprNode l, ExprNode r, String op) {
        if (l instanceof ConstNode && r instanceof ConstNode) {
            ConstNode lc = (ConstNode) l;
            ConstNode rc = (ConstNode) r;
            Object a = lc.getValue();
            Object b = rc.getValue();
            
            if (a instanceof Integer && b instanceof Integer) {
                int ai = (Integer) a;
                int bi = (Integer) b;
                
                if (op.equals("==")) return new ConstNode(ai == bi);
                if (op.equals("!=")) return new ConstNode(ai != bi);
                if (op.equals(">")) return new ConstNode(ai > bi);
                if (op.equals("<")) return new ConstNode(ai < bi);
                if (op.equals(">=")) return new ConstNode(ai >= bi);
                if (op.equals("<=")) return new ConstNode(ai <= bi);
            }
            if (a instanceof Boolean && b instanceof Boolean) {
                boolean ab = (Boolean) a;
                boolean bb = (Boolean) b;
                if (op.equals("==")) return new ConstNode(ab == bb);
                if (op.equals("!=")) return new ConstNode(ab != bb);
            }
            if (a instanceof String && b instanceof String) {
                String as = (String) a;
                String bs = (String) b;
                if (op.equals("==")) return new ConstNode(as.equals(bs));
                if (op.equals("!=")) return new ConstNode(!as.equals(bs));
            }
        }
        
        // Reconstruir nodo
        if (op.equals("==")) return new EqualsNode(l, r);
        if (op.equals("!=")) return new NotEqualsNode(l, r);
        if (op.equals(">")) return new GreaterThanNode(l, r);
        if (op.equals("<")) return new LessThanNode(l, r);
        if (op.equals(">=")) return new GreaterEqualNode(l, r);
        if (op.equals("<=")) return new LessEqualNode(l, r);
        
        return new EqualsNode(l, r); // fallback
    }

    private boolean isZero(ExprNode e) {
        if (e instanceof ConstNode) {
            Object val = ((ConstNode) e).getValue();
            return val instanceof Integer && ((Integer) val) == 0;
        }
        return false;
    }

    private boolean isOne(ExprNode e) {
        if (e instanceof ConstNode) {
            Object val = ((ConstNode) e).getValue();
            return val instanceof Integer && ((Integer) val) == 1;
        }
        return false;
    }
}

package com.miorganizacion.logotec.compilador.opt;

import java.util.*;
import com.miorganizacion.logotec.compilador.ast.*;

/**
 * AST optimizer: constant folding, simple algebraic simplifications,
 * dead-branch elimination, light constant propagation, and basic block flattening.
 *
 * Compatible con Java 8+.
 * 
 * Usage: ProgramNode optimized = AstOptimizer.optimize(programNode);
 */
public final class AstOptimizer {

    public AstOptimizer() {}

    public static ProgramNode optimize(ProgramNode root) {
        if (root == null) return null;

        // Optimize procedures
        List<ProcDeclNode> optDecls = new ArrayList<ProcDeclNode>();
        for (ProcDeclNode pd : root.getDecls()) {
            optDecls.add(optimizeProc(pd));
        }

        // Optimize main body with a fresh environment
        Env env = new Env();
        List<StmtNode> optMain = optimizeStmtList(root.getMainBody(), env);

        return new ProgramNode(optDecls, optMain);
    }

    private static ProcDeclNode optimizeProc(ProcDeclNode pd) {
        // New environment; parameters are unknown
        Env env = new Env();
        List<String> params = getParams(pd);
        for (String p : params) {
            env.kill(p);
        }
        List<StmtNode> body = optimizeStmtList(getBody(pd), env);
        return new ProcDeclNode(getName(pd), params, body);
    }

    // -------------------- Helpers para ProcDeclNode --------------------
    
    private static String getName(ProcDeclNode pd) {
        try {
            java.lang.reflect.Field f = ProcDeclNode.class.getDeclaredField("name");
            f.setAccessible(true);
            return (String) f.get(pd);
        } catch (Exception e) {
            return "unknown";
        }
    }
    
    @SuppressWarnings("unchecked")
    private static List<String> getParams(ProcDeclNode pd) {
        try {
            java.lang.reflect.Field f = ProcDeclNode.class.getDeclaredField("params");
            f.setAccessible(true);
            return (List<String>) f.get(pd);
        } catch (Exception e) {
            return new ArrayList<String>();
        }
    }
    
    @SuppressWarnings("unchecked")
    private static List<StmtNode> getBody(ProcDeclNode pd) {
        try {
            java.lang.reflect.Field f = ProcDeclNode.class.getDeclaredField("body");
            f.setAccessible(true);
            return (List<StmtNode>) f.get(pd);
        } catch (Exception e) {
            return new ArrayList<StmtNode>();
        }
    }

    // -------------------- Statements --------------------

    private static List<StmtNode> optimizeStmtList(List<StmtNode> stmts, Env env) {
        List<StmtNode> out = new ArrayList<StmtNode>();
        for (StmtNode s : stmts) {
            StmtNode opt = optimizeStmt(s, env);
            if (opt == null) continue;
            // Flatten nested ExecBlockNode
            if (opt instanceof ExecBlockNode) {
                List<StmtNode> nested = ((ExecBlockNode) opt).getBody();
                if (nested.isEmpty()) continue;
                out.addAll(nested);
            } else {
                out.add(opt);
            }
        }
        return out;
    }

    private static StmtNode optimizeStmt(StmtNode s, Env env) {
        if (s == null) return null;

        // Variable declaration
        if (s instanceof VarDeclNode) {
            VarDeclNode vd = (VarDeclNode) s;
            ExprNode expr = optimizeExpr(vd.getValue(), env);
            // Track constant if literal
            Object c = constValue(expr);
            if (c != null) env.put(vd.getName(), c); else env.kill(vd.getName());
            return new VarDeclNode(vd.getName(), expr);
        }

        // Variable assignment
        if (s instanceof VarAssignNode) {
            VarAssignNode va = (VarAssignNode) s;
            ExprNode expr = optimizeExpr(va.getExpression(), env);
            Object c = constValue(expr);
            if (c != null) env.put(va.getName(), c); else env.kill(va.getName());
            return new VarAssignNode(va.getName(), expr);
        }

        // Increment
        if (s instanceof IncNode) {
            IncNode in = (IncNode) s;
            String var = in.getVar().getName();
            ExprNode delta = optimizeExpr(in.getDelta(), env);
            // Update env if both sides are integers
            Object curr = env.get(var);
            Object d = constValue(delta);
            if (curr instanceof Integer && d instanceof Integer) {
                env.put(var, ((Integer) curr) + ((Integer) d));
            } else {
                env.kill(var);
            }
            return new IncNode(new VarRefNode(var), delta);
        }

        // Exec block
        if (s instanceof ExecBlockNode) {
            ExecBlockNode eb = (ExecBlockNode) s;
            List<StmtNode> body = optimizeStmtList(eb.getBody(), env);
            if (body.isEmpty()) return null;
            return new ExecBlockNode(body);
        }

        // Repeat
        if (s instanceof RepeatNode) {
            RepeatNode rp = (RepeatNode) s;
            ExprNode times = optimizeExpr(rp.getTimes(), env);
            List<StmtNode> body = optimizeStmtList(rp.getBody(), env.fork());
            Object c = constValue(times);
            if (c instanceof Integer) {
                int n = (Integer) c;
                if (n <= 0) return null;
                final int UNROLL_LIMIT = 16;
                if (n <= UNROLL_LIMIT) {
                    List<StmtNode> unrolled = new ArrayList<StmtNode>(n * Math.max(1, body.size()));
                    for (int i = 0; i < n; i++) {
                        unrolled.addAll(body);
                    }
                    env.clear();
                    return new ExecBlockNode(unrolled);
                }
            }
            env.clear();
            return new RepeatNode(times, body);
        }

        // If
        if (s instanceof IfNode) {
            IfNode ifn = (IfNode) s;
            ExprNode cond = optimizeExpr(ifn.getCondition(), env);

            Object c = constValue(cond);
            if (c instanceof Boolean) {
                if (((Boolean) c)) {
                    return normalizeBlock(optimizeBlockAsExec(ifn.getThenBody(), env));
                } else {
                    return normalizeBlock(optimizeBlockAsExec(ifn.getElseBody(), env));
                }
            } else {
                List<StmtNode> thenOpt = optimizeStmtList(ifn.getThenBody(), env.fork());
                List<StmtNode> elseOpt = ifn.getElseBody() == null ? null : optimizeStmtList(ifn.getElseBody(), env.fork());
                env.clear();
                return new IfNode(cond, thenOpt, elseOpt == null || elseOpt.isEmpty() ? null : elseOpt);
            }
        }

        // While
        if (s instanceof WhileNode) {
            WhileNode wn = (WhileNode) s;
            ExprNode cond = optimizeExpr(wn.getCondition(), env);
            Object c = constValue(cond);
            List<StmtNode> body = optimizeStmtList(wn.getBody(), env.fork());
            if (c instanceof Boolean && !(Boolean) c) {
                return null;
            }
            env.clear();
            return new WhileNode(cond, body);
        }

        // DoWhile
        if (s instanceof DoWhileNode) {
            DoWhileNode dwn = (DoWhileNode) s;
            ExprNode cond = optimizeExpr(dwn.getCondition(), env);
            List<StmtNode> body = optimizeStmtList(dwn.getBody(), env.fork());
            Object c = constValue(cond);
            if (c instanceof Boolean && !(Boolean) c) {
                env.clear();
                return new ExecBlockNode(body);
            }
            env.clear();
            return new DoWhileNode(body, cond);
        }

        // Until
        if (s instanceof UntilNode) {
            UntilNode un = (UntilNode) s;
            ExprNode cond = optimizeExpr(un.getCondition(), env);
            List<StmtNode> body = optimizeStmtList(un.getBody(), env.fork());
            Object c = constValue(cond);
            if (c instanceof Boolean && (Boolean) c) {
                return null;
            }
            env.clear();
            return new UntilNode(cond, body);
        }

        // DoUntil
        if (s instanceof DoUntilNode) {
            DoUntilNode dun = (DoUntilNode) s;
            ExprNode cond = optimizeExpr(dun.getCondition(), env);
            List<StmtNode> body = optimizeStmtList(dun.getBody(), env.fork());
            Object c = constValue(cond);
            if (c instanceof Boolean && (Boolean) c) {
                env.clear();
                return new ExecBlockNode(body);
            }
            env.clear();
            return new DoUntilNode(body, cond);
        }

        // Procedure call
        if (s instanceof ProcCallNode) {
            ProcCallNode pc = (ProcCallNode) s;
            List<ExprNode> args = new ArrayList<ExprNode>(pc.getArgs().size());
            for (ExprNode a : pc.getArgs()) {
                args.add(optimizeExpr(a, env));
            }
            env.clear();
            return new ProcCallNode(pc.getName(), args);
        }

        // Turtle commands with expressions
        if (s instanceof ForwardNode) {
            ForwardNode n = (ForwardNode) s;
            return new ForwardNode(optimizeExpr(n.getExpr(), env));
        }
        if (s instanceof BackwardNode) {
            BackwardNode n = (BackwardNode) s;
            return new BackwardNode(optimizeExpr(n.getExpr(), env));
        }
        if (s instanceof TurnRightNode) {
            TurnRightNode n = (TurnRightNode) s;
            return new TurnRightNode(optimizeExpr(n.getExpr(), env));
        }
        if (s instanceof TurnLeftNode) {
            TurnLeftNode n = (TurnLeftNode) s;
            return new TurnLeftNode(optimizeExpr(n.getExpr(), env));
        }
        if (s instanceof SetPosNode) {
            SetPosNode n = (SetPosNode) s;
            return new SetPosNode(optimizeExpr(n.getX(), env), optimizeExpr(n.getY(), env), n.isBracketed());
        }
        if (s instanceof SetHeadingNode) {
            SetHeadingNode n = (SetHeadingNode) s;
            return new SetHeadingNode(optimizeExpr(n.getExpr(), env));
        }
        if (s instanceof SetXNode) {
            SetXNode n = (SetXNode) s;
            return new SetXNode(optimizeExpr(n.getExpr(), env));
        }
        if (s instanceof SetYNode) {
            SetYNode n = (SetYNode) s;
            return new SetYNode(optimizeExpr(n.getExpr(), env));
        }
        if (s instanceof WaitNode) {
            WaitNode n = (WaitNode) s;
            return new WaitNode(optimizeExpr(n.getExpr(), env));
        }

        return s;
    }

    private static StmtNode normalizeBlock(StmtNode s) {
        if (s == null) return null;
        if (s instanceof ExecBlockNode) {
            List<StmtNode> body = ((ExecBlockNode) s).getBody();
            if (body.isEmpty()) return null;
            if (body.size() == 1) return body.get(0);
        }
        return s;
    }

    private static StmtNode optimizeBlockAsExec(List<StmtNode> body, Env env) {
        if (body == null || body.isEmpty()) return null;
        List<StmtNode> opt = optimizeStmtList(body, env);
        if (opt.isEmpty()) return null;
        return new ExecBlockNode(opt);
    }

    // -------------------- Expressions --------------------

    private static ExprNode optimizeExpr(ExprNode e, Env env) {
        if (e == null) return null;

        if (e instanceof ConstNode) return e;

        if (e instanceof VarRefNode) {
            String name = ((VarRefNode) e).getName();
            Object val = env.get(name);
            return val != null ? new ConstNode(val) : e;
        }

        // Not
        if (e instanceof LogicalNotNode) {
            LogicalNotNode n = (LogicalNotNode) e;
            ExprNode v = optimizeExpr(n.getExpr(), env);
            Object c = constValue(v);
            if (c instanceof Boolean) return new ConstNode(!((Boolean) c));
            return new LogicalNotNode(v);
        }

        // And
        if (e instanceof LogicalAndNode) {
            LogicalAndNode n = (LogicalAndNode) e;
            ExprNode l = optimizeExpr(n.getLeft(), env);
            ExprNode r = optimizeExpr(n.getRight(), env);
            Object lc = constValue(l);
            Object rc = constValue(r);
            if (lc instanceof Boolean) {
                return (Boolean) lc ? r : new ConstNode(false);
            }
            if (rc instanceof Boolean) {
                return (Boolean) rc ? l : new ConstNode(false);
            }
            return new LogicalAndNode(l, r);
        }

        // Or
        if (e instanceof LogicalOrNode) {
            LogicalOrNode n = (LogicalOrNode) e;
            ExprNode l = optimizeExpr(n.getLeft(), env);
            ExprNode r = optimizeExpr(n.getRight(), env);
            Object lc = constValue(l);
            Object rc = constValue(r);
            if (lc instanceof Boolean) {
                return (Boolean) lc ? new ConstNode(true) : r;
            }
            if (rc instanceof Boolean) {
                return (Boolean) rc ? new ConstNode(true) : l;
            }
            return new LogicalOrNode(l, r);
        }

        // Addition
        if (e instanceof AdditionNode) {
            AdditionNode n = (AdditionNode) e;
            ExprNode l = optimizeExpr(n.getLeft(), env);
            ExprNode r = optimizeExpr(n.getRight(), env);
            Object lc = constValue(l), rc = constValue(r);
            if (isZero(lc)) return r;
            if (isZero(rc)) return l;
            if (lc instanceof Integer && rc instanceof Integer) {
                return new ConstNode(((Integer) lc) + ((Integer) rc));
            }
            return new AdditionNode(l, r);
        }

        // Subtraction
        if (e instanceof SubtractionNode) {
            SubtractionNode n = (SubtractionNode) e;
            ExprNode l = optimizeExpr(getBinaryLeft(n), env);
            ExprNode r = optimizeExpr(getBinaryRight(n), env);
            Object lc = constValue(l), rc = constValue(r);
            if (isZero(rc)) return l;
            if (lc instanceof Integer && rc instanceof Integer) {
                return new ConstNode(((Integer) lc) - ((Integer) rc));
            }
            return new SubtractionNode(l, r);
        }

        // Multiplication
        if (e instanceof MultiplicationNode) {
            MultiplicationNode n = (MultiplicationNode) e;
            ExprNode l = optimizeExpr(getBinaryLeft(n), env);
            ExprNode r = optimizeExpr(getBinaryRight(n), env);
            Object lc = constValue(l), rc = constValue(r);
            if (isZero(lc) || isZero(rc)) return new ConstNode(0);
            if (isOne(lc)) return r;
            if (isOne(rc)) return l;
            if (lc instanceof Integer && rc instanceof Integer) {
                return new ConstNode(((Integer) lc) * ((Integer) rc));
            }
            return new MultiplicationNode(l, r);
        }

        // Division
        if (e instanceof DivisionNode) {
            DivisionNode n = (DivisionNode) e;
            ExprNode l = optimizeExpr(getBinaryLeft(n), env);
            ExprNode r = optimizeExpr(getBinaryRight(n), env);
            Object lc = constValue(l), rc = constValue(r);
            if (isZero(lc)) return new ConstNode(0);
            if (isOne(rc)) return l;
            if (lc instanceof Integer && rc instanceof Integer) {
                int den = (Integer) rc;
                if (den != 0) return new ConstNode(((Integer) lc) / den);
            }
            return new DivisionNode(l, r);
        }

        // Exponentiation
        if (e instanceof ExponentiationNode) {
            ExponentiationNode n = (ExponentiationNode) e;
            ExprNode l = optimizeExpr(getBinaryLeft(n), env);
            ExprNode r = optimizeExpr(getBinaryRight(n), env);
            Object lc = constValue(l), rc = constValue(r);
            if (isOne(rc)) return l;
            if (isZero(rc)) return new ConstNode(1);
            if (lc instanceof Integer && rc instanceof Integer) {
                int base = (Integer) lc, exp = (Integer) rc;
                if (exp >= 0) return new ConstNode(ipow(base, exp));
            }
            return new ExponentiationNode(l, r);
        }

        // Comparisons
        if (e instanceof EqualsNode) {
            EqualsNode n = (EqualsNode) e;
            ExprNode l = optimizeExpr(getBinaryLeft(n), env);
            ExprNode r = optimizeExpr(getBinaryRight(n), env);
            Object lc = constValue(l), rc = constValue(r);
            if (lc != null && rc != null) {
                return new ConstNode(Objects.equals(lc, rc));
            }
            return new EqualsNode(l, r);
        }

        if (e instanceof NotEqualsNode) {
            NotEqualsNode n = (NotEqualsNode) e;
            ExprNode l = optimizeExpr(getBinaryLeft(n), env);
            ExprNode r = optimizeExpr(getBinaryRight(n), env);
            Object lc = constValue(l), rc = constValue(r);
            if (lc != null && rc != null) {
                return new ConstNode(!Objects.equals(lc, rc));
            }
            return new NotEqualsNode(l, r);
        }

        if (e instanceof GreaterThanNode) {
            GreaterThanNode n = (GreaterThanNode) e;
            ExprNode l = optimizeExpr(getBinaryLeft(n), env);
            ExprNode r = optimizeExpr(getBinaryRight(n), env);
            Object lc = constValue(l), rc = constValue(r);
            if (lc instanceof Integer && rc instanceof Integer) {
                return new ConstNode(((Integer) lc) > ((Integer) rc));
            }
            return new GreaterThanNode(l, r);
        }

        if (e instanceof LessThanNode) {
            LessThanNode n = (LessThanNode) e;
            ExprNode l = optimizeExpr(getBinaryLeft(n), env);
            ExprNode r = optimizeExpr(getBinaryRight(n), env);
            Object lc = constValue(l), rc = constValue(r);
            if (lc instanceof Integer && rc instanceof Integer) {
                return new ConstNode(((Integer) lc) < ((Integer) rc));
            }
            return new LessThanNode(l, r);
        }

        if (e instanceof GreaterEqualNode) {
            GreaterEqualNode n = (GreaterEqualNode) e;
            ExprNode l = optimizeExpr(getBinaryLeft(n), env);
            ExprNode r = optimizeExpr(getBinaryRight(n), env);
            Object lc = constValue(l), rc = constValue(r);
            if (lc instanceof Integer && rc instanceof Integer) {
                return new ConstNode(((Integer) lc) >= ((Integer) rc));
            }
            return new GreaterEqualNode(l, r);
        }

        if (e instanceof LessEqualNode) {
            LessEqualNode n = (LessEqualNode) e;
            ExprNode l = optimizeExpr(getBinaryLeft(n), env);
            ExprNode r = optimizeExpr(getBinaryRight(n), env);
            Object lc = constValue(l), rc = constValue(r);
            if (lc instanceof Integer && rc instanceof Integer) {
                return new ConstNode(((Integer) lc) <= ((Integer) rc));
            }
            return new LessEqualNode(l, r);
        }

        if (e instanceof EqualsFuncNode) {
            EqualsFuncNode n = (EqualsFuncNode) e;
            ExprNode l = optimizeExpr(getBinaryLeft(n), env);
            ExprNode r = optimizeExpr(getBinaryRight(n), env);
            Object lc = constValue(l), rc = constValue(r);
            if (lc != null && rc != null) {
                return new ConstNode(Objects.equals(lc, rc));
            }
            return new EqualsFuncNode(l, r);
        }

        // N-ary functions
        if (e instanceof SumNode) {
            SumNode n = (SumNode) e;
            ExprNode first = optimizeExpr(n.getFirst(), env);
            List<ExprNode> rest = optimizeExprList(n.getRest(), env);
            List<ExprNode> all = new ArrayList<ExprNode>();
            all.add(first);
            all.addAll(rest);
            int acc = 0;
            List<ExprNode> terms = new ArrayList<ExprNode>();
            for (ExprNode t : all) {
                Object c = constValue(t);
                if (c instanceof Integer) acc += (Integer) c;
                else terms.add(t);
            }
            if (terms.isEmpty()) return new ConstNode(acc);
            if (acc != 0) terms.add(0, new ConstNode(acc));
            if (terms.size() == 1) return terms.get(0);
            return new SumNode(terms.get(0), terms.subList(1, terms.size()));
        }

        if (e instanceof DifferenceNode) {
            DifferenceNode n = (DifferenceNode) e;
            ExprNode first = optimizeExpr(n.getFirst(), env);
            List<ExprNode> rest = optimizeExprList(n.getRest(), env);
            Object acc = constValue(first);
            boolean allConst = acc instanceof Integer;
            int v = allConst ? (Integer) acc : 0;
            if (allConst) {
                for (ExprNode t : rest) {
                    Object c = constValue(t);
                    if (!(c instanceof Integer)) { allConst = false; break; }
                    v -= (Integer) c;
                }
            }
            if (allConst) return new ConstNode(v);
            return new DifferenceNode(first, rest);
        }

        if (e instanceof ProductNode) {
            ProductNode n = (ProductNode) e;
            ExprNode first = optimizeExpr(n.getFirst(), env);
            List<ExprNode> rest = optimizeExprList(n.getRest(), env);
            List<ExprNode> all = new ArrayList<ExprNode>();
            all.add(first);
            all.addAll(rest);
            int acc = 1;
            List<ExprNode> terms = new ArrayList<ExprNode>();
            for (ExprNode t : all) {
                Object c = constValue(t);
                if (c instanceof Integer) {
                    int val = (Integer) c;
                    if (val == 0) return new ConstNode(0);
                    acc *= val;
                } else {
                    terms.add(t);
                }
            }
            if (terms.isEmpty()) return new ConstNode(acc);
            if (acc != 1) terms.add(0, new ConstNode(acc));
            if (terms.size() == 1) return terms.get(0);
            return new ProductNode(terms.get(0), terms.subList(1, terms.size()));
        }

        // Random
        if (e instanceof RandomNode) {
            RandomNode n = (RandomNode) e;
            return new RandomNode(optimizeExpr(n.getExpr(), env));
        }

        return e;
    }

    private static List<ExprNode> optimizeExprList(List<ExprNode> list, Env env) {
        List<ExprNode> out = new ArrayList<ExprNode>(list.size());
        for (ExprNode e : list) out.add(optimizeExpr(e, env));
        return out;
    }

    // -------------------- Helpers para nodos binarios --------------------
    
    private static ExprNode getBinaryLeft(ExprNode e) {
        try {
            java.lang.reflect.Field f = e.getClass().getDeclaredField("left");
            f.setAccessible(true);
            return (ExprNode) f.get(e);
        } catch (Exception ex) {
            return null;
        }
    }
    
    private static ExprNode getBinaryRight(ExprNode e) {
        try {
            java.lang.reflect.Field f = e.getClass().getDeclaredField("right");
            f.setAccessible(true);
            return (ExprNode) f.get(e);
        } catch (Exception ex) {
            return null;
        }
    }

    private static boolean isZero(Object c) { return c instanceof Integer && ((Integer) c) == 0; }
    private static boolean isOne(Object c)  { return c instanceof Integer && ((Integer) c) == 1; }

    private static int ipow(int base, int exp) {
        int res = 1;
        int b = base, e = exp;
        while (e > 0) {
            if ((e & 1) == 1) res *= b;
            b *= b;
            e >>= 1;
        }
        return res;
    }

    private static Object constValue(ExprNode e) {
        if (e instanceof ConstNode) return ((ConstNode) e).getValue();
        return null;
    }

    // -------------------- Env --------------------

    private static final class Env {
        private final Map<String, Object> map = new HashMap<String, Object>();

        Env fork() {
            Env e = new Env();
            e.map.putAll(this.map);
            return e;
        }

        void put(String name, Object value) { map.put(name, value); }
        void kill(String name) { map.remove(name); }
        Object get(String name) { return map.get(name); }
        void clear() { map.clear(); }
    }
}

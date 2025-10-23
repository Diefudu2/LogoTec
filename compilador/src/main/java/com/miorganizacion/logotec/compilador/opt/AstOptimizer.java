package com.miorganizacion.logotec.compilador.opt;

import java.util.*;
import com.miorganizacion.logotec.compilador.ast.*;

/**
 * AST optimizer: constant folding, simple algebraic simplifications,
 * dead-branch elimination, light constant propagation, and basic block flattening.
 *
 * Usage: ProgramNode optimized = AstOptimizer.optimize(programNode);
 */
public final class AstOptimizer {

    private AstOptimizer() {}

    public static ProgramNode optimize(ProgramNode root) {
        if (root == null) return null;

        // Optimize procedures
        List<ProcDeclNode> optDecls = new ArrayList<>();
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
        for (String p : pd.getParams()) {
            env.kill(p);
        }
        List<StmtNode> body = optimizeStmtList(pd.getBody(), env);
        return new ProcDeclNode(pd.getName(), pd.getParams(), body);
    }

    // -------------------- Statements --------------------

    private static List<StmtNode> optimizeStmtList(List<StmtNode> stmts, Env env) {
        List<StmtNode> out = new ArrayList<>();
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

        // Repeat with fixed count (optional small unroll)
        if (s instanceof RepeatNode) {
            RepeatNode rp = (RepeatNode) s;
            ExprNode times = optimizeExpr(rp.getTimes(), env);
            List<StmtNode> body = optimizeStmtList(rp.getBody(), env.fork()); // isolate body effects during optimization
            Object c = constValue(times);
            if (c instanceof Integer) {
                int n = (Integer) c;
                if (n <= 0) return null;
                final int UNROLL_LIMIT = 16;
                if (n <= UNROLL_LIMIT) {
                    // Unroll
                    List<StmtNode> unrolled = new ArrayList<>(n * Math.max(1, body.size()));
                    for (int i = 0; i < n; i++) {
                        // Body effects do apply sequentially at runtime; conservatively kill env on loop
                        // For soundness just re-add same nodes
                        unrolled.addAll(body);
                    }
                    // Loop effects break const-prop; clear env
                    env.clear();
                    return new ExecBlockNode(unrolled);
                }
            }
            // Keep loop with optimized parts; clear env due to loop
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
                    // Then branch only; applies env effects
                    return normalizeBlock(optimizeBlockAsExec(ifn.getThenBody(), env));
                } else {
                    return normalizeBlock(optimizeBlockAsExec(ifn.getElseBody(), env));
                }
            } else {
                // Unknown condition: optimize branches but do not trust changes, merge conservatively (kill all)
                List<StmtNode> thenOpt = optimizeStmtList(ifn.getThenBody(), env.fork());
                List<StmtNode> elseOpt = ifn.getElseBody() == null ? null : optimizeStmtList(ifn.getElseBody(), env.fork());
                env.clear(); // conservatively drop knowledge after a conditional
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
                return null; // never executes
            }
            env.clear(); // loop unknown iterations
            return new WhileNode(cond, body);
        }

        // DoWhile
        if (s instanceof DoWhileNode) {
            DoWhileNode dwn = (DoWhileNode) s;
            ExprNode cond = optimizeExpr(dwn.getCondition(), env);
            List<StmtNode> body = optimizeStmtList(dwn.getBody(), env.fork());
            Object c = constValue(cond);
            if (c instanceof Boolean && !(Boolean) c) {
                // Executes once
                env.clear();
                return new ExecBlockNode(body);
            }
            env.clear();
            return new DoWhileNode(body, cond);
        }

        // Until (pre-check, 0+ iterations until cond true)
        if (s instanceof UntilNode) {
            UntilNode un = (UntilNode) s;
            ExprNode cond = optimizeExpr(un.getCondition(), env);
            List<StmtNode> body = optimizeStmtList(un.getBody(), env.fork());
            Object c = constValue(cond);
            if (c instanceof Boolean && (Boolean) c) {
                // immediately terminates
                return null;
            }
            env.clear();
            return new UntilNode(cond, body);
        }

        // DoUntil (1+ iterations until cond true)
        if (s instanceof DoUntilNode) {
            DoUntilNode dun = (DoUntilNode) s;
            ExprNode cond = optimizeExpr(dun.getCondition(), env);
            List<StmtNode> body = optimizeStmtList(dun.getBody(), env.fork());
            Object c = constValue(cond);
            if (c instanceof Boolean && (Boolean) c) {
                // execute once
                env.clear();
                return new ExecBlockNode(body);
            }
            env.clear();
            return new DoUntilNode(body, cond);
        }

        // Procedure call: optimize args, then kill env (may mutate globals)
        if (s instanceof ProcCallNode) {
            ProcCallNode pc = (ProcCallNode) s;
            List<ExprNode> args = new ArrayList<>(pc.getArgs().size());
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

        // Commands without expressions (OT/BL/SB/etc.)
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

        // Comparisons
        if (e instanceof EqualsNode || e instanceof EqualsFuncNode
            || e instanceof NotEqualsNode
            || e instanceof GreaterThanNode || e instanceof LessThanNode
            || e instanceof GreaterEqualNode || e instanceof LessEqualNode) {
            ExprNode l = binaryLeft(e, env);
            ExprNode r = binaryRight(e, env);
            Object lc = constValue(l), rc = constValue(r);
            if (lc != null && rc != null) {
                Boolean res = evalCompare(e, lc, rc);
                if (res != null) return new ConstNode(res);
            }
            return rebuildBinary(e, l, r);
        }

        // Arithmetic: +, -, *, /, ^

        if (e instanceof AdditionNode || e instanceof SubtractionNode
            || e instanceof MultiplicationNode || e instanceof DivisionNode
            || e instanceof ExponentiationNode) {
            ExprNode l = binaryLeft(e, env);
            ExprNode r = binaryRight(e, env);
            Object lc = constValue(l), rc = constValue(r);

            if (e instanceof AdditionNode) {
                if (isZero(lc)) return r;
                if (isZero(rc)) return l;
                if (lc instanceof Integer && rc instanceof Integer) return new ConstNode(((Integer) lc) + ((Integer) rc));
            } else if (e instanceof SubtractionNode) {
                if (isZero(rc)) return l;
                if (lc instanceof Integer && rc instanceof Integer) return new ConstNode(((Integer) lc) - ((Integer) rc));
            } else if (e instanceof MultiplicationNode) {
                if (isZero(lc) || isZero(rc)) return new ConstNode(0);
                if (isOne(lc)) return r;
                if (isOne(rc)) return l;
                if (lc instanceof Integer && rc instanceof Integer) return new ConstNode(((Integer) lc) * ((Integer) rc));
            } else if (e instanceof DivisionNode) {
                if (isZero(lc)) return new ConstNode(0);
                if (isOne(rc)) return l;
                if (lc instanceof Integer && rc instanceof Integer) {
                    int den = (Integer) rc;
                    if (den != 0) return new ConstNode(((Integer) lc) / den);
                }
            } else { // exponent
                if (isOne(rc)) return l;
                if (isZero(rc)) return new ConstNode(1);
                if (lc instanceof Integer && rc instanceof Integer) {
                    int base = (Integer) lc, exp = (Integer) rc;
                    if (exp >= 0) return new ConstNode(ipow(base, exp));
                }
            }
            return rebuildBinary(e, l, r);
        }

        // N-ary functions
        if (e instanceof SumNode) {
            SumNode n = (SumNode) e;
            ExprNode first = optimizeExpr(n.getFirst(), env);
            List<ExprNode> rest = optimizeExprList(n.getRest(), env);
            List<ExprNode> all = new ArrayList<>();
            all.add(first);
            all.addAll(rest);
            int acc = 0;
            List<ExprNode> terms = new ArrayList<>();
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
            // If all constants, fold
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
            List<ExprNode> all = new ArrayList<>();
            all.add(first);
            all.addAll(rest);
            int acc = 1;
            List<ExprNode> terms = new ArrayList<>();
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

        // Fallback: unknown node types, try generic binary rebuild
        return e;
    }

    private static List<ExprNode> optimizeExprList(List<ExprNode> list, Env env) {
        List<ExprNode> out = new ArrayList<>(list.size());
        for (ExprNode e : list) out.add(optimizeExpr(e, env));
        return out;
    }

    private static ExprNode binaryLeft(ExprNode e, Env env) {
        if (e instanceof BinaryExprNode) {
            BinaryExprNode b = (BinaryExprNode) e;
            return optimizeExpr(b.getLeft(), env);
        }
        // Fallback per type
        if (e instanceof AdditionNode) return optimizeExpr(((AdditionNode) e).getLeft(), env);
        if (e instanceof SubtractionNode) return optimizeExpr(((SubtractionNode) e).getLeft(), env);
        if (e instanceof MultiplicationNode) return optimizeExpr(((MultiplicationNode) e).getLeft(), env);
        if (e instanceof DivisionNode) return optimizeExpr(((DivisionNode) e).getLeft(), env);
        if (e instanceof ExponentiationNode) return optimizeExpr(((ExponentiationNode) e).getLeft(), env);
        if (e instanceof EqualsNode) return optimizeExpr(((EqualsNode) e).getLeft(), env);
        if (e instanceof NotEqualsNode) return optimizeExpr(((NotEqualsNode) e).getLeft(), env);
        if (e instanceof GreaterThanNode) return optimizeExpr(((GreaterThanNode) e).getLeft(), env);
        if (e instanceof LessThanNode) return optimizeExpr(((LessThanNode) e).getLeft(), env);
        if (e instanceof GreaterEqualNode) return optimizeExpr(((GreaterEqualNode) e).getLeft(), env);
        if (e instanceof LessEqualNode) return optimizeExpr(((LessEqualNode) e).getLeft(), env);
        if (e instanceof EqualsFuncNode) return optimizeExpr(((EqualsFuncNode) e).getLeft(), env);
        return null;
    }

    private static ExprNode binaryRight(ExprNode e, Env env) {
        if (e instanceof BinaryExprNode) {
            BinaryExprNode b = (BinaryExprNode) e;
            return optimizeExpr(b.getRight(), env);
        }
        if (e instanceof AdditionNode) return optimizeExpr(((AdditionNode) e).getRight(), env);
        if (e instanceof SubtractionNode) return optimizeExpr(((SubtractionNode) e).getRight(), env);
        if (e instanceof MultiplicationNode) return optimizeExpr(((MultiplicationNode) e).getRight(), env);
        if (e instanceof DivisionNode) return optimizeExpr(((DivisionNode) e).getRight(), env);
        if (e instanceof ExponentiationNode) return optimizeExpr(((ExponentiationNode) e).getRight(), env);
        if (e instanceof EqualsNode) return optimizeExpr(((EqualsNode) e).getRight(), env);
        if (e instanceof NotEqualsNode) return optimizeExpr(((NotEqualsNode) e).getRight(), env);
        if (e instanceof GreaterThanNode) return optimizeExpr(((GreaterThanNode) e).getRight(), env);
        if (e instanceof LessThanNode) return optimizeExpr(((LessThanNode) e).getRight(), env);
        if (e instanceof GreaterEqualNode) return optimizeExpr(((GreaterEqualNode) e).getRight(), env);
        if (e instanceof LessEqualNode) return optimizeExpr(((LessEqualNode) e).getRight(), env);
        if (e instanceof EqualsFuncNode) return optimizeExpr(((EqualsFuncNode) e).getRight(), env);
        return null;
    }

    private static ExprNode rebuildBinary(ExprNode proto, ExprNode l, ExprNode r) {
        if (proto instanceof AdditionNode) return new AdditionNode(l, r);
        if (proto instanceof SubtractionNode) return new SubtractionNode(l, r);
        if (proto instanceof MultiplicationNode) return new MultiplicationNode(l, r);
        if (proto instanceof DivisionNode) return new DivisionNode(l, r);
        if (proto instanceof ExponentiationNode) return new ExponentiationNode(l, r);
        if (proto instanceof EqualsNode) return new EqualsNode(l, r);
        if (proto instanceof NotEqualsNode) return new NotEqualsNode(l, r);
        if (proto instanceof GreaterThanNode) return new GreaterThanNode(l, r);
        if (proto instanceof LessThanNode) return new LessThanNode(l, r);
        if (proto instanceof GreaterEqualNode) return new GreaterEqualNode(l, r);
        if (proto instanceof LessEqualNode) return new LessEqualNode(l, r);
        if (proto instanceof EqualsFuncNode) return new EqualsFuncNode(l, r);
        if (proto instanceof LogicalAndNode) return new LogicalAndNode(l, r);
        if (proto instanceof LogicalOrNode) return new LogicalOrNode(l, r);
        return proto;
    }

    private static Boolean evalCompare(ExprNode kind, Object l, Object r) {
        // Only ints, booleans, strings supported
        if (kind instanceof EqualsNode || kind instanceof EqualsFuncNode) {
            return Objects.equals(l, r);
        }
        if (kind instanceof NotEqualsNode) {
            return !Objects.equals(l, r);
        }
        if (l instanceof Integer && r instanceof Integer) {
            int a = (Integer) l, b = (Integer) r;
            if (kind instanceof GreaterThanNode) return a > b;
            if (kind instanceof LessThanNode) return a < b;
            if (kind instanceof GreaterEqualNode) return a >= b;
            if (kind instanceof LessEqualNode) return a <= b;
        }
        return null;
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
        private final Map<String, Object> map = new HashMap<>();

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

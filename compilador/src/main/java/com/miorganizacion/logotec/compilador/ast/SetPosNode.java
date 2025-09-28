package com.miorganizacion.logotec.compilador.ast;
import java.util.Map;

/* Posición */
public class SetPosNode implements StmtNode {
    private final ExprNode x, y;
    private final boolean relative;
    public SetPosNode(ExprNode x, ExprNode y, boolean relative) {
        this.x = x; this.y = y; this.relative = relative;
    }
    @Override public Object execute(Map<String,Object> st) {
        Object xv = x.execute(st), yv = y.execute(st);
        if (!(xv instanceof Number) || !(yv instanceof Number))
            throw new RuntimeException("Posición requiere números");
        System.out.println("SetPos " + xv + "," + yv + " rel=" + relative);
        return null;
    }
}
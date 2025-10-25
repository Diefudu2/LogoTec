package com.miorganizacion.logotec.compilador.ast;
import java.util.Map;

public class SetXNode implements StmtNode {
    private final ExprNode x;
    public SetXNode(ExprNode x) { this.x = x; }
    public ExprNode getExpr() { return x; }
    @Override public Object execute(Map<String,Object> st) {
        Object val = x.execute(st);
        if (!(val instanceof Number)) throw new RuntimeException("SetX requiere número");
        System.out.println("SetX " + val);
        return null;
    }
}

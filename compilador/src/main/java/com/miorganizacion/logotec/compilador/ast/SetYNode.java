package com.miorganizacion.logotec.compilador.ast;
import java.util.Map;

public class SetYNode implements StmtNode {
    private final ExprNode y;
    public SetYNode(ExprNode y) { this.y = y; }
    @Override public Object execute(Map<String,Object> st) {
        Object val = y.execute(st);
        if (!(val instanceof Number)) throw new RuntimeException("SetY requiere número");
        System.out.println("SetY " + val);
        return null;
    }
}

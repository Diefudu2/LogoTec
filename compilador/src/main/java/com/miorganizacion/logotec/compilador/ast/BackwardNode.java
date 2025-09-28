package com.miorganizacion.logotec.compilador.ast;
import java.util.Map;

/* Retrocede */
public class BackwardNode implements StmtNode {
    private final ExprNode distance;
    public BackwardNode(ExprNode distance) { this.distance = distance; }
    @Override public Object execute(Map<String,Object> st) {
        Object val = distance.execute(st);
        if (!(val instanceof Number)) throw new RuntimeException("Retrocede requiere número");
        double d = ((Number) val).doubleValue();
        System.out.println("Retrocede " + d);
        return null;
    }
}

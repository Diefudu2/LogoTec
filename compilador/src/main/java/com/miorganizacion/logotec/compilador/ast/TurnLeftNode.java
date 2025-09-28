package com.miorganizacion.logotec.compilador.ast;
import java.util.Map;

/* Gira izquierda */
public class TurnLeftNode implements StmtNode {
    private final ExprNode angle;
    public TurnLeftNode(ExprNode angle) { this.angle = angle; }
    @Override public Object execute(Map<String,Object> st) {
        Object val = angle.execute(st);
        if (!(val instanceof Number)) throw new RuntimeException("Giraizquierda requiere número");
        double a = ((Number) val).doubleValue();
        System.out.println("Gira izquierda " + a);
        return null;
    }
}

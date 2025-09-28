package com.miorganizacion.logotec.compilador.ast;
import java.util.Map;

/* Gira derecha */
public class TurnRightNode implements StmtNode {
    private final ExprNode angle;
    public TurnRightNode(ExprNode angle) { this.angle = angle; }
    @Override public Object execute(Map<String,Object> st) {
        Object val = angle.execute(st);
        if (!(val instanceof Number)) throw new RuntimeException("Giraderecha requiere número");
        double a = ((Number) val).doubleValue();
        System.out.println("Gira derecha " + a);
        return null;
    }
}

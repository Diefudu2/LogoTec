package com.miorganizacion.logotec.compilador.ast;
import java.util.Map;

public class ForwardNode implements StmtNode {
    private final ExprNode distance;

    public ForwardNode(ExprNode distance) {
        this.distance = distance;
    }

    @Override
    public Object execute(Map<String,Object> st) {
        Object val = distance.execute(st);
        if (!(val instanceof Number)) {
            throw new RuntimeException("Avanza requiere un número");
        }
        double d = ((Number) val).doubleValue();
        // lógica de mover la tortuga
        System.out.println("Avanza " + d);
        return null;
    }
}

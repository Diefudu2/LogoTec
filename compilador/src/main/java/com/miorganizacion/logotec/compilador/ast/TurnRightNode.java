package com.miorganizacion.logotec.compilador.ast;

import com.miorganizacion.logotec.simulador.TurtleContext;
import java.util.Map;

/* Gira derecha */
public class TurnRightNode implements StmtNode {
    private final ExprNode angle;

    public TurnRightNode(ExprNode angle) {
        this.angle = angle;
    }

    public ExprNode getExpr() {
        return angle;
    }

    @Override
    public Object execute(Map<String, Object> st) {
        Object val = angle.execute(st);
        if (!(val instanceof Number)) {
            throw new RuntimeException("Giraderecha requiere número");
        }
        double a = ((Number) val).doubleValue();
        
        // Si hay un TurtleContext en la tabla de símbolos, úsalo
        if (st.containsKey("__turtle__")) {
            TurtleContext turtle = (TurtleContext) st.get("__turtle__");
            turtle.girarDerecha(a);
        } else {
            System.out.println("Gira derecha " + a);
        }
        return null;
    }
}

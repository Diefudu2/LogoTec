package com.miorganizacion.logotec.compilador.ast;
import com.miorganizacion.logotec.simulador.TurtleContext;
import java.util.Map;

public class ForwardNode implements StmtNode {
    private final ExprNode distance;

    public ForwardNode(ExprNode distance) {
        this.distance = distance;
    }
    
    public ExprNode getExpr() {
        return distance;
    }

    @Override
    public Object execute(Map<String,Object> st) {
        Object val = distance.execute(st);
        if (!(val instanceof Number)) {
            throw new RuntimeException("Avanza requiere un número");
        }
        double d = ((Number) val).doubleValue();
        
        // Si hay un TurtleContext en la tabla de símbolos, úsalo
        if (st.containsKey("__turtle__")) {
            TurtleContext turtle = (TurtleContext) st.get("__turtle__");
            turtle.avanzar(d);
        } else {
            System.out.println("Avanza " + d);
        }
        return null;
    }
}

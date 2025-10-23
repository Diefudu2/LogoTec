package com.miorganizacion.logotec.compilador.ast;
import com.miorganizacion.logotec.simulador.TurtleContext;
import java.util.Map;

/* Retrocede */
public class BackwardNode implements StmtNode {
    private final ExprNode distance;
    public BackwardNode(ExprNode distance) { this.distance = distance; }
    @Override public Object execute(Map<String,Object> st) {
        Object val = distance.execute(st);
        if (!(val instanceof Number)) throw new RuntimeException("Retrocede requiere número");
        double d = ((Number) val).doubleValue();
        
        // Si hay un TurtleContext en la tabla de símbolos, úsalo
        if (st.containsKey("__turtle__")) {
            TurtleContext turtle = (TurtleContext) st.get("__turtle__");
            turtle.retroceder(d);
        } else {
            System.out.println("Retrocede " + d);
        }
        return null;
    }
}

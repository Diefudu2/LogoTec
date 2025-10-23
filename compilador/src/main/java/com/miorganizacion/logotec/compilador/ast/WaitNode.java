package com.miorganizacion.logotec.compilador.ast;
import com.miorganizacion.logotec.simulador.TurtleContext;
import java.util.Map;

public class WaitNode implements StmtNode {
    private final ExprNode time;
    public WaitNode(ExprNode time) { this.time = time; }
    @Override public Object execute(Map<String,Object> st) {
        Object val = time.execute(st);
        if (!(val instanceof Number)) throw new RuntimeException("Espera requiere número");
        double t = ((Number) val).doubleValue();
        
        // Si hay un TurtleContext en la tabla de símbolos, úsalo
        if (st.containsKey("__turtle__")) {
            TurtleContext turtle = (TurtleContext) st.get("__turtle__");
            turtle.esperar(t);
        } else {
            System.out.println("Espera " + t);
        }
        return null;
    }
}

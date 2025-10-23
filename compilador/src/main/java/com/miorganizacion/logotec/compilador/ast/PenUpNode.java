package com.miorganizacion.logotec.compilador.ast;
import com.miorganizacion.logotec.simulador.TurtleContext;
import java.util.Map;

public class PenUpNode implements StmtNode {
    @Override public Object execute(Map<String,Object> st) {
        // Si hay un TurtleContext en la tabla de símbolos, úsalo
        if (st.containsKey("__turtle__")) {
            TurtleContext turtle = (TurtleContext) st.get("__turtle__");
            turtle.subirLapiz();
        } else {
            System.out.println("Sube lápiz");
        }
        return null;
    }
}
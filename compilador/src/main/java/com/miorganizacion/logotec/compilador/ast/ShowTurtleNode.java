package com.miorganizacion.logotec.compilador.ast;
import com.miorganizacion.logotec.simulador.TurtleContext;
import java.util.Map;

public class ShowTurtleNode implements StmtNode {
    @Override
    public Object execute(Map<String, Object> st) {
        if (st.containsKey("__turtle__")) {
            TurtleContext turtle = (TurtleContext) st.get("__turtle__");
            turtle.mostrarTortuga();
        } else {
            System.out.println("Mostrar tortuga");
        }
        return null;
    }
}

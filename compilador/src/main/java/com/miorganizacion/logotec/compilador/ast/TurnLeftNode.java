package com.miorganizacion.logotec.compilador.ast;
import com.miorganizacion.logotec.simulador.TurtleContext;
import java.util.Map;

/* Gira izquierda */
public class TurnLeftNode implements StmtNode {
    private final ExprNode angle;
    public TurnLeftNode(ExprNode angle) { this.angle = angle; }
    public ExprNode getExpr() { return angle; }
    @Override public Object execute(Map<String,Object> st) {
        Object val = angle.execute(st);
        if (!(val instanceof Number)) throw new RuntimeException("Giraizquierda requiere número");
        double a = ((Number) val).doubleValue();
        
        // Si hay un TurtleContext en la tabla de símbolos, úsalo
        if (st.containsKey("__turtle__")) {
            TurtleContext turtle = (TurtleContext) st.get("__turtle__");
            turtle.girarIzquierda(a);
        } else {
            System.out.println("Gira izquierda " + a);
        }
        return null;
    }
}

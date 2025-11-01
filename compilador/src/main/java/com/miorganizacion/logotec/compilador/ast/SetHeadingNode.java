package com.miorganizacion.logotec.compilador.ast;

import com.miorganizacion.logotec.simulador.TurtleContext;
import java.util.Map;

/* Rumbo */
public class SetHeadingNode implements StmtNode {
    private final ExprNode angle;
    public SetHeadingNode(ExprNode angle) { this.angle = angle; }
    public ExprNode getExpr() { return angle; }
    @Override public Object execute(Map<String,Object> st) {
        Object val = angle.execute(st);
        if (!(val instanceof Number)) throw new RuntimeException("Rumbo requiere número");
        
        double angulo = ((Number) val).doubleValue();
        
        if (st.containsKey("__turtle__")) {
            TurtleContext turtle = (TurtleContext) st.get("__turtle__");
            turtle.setRumbo(angulo);
        } else {
            System.out.println("SetHeading(" + angulo + ")");
        }
        return null;
    }
}

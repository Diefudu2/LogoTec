package com.miorganizacion.logotec.compilador.ast;

import com.miorganizacion.logotec.simulador.TurtleContext;
import java.util.Map;

public class SetYNode implements StmtNode {
    private final ExprNode y;
    
    public SetYNode(ExprNode y) { 
        this.y = y; 
    }
    
    public ExprNode getExpr() { return y; }
    
    @Override 
    public Object execute(Map<String,Object> st) {
        Object val = y.execute(st);
        if (!(val instanceof Number)) {
            throw new RuntimeException("PONY requiere un número");
        }
        
        double yVal = ((Number) val).doubleValue();
        
        if (st.containsKey("__turtle__")) {
            TurtleContext turtle = (TurtleContext) st.get("__turtle__");
            turtle.setPosY(yVal);
        } else {
            System.out.println("SetY(" + yVal + ")");
        }
        return null;
    }
}

package com.miorganizacion.logotec.compilador.ast;

import com.miorganizacion.logotec.simulador.TurtleContext;
import java.util.Map;

public class SetXNode implements StmtNode {
    private final ExprNode x;
    
    public SetXNode(ExprNode x) { 
        this.x = x; 
    }
    
    public ExprNode getExpr() { return x; }
    
    @Override 
    public Object execute(Map<String,Object> st) {
        Object val = x.execute(st);
        if (!(val instanceof Number)) {
            throw new RuntimeException("PONX requiere un número");
        }
        
        double xVal = ((Number) val).doubleValue();
        
        if (st.containsKey("__turtle__")) {
            TurtleContext turtle = (TurtleContext) st.get("__turtle__");
            turtle.setPosX(xVal);
        } else {
            System.out.println("SetX(" + xVal + ")");
        }
        return null;
    }
}

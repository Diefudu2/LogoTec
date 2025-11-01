package com.miorganizacion.logotec.compilador.ast;

import com.miorganizacion.logotec.simulador.TurtleContext;
import java.util.Map;

public class SetPosNode implements StmtNode {
    private final ExprNode x, y;
    private final boolean bracketed;
    
    public SetPosNode(ExprNode x, ExprNode y, boolean bracketed) {
        this.x = x; 
        this.y = y; 
        this.bracketed = bracketed;
    }
    
    public ExprNode getX() { return x; }
    public ExprNode getY() { return y; }
    public boolean isRelative() { return bracketed; }
    public boolean isBracketed() { return bracketed; }
    
    @Override 
    public Object execute(Map<String,Object> st) {
        Object xv = x.execute(st);
        Object yv = y.execute(st);
        
        if (!(xv instanceof Number) || !(yv instanceof Number)) {
            throw new RuntimeException("PONPOS requiere dos números para X e Y");
        }
        
        double xVal = ((Number) xv).doubleValue();
        double yVal = ((Number) yv).doubleValue();
        
        if (st.containsKey("__turtle__")) {
            TurtleContext turtle = (TurtleContext) st.get("__turtle__");
            turtle.setPosicion(xVal, yVal);
        } else {
            System.out.println("SetPos(" + xVal + ", " + yVal + ")");
        }
        return null;
    }
}
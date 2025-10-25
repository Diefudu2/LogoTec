package com.miorganizacion.logotec.compilador.ast;
import com.miorganizacion.logotec.simulador.TurtleContext;
import java.util.Map;

public class SetColorNode implements StmtNode {
    private final ExprNode r, g, b;
    
    public SetColorNode(ExprNode r, ExprNode g, ExprNode b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }
    
    public ExprNode getR() { return r; }
    public ExprNode getG() { return g; }
    public ExprNode getB() { return b; }
    
    @Override
    public Object execute(Map<String, Object> st) {
        Object rv = r.execute(st);
        Object gv = g.execute(st);
        Object bv = b.execute(st);
        
        if (!(rv instanceof Number) || !(gv instanceof Number) || !(bv instanceof Number)) {
            throw new RuntimeException("SetColor requiere tres números RGB");
        }
        
        if (st.containsKey("__turtle__")) {
            TurtleContext turtle = (TurtleContext) st.get("__turtle__");
            // turtle.setColor(...); // implementar en TurtleContext
        } else {
            System.out.println("PonColorLapiz [" + rv + "," + gv + "," + bv + "]");
        }
        return null;
    }
}

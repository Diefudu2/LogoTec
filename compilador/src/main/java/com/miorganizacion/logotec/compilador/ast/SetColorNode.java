package com.miorganizacion.logotec.compilador.ast;

import com.miorganizacion.logotec.simulador.TurtleContext;
import java.util.Map;

/**
 * Nodo para PONCOLORLAPIZ [r g b] o PONCL [r g b]
 * 
 * NOTA: Solo se permiten 3 colores:
 * - Negro: [0 0 0]
 * - Azul:  [0 0 255]
 * - Rojo:  [255 0 0]
 */
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
            throw new RuntimeException("PONCOLORLAPIZ requiere tres números RGB");
        }
        
        int rVal = ((Number) rv).intValue();
        int gVal = ((Number) gv).intValue();
        int bVal = ((Number) bv).intValue();
        
        // Validar que sea uno de los 3 colores permitidos
        boolean esNegro = (rVal == 0 && gVal == 0 && bVal == 0);
        boolean esAzul = (rVal == 0 && gVal == 0 && bVal == 255);
        boolean esRojo = (rVal == 255 && gVal == 0 && bVal == 0);
        
        if (!esNegro && !esAzul && !esRojo) {
            throw new RuntimeException(
                "Color RGB no permitido: [" + rVal + " " + gVal + " " + bVal + "]. " +
                "Solo se permiten: Negro [0 0 0], Azul [0 0 255], Rojo [255 0 0]"
            );
        }
        
        if (st.containsKey("__turtle__")) {
            TurtleContext turtle = (TurtleContext) st.get("__turtle__");
            turtle.setColor(rVal, gVal, bVal);
        } else {
            String nombreColor = esNegro ? "Negro" : (esAzul ? "Azul" : "Rojo");
            System.out.println("PonColorLapiz " + nombreColor + " RGB(" + rVal + ", " + gVal + ", " + bVal + ")");
        }
        return null;
    }
    
    @Override
    public String toString() {
        return "SetColor(" + r + ", " + g + ", " + b + ")";
    }
}

package com.miorganizacion.logotec.compilador.ast;

import com.miorganizacion.logotec.simulador.TurtleContext;
import java.util.Map;
import java.util.Arrays;
import java.util.List;

/**
 * Nodo para cambiar el color del lápiz por nombre.
 * Solo permite 3 colores: Negro, Azul, Rojo
 * 
 * Ejemplo: PONCOLORLAPIZ Negro
 */
public class SetPenColorNode implements StmtNode {
    private final String color;
    
    // Colores permitidos
    private static final List<String> COLORES_PERMITIDOS = Arrays.asList("negro", "azul", "rojo");

    public SetPenColorNode(String color) {
        String normalized = color == null ? "" : color.trim().toLowerCase();
        if (!COLORES_PERMITIDOS.contains(normalized)) {
            throw new RuntimeException("Color no permitido: '" + color + "'. Solo se permiten: Negro, Azul, Rojo");
        }
        this.color = normalized;
    }
    
    public String getColor() {
        return color;
    }

    @Override
    public Object execute(Map<String, Object> st) {
        if (st.containsKey("__turtle__")) {
            TurtleContext turtle = (TurtleContext) st.get("__turtle__");
            turtle.setColorPorNombre(color);
        } else {
            System.out.println("PonColorLapiz " + color);
        }
        return null;
    }
    
    @Override
    public String toString() {
        return "SetPenColor(" + color + ")";
    }
}

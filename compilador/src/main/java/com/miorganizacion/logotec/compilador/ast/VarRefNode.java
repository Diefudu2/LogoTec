package com.miorganizacion.logotec.compilador.ast;
import java.util.Map;

public class VarRefNode implements ExprNode {
    private final String name;
    public VarRefNode(String name) { this.name = name; }
    
    @Override 
    public Object execute(Map<String,Object> st) {
        Object value = st.get(name);
        System.out.println("[VarRefNode] Obteniendo '" + name + "' = " + value + " (tipo: " + (value != null ? value.getClass().getSimpleName() : "null") + ")");
        if (value == null) {
            System.err.println("[VarRefNode] WARNING: Variable '" + name + "' no encontrada en: " + st.keySet());
            return 0.0;  // ← CAMBIO: devolver 0.0 en lugar de null para evitar NullPointerException
        }
        return value;
    }
    
    public String getName() {
        return name;
    }
    
    @Override
    public String toString() {
        return "VarRef(" + name + ")";
    }
}

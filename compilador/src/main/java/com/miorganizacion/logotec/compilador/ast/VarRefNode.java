package com.miorganizacion.logotec.compilador.ast;
import java.util.Map;

public class VarRefNode implements ExprNode {
    private final String name;
    public VarRefNode(String name) { this.name = name; }
    
    @Override 
    public Object execute(Map<String,Object> st) {
        Object value = st.get(name);
        System.out.println("[VarRefNode] Obteniendo '" + name + "' = " + value);
        if (value == null) {
            System.err.println("[VarRefNode] WARNING: Variable '" + name + "' no encontrada en: " + st.keySet());
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

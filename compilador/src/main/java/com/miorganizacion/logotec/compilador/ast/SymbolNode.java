package com.miorganizacion.logotec.compilador.ast;
import java.util.Map;

public class SymbolNode implements ASTNode {
    private String name;
    public SymbolNode(String name) { this.name = name; }
    @Override public Object execute(Map<String,Object> st) {
        return st.get(name);
    }
}

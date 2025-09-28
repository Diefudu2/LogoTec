package com.miorganizacion.logotec.compilador.ast;
import java.util.Map;

public class ExportDeclarationNode implements ASTNode {
    private String name;
    private ASTNode value;
    public ExportDeclarationNode(String name, ASTNode value) {
        this.name = name; this.value = value;
    }
    @Override public Object execute(Map<String,Object> st) {
        Object val = value.execute(st);
        st.put(name, val);
        System.out.println("🔗 Exportando " + name + " = " + val);
        return val;
    }
}

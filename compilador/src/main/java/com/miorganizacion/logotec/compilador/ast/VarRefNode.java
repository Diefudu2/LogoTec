package com.miorganizacion.logotec.compilador.ast;
import java.util.Map;

public class VarRefNode implements ExprNode {
    private final String name;
    public VarRefNode(String name) { this.name = name; }
    @Override public Object execute(Map<String,Object> st) { return st.get(name); }
	public String getName() {
		return name;
	}
}

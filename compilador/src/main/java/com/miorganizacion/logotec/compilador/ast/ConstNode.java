package com.miorganizacion.logotec.compilador.ast;
import java.util.Map;

public class ConstNode implements ExprNode {
    private final Object value;

    public ConstNode(Object value) {
        this.value = value;
    }

    @Override
    public Object execute(Map<String, Object> symbolTable) {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
    public Object getValue() {
        return value; // el campo interno donde guardas el literal
    }

}
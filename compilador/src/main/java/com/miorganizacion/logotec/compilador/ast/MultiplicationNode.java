package com.miorganizacion.logotec.compilador.ast;
import java.util.Map;

public class MultiplicationNode implements ExprNode {
    private final ExprNode left, right;

    public MultiplicationNode(ExprNode left, ExprNode right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public Object execute(Map<String,Object> st) {
        Object l = left.execute(st);
        Object r = right.execute(st);

        if (!(l instanceof Number) || !(r instanceof Number)) {
            throw new RuntimeException("Operador '*' requiere operandos numéricos.");
        }

        return ((Number) l).doubleValue() * ((Number) r).doubleValue();
    }

    @Override
    public String toString() {
        return "(" + left + " * " + right + ")";
    }
}

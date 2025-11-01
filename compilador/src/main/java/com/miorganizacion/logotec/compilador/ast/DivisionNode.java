package com.miorganizacion.logotec.compilador.ast;
import java.util.Map;

public class DivisionNode implements ExprNode {
    private final ExprNode left, right;

    public DivisionNode(ExprNode left, ExprNode right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public Object execute(Map<String,Object> st) {
        Object l = left.execute(st);
        Object r = right.execute(st);

        if (!(l instanceof Number) || !(r instanceof Number)) {
            throw new RuntimeException("Operador '/' requiere operandos numéricos.");
        }

        double divisor = ((Number) r).doubleValue();
        if (divisor == 0.0) {
            throw new ArithmeticException("División por cero");
        }

        return ((Number) l).doubleValue() / divisor;
    }

    @Override
    public String toString() {
        return "(" + left + " / " + right + ")";
    }

    public ExprNode getLeft() { return left; }
    public ExprNode getRight() { return right; }
}

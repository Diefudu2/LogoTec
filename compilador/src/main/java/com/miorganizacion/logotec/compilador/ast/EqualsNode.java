package com.miorganizacion.logotec.compilador.ast;
import java.util.Map;

public class EqualsNode implements ExprNode {
    private final ExprNode left, right;

    public EqualsNode(ExprNode left, ExprNode right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public Object execute(Map<String, Object> symbolTable) {
        Object l = left.execute(symbolTable);
        Object r = right.execute(symbolTable);

        if (!(l instanceof Number) || !(r instanceof Number)) {
            throw new RuntimeException("Operador '==' requiere operandos numéricos.");
        }

        return ((Number) l).doubleValue() == ((Number) r).doubleValue();
    }

    @Override
    public String toString() {
        return "(" + left + " == " + right + ")";
    }

    public ExprNode getLeft() { return left; }
    public ExprNode getRight() { return right; }
}

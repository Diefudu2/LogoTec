package com.miorganizacion.logotec.compilador.ast;
import java.util.Map;

public class LogicalOrNode implements ExprNode {
    private final ExprNode left, right;

    public LogicalOrNode(ExprNode left, ExprNode right) {
        this.left = left;
        this.right = right;
    }
    
    public ExprNode getLeft() { return left; }
    public ExprNode getRight() { return right; }

    @Override
    public Object execute(Map<String, Object> symbolTable) {
        Object l = left.execute(symbolTable);
        if (!(l instanceof Boolean))
            throw new RuntimeException("Operador OR requiere booleanos.");

        // Evaluación perezosa
        if ((Boolean) l) return true;

        Object r = right.execute(symbolTable);
        if (!(r instanceof Boolean))
            throw new RuntimeException("Operador OR requiere booleanos.");

        return (Boolean) r;
    }

    @Override
    public String toString() {
        return "(" + left + " OR " + right + ")";
    }
}

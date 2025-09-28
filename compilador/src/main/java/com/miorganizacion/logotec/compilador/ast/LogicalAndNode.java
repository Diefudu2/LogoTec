package com.miorganizacion.logotec.compilador.ast;
import java.util.Map;

public class LogicalAndNode implements ExprNode {
    private final ExprNode left, right;

    public LogicalAndNode(ExprNode left, ExprNode right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public Object execute(Map<String, Object> symbolTable) {
        Object l = left.execute(symbolTable);
        if (!(l instanceof Boolean))
            throw new RuntimeException("Operador AND requiere booleanos.");

        // Evaluación perezosa
        if (!(Boolean) l) return false;

        Object r = right.execute(symbolTable);
        if (!(r instanceof Boolean))
            throw new RuntimeException("Operador AND requiere booleanos.");

        return (Boolean) r;
    }

    @Override
    public String toString() {
        return "(" + left + " AND " + right + ")";
    }
}

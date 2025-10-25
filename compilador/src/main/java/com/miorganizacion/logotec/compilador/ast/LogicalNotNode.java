package com.miorganizacion.logotec.compilador.ast;
import java.util.Map;

public class LogicalNotNode implements ExprNode {
    private final ExprNode expr;

    public LogicalNotNode(ExprNode expr) {
        this.expr = expr;
    }
    
    public ExprNode getExpr() { return expr; }

    @Override
    public Object execute(Map<String, Object> symbolTable) {
        Object val = expr.execute(symbolTable);
        if (!(val instanceof Boolean)) {
            throw new RuntimeException("Operador NOT requiere un booleano.");
        }
        return !(Boolean) val;
    }

    @Override
    public String toString() {
        return "(NOT " + expr + ")";
    }
}

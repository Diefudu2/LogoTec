package com.miorganizacion.logotec.compilador.ast;
import java.util.Map;

public class EqualsFuncNode implements ExprNode {
    private final ExprNode left;
    private final ExprNode right;

    public EqualsFuncNode(ExprNode left, ExprNode right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public Object execute(Map<String, Object> symbolTable) {
        Object l = left.execute(symbolTable);
        Object r = right.execute(symbolTable);
        return (l == null) ? (r == null) : l.equals(r);
    }

    @Override
    public String toString() {
        return "EqualsFunc(" + left + " == " + right + ")";
    }
}

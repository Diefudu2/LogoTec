package com.miorganizacion.logotec.compilador.ast;
import java.util.Map;

public class NotEqualsNode implements ExprNode {
    private ExprNode left, right;
    public NotEqualsNode(ExprNode left, ExprNode right) { this.left = left; this.right = right; }
    @Override public Object execute(Map<String,Object> st) {
        return !new EqualsNode(left,right).execute(st).equals(true);
    }
    public ExprNode getLeft() { return left; }
    public ExprNode getRight() { return right; }
}

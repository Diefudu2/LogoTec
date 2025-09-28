package com.miorganizacion.logotec.compilador.ast;
import java.util.Map;

public class Equals implements ASTNode {
    private ASTNode left, right;

    public Equals(ASTNode left, ASTNode right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public Object execute(Map<String, Object> symbolTable) {
        Object l = left.execute(symbolTable);
        Object r = right.execute(symbolTable);
        return l == null ? r == null : l.equals(r);
    }
}

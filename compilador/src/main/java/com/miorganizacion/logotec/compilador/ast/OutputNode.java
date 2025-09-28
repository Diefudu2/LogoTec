package com.miorganizacion.logotec.compilador.ast;
import java.util.Map;

public class OutputNode implements ASTNode {
    private ASTNode expr;
    public OutputNode(ASTNode expr) { this.expr = expr; }
    @Override public Object execute(Map<String,Object> st) {
        Object val = expr.execute(st);
        System.out.println("📢 " + val);
        return val;
    }
}

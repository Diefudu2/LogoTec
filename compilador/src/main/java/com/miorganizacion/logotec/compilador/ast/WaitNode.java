package com.miorganizacion.logotec.compilador.ast;
import java.util.Map;

public class WaitNode implements StmtNode {
    private final ExprNode time;
    public WaitNode(ExprNode time) { this.time = time; }
    @Override public Object execute(Map<String,Object> st) {
        Object val = time.execute(st);
        if (!(val instanceof Number)) throw new RuntimeException("Espera requiere número");
        System.out.println("Espera " + val);
        return null;
    }
}

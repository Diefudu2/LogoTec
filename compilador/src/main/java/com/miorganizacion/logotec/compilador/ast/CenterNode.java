package com.miorganizacion.logotec.compilador.ast;
import java.util.Map;

public class CenterNode implements StmtNode {
    @Override public Object execute(Map<String,Object> st) {
        System.out.println("Centro");
        return null;
    }
}

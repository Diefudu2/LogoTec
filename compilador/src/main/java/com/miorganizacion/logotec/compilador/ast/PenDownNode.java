package com.miorganizacion.logotec.compilador.ast;
import java.util.Map;

public class PenDownNode implements StmtNode {
    @Override public Object execute(Map<String,Object> st) {
        System.out.println("Baja lápiz");
        return null;
    }
}

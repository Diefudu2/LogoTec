package com.miorganizacion.logotec.compilador.ast;
import java.util.Map;

public class PenUpNode implements StmtNode {
    @Override public Object execute(Map<String,Object> st) {
        System.out.println("Sube lápiz");
        return null;
    }
}
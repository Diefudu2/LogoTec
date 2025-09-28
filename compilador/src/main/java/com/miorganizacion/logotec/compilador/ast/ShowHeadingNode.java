package com.miorganizacion.logotec.compilador.ast;
import java.util.Map;

public class ShowHeadingNode implements StmtNode {
    @Override public Object execute(Map<String,Object> st) {
        System.out.println("Mostrar rumbo");
        return null;
    }
}
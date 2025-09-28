package com.miorganizacion.logotec.compilador.ast;
import java.util.Map;

/* Oculta tortuga */
public class HideTurtleNode implements StmtNode {
    @Override public Object execute(Map<String,Object> st) {
        System.out.println("Oculta tortuga");
        return null;
    }
}

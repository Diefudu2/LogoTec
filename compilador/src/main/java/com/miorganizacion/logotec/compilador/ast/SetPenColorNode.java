package com.miorganizacion.logotec.compilador.ast;

import java.util.Map;

public class SetPenColorNode implements ASTNode {
    private final String color;

    public SetPenColorNode(String color) {
        this.color = color;
    }

    @Override
    public Object execute(Map<String, Object> symbolTable) {
        System.out.println("Cambiando color del lápiz a " + color);
        return null;
    }
}

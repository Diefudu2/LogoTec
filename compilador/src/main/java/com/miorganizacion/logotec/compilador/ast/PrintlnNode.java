package com.miorganizacion.logotec.compilador.ast;
import java.util.List;
import java.util.Map;

public class PrintlnNode implements ASTNode {
    private ASTNode expression;
    
    public PrintlnNode(ASTNode expression) {
        this.expression = expression;
    }
    
    @Override
    public Object execute(Map<String, Object> symbolTable) {
        Object value = expression.execute(symbolTable);
        System.out.println(value);
        return value;
    }
}
package com.miorganizacion.logotec.compilador.ast;
import java.util.Map;

public class VarDeclNode implements StmtNode {
    private final String name;
    private final ExprNode value; // más preciso que ASTNode

    public VarDeclNode(String name, ExprNode value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public Object execute(Map<String, Object> symbolTable) {
        Object val = value.execute(symbolTable);
        symbolTable.put(name, val);
        System.out.println("📦 Declarada variable '" + name + "' = " + val);
        return val;
    }

    @Override
    public String toString() {
        return "VarDecl(" + name + " = " + value + ")";
    }
}


package com.miorganizacion.logotec.compilador.ast;
import java.util.Map;

public class VarAssignNode implements StmtNode {
    private final String name;
    private final ASTNode expr;

    public VarAssignNode(String name, ASTNode expr) {
        this.name = name;
        this.expr = expr;
    }
    
    public String getName() { return name; }
    public String getVariable() { return name; }
    public ExprNode getExpression() { return (ExprNode) expr; }

    @Override
    public Object execute(Map<String, Object> symbolTable) {
        Object val = expr.execute(symbolTable);
        symbolTable.put(name, val);
        return val;
    }
}

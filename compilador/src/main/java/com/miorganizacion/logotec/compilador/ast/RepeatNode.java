package com.miorganizacion.logotec.compilador.ast;

import java.util.List;
import java.util.Map;

public class RepeatNode implements StmtNode {
    private final ExprNode timesExpr;
    private final List<StmtNode> body;

    public RepeatNode(ExprNode timesExpr, List<StmtNode> body) {
        this.timesExpr = timesExpr;
        this.body = body;
    }
    
    public ExprNode getTimes() { return timesExpr; }
    public List<StmtNode> getBody() { return body; }

    @Override
    public Object execute(Map<String, Object> symbolTable) {
        Object timesValue = timesExpr.execute(symbolTable);

        if (!(timesValue instanceof Number)) {
            throw new RuntimeException("REPITE: se esperaba un número, se recibió: " + timesValue);
        }

        int times = ((Number) timesValue).intValue();
        
        if (times < 0) {
            throw new RuntimeException("REPITE: el número de repeticiones no puede ser negativo: " + times);
        }

        for (int i = 0; i < times; i++) {
            for (StmtNode stmt : body) {
                if (stmt != null) {
                    stmt.execute(symbolTable);
                }
            }
        }

        return null;
    }

    @Override
    public String toString() {
        return "Repite(" + timesExpr + ", " + body + ")";
    }
}

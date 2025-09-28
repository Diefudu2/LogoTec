package com.miorganizacion.logotec.compilador.ast;

import java.util.List;
import java.util.Map;

public class RepeatNode implements StmtNode {
    private final ExprNode timesExpr;      // Expresión que indica cuántas veces repetir
    private final List<StmtNode> body;     // Bloque de sentencias dentro del Repite

    public RepeatNode(ExprNode timesExpr, List<StmtNode> body) {
        this.timesExpr = timesExpr;
        this.body = body;
    }

    @Override
    public Object execute(Map<String, Object> symbolTable) {
        Object timesValue = timesExpr.execute(symbolTable);

        if (!(timesValue instanceof Number)) {
            throw new RuntimeException("El valor de 'Repite' debe ser un número entero.");
        }

        int times = ((Number) timesValue).intValue();

        for (int i = 0; i < times; i++) {
            for (StmtNode stmt : body) {
                stmt.execute(symbolTable);
            }
        }

        return null;
    }

    @Override
    public String toString() {
        return "Repite(" + timesExpr + ", " + body + ")";
    }
}

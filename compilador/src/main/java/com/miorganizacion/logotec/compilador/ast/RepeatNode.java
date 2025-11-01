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
        System.out.println("[RepeatNode] Evaluando expresión de repeticiones: " + timesExpr);
        Object timesValue = timesExpr.execute(symbolTable);

        if (timesValue == null) {
            System.err.println("[RepeatNode] ERROR: la expresión de repeticiones es null");
            System.err.println("  Expresión: " + timesExpr);
            System.err.println("  Tabla de símbolos: " + symbolTable.keySet());
            throw new RuntimeException("REPITE: la expresión de repeticiones es null");
        }

        if (!(timesValue instanceof Number)) {
            System.err.println("[RepeatNode] ERROR: se esperaba un número");
            System.err.println("  Valor recibido: " + timesValue + " (" + timesValue.getClass().getSimpleName() + ")");
            throw new RuntimeException("REPITE: se esperaba un número, se recibió: " + timesValue + " (" + timesValue.getClass().getSimpleName() + ")");
        }

        int times = ((Number) timesValue).intValue();
        
        if (times < 0) {
            throw new RuntimeException("REPITE: el número de repeticiones no puede ser negativo: " + times);
        }

        System.out.println("[RepeatNode] Ejecutando " + times + " repeticiones con " + body.size() + " sentencias");
        
        for (int i = 0; i < times; i++) {
            System.out.println("[RepeatNode] --- Iteración " + (i + 1) + " de " + times + " ---");
            for (int j = 0; j < body.size(); j++) {
                StmtNode stmt = body.get(j);
                if (stmt != null) {
                    System.out.println("[RepeatNode]   Ejecutando sentencia " + (j+1) + ": " + stmt.getClass().getSimpleName());
                    stmt.execute(symbolTable);
                }
            }
        }

        System.out.println("[RepeatNode] Ciclo completado");
        return null;
    }

    @Override
    public String toString() {
        return "Repite(" + timesExpr + ", " + body.size() + " sentencias)";
    }
}

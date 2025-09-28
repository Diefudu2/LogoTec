package com.miorganizacion.logotec.compilador.ast;
import java.util.List;
import java.util.Map;

public class ProcCallNode implements StmtNode {
    private final String name;
    private final List<ExprNode> args;

    public ProcCallNode(String name, List<ExprNode> args) {
        this.name = name;
        this.args = args;
    }

    @Override
    public Object execute(Map<String, Object> symbolTable) {
        // Recuperar la declaración del procedimiento
        ProcDeclNode proc = (ProcDeclNode) symbolTable.get(name);
        if (proc == null) {
            throw new RuntimeException("Procedimiento no definido: " + name);
        }

        // Evaluar argumentos
        List<Object> values = new java.util.ArrayList<>();
        for (ExprNode a : args) {
            values.add(a.execute(symbolTable));
        }

        // Llamar al procedimiento
        return proc.call(symbolTable, values);
    }

    @Override
    public String toString() {
        return "ProcCall(" + name + ", args=" + args + ")";
    }
}

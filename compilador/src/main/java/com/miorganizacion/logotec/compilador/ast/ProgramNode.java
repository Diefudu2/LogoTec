package com.miorganizacion.logotec.compilador.ast;

import java.util.List;
import java.util.Map;

public class ProgramNode implements ASTNode {
    private final List<ProcDeclNode> decls;
    private final List<StmtNode> body;

    public ProgramNode(List<ProcDeclNode> decls, List<StmtNode> body) {
        this.decls = decls;
        this.body = body;
    }

    @Override
    public Object execute(Map<String, Object> symbolTable) {
        // Ejecutar declaraciones de procedimientos (se registran en la tabla)
        for (ProcDeclNode d : decls) {
            d.execute(symbolTable);
        }
        // Ejecutar cuerpo principal
        for (StmtNode stmt : body) {
            stmt.execute(symbolTable);
        }
        return null;
    }

    @Override
    public String toString() {
        return "ProgramNode{" +
                "decls=" + decls +
                ", body=" + body +
                '}';
    }
}

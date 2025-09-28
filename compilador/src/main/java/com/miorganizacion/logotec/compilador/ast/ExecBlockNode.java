package com.miorganizacion.logotec.compilador.ast;
import java.util.List;
import java.util.Map;

public class ExecBlockNode implements StmtNode {
    private final List<StmtNode> body;

    public ExecBlockNode(List<StmtNode> body) {
        this.body = body;
    }

    @Override
    public Object execute(Map<String,Object> st) {
        for (StmtNode stmt : body) {
            stmt.execute(st);
        }
        return null;
    }

    @Override
    public String toString() {
        return "ExecBlock" + body;
    }
}

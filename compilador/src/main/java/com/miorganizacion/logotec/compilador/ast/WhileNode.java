package com.miorganizacion.logotec.compilador.ast;
import java.util.List;
import java.util.Map;

public class WhileNode implements StmtNode {
    private ExprNode condition;
    private List<StmtNode> body;

    public WhileNode(ExprNode condition, List<StmtNode> body) {
        this.condition = condition;
        this.body = body;
    }

    @Override
    public Object execute(Map<String,Object> st) {
        while ((Boolean) condition.execute(st)) {
            for (StmtNode stmt : body) stmt.execute(st);
        }
        return null;
    }
}
package com.miorganizacion.logotec.compilador.ast;
import java.util.List;
import java.util.Map;

public class DoUntilNode implements StmtNode {
    private final List<StmtNode> body;
    private final ExprNode condition;

    public DoUntilNode(List<StmtNode> body, ExprNode condition) {
        this.body = body;
        this.condition = condition;
    }

    @Override
    public Object execute(Map<String,Object> st) {
        do {
            for (StmtNode stmt : body) {
                stmt.execute(st);
            }
        } while (!(Boolean) condition.execute(st));
        return null;
    }

    @Override
    public String toString() {
        return "DoUntil(" + condition + ", body=" + body + ")";
    }
}

package com.miorganizacion.logotec.compilador.ast;
import java.util.List;
import java.util.Map;

public class WhileNode implements StmtNode {
    private final ExprNode condition;
    private final List<StmtNode> body;

    public WhileNode(ExprNode condition, List<StmtNode> body) {
        this.condition = condition;
        this.body = body;
    }
    
    public ExprNode getCond() { return condition; }
    public List<StmtNode> getBody() { return body; }

    @Override
    public Object execute(Map<String,Object> st) {
        while (true) {
            Object condValue = condition.execute(st);
            
            if (!(condValue instanceof Boolean)) {
                throw new RuntimeException("MIENTRAS: la condición debe ser booleana, se recibió: " + condValue);
            }
            
            if (!(Boolean) condValue) {
                break;
            }
            
            for (StmtNode stmt : body) {
                if (stmt != null) {
                    stmt.execute(st);
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Mientras(" + condition + ", " + body + ")";
    }
}
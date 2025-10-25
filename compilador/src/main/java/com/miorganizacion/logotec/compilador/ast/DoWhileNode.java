package com.miorganizacion.logotec.compilador.ast;
import java.util.List;
import java.util.Map;

public class DoWhileNode implements StmtNode {
    private final List<StmtNode> body;
    private final ExprNode condition;

    public DoWhileNode(List<StmtNode> body, ExprNode condition) {
        this.body = body;
        this.condition = condition;
    }
    
    public List<StmtNode> getBody() { return body; }
    public ExprNode getCond() { return condition; }

    @Override
    public Object execute(Map<String,Object> st) {
        do {
            for (StmtNode stmt : body) {
                if (stmt != null) {
                    stmt.execute(st);
                }
            }
            
            Object condValue = condition.execute(st);
            
            if (!(condValue instanceof Boolean)) {
                throw new RuntimeException("HAZ.MIENTRAS: la condición debe ser booleana, se recibió: " + condValue);
            }
            
            if (!(Boolean) condValue) {
                break;
            }
        } while (true);
        
        return null;
    }

    @Override
    public String toString() {
        return "DoWhile(body=" + body + ", cond=" + condition + ")";
    }
}

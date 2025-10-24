package com.miorganizacion.logotec.compilador.ast;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class IfNode implements StmtNode {
    private ASTNode condition;
    private List<ASTNode> thenBody, elseBody;
    
    public IfNode(ASTNode condition, List<ASTNode> thenBody, List<ASTNode> elseBody) {
        this.condition = condition; 
        this.thenBody = thenBody; 
        this.elseBody = elseBody;
    }
    
    @SuppressWarnings("unchecked")
    public IfNode(ExprNode condition, List<StmtNode> thenBody, Object elseBody) {
        this.condition = condition;
        this.thenBody = new ArrayList<>();
        if (thenBody != null) {
            this.thenBody.addAll(thenBody);
        }
        
        // Convertir elseBody (puede ser List o null)
        this.elseBody = new ArrayList<>();
        if (elseBody != null && elseBody instanceof List) {
            this.elseBody.addAll((List<ASTNode>) elseBody);
        }
    }
    
    @Override 
    public Object execute(Map<String,Object> st) {
        if ((Boolean)condition.execute(st)) {
            for (ASTNode stmt : thenBody) stmt.execute(st);
        } else if (elseBody != null) {
            for (ASTNode stmt : elseBody) stmt.execute(st);
        }
        return null;
    }
}

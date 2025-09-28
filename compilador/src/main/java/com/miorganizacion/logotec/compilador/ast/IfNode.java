package com.miorganizacion.logotec.compilador.ast;
import java.util.List;
import java.util.Map;

public class IfNode implements StmtNode {
    private ASTNode condition;
    private List<ASTNode> thenBody, elseBody;
    public IfNode(ASTNode condition, List<ASTNode> thenBody, List<ASTNode> elseBody) {
        this.condition = condition; this.thenBody = thenBody; this.elseBody = elseBody;
    }
    public IfNode(ExprNode node, List<StmtNode> thenBody2, Object elseBody2) {
		// TODO Auto-generated constructor stub
	}
	@Override public Object execute(Map<String,Object> st) {
        if ((Boolean)condition.execute(st)) {
            for (ASTNode stmt : thenBody) stmt.execute(st);
        } else if (elseBody != null) {
            for (ASTNode stmt : elseBody) stmt.execute(st);
        }
        return null;
    }
}

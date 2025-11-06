package com.miorganizacion.logotec.compilador.ast;
import java.util.Map;

public class IncNode implements StmtNode {
    private VarRefNode var;
    private ASTNode amount;
    
    public IncNode(VarRefNode var, ASTNode amount) {
        this.var = var; 
        this.amount = amount;
    }
    
    public VarRefNode getVar() { return var; }
    public ExprNode getDelta() { return (ExprNode) amount; }
    
    @Override 
    public Object execute(Map<String,Object> st) {
        Object current = var.execute(st);
        int inc = (Integer) amount.execute(st);
        int newVal = ((Number)current).intValue() + inc;
        st.put(var.getName(), newVal);
        System.out.println("🔼 Incrementar " + var.getName() + " en " + inc + " → " + newVal);
        return newVal;
    }
    
    // ← NO AGREGAR generateIR() aquí
    // ASTtoIRTranslator.generateInc() ya maneja la generación de IR
}

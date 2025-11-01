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
    
    public String getName() { return name; }
    public List<ExprNode> getArgs() { return args; }

    @Override
    public Object execute(Map<String, Object> symbolTable) {
        System.out.println("[ProcCallNode] Llamando a procedimiento: " + name);
        
        // Recuperar la declaración del procedimiento
        Object procObj = symbolTable.get(name);
        if (procObj == null) {
            throw new RuntimeException("Procedimiento no definido: " + name);
        }
        
        if (!(procObj instanceof ProcDeclNode)) {
            throw new RuntimeException("'" + name + "' no es un procedimiento, es: " + procObj.getClass().getSimpleName());
        }
        
        ProcDeclNode proc = (ProcDeclNode) procObj;

        // Evaluar argumentos ANTES de llamar al procedimiento
        List<Object> values = new java.util.ArrayList<>();
        System.out.println("[ProcCallNode] Evaluando " + args.size() + " argumentos:");
        for (int i = 0; i < args.size(); i++) {
            ExprNode argExpr = args.get(i);
            Object val = argExpr.execute(symbolTable);
            System.out.println("  Arg[" + i + "]: " + argExpr + " = " + val);
            values.add(val);
        }

        // Llamar al procedimiento con los valores evaluados
        return proc.call(symbolTable, values);
    }

    @Override
    public String toString() {
        return "ProcCall(" + name + ", args=" + args + ")";
    }
}

package com.miorganizacion.logotec.compilador.ast;
import java.util.List;
import java.util.Map;


public class DifferenceNode implements ExprNode {
	public DifferenceNode(ExprNode first, List<ExprNode> rest) {
	    this.first = first;
	    this.rest = rest;
	}

	private final ExprNode first;
    private final List<ExprNode> rest;



    @Override
    public Object execute(Map<String,Object> st) {
        // Evaluar el primer operando
        Object firstVal = first.execute(st);
        if (!(firstVal instanceof Number)) {
            throw new RuntimeException("DifferenceNode: el primer operando no es numérico -> " + firstVal);
        }

        int result = ((Number) firstVal).intValue();

        // Evaluar el resto de operandos
        if (rest != null) {
            for (ExprNode e : rest) {
                Object val = e.execute(st);
                if (!(val instanceof Number)) {
                    throw new RuntimeException("DifferenceNode: operando no numérico -> " + val);
                }
                result -= ((Number) val).intValue();
            }
        }

        return result;
    }

    public ExprNode getFirst() {
        return first;
    }

    public List<ExprNode> getRest() {
        return rest;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Difference(");
        sb.append(first);
        if (rest != null && !rest.isEmpty()) {
            for (ExprNode e : rest) {
                sb.append(" - ").append(e);
            }
        }
        sb.append(")");
        return sb.toString();
    }
}

package com.miorganizacion.logotec.compilador.ast;
import java.util.List;
import java.util.Map;

public class ProductNode implements ExprNode {
    private final ExprNode first;
    private final List<ExprNode> rest;

    public ProductNode(ExprNode first, List<ExprNode> rest) {
        this.first = first;
        this.rest = rest;
    }
    
    public ExprNode getFirst() { return first; }
    public List<ExprNode> getRest() { return rest; }

    @Override
    public Object execute(Map<String,Object> st) {
        Object firstVal = first.execute(st);
        if (!(firstVal instanceof Number)) {
            throw new RuntimeException("ProductNode: el primer operando no es numérico -> " + firstVal);
        }

        int total = ((Number) firstVal).intValue();

        if (rest != null) {
            for (ExprNode e : rest) {
                Object val = e.execute(st);
                if (!(val instanceof Number)) {
                    throw new RuntimeException("ProductNode: operando no numérico -> " + val);
                }
                total *= ((Number) val).intValue();
            }
        }

        return total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Product(");
        sb.append(first);
        if (rest != null && !rest.isEmpty()) {
            for (ExprNode e : rest) {
                sb.append(" * ").append(e);
            }
        }
        sb.append(")");
        return sb.toString();
    }
}

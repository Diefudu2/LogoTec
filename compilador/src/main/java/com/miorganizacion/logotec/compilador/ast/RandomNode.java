package com.miorganizacion.logotec.compilador.ast;
import java.util.Map;
import java.util.Random;

public class RandomNode implements ExprNode {
    private final ExprNode max;

    public RandomNode(ExprNode max) {
        this.max = max;
    }
    
    public ExprNode getExpr() { return max; }

    @Override
    public Object execute(Map<String,Object> st) {
        Object val = max.execute(st);
        if (!(val instanceof Number)) {
            throw new RuntimeException("RandomNode: el argumento no es numérico -> " + val);
        }

        int bound = ((Number) val).intValue();
        if (bound <= 0) {
            throw new RuntimeException("RandomNode: el límite debe ser mayor que 0, recibido: " + bound);
        }

        return new Random().nextInt(bound);
    }

    @Override
    public String toString() {
        return "Random(0.." + max + ")";
    }
}

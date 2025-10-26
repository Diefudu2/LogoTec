package com.miorganizacion.logotec.compilador.ast;

public enum ValueType {
    INT, BOOL, STR, PROCEDURE, UNKNOWN;

    /**
     * Deducción de tipo a partir de un nodo de expresión.
     */
    public static ValueType infer(ExprNode node) {
        if (node instanceof ConstNode) {
            Object val = ((ConstNode) node).getValue(); // asegúrate de que ConstNode tenga getValue()
            if (val instanceof Integer) return INT;
            if (val instanceof Boolean) return BOOL;
            if (val instanceof String) return STR;
        }
        // Si no es un literal, no podemos deducirlo aquí
        return UNKNOWN;
    }
}

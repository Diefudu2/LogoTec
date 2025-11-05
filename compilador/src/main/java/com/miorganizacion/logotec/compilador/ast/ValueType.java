package com.miorganizacion.logotec.compilador.ast;

import java.util.Map;

public enum ValueType {
    INT, BOOL, STR, PROCEDURE, UNKNOWN;

    /**
     * Deducción de tipo a partir de un nodo de expresión (sin tabla de símbolos).
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

    /**
     * Deducción de tipo a partir de un nodo de expresión CON tabla de símbolos.
     * Permite inferir tipos de variables, expresiones complejas y funciones.
     */
    public static ValueType infer(ExprNode node, Map<String, Symbol> symbols) {
        if (node == null) return UNKNOWN;

        // Literales constantes
        if (node instanceof ConstNode) {
            Object val = ((ConstNode) node).getValue();
            if (val instanceof Integer || val instanceof Double) return INT;
            if (val instanceof Boolean) return BOOL;
            if (val instanceof String) return STR;
            return UNKNOWN;
        }

        // Referencias a variables
        String className = node.getClass().getSimpleName();
        if (className.equals("VarRefNode")) {
            try {
                java.lang.reflect.Method getNameMethod = node.getClass().getMethod("getName");
                String varName = (String) getNameMethod.invoke(node);
                Symbol s = symbols.get(varName);
                return s != null ? s.type : UNKNOWN;
            } catch (Exception e) {
                return UNKNOWN;
            }
        }

        // Operaciones aritméticas → INT
        if (className.equals("AdditionNode") || className.equals("SubtractionNode") ||
            className.equals("MultiplicationNode") || className.equals("DivisionNode") ||
            className.equals("ExponentiationNode") || className.equals("ProductNode") ||
            className.equals("SumNode") || className.equals("DifferenceNode")) {
            return INT;
        }

        // Operaciones lógicas y relacionales → BOOL
        if (className.equals("LogicalAndNode") || className.equals("LogicalOrNode") ||
            className.equals("LogicalNotNode") || className.equals("GreaterThanNode") ||
            className.equals("LessThanNode") || className.equals("GreaterEqualNode") ||
            className.equals("LessEqualNode") || className.equals("EqualsNode") ||
            className.equals("NotEqualsNode") || className.equals("EqualsFuncNode")) {
            return BOOL;
        }

        // Funciones específicas
        if (className.equals("RandomNode")) {
            return INT;
        }

        return UNKNOWN;
    }
}

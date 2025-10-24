package com.miorganizacion.logotec.compilador.ir;

/**
 * Representa un operando en una instrucción de tres direcciones.
 * Puede ser un temporal, una variable, una constante o una etiqueta.
 */
public class Operand {
    
    public enum Type {
        TEMPORARY,   // t1, t2, t3...
        VARIABLE,    // lado, color, x...
        CONSTANT,    // #50, #3.14...
        LABEL        // L1, L2, L3...
    }
    
    private final Type type;
    private final String value;
    
    /**
     * Constructor privado. Usar métodos estáticos para crear operandos.
     */
    private Operand(Type type, String value) {
        this.type = type;
        this.value = value;
    }
    
    // ==================== FACTORY METHODS ====================
    
    public static Operand temp(String name) {
        return new Operand(Type.TEMPORARY, name);
    }
    
    public static Operand variable(String name) {
        return new Operand(Type.VARIABLE, name);
    }
    
    public static Operand constant(double value) {
        return new Operand(Type.CONSTANT, String.valueOf(value));
    }
    
    public static Operand constant(int value) {
        return new Operand(Type.CONSTANT, String.valueOf(value));
    }
    
    public static Operand label(String name) {
        return new Operand(Type.LABEL, name);
    }
    
    // ==================== GETTERS ====================
    
    public Type getType() {
        return type;
    }
    
    public String getValue() {
        return value;
    }
    
    public boolean isTemporary() {
        return type == Type.TEMPORARY;
    }
    
    public boolean isVariable() {
        return type == Type.VARIABLE;
    }
    
    public boolean isConstant() {
        return type == Type.CONSTANT;
    }
    
    public boolean isLabel() {
        return type == Type.LABEL;
    }
    
    /**
     * Obtiene el valor numérico si es una constante.
     */
    public double getNumericValue() {
        if (!isConstant()) {
            throw new IllegalStateException("El operando no es una constante");
        }
        return Double.parseDouble(value);
    }
    
    // ==================== REPRESENTACIÓN ====================
    
    @Override
    public String toString() {
        switch (type) {
            case TEMPORARY:
                return value;
            case VARIABLE:
                return "[" + value + "]";
            case CONSTANT:
                return "#" + value;
            case LABEL:
                return value;
            default:
                return value;
        }
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Operand operand = (Operand) obj;
        return type == operand.type && value.equals(operand.value);
    }
    
    @Override
    public int hashCode() {
        return 31 * type.hashCode() + value.hashCode();
    }
}

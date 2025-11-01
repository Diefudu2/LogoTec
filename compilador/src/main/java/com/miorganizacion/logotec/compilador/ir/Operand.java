package com.miorganizacion.logotec.compilador.ir;

import java.util.Objects;

/**
 * Representa un operando en una instrucción de tres direcciones.
 */
public class Operand {
    
    public enum Type {
        CONSTANT,   // Valor literal
        VARIABLE,   // Variable con nombre
        TEMP,       // Temporal generado
        LABEL       // Etiqueta de salto
    }
    
    private final Type type;
    private final String value;
    private final double numericValue;
    
    private Operand(Type type, String value, double numericValue) {
        this.type = type;
        this.value = value;
        this.numericValue = numericValue;
    }
    
    // Factory methods
    public static Operand constant(double value) {
        return new Operand(Type.CONSTANT, String.valueOf(value), value);
    }
    
    public static Operand constant(int value) {
        return new Operand(Type.CONSTANT, String.valueOf(value), value);
    }
    
    public static Operand variable(String name) {
        return new Operand(Type.VARIABLE, name, 0);
    }
    
    public static Operand temp(String name) {
        return new Operand(Type.TEMP, name, 0);
    }
    
    public static Operand label(String name) {
        return new Operand(Type.LABEL, name, 0);
    }
    
    // Getters
    public Type getType() { return type; }
    public String getValue() { return value; }
    public double getNumericValue() { return numericValue; }
    
    public boolean isConstant() { return type == Type.CONSTANT; }
    public boolean isVariable() { return type == Type.VARIABLE; }
    public boolean isTemp() { return type == Type.TEMP; }
    public boolean isLabel() { return type == Type.LABEL; }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Operand)) return false;
        Operand other = (Operand) obj;
        return type == other.type
            && Objects.equals(value, other.value)
            && Objects.equals(numericValue, other.numericValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, value, numericValue);
    }

    @Override
    public String toString() {
        if (numericValue != 0) {
            return type + "(" + numericValue + ")";
        }
        return type + "(" + value + ")";
    }
}

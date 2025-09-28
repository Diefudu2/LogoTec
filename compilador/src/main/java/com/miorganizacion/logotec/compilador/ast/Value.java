package com.miorganizacion.logotec.compilador.ast;

public final class Value {
    public final ValueType type;
    public final Object raw;

    private Value(ValueType t, Object r) {
        this.type = t;
        this.raw = r;
    }

    public static Value of(ValueType t, Object r) {
        return new Value(t, r);
    }
    
    public static Value unknown() {
        return new Value(ValueType.UNKNOWN, null);
    }
    
 // Alias para compatibilidad con la gramática
    public static Value str(String s) {
        return string(s);
    }

    public static Value Int(Integer i) { // con mayúscula para no chocar con palabra reservada
        return integer(i);
    }


    // Factories específicos
    public static Value bool(boolean b) {
        return new Value(ValueType.BOOL, b);
    }

    public static Value integer(int i) {
        return new Value(ValueType.INT, i);
    }

    public static Value string(String s) {
        return new Value(ValueType.STR, s);
    }

    // Métodos de acceso seguros
    public boolean asBool() {
        return (Boolean) raw;
    }

    public int asInt() {
        return (Integer) raw;
    }

    public String asString() {
        return (String) raw;
    }

    @Override
    public String toString() {
        return String.valueOf(raw);
    }
}

package com.miorganizacion.logotec.compilador.backend;

/**
 * Representa un registro del procesador.
 * Basado en la convención MIPS pero simplificado.
 */
public class Register {
    
    private final String name;
    private final int number;
    private final Type type;
    
    public enum Type {
        ZERO,       // Registro siempre cero ($zero)
        TEMP,       // Temporales ($t0-$t9)
        SAVED,      // Guardados ($s0-$s7)
        ARG,        // Argumentos ($a0-$a3)
        RESULT,     // Resultados ($v0-$v1)
        SPECIAL     // Especiales ($sp, $fp, $ra)
    }
    
    // ==================== REGISTROS ESPECIALES ====================
    public static final Register ZERO = new Register("$zero", 0, Type.ZERO);
    public static final Register SP = new Register("$sp", 29, Type.SPECIAL);   // Stack pointer
    public static final Register FP = new Register("$fp", 30, Type.SPECIAL);   // Frame pointer
    public static final Register RA = new Register("$ra", 31, Type.SPECIAL);   // Return address
    
    // ==================== REGISTROS DE RESULTADOS ====================
    public static final Register V0 = new Register("$v0", 2, Type.RESULT);
    public static final Register V1 = new Register("$v1", 3, Type.RESULT);
    
    // ==================== REGISTROS DE ARGUMENTOS ====================
    public static final Register A0 = new Register("$a0", 4, Type.ARG);
    public static final Register A1 = new Register("$a1", 5, Type.ARG);
    public static final Register A2 = new Register("$a2", 6, Type.ARG);
    public static final Register A3 = new Register("$a3", 7, Type.ARG);
    
    // ==================== REGISTROS TEMPORALES ====================
    public static final Register T0 = new Register("$t0", 8, Type.TEMP);
    public static final Register T1 = new Register("$t1", 9, Type.TEMP);
    public static final Register T2 = new Register("$t2", 10, Type.TEMP);
    public static final Register T3 = new Register("$t3", 11, Type.TEMP);
    public static final Register T4 = new Register("$t4", 12, Type.TEMP);
    public static final Register T5 = new Register("$t5", 13, Type.TEMP);
    public static final Register T6 = new Register("$t6", 14, Type.TEMP);
    public static final Register T7 = new Register("$t7", 15, Type.TEMP);
    public static final Register T8 = new Register("$t8", 24, Type.TEMP);
    public static final Register T9 = new Register("$t9", 25, Type.TEMP);
    
    // ==================== REGISTROS GUARDADOS ====================
    public static final Register S0 = new Register("$s0", 16, Type.SAVED);
    public static final Register S1 = new Register("$s1", 17, Type.SAVED);
    public static final Register S2 = new Register("$s2", 18, Type.SAVED);
    public static final Register S3 = new Register("$s3", 19, Type.SAVED);
    public static final Register S4 = new Register("$s4", 20, Type.SAVED);
    public static final Register S5 = new Register("$s5", 21, Type.SAVED);
    public static final Register S6 = new Register("$s6", 22, Type.SAVED);
    public static final Register S7 = new Register("$s7", 23, Type.SAVED);
    
    // Array de registros temporales para asignación
    public static final Register[] TEMP_REGISTERS = {
        T0, T1, T2, T3, T4, T5, T6, T7, T8, T9
    };
    
    // Array de registros guardados
    public static final Register[] SAVED_REGISTERS = {
        S0, S1, S2, S3, S4, S5, S6, S7
    };
    
    private Register(String name, int number, Type type) {
        this.name = name;
        this.number = number;
        this.type = type;
    }
    
    public String getName() {
        return name;
    }
    
    public int getNumber() {
        return number;
    }
    
    public Type getType() {
        return type;
    }
    
    public boolean isTemporary() {
        return type == Type.TEMP;
    }
    
    public boolean isSaved() {
        return type == Type.SAVED;
    }
    
    @Override
    public String toString() {
        return name;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Register register = (Register) obj;
        return number == register.number;
    }
    
    @Override
    public int hashCode() {
        return number;
    }
}

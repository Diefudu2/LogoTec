package com.miorganizacion.logotec.compilador.backend;

import java.util.HashMap;
import java.util.Map;

/**
 * Registros de la arquitectura objetivo (estilo MIPS).
 * 
 * Convención de uso:
 * - $zero: Siempre contiene 0
 * - $v0-$v1: Valores de retorno
 * - $a0-$a3: Argumentos de función
 * - $t0-$t9: Temporales (caller-saved)
 * - $s0-$s7: Salvados (callee-saved)
 * - $sp: Stack pointer
 * - $fp: Frame pointer
 * - $ra: Return address
 */
public enum Register {
    
    // Registro cero (siempre 0)
    ZERO("$zero", 0),
    
    // Registros de valor de retorno
    V0("$v0", 2),
    V1("$v1", 3),
    
    // Registros de argumentos
    A0("$a0", 4),
    A1("$a1", 5),
    A2("$a2", 6),
    A3("$a3", 7),
    
    // Registros temporales (caller-saved)
    T0("$t0", 8),
    T1("$t1", 9),
    T2("$t2", 10),
    T3("$t3", 11),
    T4("$t4", 12),
    T5("$t5", 13),
    T6("$t6", 14),
    T7("$t7", 15),
    T8("$t8", 24),
    T9("$t9", 25),
    
    // Registros salvados (callee-saved)
    S0("$s0", 16),
    S1("$s1", 17),
    S2("$s2", 18),
    S3("$s3", 19),
    S4("$s4", 20),
    S5("$s5", 21),
    S6("$s6", 22),
    S7("$s7", 23),
    
    // Registros especiales
    SP("$sp", 29),  // Stack pointer
    FP("$fp", 30),  // Frame pointer
    RA("$ra", 31);  // Return address
    
    private final String name;
    private final int number;
    
    Register(String name, int number) {
        this.name = name;
        this.number = number;
    }
    
    public String getName() {
        return name;
    }
    
    public int getNumber() {
        return number;
    }
    
    @Override
    public String toString() {
        return name;
    }
    
    // Mapa para búsqueda por nombre
    private static final Map<String, Register> BY_NAME = new HashMap<String, Register>();
    private static final Map<Integer, Register> BY_NUMBER = new HashMap<Integer, Register>();
    
    static {
        for (Register reg : values()) {
            BY_NAME.put(reg.name, reg);
            BY_NAME.put(reg.name.substring(1), reg); // Sin el $
            BY_NUMBER.put(reg.number, reg);
        }
    }
    
    /**
     * Obtiene un registro por nombre.
     */
    public static Register fromName(String name) {
        Register reg = BY_NAME.get(name);
        if (reg == null) {
            throw new IllegalArgumentException("Registro desconocido: " + name);
        }
        return reg;
    }
    
    /**
     * Obtiene un registro por número.
     */
    public static Register fromNumber(int number) {
        Register reg = BY_NUMBER.get(number);
        if (reg == null) {
            throw new IllegalArgumentException("Número de registro inválido: " + number);
        }
        return reg;
    }
    
    /**
     * Verifica si es un registro temporal.
     */
    public boolean isTemporary() {
        return number >= 8 && number <= 15 || number >= 24 && number <= 25;
    }
    
    /**
     * Verifica si es un registro salvado.
     */
    public boolean isSaved() {
        return number >= 16 && number <= 23;
    }
    
    /**
     * Verifica si es un registro de argumento.
     */
    public boolean isArgument() {
        return number >= 4 && number <= 7;
    }
}

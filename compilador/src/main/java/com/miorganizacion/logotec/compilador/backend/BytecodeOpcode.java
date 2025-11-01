package com.miorganizacion.logotec.compilador.backend;

import java.util.HashMap;
import java.util.Map;

/**
 * Códigos de operación del bytecode ejecutable.
 * 
 * Cada instrucción Assembly se mapea a un bytecode numérico.
 * El bytecode es un formato binario compacto ejecutable por la VM.
 * 
 * FASE 4: Object Code Generation
 */
public enum BytecodeOpcode {
    
    // ==================== MOVIMIENTO DE DATOS ====================
    /** Cargar inmediato de 16 bits en registro */
    LOAD_IMM(0x01),
    
    /** Cargar desde memoria */
    LOAD_MEM(0x02),
    
    /** Almacenar en memoria */
    STORE_MEM(0x03),
    
    /** Mover entre registros */
    MOVE(0x04),
    
    // ==================== OPERACIONES ARITMÉTICAS ====================
    /** Suma: dest = src1 + src2 */
    ADD(0x10),
    
    /** Suma con inmediato: dest = src + imm */
    ADD_IMM(0x11),
    
    /** Resta: dest = src1 - src2 */
    SUB(0x12),
    
    /** Multiplicación: dest = src1 * src2 */
    MUL(0x13),
    
    /** División: dest = src1 / src2 */
    DIV(0x14),
    
    /** Módulo: dest = src1 % src2 */
    REM(0x15),
    
    // ==================== OPERACIONES LÓGICAS ====================
    /** AND bit a bit */
    AND(0x20),
    
    /** OR bit a bit */
    OR(0x21),
    
    /** XOR bit a bit */
    XOR(0x22),
    
    /** NOT bit a bit */
    NOT(0x23),
    
    // ==================== COMPARACIONES ====================
    /** Set if equal */
    SEQ(0x30),
    
    /** Set if not equal */
    SNE(0x31),
    
    /** Set if less than */
    SLT(0x32),
    
    /** Set if less or equal */
    SLE(0x33),
    
    /** Set if greater than */
    SGT(0x34),
    
    /** Set if greater or equal */
    SGE(0x35),
    
    // ==================== CONTROL DE FLUJO ====================
    /** Salto incondicional */
    JUMP(0x40),
    
    /** Branch if equal to zero */
    BEQZ(0x41),
    
    /** Branch if not equal to zero */
    BNEZ(0x42),
    
    /** Branch if equal */
    BEQ(0x43),
    
    /** Branch if not equal */
    BNE(0x44),
    
    /** Llamada a subrutina */
    CALL(0x48),
    
    /** Retorno de subrutina */
    RET(0x49),
    
    // ==================== STACK ====================
    /** Push registro al stack */
    PUSH(0x50),
    
    /** Pop del stack a registro */
    POP(0x51),
    
    // ==================== SYSCALL (TORTUGA) ====================
    /** Llamada al sistema (comandos de tortuga) */
    SYSCALL(0x60),
    
    // ==================== ESPECIALES ====================
    /** No operación */
    NOP(0x00),
    
    /** Terminar programa */
    HALT(0x7F);
    
    // ==================== CAMPOS Y MÉTODOS ====================
    
    private final int code;
    
    BytecodeOpcode(int code) {
        this.code = code;
    }
    
    public int getCode() {
        return code;
    }
    
    // Mapa para búsqueda inversa
    private static final Map<Integer, BytecodeOpcode> BY_CODE = new HashMap<Integer, BytecodeOpcode>();
    
    static {
        for (BytecodeOpcode op : values()) {
            BY_CODE.put(op.code, op);
        }
    }
    
    /**
     * Obtiene el opcode desde su código numérico.
     */
    public static BytecodeOpcode fromCode(int code) {
        BytecodeOpcode op = BY_CODE.get(code);
        if (op == null) {
            throw new IllegalArgumentException("Bytecode opcode desconocido: 0x" + Integer.toHexString(code));
        }
        return op;
    }
    
    /**
     * Verifica si el código es válido.
     */
    public static boolean isValid(int code) {
        return BY_CODE.containsKey(code);
    }
}

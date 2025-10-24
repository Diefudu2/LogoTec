package com.miorganizacion.logotec.compilador.backend;

/**
 * Enum que define las instrucciones del lenguaje ensamblador objetivo.
 * Inspirado en MIPS pero simplificado para LogoTec.
 * 
 * FASE 3: Generación de Assembly
 */
public enum AssemblyOpcode {
    
    // ==================== MOVIMIENTO DE DATOS ====================
    /** Cargar inmediato: LI reg, value */
    LI,
    
    /** Cargar desde memoria: LW reg, offset(base) */
    LW,
    
    /** Guardar en memoria: SW reg, offset(base) */
    SW,
    
    /** Mover entre registros: MOVE dest, source */
    MOVE,
    
    // ==================== OPERACIONES ARITMÉTICAS ====================
    /** Sumar: ADD dest, src1, src2 */
    ADD,
    
    /** Sumar inmediato: ADDI dest, src, immediate */
    ADDI,
    
    /** Restar: SUB dest, src1, src2 */
    SUB,
    
    /** Multiplicar: MUL dest, src1, src2 */
    MUL,
    
    /** Dividir: DIV dest, src1, src2 */
    DIV,
    
    /** Módulo: REM dest, src1, src2 */
    REM,
    
    // ==================== OPERACIONES LÓGICAS ====================
    /** AND lógico: AND dest, src1, src2 */
    AND,
    
    /** OR lógico: OR dest, src1, src2 */
    OR,
    
    /** XOR lógico: XOR dest, src1, src2 */
    XOR,
    
    /** NOT lógico: NOT dest, src */
    NOT,
    
    // ==================== COMPARACIONES ====================
    /** Set if equal: SEQ dest, src1, src2 */
    SEQ,
    
    /** Set if not equal: SNE dest, src1, src2 */
    SNE,
    
    /** Set if less than: SLT dest, src1, src2 */
    SLT,
    
    /** Set if less or equal: SLE dest, src1, src2 */
    SLE,
    
    /** Set if greater than: SGT dest, src1, src2 */
    SGT,
    
    /** Set if greater or equal: SGE dest, src1, src2 */
    SGE,
    
    // ==================== CONTROL DE FLUJO ====================
    /** Etiqueta: label: */
    LABEL,
    
    /** Salto incondicional: J label */
    J,
    
    /** Salto si cero: BEQZ reg, label */
    BEQZ,
    
    /** Salto si no cero: BNEZ reg, label */
    BNEZ,
    
    /** Salto si igual: BEQ reg1, reg2, label */
    BEQ,
    
    /** Salto si diferente: BNE reg1, reg2, label */
    BNE,
    
    // ==================== LLAMADAS A FUNCIONES / PROCEDIMIENTOS ====================
    /** Llamar a función: JAL label */
    JAL,
    
    /** Retornar de función: JR $ra */
    JR,
    
    // ==================== STACK ====================
    /** Push a la pila: PUSH reg */
    PUSH,
    
    /** Pop de la pila: POP reg */
    POP,
    
    // ==================== LLAMADAS AL RUNTIME (TORTUGA) ====================
    /** Llamada al sistema (tortuga): SYSCALL code */
    SYSCALL,
    
    // ==================== ESPECIALES ====================
    /** No operación: NOP */
    NOP,
    
    /** Comentario: # text */
    COMMENT,
    
    /** Directiva de datos: .data */
    DATA,
    
    /** Directiva de texto/código: .text */
    TEXT,
    
    /** Directiva de palabra: .word value */
    WORD,
    
    /** Directiva de float: .float value */
    FLOAT,
    
    /** Directiva de globl: .globl label */
    GLOBL
}

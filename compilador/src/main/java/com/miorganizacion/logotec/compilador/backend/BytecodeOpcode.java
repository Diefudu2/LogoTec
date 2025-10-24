package com.miorganizacion.logotec.compilador.backend;

/**
 * Códigos de operación del bytecode ejecutable.
 * 
 * Cada instrucción Assembly se mapea a un bytecode numérico.
 * El bytecode es un formato binario compacto ejecutable por la VM.
 * 
 * FASE 4: Object Code Generation
 */
public enum BytecodeOpcode {
    // === Movimiento de datos ===
    LOAD_IMM    (0x01),  // Cargar inmediato: reg = valor
    LOAD_MEM    (0x02),  // Cargar de memoria: reg = mem[addr]
    STORE_MEM   (0x03),  // Guardar en memoria: mem[addr] = reg
    MOVE        (0x04),  // Mover entre registros: reg1 = reg2
    
    // === Operaciones aritméticas ===
    ADD         (0x10),  // Suma: reg1 = reg2 + reg3
    ADD_IMM     (0x11),  // Suma inmediata: reg1 = reg2 + valor
    SUB         (0x12),  // Resta: reg1 = reg2 - reg3
    MUL         (0x13),  // Multiplicación: reg1 = reg2 * reg3
    DIV         (0x14),  // División: reg1 = reg2 / reg3
    REM         (0x15),  // Módulo: reg1 = reg2 % reg3
    
    // === Operaciones lógicas ===
    AND         (0x20),  // AND: reg1 = reg2 & reg3
    OR          (0x21),  // OR: reg1 = reg2 | reg3
    XOR         (0x22),  // XOR: reg1 = reg2 ^ reg3
    NOT         (0x23),  // NOT: reg1 = ~reg2
    
    // === Comparaciones ===
    SEQ         (0x30),  // Set if equal: reg1 = (reg2 == reg3)
    SNE         (0x31),  // Set if not equal: reg1 = (reg2 != reg3)
    SLT         (0x32),  // Set if less than: reg1 = (reg2 < reg3)
    SLE         (0x33),  // Set if less or equal: reg1 = (reg2 <= reg3)
    SGT         (0x34),  // Set if greater than: reg1 = (reg2 > reg3)
    SGE         (0x35),  // Set if greater or equal: reg1 = (reg2 >= reg3)
    
    // === Control de flujo ===
    JUMP        (0x40),  // Salto incondicional: pc = label
    BEQZ        (0x41),  // Branch if equal zero: if (reg == 0) pc = label
    BNEZ        (0x42),  // Branch if not equal zero: if (reg != 0) pc = label
    BEQ         (0x43),  // Branch if equal: if (reg1 == reg2) pc = label
    BNE         (0x44),  // Branch if not equal: if (reg1 != reg2) pc = label
    CALL        (0x45),  // Llamada a función: push pc, pc = label
    RET         (0x46),  // Retorno: pc = pop
    
    // === Stack ===
    PUSH        (0x50),  // Push: stack[sp++] = reg
    POP         (0x51),  // Pop: reg = stack[--sp]
    
    // === Syscalls (Comandos de tortuga) ===
    SYSCALL     (0x60),  // Llamada al sistema con código en $v0
    
    // === Especiales ===
    NOP         (0x00),  // No operation
    HALT        (0xFF);  // Terminar programa
    
    private final int code;
    
    BytecodeOpcode(int code) {
        this.code = code;
    }
    
    /**
     * Obtiene el código numérico del bytecode
     */
    public int getCode() {
        return code;
    }
    
    /**
     * Obtiene el bytecode desde un código numérico
     */
    public static BytecodeOpcode fromCode(int code) {
        for (BytecodeOpcode op : values()) {
            if (op.code == code) {
                return op;
            }
        }
        throw new IllegalArgumentException("Código de bytecode inválido: 0x" + 
                                         Integer.toHexString(code));
    }
    
    /**
     * Mapea un AssemblyOpcode a su BytecodeOpcode correspondiente
     */
    public static BytecodeOpcode fromAssembly(AssemblyOpcode asmOp) {
        switch (asmOp) {
            // Movimiento de datos
            case LI:    return LOAD_IMM;
            case LW:    return LOAD_MEM;
            case SW:    return STORE_MEM;
            case MOVE:  return MOVE;
            
            // Aritméticas
            case ADD:   return ADD;
            case ADDI:  return ADD_IMM;
            case SUB:   return SUB;
            case MUL:   return MUL;
            case DIV:   return DIV;
            case REM:   return REM;
            
            // Lógicas
            case AND:   return AND;
            case OR:    return OR;
            case XOR:   return XOR;
            case NOT:   return NOT;
            
            // Comparaciones
            case SEQ:   return SEQ;
            case SNE:   return SNE;
            case SLT:   return SLT;
            case SLE:   return SLE;
            case SGT:   return SGT;
            case SGE:   return SGE;
            
            // Control de flujo
            case J:     return JUMP;
            case BEQZ:  return BEQZ;
            case BNEZ:  return BNEZ;
            case BEQ:   return BEQ;
            case BNE:   return BNE;
            case JAL:   return CALL;
            case JR:    return RET;
            
            // Stack
            case PUSH:  return PUSH;
            case POP:   return POP;
            
            // Syscall
            case SYSCALL: return SYSCALL;
            
            default:
                throw new IllegalArgumentException("No se puede mapear assembly: " + asmOp);
        }
    }
    
    @Override
    public String toString() {
        return String.format("%s (0x%02X)", name(), code);
    }
}

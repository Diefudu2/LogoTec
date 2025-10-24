package com.miorganizacion.logotec.compilador.backend;

import java.util.Arrays;

/**
 * Representa una instrucción de bytecode ejecutable.
 * 
 * Formato binario de instrucción (4 bytes por instrucción):
 * - Byte 0: Opcode
 * - Byte 1: Operando 1 (registro destino o dirección)
 * - Byte 2: Operando 2 (registro fuente o inmediato alto)
 * - Byte 3: Operando 3 (registro fuente o inmediato bajo)
 * 
 * Para valores inmediatos de 16 bits:
 * - Bytes 2-3: Valor inmediato (big-endian)
 * 
 * FASE 4: Object Code Generation
 */
public class BytecodeInstruction {
    private final BytecodeOpcode opcode;
    private final int operand1;  // Registro destino o dirección
    private final int operand2;  // Registro fuente o inmediato
    private final int operand3;  // Registro fuente o inmediato
    
    public BytecodeInstruction(BytecodeOpcode opcode, int operand1, int operand2, int operand3) {
        this.opcode = opcode;
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.operand3 = operand3;
    }
    
    // Constructores de conveniencia
    
    public BytecodeInstruction(BytecodeOpcode opcode) {
        this(opcode, 0, 0, 0);
    }
    
    public BytecodeInstruction(BytecodeOpcode opcode, int operand1) {
        this(opcode, operand1, 0, 0);
    }
    
    public BytecodeInstruction(BytecodeOpcode opcode, int operand1, int operand2) {
        this(opcode, operand1, operand2, 0);
    }
    
    /**
     * Convierte la instrucción a bytes (formato binario)
     */
    public byte[] toBytes() {
        byte[] bytes = new byte[4];
        bytes[0] = (byte) opcode.getCode();
        bytes[1] = (byte) (operand1 & 0xFF);
        bytes[2] = (byte) (operand2 & 0xFF);
        bytes[3] = (byte) (operand3 & 0xFF);
        return bytes;
    }
    
    /**
     * Crea una instrucción desde bytes
     */
    public static BytecodeInstruction fromBytes(byte[] bytes) {
        if (bytes.length != 4) {
            throw new IllegalArgumentException("Instrucción debe ser de 4 bytes");
        }
        
        BytecodeOpcode opcode = BytecodeOpcode.fromCode(bytes[0] & 0xFF);
        int op1 = bytes[1] & 0xFF;
        int op2 = bytes[2] & 0xFF;
        int op3 = bytes[3] & 0xFF;
        
        return new BytecodeInstruction(opcode, op1, op2, op3);
    }
    
    /**
     * Obtiene el valor inmediato de 16 bits (operand2 y operand3 combinados)
     */
    public int getImmediate16() {
        return ((operand2 & 0xFF) << 8) | (operand3 & 0xFF);
    }
    
    /**
     * Crea una instrucción con un valor inmediato de 16 bits
     */
    public static BytecodeInstruction withImmediate16(BytecodeOpcode opcode, int reg, int immediate) {
        int high = (immediate >> 8) & 0xFF;
        int low = immediate & 0xFF;
        return new BytecodeInstruction(opcode, reg, high, low);
    }
    
    // Getters
    
    public BytecodeOpcode getOpcode() {
        return opcode;
    }
    
    public int getOperand1() {
        return operand1;
    }
    
    public int getOperand2() {
        return operand2;
    }
    
    public int getOperand3() {
        return operand3;
    }
    
    /**
     * Representación hexadecimal de la instrucción
     */
    public String toHex() {
        byte[] bytes = toBytes();
        return String.format("%02X %02X %02X %02X", 
                           bytes[0] & 0xFF, 
                           bytes[1] & 0xFF, 
                           bytes[2] & 0xFF, 
                           bytes[3] & 0xFF);
    }
    
    @Override
    public String toString() {
        return String.format("%-12s %3d, %3d, %3d  [%s]", 
                           opcode.name(), 
                           operand1, 
                           operand2, 
                           operand3,
                           toHex());
    }
    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof BytecodeInstruction)) {
            return false;
        }
        BytecodeInstruction other = (BytecodeInstruction) obj;
        return this.opcode == other.opcode &&
               this.operand1 == other.operand1 &&
               this.operand2 == other.operand2 &&
               this.operand3 == other.operand3;
    }
    
    @Override
    public int hashCode() {
        return Arrays.hashCode(toBytes());
    }
}

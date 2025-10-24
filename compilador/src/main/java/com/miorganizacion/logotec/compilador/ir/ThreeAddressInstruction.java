package com.miorganizacion.logotec.compilador.ir;

/**
 * Representa una instrucción de tres direcciones en la Representación Intermedia (IR).
 * Formato general: opcode dest, op1, op2
 * 
 * Ejemplos:
 *   ADD t1, t2, t3       -> t1 = t2 + t3
 *   LOAD_CONST t1, #50   -> t1 = 50
 *   STORE [x], t1        -> x = t1
 *   FORWARD t1           -> avanza(t1)
 *   JUMP L1              -> goto L1
 */
public class ThreeAddressInstruction {
    
    private final IROpcode opcode;
    private final Operand dest;      // Operando destino (puede ser null)
    private final Operand op1;       // Primer operando (puede ser null)
    private final Operand op2;       // Segundo operando (puede ser null)
    private final String comment;    // Comentario opcional
    
    // ==================== CONSTRUCTORES ====================
    
    /**
     * Constructor completo (para instrucciones de 3 operandos)
     */
    public ThreeAddressInstruction(IROpcode opcode, Operand dest, Operand op1, Operand op2) {
        this(opcode, dest, op1, op2, null);
    }
    
    public ThreeAddressInstruction(IROpcode opcode, Operand dest, Operand op1, Operand op2, String comment) {
        this.opcode = opcode;
        this.dest = dest;
        this.op1 = op1;
        this.op2 = op2;
        this.comment = comment;
    }
    
    /**
     * Constructor para instrucciones de 2 operandos
     */
    public ThreeAddressInstruction(IROpcode opcode, Operand dest, Operand op1) {
        this(opcode, dest, op1, null, null);
    }
    
    public ThreeAddressInstruction(IROpcode opcode, Operand dest, Operand op1, String comment) {
        this(opcode, dest, op1, null, comment);
    }
    
    /**
     * Constructor para instrucciones de 1 operando
     */
    public ThreeAddressInstruction(IROpcode opcode, Operand dest) {
        this(opcode, dest, null, null, null);
    }
    
    public ThreeAddressInstruction(IROpcode opcode, Operand dest, String comment) {
        this(opcode, dest, null, null, comment);
    }
    
    /**
     * Constructor para instrucciones sin operandos (NOP, etc.)
     */
    public ThreeAddressInstruction(IROpcode opcode) {
        this(opcode, null, null, null, null);
    }
    
    public ThreeAddressInstruction(IROpcode opcode, String comment) {
        this(opcode, null, null, null, comment);
    }
    
    // ==================== GETTERS ====================
    
    public IROpcode getOpcode() {
        return opcode;
    }
    
    public Operand getDest() {
        return dest;
    }
    
    public Operand getOp1() {
        return op1;
    }
    
    public Operand getOp2() {
        return op2;
    }
    
    public String getComment() {
        return comment;
    }
    
    public boolean hasComment() {
        return comment != null && !comment.isEmpty();
    }
    
    // ==================== REPRESENTACIÓN ====================
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        // Opcode
        sb.append(String.format("%-15s", opcode.name()));
        
        // Operandos (separados por comas)
        boolean needsComma = false;
        
        if (dest != null) {
            sb.append(dest);
            needsComma = true;
        }
        
        if (op1 != null) {
            if (needsComma) sb.append(", ");
            sb.append(op1);
            needsComma = true;
        }
        
        if (op2 != null) {
            if (needsComma) sb.append(", ");
            sb.append(op2);
        }
        
        // Comentario opcional
        if (hasComment()) {
            sb.append(String.format("%-30s", "")).append(" ; " + comment);
        }
        
        return sb.toString();
    }
    
    /**
     * Representación compacta sin espaciado
     */
    public String toCompactString() {
        StringBuilder sb = new StringBuilder();
        sb.append(opcode.name());
        
        if (dest != null) {
            sb.append(" ").append(dest);
        }
        if (op1 != null) {
            sb.append(", ").append(op1);
        }
        if (op2 != null) {
            sb.append(", ").append(op2);
        }
        
        return sb.toString();
    }
}

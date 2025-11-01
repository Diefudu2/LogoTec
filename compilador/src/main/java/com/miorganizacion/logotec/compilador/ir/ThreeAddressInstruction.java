package com.miorganizacion.logotec.compilador.ir;

/**
 * Instrucción de tres direcciones para la representación intermedia.
 */
public class ThreeAddressInstruction {
    private final IROpcode opcode;
    private final Operand dest;
    private final Operand op1;
    private final Operand op2;
    private final String comment;
    
    // Constructor completo
    public ThreeAddressInstruction(IROpcode opcode, Operand dest, Operand op1, Operand op2) {
        this.opcode = opcode;
        this.dest = dest;
        this.op1 = op1;
        this.op2 = op2;
        this.comment = null;
    }
    
    // Constructor con comentario (4 operandos + comentario)
    public ThreeAddressInstruction(IROpcode opcode, Operand dest, Operand op1, Operand op2, String comment) {
        this.opcode = opcode;
        this.dest = dest;
        this.op1 = op1;
        this.op2 = op2;
        this.comment = comment;
    }
    
    // Constructor para instrucciones con dos operandos
    public ThreeAddressInstruction(IROpcode opcode, Operand dest, Operand op1) {
        this(opcode, dest, op1, null);
    }
    
    // Constructor para instrucciones con un operando (destino)
    public ThreeAddressInstruction(IROpcode opcode, Operand dest) {
        this(opcode, dest, null, null);
    }
    
    // Constructor para instrucciones sin operandos
    public ThreeAddressInstruction(IROpcode opcode) {
        this(opcode, null, null, null);
    }
    
    // Constructor especial para COMMENT (opcode + String)
    public ThreeAddressInstruction(IROpcode opcode, String comment) {
        this.opcode = opcode;
        this.dest = null;
        this.op1 = null;
        this.op2 = null;
        this.comment = comment;
    }
    
    // Factory methods
    public static ThreeAddressInstruction label(String name) {
        return new ThreeAddressInstruction(IROpcode.LABEL, Operand.label(name));
    }
    
    public static ThreeAddressInstruction comment(String text) {
        return new ThreeAddressInstruction(IROpcode.COMMENT, text);
    }
    
    public static ThreeAddressInstruction nop() {
        return new ThreeAddressInstruction(IROpcode.NOP);
    }
    
    // Getters
    public IROpcode getOpcode() { return opcode; }
    public Operand getDest() { return dest; }
    public Operand getOp1() { return op1; }
    public Operand getOp2() { return op2; }
    public String getComment() { return comment; }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        if (opcode == IROpcode.COMMENT) {
            return "# " + (comment != null ? comment : "");
        }
        
        if (opcode == IROpcode.LABEL) {
            return (dest != null ? dest.getValue() : "?") + ":";
        }
        
        sb.append(String.format("%-15s", opcode.name()));
        
        if (dest != null) {
            sb.append(dest);
        }
        if (op1 != null) {
            sb.append(", ").append(op1);
        }
        if (op2 != null) {
            sb.append(", ").append(op2);
        }
        
        if (comment != null && !comment.isEmpty()) {
            sb.append("  # ").append(comment);
        }
        
        return sb.toString();
    }
}

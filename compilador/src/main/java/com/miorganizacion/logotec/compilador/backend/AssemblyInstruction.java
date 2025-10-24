package com.miorganizacion.logotec.compilador.backend;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa una instrucción de ensamblador.
 * Formato: opcode operand1, operand2, operand3
 */
public class AssemblyInstruction {
    
    private final AssemblyOpcode opcode;
    private final List<String> operands;
    private final String comment;
    
    // ==================== CONSTRUCTORES ====================
    
    public AssemblyInstruction(AssemblyOpcode opcode) {
        this(opcode, new ArrayList<>(), null);
    }
    
    public AssemblyInstruction(AssemblyOpcode opcode, String comment) {
        this(opcode, new ArrayList<>(), comment);
    }
    
    public AssemblyInstruction(AssemblyOpcode opcode, List<String> operands) {
        this(opcode, operands, null);
    }
    
    public AssemblyInstruction(AssemblyOpcode opcode, List<String> operands, String comment) {
        this.opcode = opcode;
        this.operands = new ArrayList<>(operands);
        this.comment = comment;
    }
    
    // ==================== FACTORY METHODS ====================
    
    public static AssemblyInstruction label(String name) {
        List<String> ops = new ArrayList<>();
        ops.add(name);
        return new AssemblyInstruction(AssemblyOpcode.LABEL, ops);
    }
    
    public static AssemblyInstruction comment(String text) {
        return new AssemblyInstruction(AssemblyOpcode.COMMENT, text);
    }
    
    public static AssemblyInstruction li(Register dest, int value) {
        List<String> ops = new ArrayList<>();
        ops.add(dest.getName());
        ops.add(String.valueOf(value));
        return new AssemblyInstruction(AssemblyOpcode.LI, ops);
    }
    
    public static AssemblyInstruction li(Register dest, double value) {
        List<String> ops = new ArrayList<>();
        ops.add(dest.getName());
        ops.add(String.valueOf(value));
        return new AssemblyInstruction(AssemblyOpcode.LI, ops);
    }
    
    public static AssemblyInstruction lw(Register dest, String varName) {
        List<String> ops = new ArrayList<>();
        ops.add(dest.getName());
        ops.add(varName);
        return new AssemblyInstruction(AssemblyOpcode.LW, ops);
    }
    
    public static AssemblyInstruction sw(Register source, String varName) {
        List<String> ops = new ArrayList<>();
        ops.add(source.getName());
        ops.add(varName);
        return new AssemblyInstruction(AssemblyOpcode.SW, ops);
    }
    
    public static AssemblyInstruction move(Register dest, Register source) {
        List<String> ops = new ArrayList<>();
        ops.add(dest.getName());
        ops.add(source.getName());
        return new AssemblyInstruction(AssemblyOpcode.MOVE, ops);
    }
    
    public static AssemblyInstruction add(Register dest, Register src1, Register src2) {
        List<String> ops = new ArrayList<>();
        ops.add(dest.getName());
        ops.add(src1.getName());
        ops.add(src2.getName());
        return new AssemblyInstruction(AssemblyOpcode.ADD, ops);
    }
    
    public static AssemblyInstruction sub(Register dest, Register src1, Register src2) {
        List<String> ops = new ArrayList<>();
        ops.add(dest.getName());
        ops.add(src1.getName());
        ops.add(src2.getName());
        return new AssemblyInstruction(AssemblyOpcode.SUB, ops);
    }
    
    public static AssemblyInstruction mul(Register dest, Register src1, Register src2) {
        List<String> ops = new ArrayList<>();
        ops.add(dest.getName());
        ops.add(src1.getName());
        ops.add(src2.getName());
        return new AssemblyInstruction(AssemblyOpcode.MUL, ops);
    }
    
    public static AssemblyInstruction div(Register dest, Register src1, Register src2) {
        List<String> ops = new ArrayList<>();
        ops.add(dest.getName());
        ops.add(src1.getName());
        ops.add(src2.getName());
        return new AssemblyInstruction(AssemblyOpcode.DIV, ops);
    }
    
    public static AssemblyInstruction jump(String label) {
        List<String> ops = new ArrayList<>();
        ops.add(label);
        return new AssemblyInstruction(AssemblyOpcode.J, ops);
    }
    
    public static AssemblyInstruction beqz(Register reg, String label) {
        List<String> ops = new ArrayList<>();
        ops.add(reg.getName());
        ops.add(label);
        return new AssemblyInstruction(AssemblyOpcode.BEQZ, ops);
    }
    
    public static AssemblyInstruction bnez(Register reg, String label) {
        List<String> ops = new ArrayList<>();
        ops.add(reg.getName());
        ops.add(label);
        return new AssemblyInstruction(AssemblyOpcode.BNEZ, ops);
    }
    
    public static AssemblyInstruction syscall(int code) {
        List<String> ops = new ArrayList<>();
        ops.add(String.valueOf(code));
        return new AssemblyInstruction(AssemblyOpcode.SYSCALL, ops);
    }
    
    // ==================== GETTERS ====================
    
    public AssemblyOpcode getOpcode() {
        return opcode;
    }
    
    public List<String> getOperands() {
        return new ArrayList<>(operands);
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
        
        // Etiquetas no tienen indentación
        if (opcode == AssemblyOpcode.LABEL) {
            sb.append(operands.get(0)).append(":");
            if (hasComment()) {
                sb.append("    # ").append(comment);
            }
            return sb.toString();
        }
        
        // Comentarios solos
        if (opcode == AssemblyOpcode.COMMENT) {
            return "    # " + comment;
        }
        
        // Directivas
        if (opcode == AssemblyOpcode.DATA || opcode == AssemblyOpcode.TEXT) {
            return "." + opcode.name().toLowerCase();
        }
        
        // Instrucciones normales con indentación
        sb.append("    ");
        sb.append(String.format("%-8s", opcode.name().toLowerCase()));
        
        // Operandos separados por comas
        for (int i = 0; i < operands.size(); i++) {
            if (i > 0) sb.append(", ");
            sb.append(operands.get(i));
        }
        
        // Comentario opcional
        if (hasComment()) {
            sb.append(String.format("%-30s", ""));
            sb.append(" # ").append(comment);
        }
        
        return sb.toString();
    }
    
    /**
     * Representación compacta sin espaciado
     */
    public String toCompactString() {
        if (opcode == AssemblyOpcode.LABEL) {
            return operands.get(0) + ":";
        }
        if (opcode == AssemblyOpcode.COMMENT) {
            return "# " + comment;
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append(opcode.name().toLowerCase());
        
        if (!operands.isEmpty()) {
            sb.append(" ");
            for (int i = 0; i < operands.size(); i++) {
                if (i > 0) sb.append(", ");
                sb.append(operands.get(i));
            }
        }
        
        return sb.toString();
    }
}

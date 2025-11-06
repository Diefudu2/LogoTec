package com.miorganizacion.logotec.compilador.backend;

import java.util.*;

/**
 * Generador de Object Code (Bytecode ejecutable).
 * 
 * Traduce código Assembly a bytecode binario ejecutable por la VM.
 * 
 * Pipeline completo:
 *   LogoTec → AST → IR → Assembly → Bytecode
 * 
 * Responsabilidades:
 * - Traducir instrucciones Assembly a Bytecode
 * - Resolver direcciones de labels
 * - Asignar direcciones de memoria a variables
 * - Generar tabla de símbolos
 * 
 * FASE 4: Object Code Generation
 */
public class ObjectCodeGenerator {
    
    // Tabla de símbolos: variable -> dirección de memoria
    private Map<String, Integer> symbolTable;
    
    // Tabla de labels: label -> dirección de instrucción
    private Map<String, Integer> labelTable;
    
    // Dirección base de memoria para variables (compatible con VM de 1024 words)
    private static final int MEMORY_BASE = 0x0100;  // 256 en decimal
    
    // Contador de direcciones de variables
    private int nextMemoryAddress;
    
    public ObjectCodeGenerator() {
        this.symbolTable = new HashMap<>();
        this.labelTable = new HashMap<>();
        this.nextMemoryAddress = MEMORY_BASE;
    }
    
    /**
     * Genera bytecode desde Assembly
     * 
     * @param assembly Lista de instrucciones Assembly
     * @return Resultado con bytecode, symbol table y label table
     */
    public Result generate(List<AssemblyInstruction> assembly) {
        // PASO 1: Análisis inicial - extraer variables y labels
        extractSymbols(assembly);
        
        // PASO 2: Primera pasada - calcular direcciones de labels
        firstPass(assembly);
        
        // PASO 3: Segunda pasada - generar bytecode
        List<BytecodeInstruction> bytecode = secondPass(assembly);
        
        return new Result(bytecode, symbolTable, labelTable);
    }
    
    /**
     * PASO 1: Extrae variables y asigna direcciones de memoria
     */
    private void extractSymbols(List<AssemblyInstruction> assembly) {
        symbolTable.clear();
        nextMemoryAddress = MEMORY_BASE;
        
        for (AssemblyInstruction instr : assembly) {
            // Buscar referencias a variables en operandos
            for (String operand : instr.getOperands()) {
                if (isVariableName(operand) && !symbolTable.containsKey(operand)) {
                    symbolTable.put(operand, nextMemoryAddress);
                    nextMemoryAddress += 4; // 4 bytes por variable
                }
            }
        }
    }
    
    /**
     * PASO 2: Primera pasada - calcular posiciones de labels
     */
    private void firstPass(List<AssemblyInstruction> assembly) {
        labelTable.clear();
        int instructionAddress = 0;
        
        for (AssemblyInstruction instr : assembly) {
            if (instr.getOpcode() == AssemblyOpcode.LABEL) {
                // Guardar la dirección del label
                String labelName = instr.getOperands().get(0);
                labelTable.put(labelName, instructionAddress);
            } else if (isExecutableInstruction(instr)) {
                // Solo contar instrucciones ejecutables
                instructionAddress++;
            }
        }
    }
    
    /**
     * PASO 3: Segunda pasada - generar bytecode
     */
    private List<BytecodeInstruction> secondPass(List<AssemblyInstruction> assembly) {
        List<BytecodeInstruction> bytecode = new ArrayList<>();
        
        for (AssemblyInstruction instr : assembly) {
            if (!isExecutableInstruction(instr)) {
                continue; // Saltar directivas y labels
            }
            
            BytecodeInstruction bcInstr = translateInstruction(instr);
            if (bcInstr != null) {
                bytecode.add(bcInstr);
            }
        }
        
        // Agregar instrucción HALT al final
        bytecode.add(new BytecodeInstruction(BytecodeOpcode.HALT));
        
        return bytecode;
    }
    
    /**
     * Traduce una instrucción Assembly a Bytecode
     */
    private BytecodeInstruction translateInstruction(AssemblyInstruction instr) {
        List<String> operands = instr.getOperands();
        
        switch (instr.getOpcode()) {
            // === Movimiento de datos ===
            case LI: {
                // li $t0, 50 → LOAD_IMM reg, immediate
                int reg = parseRegister(operands.get(0));
                int imm = parseImmediate(operands.get(1));
                return BytecodeInstruction.withImmediate16(BytecodeOpcode.LOAD_IMM, reg, imm);
            }
            
            case LW: {
                // lw $t0, lado → LOAD_MEM reg, addr
                int reg = parseRegister(operands.get(0));
                int addr = getVariableAddress(operands.get(1));
                return BytecodeInstruction.withImmediate16(BytecodeOpcode.LOAD_MEM, reg, addr);
            }
            
            case SW: {
                // sw $t0, lado → STORE_MEM reg, addr
                int reg = parseRegister(operands.get(0));
                int addr = getVariableAddress(operands.get(1));
                return BytecodeInstruction.withImmediate16(BytecodeOpcode.STORE_MEM, reg, addr);
            }
            
            case MOVE: {
                // move $t0, $t1 → MOVE dest, src
                int dest = parseRegister(operands.get(0));
                int src = parseRegister(operands.get(1));
                return new BytecodeInstruction(BytecodeOpcode.MOVE, dest, src, 0);
            }
            
            // === Operaciones aritméticas ===
            case ADD: {
                // add $t2, $t0, $t1 → ADD dest, src1, src2
                int dest = parseRegister(operands.get(0));
                int src1 = parseRegister(operands.get(1));
                int src2 = parseRegister(operands.get(2));
                return new BytecodeInstruction(BytecodeOpcode.ADD, dest, src1, src2);
            }
            
            case ADDI: {
                // addi $t0, $t1, 5 → ADD_IMM dest, src, immediate
                int dest = parseRegister(operands.get(0));
                int src = parseRegister(operands.get(1));
                int imm = parseImmediate(operands.get(2));
                return new BytecodeInstruction(BytecodeOpcode.ADD_IMM, dest, src, imm);
            }
            
            case SUB: {
                int dest = parseRegister(operands.get(0));
                int src1 = parseRegister(operands.get(1));
                int src2 = parseRegister(operands.get(2));
                return new BytecodeInstruction(BytecodeOpcode.SUB, dest, src1, src2);
            }
            
            case MUL: {
                int dest = parseRegister(operands.get(0));
                int src1 = parseRegister(operands.get(1));
                int src2 = parseRegister(operands.get(2));
                return new BytecodeInstruction(BytecodeOpcode.MUL, dest, src1, src2);
            }
            
            case DIV: {
                int dest = parseRegister(operands.get(0));
                int src1 = parseRegister(operands.get(1));
                int src2 = parseRegister(operands.get(2));
                return new BytecodeInstruction(BytecodeOpcode.DIV, dest, src1, src2);
            }
            
            case REM: {
                int dest = parseRegister(operands.get(0));
                int src1 = parseRegister(operands.get(1));
                int src2 = parseRegister(operands.get(2));
                return new BytecodeInstruction(BytecodeOpcode.REM, dest, src1, src2);
            }
            
            // === Comparaciones ===
            case SLT: {
                int dest = parseRegister(operands.get(0));
                int src1 = parseRegister(operands.get(1));
                int src2 = parseRegister(operands.get(2));
                return new BytecodeInstruction(BytecodeOpcode.SLT, dest, src1, src2);
            }
            
            case SEQ: {
                int dest = parseRegister(operands.get(0));
                int src1 = parseRegister(operands.get(1));
                int src2 = parseRegister(operands.get(2));
                
                // ← VALIDACIÓN: Detectar si Assembly usa mismo registro
                if (dest == src1 && src1 == src2) {
                    System.err.println("⚠️  WARNING: SEQ usa mismo registro para dest/src1/src2: $" + dest);
                    System.err.println("   Instrucción: " + instr);
                }
                
                return new BytecodeInstruction(BytecodeOpcode.SEQ, dest, src1, src2);
            }
            
            case SNE: {
                int dest = parseRegister(operands.get(0));
                int src1 = parseRegister(operands.get(1));
                int src2 = parseRegister(operands.get(2));
                return new BytecodeInstruction(BytecodeOpcode.SNE, dest, src1, src2);
            }
            
            case SGT: {
                int dest = parseRegister(operands.get(0));
                int src1 = parseRegister(operands.get(1));
                int src2 = parseRegister(operands.get(2));
                return new BytecodeInstruction(BytecodeOpcode.SGT, dest, src1, src2);
            }
            
            case SLE: {
                int dest = parseRegister(operands.get(0));
                int src1 = parseRegister(operands.get(1));
                int src2 = parseRegister(operands.get(2));
                return new BytecodeInstruction(BytecodeOpcode.SLE, dest, src1, src2);
            }
            
            case SGE: {
                int dest = parseRegister(operands.get(0));
                int src1 = parseRegister(operands.get(1));
                int src2 = parseRegister(operands.get(2));
                return new BytecodeInstruction(BytecodeOpcode.SGE, dest, src1, src2);
            }
            
            // === Control de flujo ===
            case J: {
                // j L1 → JUMP addr
                String label = operands.get(0);
                int addr = getLabelAddress(label);
                return BytecodeInstruction.withImmediate16(BytecodeOpcode.JUMP, 0, addr);
            }
            
            case BEQZ: {
                // beqz $t0, L1 → BEQZ reg, addr
                int reg = parseRegister(operands.get(0));
                int addr = getLabelAddress(operands.get(1));
                return BytecodeInstruction.withImmediate16(BytecodeOpcode.BEQZ, reg, addr);
            }
            
            case BNEZ: {
                // bnez $t0, L1 → BNEZ reg, addr
                int reg = parseRegister(operands.get(0));
                int addr = getLabelAddress(operands.get(1));
                return BytecodeInstruction.withImmediate16(BytecodeOpcode.BNEZ, reg, addr);
            }
            
            case BEQ: {
                // beq $t0, $t1, L1 → BEQ reg1, reg2, addr
                int reg1 = parseRegister(operands.get(0));
                int reg2 = parseRegister(operands.get(1));
                int addr = getLabelAddress(operands.get(2));
                // Nota: addr en operand3, limitado a 8 bits
                return new BytecodeInstruction(BytecodeOpcode.BEQ, reg1, reg2, addr & 0xFF);
            }
            
            case BNE: {
                int reg1 = parseRegister(operands.get(0));
                int reg2 = parseRegister(operands.get(1));
                int addr = getLabelAddress(operands.get(2));
                return new BytecodeInstruction(BytecodeOpcode.BNE, reg1, reg2, addr & 0xFF);
            }
            
            // === Llamadas a procedimientos ===
            case JAL: {
                // jal proc_label → CALL addr
                String label = operands.get(0);
                int addr = getLabelAddress(label);
                return BytecodeInstruction.withImmediate16(BytecodeOpcode.CALL, 0, addr);
            }
            
            case JR: {
                // jr $ra → RET
                // El registro en $ra se maneja automáticamente por la VM
                return new BytecodeInstruction(BytecodeOpcode.RET);
            }
            
            // === Stack ===
            case PUSH: {
                // push $ra → PUSH reg
                int reg = parseRegister(operands.get(0));
                return new BytecodeInstruction(BytecodeOpcode.PUSH, reg, 0, 0);
            }
            
            case POP: {
                // pop $ra → POP reg
                int reg = parseRegister(operands.get(0));
                return new BytecodeInstruction(BytecodeOpcode.POP, reg, 0, 0);
            }
            
            // === Syscall ===
            case SYSCALL: {
                // syscall <code> → SYSCALL code
                int code = parseImmediate(operands.get(0));
                return new BytecodeInstruction(BytecodeOpcode.SYSCALL, code, 0, 0);
            }
            
            // === No Operation ===
            case NOP: {
                // nop → NOP (0x00 0x00 0x00 0x00)
                return new BytecodeInstruction(BytecodeOpcode.NOP);
            }
            
            default:
                System.err.println("⚠️  Instrucción no soportada: " + instr.getOpcode());
                return new BytecodeInstruction(BytecodeOpcode.NOP);
        }
    }
    
    // === Métodos auxiliares ===
    
    private boolean isExecutableInstruction(AssemblyInstruction instr) {
        AssemblyOpcode op = instr.getOpcode();
        return op != AssemblyOpcode.LABEL && 
               op != AssemblyOpcode.DATA && 
               op != AssemblyOpcode.TEXT &&
               op != AssemblyOpcode.GLOBL &&
               op != AssemblyOpcode.COMMENT;
    }
    
    private boolean isVariableName(String operand) {
        // Las variables no empiezan con $, no son números, y no son labels
        return !operand.startsWith("$") && 
               !operand.matches("-?\\d+") &&
               !operand.startsWith("L") &&
               operand.length() > 0 &&
               Character.isLetter(operand.charAt(0));
    }
    
    private int parseRegister(String reg) {
        // Parsear registros: $t0, $s0, $a0, etc.
        if (reg.startsWith("$")) {
            reg = reg.substring(1); // Quitar el $
        }
        
        // Mapear nombres de registros a números
        if (reg.equals("zero")) return 0;
        if (reg.equals("v0")) return 2;
        if (reg.equals("v1")) return 3;
        if (reg.equals("a0")) return 4;
        if (reg.equals("a1")) return 5;
        if (reg.equals("a2")) return 6;
        if (reg.equals("a3")) return 7;
        
        // Registros especiales (ANTES de startsWith para evitar conflictos)
        if (reg.equals("sp")) return 29;
        if (reg.equals("fp")) return 30;
        if (reg.equals("ra")) return 31;
        
        // Registros temporales: $t0-$t9 → 8-17
        if (reg.startsWith("t")) {
            int num = Integer.parseInt(reg.substring(1));
            return 8 + num;
        }
        
        // Registros guardados: $s0-$s7 → 16-23
        if (reg.startsWith("s")) {
            int num = Integer.parseInt(reg.substring(1));
            return 16 + num;
        }
        
        throw new IllegalArgumentException("Registro desconocido: " + reg);
    }
    
    private int parseImmediate(String value) {
        return Integer.parseInt(value);
    }
    
    private int getVariableAddress(String varName) {
        Integer addr = symbolTable.get(varName);
        if (addr == null) {
            throw new IllegalArgumentException("Variable no declarada: " + varName);
        }
        return addr;
    }
    
    private int getLabelAddress(String label) {
        Integer addr = labelTable.get(label);
        if (addr == null) {
            throw new IllegalArgumentException("Label no encontrado: " + label);
        }
        return addr;
    }
    
    /**
     * Resultado de la generación de Object Code
     */
    public static class Result {
        public final List<BytecodeInstruction> bytecode;
        public final Map<String, Integer> symbolTable;
        public final Map<String, Integer> labelTable;
        
        public Result(List<BytecodeInstruction> bytecode, 
                     Map<String, Integer> symbolTable,
                     Map<String, Integer> labelTable) {
            this.bytecode = bytecode;
            this.symbolTable = new HashMap<>(symbolTable);
            this.labelTable = new HashMap<>(labelTable);
        }
    }
}

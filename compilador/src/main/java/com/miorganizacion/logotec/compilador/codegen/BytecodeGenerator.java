package com.miorganizacion.logotec.compilador.codegen;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * Generador de bytecode simple y autocontenido para una VM de pila.
 * Formato de archivo:
 *  - magic: 'LTBC' (0x4C544243)
 *  - version: byte (1)
 *  - const_count: int
 *    - por cada constante: tag(byte) 1=int, 2=string, seguido del valor
 *  - instr_count: int
 *    - por cada instrucción: opcode(byte), operand_count(byte), luego operandos (int)
 * 
 * Compatible con Java 8+
 */
public final class BytecodeGenerator {

    private BytecodeGenerator() {}

    public static final int MAGIC = 0x4C_54_42_43; // 'LTBC'
    public static final byte VERSION = 1;

    /**
     * Códigos de operación del bytecode
     */
    public enum OpCode {
        // Pila / constantes
        PUSH_CONST(0x01),
        // Aritmética
        ADD(0x10), SUB(0x11), MUL(0x12), DIV(0x13),
        // Variables locales (op = índice)
        LOAD_LOCAL(0x20), STORE_LOCAL(0x21),
        // E/S
        PRINT(0x30),
        // Control
        HALT(0x7F);

        public final int code;
        
        OpCode(int code) { 
            this.code = code; 
        }
        
        private static final Map<Integer, OpCode> BY_CODE = new HashMap<Integer, OpCode>();
        static {
            for (OpCode op : values()) {
                BY_CODE.put(op.code, op);
            }
        }
        
        public static OpCode from(int code) {
            OpCode op = BY_CODE.get(code);
            if (op == null) {
                throw new IllegalArgumentException("Opcode desconocido: " + code);
            }
            return op;
        }
    }

    /**
     * Interfaz base para constantes
     */
    public interface Constant {
        byte tag();
    }

    /**
     * Constante de tipo entero
     */
    public static class IntConst implements Constant {
        private final int value;
        
        public IntConst(int value) {
            this.value = value;
        }
        
        public int getValue() {
            return value;
        }
        
        @Override
        public byte tag() {
            return 1;
        }
        
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof IntConst)) return false;
            IntConst other = (IntConst) obj;
            return this.value == other.value;
        }
        
        @Override
        public int hashCode() {
            return Integer.hashCode(value);
        }
    }

    /**
     * Constante de tipo string
     */
    public static class StringConst implements Constant {
        private final String value;
        
        public StringConst(String value) {
            this.value = value;
        }
        
        public String getValue() {
            return value;
        }
        
        @Override
        public byte tag() {
            return 2;
        }
        
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof StringConst)) return false;
            StringConst other = (StringConst) obj;
            return Objects.equals(this.value, other.value);
        }
        
        @Override
        public int hashCode() {
            return value != null ? value.hashCode() : 0;
        }
    }

    /**
     * Representa una instrucción de bytecode
     */
    public static class Instruction {
        private final OpCode op;
        private final int[] operands;
        
        public Instruction(OpCode op, int... operands) {
            if (op == null) {
                throw new NullPointerException("op no puede ser null");
            }
            this.op = op;
            this.operands = operands != null ? operands.clone() : new int[0];
        }
        
        public OpCode getOp() {
            return op;
        }
        
        public int[] getOperands() {
            return operands.clone();
        }
    }

    /**
     * Representa un programa compilado
     */
    public static class Program {
        private final List<Constant> constants;
        private final List<Instruction> instructions;
        
        public Program(List<Constant> constants, List<Instruction> instructions) {
            if (constants == null) {
                throw new NullPointerException("constants no puede ser null");
            }
            if (instructions == null) {
                throw new NullPointerException("instructions no puede ser null");
            }
            this.constants = new ArrayList<Constant>(constants);
            this.instructions = new ArrayList<Instruction>(instructions);
        }
        
        public List<Constant> getConstants() {
            return Collections.unmodifiableList(constants);
        }
        
        public List<Instruction> getInstructions() {
            return Collections.unmodifiableList(instructions);
        }
    }

    /**
     * Builder para construir programas de bytecode
     */
    public static final class ProgramBuilder {
        private final List<Constant> constants = new ArrayList<Constant>();
        private final Map<Object, Integer> constIndex = new HashMap<Object, Integer>();
        private final List<Instruction> instructions = new ArrayList<Instruction>();
        private int localsCount = 0;

        public int declareLocal() { 
            return localsCount++; 
        }

        public ProgramBuilder pushInt(int value) {
            int idx = constantIndex(new IntConst(value));
            instructions.add(new Instruction(OpCode.PUSH_CONST, idx));
            return this;
        }

        public ProgramBuilder pushString(String value) {
            int idx = constantIndex(new StringConst(value));
            instructions.add(new Instruction(OpCode.PUSH_CONST, idx));
            return this;
        }

        public ProgramBuilder loadLocal(int slot) {
            instructions.add(new Instruction(OpCode.LOAD_LOCAL, slot));
            return this;
        }

        public ProgramBuilder storeLocal(int slot) {
            instructions.add(new Instruction(OpCode.STORE_LOCAL, slot));
            return this;
        }

        public ProgramBuilder add() { 
            instructions.add(new Instruction(OpCode.ADD)); 
            return this; 
        }
        
        public ProgramBuilder sub() { 
            instructions.add(new Instruction(OpCode.SUB)); 
            return this; 
        }
        
        public ProgramBuilder mul() { 
            instructions.add(new Instruction(OpCode.MUL)); 
            return this; 
        }
        
        public ProgramBuilder div() { 
            instructions.add(new Instruction(OpCode.DIV)); 
            return this; 
        }
        
        public ProgramBuilder print() { 
            instructions.add(new Instruction(OpCode.PRINT)); 
            return this; 
        }
        
        public ProgramBuilder halt() { 
            instructions.add(new Instruction(OpCode.HALT)); 
            return this; 
        }

        public Program build() {
            return new Program(constants, instructions);
        }

        private int constantIndex(Constant c) {
            // Crear una clave única para la constante
            Object key;
            if (c instanceof IntConst) {
                IntConst ic = (IntConst) c;
                key = Arrays.asList((byte) 1, ic.getValue());
            } else if (c instanceof StringConst) {
                StringConst sc = (StringConst) c;
                key = Arrays.asList((byte) 2, sc.getValue());
            } else {
                throw new IllegalArgumentException("Tipo de constante no soportado: " + c.getClass());
            }
            
            Integer idx = constIndex.get(key);
            if (idx != null) {
                return idx;
            }
            int newIdx = constants.size();
            constants.add(c);
            constIndex.put(key, newIdx);
            return newIdx;
        }
    }

    /**
     * Serializa un programa a bytecode binario
     */
    public static byte[] serialize(Program program) {
        if (program == null) {
            throw new NullPointerException("program no puede ser null");
        }
        
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(bos);

            out.writeInt(MAGIC);
            out.writeByte(VERSION);

            // Constantes
            List<Constant> constants = program.getConstants();
            out.writeInt(constants.size());
            for (Constant c : constants) {
                out.writeByte(c.tag());
                if (c instanceof IntConst) {
                    IntConst ic = (IntConst) c;
                    out.writeInt(ic.getValue());
                } else if (c instanceof StringConst) {
                    StringConst sc = (StringConst) c;
                    byte[] utf = sc.getValue().getBytes(StandardCharsets.UTF_8);
                    out.writeInt(utf.length);
                    out.write(utf);
                } else {
                    throw new IllegalStateException("Constante no soportada: " + c.getClass());
                }
            }

            // Instrucciones
            List<Instruction> instructions = program.getInstructions();
            out.writeInt(instructions.size());
            for (Instruction ins : instructions) {
                out.writeByte(ins.getOp().code);
                int[] ops = ins.getOperands();
                out.writeByte(ops.length);
                for (int operand : ops) {
                    out.writeInt(operand);
                }
            }

            out.flush();
            return bos.toByteArray();
        } catch (IOException e) {
            throw new IllegalStateException("Error serializando bytecode", e);
        }
    }

    /**
     * Ejemplo mínimo de uso para pruebas rápidas.
     */
    public static void main(String[] args) {
        ProgramBuilder b = new ProgramBuilder();
        // push "Resultado: ", print
        // push 40, push 2, add => 42, print
        b.pushString("Resultado: ").print();
        b.pushInt(40).pushInt(2).add().print();
        b.halt();

        byte[] bytes = serialize(b.build());
        System.out.println("Bytecode generado (" + bytes.length + " bytes). Magic=" + Integer.toHexString(MAGIC));
    }
}

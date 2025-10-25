package com.miorganizacion.logotec.compilador.codegen;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
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
 */
public final class BytecodeGenerator {

    private BytecodeGenerator() {}

    public static final int MAGIC = 0x4C_54_42_43; // 'LTBC'
    public static final byte VERSION = 1;

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
        OpCode(int code) { this.code = code; }
        private static final Map<Integer, OpCode> BY_CODE = new HashMap<>();
        static {
            for (OpCode op : values()) BY_CODE.put(op.code, op);
        }
        public static OpCode from(int code) {
            OpCode op = BY_CODE.get(code);
            if (op == null) throw new IllegalArgumentException("Opcode desconocido: " + code);
            return op;
        }
    }

    public sealed interface Constant permits Constant.IntConst, Constant.StringConst {
        byte tag();
        record IntConst(int value) implements Constant { public byte tag() { return 1; } }
        record StringConst(String value) implements Constant { public byte tag() { return 2; } }
    }

    public record Instruction(OpCode op, int... operands) {
        public Instruction {
            Objects.requireNonNull(op, "op");
            operands = operands == null ? new int[0] : operands;
        }
    }

    public record Program(List<Constant> constants, List<Instruction> instructions) {
        public Program {
            Objects.requireNonNull(constants, "constants");
            Objects.requireNonNull(instructions, "instructions");
        }
    }

    public static final class ProgramBuilder {
        private final List<Constant> constants = new ArrayList<>();
        private final Map<Object, Integer> constIndex = new HashMap<>();
        private final List<Instruction> instructions = new ArrayList<>();
        private int localsCount = 0;

        public int declareLocal() { return localsCount++; }

        public ProgramBuilder pushInt(int value) {
            int idx = constantIndex(new Constant.IntConst(value));
            instructions.add(new Instruction(OpCode.PUSH_CONST, idx));
            return this;
        }

        public ProgramBuilder pushString(String value) {
            int idx = constantIndex(new Constant.StringConst(value));
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

        public ProgramBuilder add() { instructions.add(new Instruction(OpCode.ADD)); return this; }
        public ProgramBuilder sub() { instructions.add(new Instruction(OpCode.SUB)); return this; }
        public ProgramBuilder mul() { instructions.add(new Instruction(OpCode.MUL)); return this; }
        public ProgramBuilder div() { instructions.add(new Instruction(OpCode.DIV)); return this; }
        public ProgramBuilder print() { instructions.add(new Instruction(OpCode.PRINT)); return this; }
        public ProgramBuilder halt() { instructions.add(new Instruction(OpCode.HALT)); return this; }

        public Program build() {
            return new Program(List.copyOf(constants), List.copyOf(instructions));
        }

        private int constantIndex(Constant c) {
            Object key = switch (c) {
                case Constant.IntConst ic -> List.of((byte)1, ic.value());
                case Constant.StringConst sc -> List.of((byte)2, sc.value());
            };
            Integer idx = constIndex.get(key);
            if (idx != null) return idx;
            int newIdx = constants.size();
            constants.add(c);
            constIndex.put(key, newIdx);
            return newIdx;
        }
    }

    public static byte[] serialize(Program program) {
        Objects.requireNonNull(program, "program");
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(bos);

            out.writeInt(MAGIC);
            out.writeByte(VERSION);

            // Constantes
            out.writeInt(program.constants().size());
            for (Constant c : program.constants()) {
                out.writeByte(c.tag());
                if (c instanceof Constant.IntConst ic) {
                    out.writeInt(ic.value());
                } else if (c instanceof Constant.StringConst sc) {
                    byte[] utf = sc.value().getBytes(java.nio.charset.StandardCharsets.UTF_8);
                    out.writeInt(utf.length);
                    out.write(utf);
                } else {
                    throw new IllegalStateException("Constante no soportada: " + c.getClass());
                }
            }

            // Instrucciones
            out.writeInt(program.instructions().size());
            for (Instruction ins : program.instructions()) {
                out.writeByte(ins.op().code);
                int[] ops = ins.operands();
                out.writeByte(ops.length);
                for (int operand : ops) out.writeInt(operand);
            }

            out.flush();
            return bos.toByteArray();
        } catch (IOException e) {
            throw new IllegalStateException("Error serializando bytecode", e);
        }
    }

    // Ejemplo mínimo de uso para pruebas rápidas.
    // Elimina o adapta según el flujo del compilador.
    public static void main(String[] args) {
        ProgramBuilder b = new ProgramBuilder();
        // c0 = "Resultado: "
        // push 40, push 2, add => 42
        b.pushString("Resultado: ").print();
        b.pushInt(40).pushInt(2).add().print();
        b.halt();

        byte[] bytes = serialize(b.build());
        System.out.println("Bytecode generado (" + bytes.length + " bytes). Magic=" + Integer.toHexString(MAGIC));
    }
}

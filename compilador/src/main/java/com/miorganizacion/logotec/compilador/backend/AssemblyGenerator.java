package com.miorganizacion.logotec.compilador.backend;

import com.miorganizacion.logotec.compilador.ir.*;
import java.util.*;

/**
 * Generador de código Assembly desde IR.
 * Traduce instrucciones de tres direcciones a ensamblador MIPS-like.
 * 
 * FASE 3: IR → Assembly
 */
public class AssemblyGenerator {
    
    private final List<AssemblyInstruction> instructions;
    private final RegisterAllocator regAlloc;
    private final Set<String> variables;
    private final Map<Integer, String> syscallCodes;
    
    public AssemblyGenerator() {
        this.instructions = new ArrayList<>();
        this.regAlloc = new RegisterAllocator();
        this.variables = new LinkedHashSet<>();
        this.syscallCodes = initSyscallCodes();
    }
    
    /**
     * Códigos de syscall para comandos de tortuga.
     */
    private Map<Integer, String> initSyscallCodes() {
        Map<Integer, String> codes = new HashMap<>();
        codes.put(1, "FORWARD");
        codes.put(2, "BACKWARD");
        codes.put(3, "TURN_RIGHT");
        codes.put(4, "TURN_LEFT");
        codes.put(5, "PEN_UP");
        codes.put(6, "PEN_DOWN");
        codes.put(7, "CENTER");
        codes.put(8, "SET_POS");
        codes.put(9, "SET_COLOR");
        codes.put(10, "SET_HEADING");
        codes.put(11, "HIDE_TURTLE");
        codes.put(12, "SHOW_TURTLE");
        return codes;
    }
    
    /**
     * Genera código Assembly desde una lista de instrucciones IR.
     */
    public List<AssemblyInstruction> generate(List<ThreeAddressInstruction> irCode) {
        instructions.clear();
        variables.clear();
        regAlloc.reset();
        
        // Recopilar variables del IR
        collectVariables(irCode);
        
        // Generar sección de datos
        generateDataSection();
        
        // Generar sección de texto (código)
        generateTextSection(irCode);
        
        return new ArrayList<>(instructions);
    }
    
    /**
     * Recopila todas las variables usadas en el código IR.
     */
    private void collectVariables(List<ThreeAddressInstruction> irCode) {
        for (ThreeAddressInstruction instr : irCode) {
            if (instr.getOpcode() == IROpcode.STORE) {
                Operand dest = instr.getDest();
                if (dest != null && dest.isVariable()) {
                    variables.add(dest.getValue());
                }
            }
            if (instr.getOpcode() == IROpcode.LOAD_VAR) {
                Operand op1 = instr.getOp1();
                if (op1 != null && op1.isVariable()) {
                    variables.add(op1.getValue());
                }
            }
        }
    }
    
    /**
     * Genera la sección de datos (.data).
     */
    private void generateDataSection() {
        if (variables.isEmpty()) {
            return;
        }
        
        emit(new AssemblyInstruction(AssemblyOpcode.DATA));
        emit(AssemblyInstruction.comment("Variables del programa"));
        
        for (String var : variables) {
            // Formato: variable: .word 0
            instructions.add(new AssemblyInstruction(
                AssemblyOpcode.COMMENT, 
                var + ": .word 0"
            ));
        }
        
        emit(new AssemblyInstruction(AssemblyOpcode.COMMENT, ""));
    }
    
    /**
     * Genera la sección de texto (.text) con el código.
     */
    private void generateTextSection(List<ThreeAddressInstruction> irCode) {
        emit(new AssemblyInstruction(AssemblyOpcode.TEXT));
        emit(new AssemblyInstruction(AssemblyOpcode.GLOBL, 
            Arrays.asList("main"), "Punto de entrada"));
        emit(AssemblyInstruction.comment(""));
        
        // Saltar a main al inicio (para evitar ejecutar procedimientos directamente)
        emit(AssemblyInstruction.jump("main"));
        emit(AssemblyInstruction.comment(""));
        
        for (ThreeAddressInstruction ir : irCode) {
            translateInstruction(ir);
        }
        
        // Agregar código de salida al final
        emit(AssemblyInstruction.comment("Terminar programa"));
        emit(AssemblyInstruction.li(Register.V0, 10)); // syscall exit
        emit(AssemblyInstruction.syscall(10));
    }
    
    /**
     * Traduce una instrucción IR a una o más instrucciones Assembly.
     */
    private void translateInstruction(ThreeAddressInstruction ir) {
        IROpcode opcode = ir.getOpcode();
        
        switch (opcode) {
            case COMMENT:
                if (ir.getComment() != null) {
                    emit(AssemblyInstruction.comment(ir.getComment()));
                }
                break;
                
            case LABEL:
                emit(AssemblyInstruction.label(ir.getDest().getValue()));
                break;
                
            case LOAD_CONST:
                translateLoadConst(ir);
                break;
                
            case LOAD_VAR:
                translateLoadVar(ir);
                break;
                
            case STORE:
                translateStore(ir);
                break;
                
            case MOVE:
                translateMove(ir);
                break;
                
            case ADD:
                translateBinaryOp(ir, AssemblyOpcode.ADD);
                break;
                
            case SUB:
                translateBinaryOp(ir, AssemblyOpcode.SUB);
                break;
                
            case MUL:
                translateBinaryOp(ir, AssemblyOpcode.MUL);
                break;
                
            case DIV:
                translateBinaryOp(ir, AssemblyOpcode.DIV);
                break;
                
            case POW:
                translatePow(ir);
                break;
                
            case LT:
                translateComparison(ir, "slt");
                break;
                
            case GT:
                translateComparison(ir, "sgt");
                break;
                
            case LTE:
                translateComparison(ir, "sle");
                break;
                
            case GTE:
                translateComparison(ir, "sge");
                break;
                
            case EQ:
                translateComparison(ir, "seq");
                break;
                
            case NEQ:
                translateComparison(ir, "sne");
                break;
                
            case JUMP:
                emit(AssemblyInstruction.jump(ir.getDest().getValue()));
                break;
                
            case JUMP_IF_FALSE:
                translateJumpIfFalse(ir);
                break;
                
            case JUMP_IF_TRUE:
                translateJumpIfTrue(ir);
                break;
                
            case FORWARD:
                translateTurtleCommand(ir, 1);
                break;
                
            case BACKWARD:
                translateTurtleCommand(ir, 2);
                break;
                
            case TURN_RIGHT:
                translateTurtleCommand(ir, 3);
                break;
                
            case TURN_LEFT:
                translateTurtleCommand(ir, 4);
                break;
                
            case PEN_UP:
                translateTurtleNoArg(5, "PEN_UP");
                break;
                
            case PEN_DOWN:
                translateTurtleNoArg(6, "PEN_DOWN");
                break;
                
            case CENTER:
                translateTurtleNoArg(7, "CENTER");
                break;
                
            case SET_POS:
                translateSetPos(ir);
                break;
                
            case SET_COLOR:
                translateSetColor(ir);
                break;
                
            case SET_HEADING:
                translateTurtleCommand(ir, 10);
                break;
                
            case HIDE_TURTLE:
                translateTurtleNoArg(11, "HIDE_TURTLE");
                break;
                
            case SHOW_TURTLE:
                translateTurtleNoArg(12, "SHOW_TURTLE");
                break;
                
            case NOP:
                emit(new AssemblyInstruction(AssemblyOpcode.NOP, 
                    ir.getComment() != null ? ir.getComment() : "nop"));
                break;
                
            // ==================== PROCEDIMIENTOS ====================
            case PROC_BEGIN:
                // Etiqueta ya generada por LABEL anterior
                emit(AssemblyInstruction.comment("Begin procedure: " + ir.getDest().getValue()));
                // Prólogo del procedimiento: guardar $ra y $fp en el stack
                emit(new AssemblyInstruction(AssemblyOpcode.PUSH, Arrays.asList(Register.RA.toString())));
                emit(new AssemblyInstruction(AssemblyOpcode.PUSH, Arrays.asList(Register.FP.toString())));
                emit(AssemblyInstruction.move(Register.FP, Register.SP));
                break;
                
            case PROC_END:
                emit(AssemblyInstruction.comment("End procedure: " + ir.getDest().getValue()));
                break;
                
            case CALL:
                translateCall(ir);
                break;
                
            case RETURN:
                translateReturn(ir);
                break;
                
            case PARAM:
                translateParam(ir);
                break;
                
            case GET_ARG:
                translateGetArg(ir);
                break;
                
            default:
                emit(AssemblyInstruction.comment("IR no soportado: " + opcode));
                break;
        }
    }
    
    // ==================== TRADUCCIONES ESPECÍFICAS ====================
    
    private void translateLoadConst(ThreeAddressInstruction ir) {
        Operand dest = ir.getDest();
        Operand value = ir.getOp1();
        
        Register reg = regAlloc.getRegister(dest.getValue());
        
        if (value.isConstant()) {
            double val = value.getNumericValue();
            if (val == (int) val) {
                emit(AssemblyInstruction.li(reg, (int) val));
            } else {
                emit(AssemblyInstruction.li(reg, val));
            }
        }
    }
    
    private void translateLoadVar(ThreeAddressInstruction ir) {
        Operand dest = ir.getDest();
        Operand var = ir.getOp1();
        
        Register reg = regAlloc.getRegister(dest.getValue());
        emit(AssemblyInstruction.lw(reg, var.getValue()));
    }
    
    private void translateStore(ThreeAddressInstruction ir) {
        Operand var = ir.getDest();
        Operand source = ir.getOp1();
        
        Register reg = regAlloc.getRegister(source.getValue());
        emit(AssemblyInstruction.sw(reg, var.getValue()));
    }
    
    private void translateMove(ThreeAddressInstruction ir) {
        Operand dest = ir.getDest();
        Operand source = ir.getOp1();
        
        // Si source es constante, necesitamos cargarla primero
        if (source.isConstant()) {
            Register destReg = regAlloc.getRegister(dest.getValue());
            double value = source.getNumericValue();
            emit(AssemblyInstruction.li(destReg, (int) value));
        } else {
            Register destReg = regAlloc.getRegister(dest.getValue());
            Register srcReg = regAlloc.getRegister(source.getValue());
            emit(AssemblyInstruction.move(destReg, srcReg));
        }
    }
    
    private void translateBinaryOp(ThreeAddressInstruction ir, AssemblyOpcode asmOp) {
        Operand dest = ir.getDest();
        Operand op1 = ir.getOp1();
        Operand op2 = ir.getOp2();
        
        // ✅ SOLUCIÓN: NO usar registros fijos, usar DIRECTAMENTE los registros asignados
        
        // 1. Obtener/cargar operando 1
        Register src1Reg;
        if (op1.isConstant()) {
            src1Reg = Register.T7;  // Usar $t7 solo para constantes
            emit(AssemblyInstruction.li(src1Reg, (int) op1.getNumericValue()));
        } else if (op1.isVariable()) {
            src1Reg = Register.T7;  // Usar $t7 solo para variables
            emit(AssemblyInstruction.lw(src1Reg, op1.getValue()));
        } else {
            // ✅ CAMBIO: Usar DIRECTAMENTE el registro del temporal (NO moverlo)
            src1Reg = regAlloc.getRegister(op1.getValue());
        }
        
        // 2. Obtener/cargar operando 2
        Register src2Reg;
        if (op2.isConstant()) {
            src2Reg = Register.T8;  // Usar $t8 solo para constantes
            emit(AssemblyInstruction.li(src2Reg, (int) op2.getNumericValue()));
        } else if (op2.isVariable()) {
            src2Reg = Register.T8;  // Usar $t8 solo para variables
            emit(AssemblyInstruction.lw(src2Reg, op2.getValue()));
        } else {
            // ✅ CAMBIO: Usar DIRECTAMENTE el registro del temporal (NO moverlo)
            src2Reg = regAlloc.getRegister(op2.getValue());
        }
        
        // 3. Obtener registro destino
        Register resultReg = regAlloc.getRegister(dest.getValue());
        
        // 4. Ejecutar operación
        emit(new AssemblyInstruction(asmOp, 
            Arrays.asList(resultReg.getName(), src1Reg.getName(), src2Reg.getName())));
    }
    
    private void translateComparison(ThreeAddressInstruction ir, String op) {
        emit(AssemblyInstruction.comment("Comparación: " + op));
        
        Operand dest = ir.getDest();
        Operand op1 = ir.getOp1();
        Operand op2 = ir.getOp2();
        
        // ✅ SOLUCIÓN: NO usar registros fijos, usar DIRECTAMENTE los registros asignados
        
        // 1. Obtener/cargar operando 1
        Register src1Reg;
        if (op1.isConstant()) {
            src1Reg = Register.T7;  // Usar $t7 solo para constantes
            emit(AssemblyInstruction.li(src1Reg, (int) op1.getNumericValue()));
        } else if (op1.isVariable()) {
            src1Reg = Register.T7;  // Usar $t7 solo para variables
            emit(AssemblyInstruction.lw(src1Reg, op1.getValue()));
        } else {
            // ✅ CAMBIO: Usar DIRECTAMENTE el registro del temporal (NO moverlo)
            src1Reg = regAlloc.getRegister(op1.getValue());
        }
        
        // 2. Obtener/cargar operando 2
        Register src2Reg;
        if (op2.isConstant()) {
            src2Reg = Register.T8;  // Usar $t8 solo para constantes
            emit(AssemblyInstruction.li(src2Reg, (int) op2.getNumericValue()));
        } else if (op2.isVariable()) {
            src2Reg = Register.T8;  // Usar $t8 solo para variables
            emit(AssemblyInstruction.lw(src2Reg, op2.getValue()));
        } else {
            // ✅ CAMBIO: Usar DIRECTAMENTE el registro del temporal (NO moverlo)
            src2Reg = regAlloc.getRegister(op2.getValue());
        }
        
        // 3. Obtener registro destino
        Register destReg = regAlloc.getRegister(dest.getValue());
        
        // 4. Ejecutar comparación
        emit(new AssemblyInstruction(
            AssemblyOpcode.valueOf(op.toUpperCase()),
            Arrays.asList(destReg.getName(), src1Reg.getName(), src2Reg.getName())
        ));
        
        // 4. Guardar resultado en el registro del temporal destino
        Register finalDestReg = regAlloc.getRegister(dest.getValue());
        if (!destReg.equals(finalDestReg)) {
            emit(AssemblyInstruction.move(finalDestReg, destReg));
        }
    }
    
    private void translatePow(ThreeAddressInstruction ir) {
        emit(AssemblyInstruction.comment("Potencia (llamada a runtime)"));
        // En una implementación real, esto llamaría a una función de librería
        // Por ahora, simplificamos
        Operand dest = ir.getDest();
        Register destReg = regAlloc.getRegister(dest.getValue());
        emit(AssemblyInstruction.li(destReg, 0));
        emit(AssemblyInstruction.comment("TODO: implementar pow"));
    }
    
    private void translateJumpIfFalse(ThreeAddressInstruction ir) {
        Operand label = ir.getDest();
        Operand condition = ir.getOp1();
        
        Register condReg = regAlloc.getRegister(condition.getValue());
        emit(AssemblyInstruction.beqz(condReg, label.getValue()));
    }
    
    private void translateJumpIfTrue(ThreeAddressInstruction ir) {
        Operand label = ir.getDest();
        Operand condition = ir.getOp1();
        
        Register condReg = regAlloc.getRegister(condition.getValue());
        emit(AssemblyInstruction.bnez(condReg, label.getValue()));
    }
    
    private void translateTurtleCommand(ThreeAddressInstruction ir, int syscallCode) {
        Operand arg = ir.getDest();
        
        emit(AssemblyInstruction.comment("Comando tortuga: " + syscallCodes.get(syscallCode)));
        
        // Cargar argumento en $a0
        Register argReg = regAlloc.getRegister(arg.getValue());
        emit(AssemblyInstruction.move(Register.A0, argReg));
        
        // Cargar código syscall en $v0
        emit(AssemblyInstruction.li(Register.V0, syscallCode));
        
        // Ejecutar syscall
        emit(AssemblyInstruction.syscall(syscallCode));
    }
    
    private void translateTurtleNoArg(int syscallCode, String name) {
        emit(AssemblyInstruction.comment("Comando tortuga: " + name));
        emit(AssemblyInstruction.li(Register.V0, syscallCode));
        emit(AssemblyInstruction.syscall(syscallCode));
    }
    
    private void translateSetPos(ThreeAddressInstruction ir) {
        emit(AssemblyInstruction.comment("SET_POS(x, y)"));
        
        Operand x = ir.getDest();
        Operand y = ir.getOp1();
        
        Register xReg = regAlloc.getRegister(x.getValue());
        Register yReg = regAlloc.getRegister(y.getValue());
        
        emit(AssemblyInstruction.move(Register.A0, xReg));
        emit(AssemblyInstruction.move(Register.A1, yReg));
        emit(AssemblyInstruction.li(Register.V0, 8));
        emit(AssemblyInstruction.syscall(8));
    }
    
    private void translateSetColor(ThreeAddressInstruction ir) {
        emit(AssemblyInstruction.comment("SET_COLOR(r, g, b)"));

        Operand r = ir.getDest();
        Operand g = ir.getOp1();
        Operand b = ir.getOp2();

        moveOperandToArg(Register.A0, r);
        moveOperandToArg(Register.A1, g);
        moveOperandToArg(Register.A2, b);

        emit(AssemblyInstruction.li(Register.V0, 9));
        emit(AssemblyInstruction.syscall(9));
    }
    
    // ==================== UTILIDADES ====================
    
    private void emit(AssemblyInstruction instr) {
        instructions.add(instr);
    }
    
    /**
     * Carga un operando en un registro y lo retorna.
     * Maneja constantes, variables y temporales.
     */
    private Register loadOperandToRegister(Operand op) {
        if (op.isConstant()) {
            // Cargar constante en registro temporal
            Register reg = Register.T0;  // Usar $t0 como temporal
            double value = Double.parseDouble(op.getValue());
            emit(AssemblyInstruction.li(reg, (int) value));
            return reg;
        } else if (op.isVariable()) {
            // Cargar variable desde memoria
            Register reg = regAlloc.getRegister(op.getValue());
            return reg;
        } else {
            // Temporal: ya tiene un registro asignado
            Register reg = regAlloc.getRegister(op.getValue());
            return reg;
        }
    }
    
    // ==================== TRADUCCIONES DE PROCEDIMIENTOS ====================
    
    /**
     * Traduce CALL: llamada a procedimiento
     * IR: CALL dest, proc_name, num_args
     */
    private void translateCall(ThreeAddressInstruction ir) {
        Operand procLabel = ir.getOp1();  // Nombre del procedimiento
        
        emit(AssemblyInstruction.comment("Call " + procLabel.getValue()));
        
        // JAL salta y guarda la dirección de retorno en $ra
        emit(new AssemblyInstruction(AssemblyOpcode.JAL, Arrays.asList(procLabel.getValue())));
    }
    
    /**
     * Traduce RETURN: retorno de procedimiento
     * IR: RETURN [valor_opcional]
     */
    private void translateReturn(ThreeAddressInstruction ir) {
        emit(AssemblyInstruction.comment("Return from procedure"));
        
        // Epílogo: restaurar $fp y $ra
        emit(AssemblyInstruction.move(Register.SP, Register.FP));
        emit(new AssemblyInstruction(AssemblyOpcode.POP, Arrays.asList(Register.FP.toString())));
        emit(new AssemblyInstruction(AssemblyOpcode.POP, Arrays.asList(Register.RA.toString())));
        
        // Retornar saltando a $ra
        emit(new AssemblyInstruction(AssemblyOpcode.JR, Arrays.asList(Register.RA.toString())));
    }
    
    /**
     * Traduce PARAM: pasar parámetro
     * IR: PARAM index, value
     * 
     * Convención: Parámetros en registros $a0-$a3 (primeros 4)
     * Los demás se ponen en el stack
     */
    private void translateParam(ThreeAddressInstruction ir) {
        Operand indexOp = ir.getDest();
        Operand value = ir.getOp1();
        
        int index = (int) Double.parseDouble(indexOp.getValue());
        
        emit(AssemblyInstruction.comment("Param[" + index + "] = " + value.getValue()));
        
        // Cargar el valor en un registro temporal
        Register valueReg = loadOperandToRegister(value);
        
        // Según el índice, mover a $a0, $a1, $a2 o $a3
        Register argReg;
        switch (index) {
            case 0: argReg = Register.A0; break;
            case 1: argReg = Register.A1; break;
            case 2: argReg = Register.A2; break;
            case 3: argReg = Register.A3; break;
            default:
                // Si hay más de 4 parámetros, push al stack
                emit(new AssemblyInstruction(AssemblyOpcode.PUSH, Arrays.asList(valueReg.toString())));
                return;
        }
        
        emit(AssemblyInstruction.move(argReg, valueReg));
    }
    
    /**
     * Traduce GET_ARG: obtener argumento dentro del procedimiento
     * IR: GET_ARG dest, index
     * 
     * Los argumentos están en $a0-$a3 o en el stack
     */
    private void translateGetArg(ThreeAddressInstruction ir) {
        Operand dest = ir.getDest();
        Operand indexOp = ir.getOp1();
        
        int index = (int) Double.parseDouble(indexOp.getValue());
        
        emit(AssemblyInstruction.comment("Get arg[" + index + "] -> " + dest.getValue()));
        
        // Registro fuente del argumento
        Register argReg;
        switch (index) {
            case 0: argReg = Register.A0; break;
            case 1: argReg = Register.A1; break;
            case 2: argReg = Register.A2; break;
            case 3: argReg = Register.A3; break;
            default:
                emit(AssemblyInstruction.comment("TODO: Load from stack for arg > 3"));
                return;
        }
        
        // ← CAMBIO: Guardar DIRECTAMENTE en memoria en lugar de moverlo a un registro temporal
        // El destino es una variable, así que guardarlo en su dirección de memoria
        if (dest.isVariable()) {
            String varName = dest.getValue();
            emit(AssemblyInstruction.sw(argReg, varName));  // sw $aX, varName
        } else {
            // Si es un temporal, moverlo al registro correspondiente
            Register destReg = regAlloc.getRegister(dest.getValue());
            emit(AssemblyInstruction.move(destReg, argReg));
        }
    }
    
    /**
     * Obtiene las instrucciones generadas.
     */
    public List<AssemblyInstruction> getInstructions() {
        return new ArrayList<>(instructions);
    }
    
    /**
     * Obtiene el allocador de registros (para debugging).
     */
    public RegisterAllocator getRegisterAllocator() {
        return regAlloc;
    }
    
    private void moveOperandToArg(Register target, Operand value) {
        if (value == null) {
            emit(AssemblyInstruction.li(target, 0));
            return;
        }

        if (value.isConstant()) {
            emit(AssemblyInstruction.li(target, (int) value.getNumericValue()));
        } else {
            Register valueReg = regAlloc.getRegister(value.getValue());
            emit(AssemblyInstruction.move(target, valueReg));
        }
    }
}

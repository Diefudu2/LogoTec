package com.miorganizacion.logotec.compilador.ir;

import java.util.ArrayList;
import java.util.List;

/**
 * Builder para facilitar la construcción de código IR.
 * Proporciona métodos fluent para crear instrucciones de forma más legible.
 * 
 * Ejemplo de uso:
 * <pre>
 * IRBuilder builder = new IRBuilder();
 * builder.loadConst(t1, 50)
 *        .store("lado", t1)
 *        .loadVar(t2, "lado")
 *        .forward(t2);
 * List&lt;ThreeAddressInstruction&gt; code = builder.getInstructions();
 * </pre>
 */
public class IRBuilder {
    
    private final List<ThreeAddressInstruction> instructions;
    private final TempGenerator tempGen;
    private final LabelGenerator labelGen;
    
    public IRBuilder() {
        this.instructions = new ArrayList<>();
        this.tempGen = new TempGenerator();
        this.labelGen = new LabelGenerator();
    }
    
    // ==================== GENERADORES ====================
    
    public TempGenerator getTempGen() {
        return tempGen;
    }
    
    public LabelGenerator getLabelGen() {
        return labelGen;
    }
    
    /**
     * Obtiene todas las instrucciones generadas
     */
    public List<ThreeAddressInstruction> getInstructions() {
        return instructions;
    }
    
    /**
     * Agrega una instrucción existente
     */
    public IRBuilder add(ThreeAddressInstruction instr) {
        instructions.add(instr);
        return this;
    }
    
    /**
     * Agrega múltiples instrucciones
     */
    public IRBuilder addAll(List<ThreeAddressInstruction> instrs) {
        instructions.addAll(instrs);
        return this;
    }
    
    /**
     * Limpia todas las instrucciones
     */
    public IRBuilder clear() {
        instructions.clear();
        return this;
    }
    
    // ==================== OPERACIONES DE MOVIMIENTO ====================
    
    public IRBuilder loadConst(Operand dest, double value) {
        instructions.add(new ThreeAddressInstruction(
            IROpcode.LOAD_CONST, dest, Operand.constant(value)
        ));
        return this;
    }
    
    public IRBuilder loadVar(Operand dest, String varName) {
        instructions.add(new ThreeAddressInstruction(
            IROpcode.LOAD_VAR, dest, Operand.variable(varName)
        ));
        return this;
    }
    
    public IRBuilder store(String varName, Operand source) {
        instructions.add(new ThreeAddressInstruction(
            IROpcode.STORE, Operand.variable(varName), source
        ));
        return this;
    }
    
    public IRBuilder move(Operand dest, Operand source) {
        instructions.add(new ThreeAddressInstruction(
            IROpcode.MOVE, dest, source
        ));
        return this;
    }
    
    // ==================== OPERACIONES ARITMÉTICAS ====================
    
    public IRBuilder add(Operand dest, Operand op1, Operand op2) {
        instructions.add(new ThreeAddressInstruction(
            IROpcode.ADD, dest, op1, op2
        ));
        return this;
    }
    
    public IRBuilder sub(Operand dest, Operand op1, Operand op2) {
        instructions.add(new ThreeAddressInstruction(
            IROpcode.SUB, dest, op1, op2
        ));
        return this;
    }
    
    public IRBuilder mul(Operand dest, Operand op1, Operand op2) {
        instructions.add(new ThreeAddressInstruction(
            IROpcode.MUL, dest, op1, op2
        ));
        return this;
    }
    
    public IRBuilder div(Operand dest, Operand op1, Operand op2) {
        instructions.add(new ThreeAddressInstruction(
            IROpcode.DIV, dest, op1, op2
        ));
        return this;
    }
    
    public IRBuilder pow(Operand dest, Operand base, Operand exp) {
        instructions.add(new ThreeAddressInstruction(
            IROpcode.POW, dest, base, exp
        ));
        return this;
    }
    
    // ==================== OPERACIONES DE COMPARACIÓN ====================
    
    public IRBuilder eq(Operand dest, Operand op1, Operand op2) {
        instructions.add(new ThreeAddressInstruction(
            IROpcode.EQ, dest, op1, op2
        ));
        return this;
    }
    
    public IRBuilder lt(Operand dest, Operand op1, Operand op2) {
        instructions.add(new ThreeAddressInstruction(
            IROpcode.LT, dest, op1, op2
        ));
        return this;
    }
    
    public IRBuilder gt(Operand dest, Operand op1, Operand op2) {
        instructions.add(new ThreeAddressInstruction(
            IROpcode.GT, dest, op1, op2
        ));
        return this;
    }
    
    // ==================== CONTROL DE FLUJO ====================
    
    public IRBuilder label(String labelName) {
        instructions.add(new ThreeAddressInstruction(
            IROpcode.LABEL, Operand.label(labelName)
        ));
        return this;
    }
    
    public IRBuilder label(Operand label) {
        instructions.add(new ThreeAddressInstruction(
            IROpcode.LABEL, label
        ));
        return this;
    }
    
    public IRBuilder jump(String labelName) {
        instructions.add(new ThreeAddressInstruction(
            IROpcode.JUMP, Operand.label(labelName)
        ));
        return this;
    }
    
    public IRBuilder jump(Operand label) {
        instructions.add(new ThreeAddressInstruction(
            IROpcode.JUMP, label
        ));
        return this;
    }
    
    public IRBuilder jumpIfFalse(Operand label, Operand condition) {
        instructions.add(new ThreeAddressInstruction(
            IROpcode.JUMP_IF_FALSE, label, condition
        ));
        return this;
    }
    
    public IRBuilder jumpIfTrue(Operand label, Operand condition) {
        instructions.add(new ThreeAddressInstruction(
            IROpcode.JUMP_IF_TRUE, label, condition
        ));
        return this;
    }
    
    // ==================== COMANDOS DE TORTUGA ====================
    
    public IRBuilder forward(Operand distance) {
        instructions.add(new ThreeAddressInstruction(
            IROpcode.FORWARD, distance
        ));
        return this;
    }
    
    public IRBuilder backward(Operand distance) {
        instructions.add(new ThreeAddressInstruction(
            IROpcode.BACKWARD, distance
        ));
        return this;
    }
    
    public IRBuilder turnRight(Operand degrees) {
        instructions.add(new ThreeAddressInstruction(
            IROpcode.TURN_RIGHT, degrees
        ));
        return this;
    }
    
    public IRBuilder turnLeft(Operand degrees) {
        instructions.add(new ThreeAddressInstruction(
            IROpcode.TURN_LEFT, degrees
        ));
        return this;
    }
    
    public IRBuilder penDown() {
        instructions.add(new ThreeAddressInstruction(IROpcode.PEN_DOWN));
        return this;
    }
    
    public IRBuilder penUp() {
        instructions.add(new ThreeAddressInstruction(IROpcode.PEN_UP));
        return this;
    }
    
    public IRBuilder center() {
        instructions.add(new ThreeAddressInstruction(IROpcode.CENTER));
        return this;
    }
    
    public IRBuilder setColor(Operand r, Operand g, Operand b) {
        instructions.add(new ThreeAddressInstruction(
            IROpcode.SET_COLOR, r, g, b
        ));
        return this;
    }
    
    public IRBuilder setPos(Operand x, Operand y) {
        instructions.add(new ThreeAddressInstruction(
            IROpcode.SET_POS, x, y
        ));
        return this;
    }
    
    // ==================== UTILIDADES ====================
    
    public IRBuilder comment(String text) {
        instructions.add(new ThreeAddressInstruction(
            IROpcode.COMMENT, null, text
        ));
        return this;
    }
    
    public IRBuilder nop() {
        instructions.add(new ThreeAddressInstruction(IROpcode.NOP));
        return this;
    }
    
    // ==================== PROCEDIMIENTOS ====================
    
    /**
     * Inicio de definición de procedimiento
     */
    public IRBuilder procBegin(String procName) {
        instructions.add(new ThreeAddressInstruction(
            IROpcode.PROC_BEGIN, Operand.label(procName), "procedure " + procName
        ));
        return this;
    }
    
    /**
     * Fin de definición de procedimiento
     */
    public IRBuilder procEnd(String procName) {
        instructions.add(new ThreeAddressInstruction(
            IROpcode.PROC_END, Operand.label(procName), "end " + procName
        ));
        return this;
    }
    
    /**
     * Llamada a procedimiento
     * @param dest Operando donde se almacena el resultado (puede ser null para procedimientos void)
     * @param procName Nombre del procedimiento
     * @param numArgs Número de argumentos
     */
    public IRBuilder call(Operand dest, String procName, int numArgs) {
        instructions.add(new ThreeAddressInstruction(
            IROpcode.CALL, dest, Operand.label(procName), Operand.constant(numArgs),
            "call " + procName + "(" + numArgs + " args)"
        ));
        return this;
    }
    
    /**
     * Parámetro para llamada a procedimiento
     * @param index Índice del parámetro (0, 1, 2, ...)
     * @param value Valor del parámetro
     */
    public IRBuilder param(int index, Operand value) {
        instructions.add(new ThreeAddressInstruction(
            IROpcode.PARAM, Operand.constant(index), value,
            "param[" + index + "] = " + value
        ));
        return this;
    }
    
    /**
     * Obtener argumento dentro del procedimiento
     * @param dest Donde almacenar el argumento
     * @param index Índice del argumento
     */
    public IRBuilder getArg(Operand dest, int index) {
        instructions.add(new ThreeAddressInstruction(
            IROpcode.GET_ARG, dest, Operand.constant(index),
            dest + " = arg[" + index + "]"
        ));
        return this;
    }
    
    /**
     * Retorno de procedimiento
     * @param value Valor de retorno (null para void)
     */
    public IRBuilder returnStmt(Operand value) {
        if (value != null) {
            instructions.add(new ThreeAddressInstruction(
                IROpcode.RETURN, value, "return " + value
            ));
        } else {
            instructions.add(new ThreeAddressInstruction(
                IROpcode.RETURN, null, "return"
            ));
        }
        return this;
    }
    
    /**
     * Imprime todas las instrucciones en formato legible
     */
    public void print() {
        for (ThreeAddressInstruction instr : instructions) {
            System.out.println(instr);
        }
    }
    
    /**
     * Obtiene el código IR como String
     */
    public String toIRCode() {
        StringBuilder sb = new StringBuilder();
        for (ThreeAddressInstruction instr : instructions) {
            sb.append(instr.toString()).append("\n");
        }
        return sb.toString();
    }
}

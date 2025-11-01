package com.miorganizacion.logotec.compilador.ir;

import com.miorganizacion.logotec.compilador.ast.ExprNode;
import java.util.ArrayList;
import java.util.List;

/**
 * Builder para construir listas de instrucciones IR.
 */
public class IRBuilder {
    
    private final List<ThreeAddressInstruction> instructions;
    
    public IRBuilder() {
        this.instructions = new ArrayList<>();
    }
    
    /**
     * Agrega una instrucción a la lista.
     */
    public IRBuilder add(ThreeAddressInstruction instr) {
        instructions.add(instr);
        return this;
    }
    
    /**
     * Agrega una etiqueta.
     */
    public IRBuilder label(String name) {
        instructions.add(ThreeAddressInstruction.label(name));
        return this;
    }
    
    /**
     * Agrega un comentario.
     */
    public IRBuilder comment(String text) {
        instructions.add(ThreeAddressInstruction.comment(text));
        return this;
    }
    
    /**
     * Agrega una instrucción NOP.
     */
    public IRBuilder nop() {
        instructions.add(ThreeAddressInstruction.nop());
        return this;
    }
    
    // ==================== MÉTODOS AUXILIARES ====================
    
    /**
     * Carga una constante en un temporal.
     */
    public IRBuilder loadConst(Operand dest, int value) {
        instructions.add(new ThreeAddressInstruction(IROpcode.LOAD_CONST, dest, Operand.constant(value)));
        return this;
    }
    
    /**
     * Carga una constante double en un temporal.
     */
    public IRBuilder loadConst(Operand dest, double value) {
        instructions.add(new ThreeAddressInstruction(IROpcode.LOAD_CONST, dest, Operand.constant(value)));
        return this;
    }
    
    /**
     * Carga una variable en un temporal.
     */
    public IRBuilder loadVar(Operand dest, String varName) {
        instructions.add(new ThreeAddressInstruction(IROpcode.LOAD_VAR, dest, Operand.variable(varName)));
        return this;
    }
    
    /**
     * Guarda un valor en una variable.
     */
    public IRBuilder store(String varName, Operand source) {
        instructions.add(new ThreeAddressInstruction(IROpcode.STORE, Operand.variable(varName), source));
        return this;
    }
    
    /**
     * Mueve un valor de un operando a otro.
     */
    public IRBuilder move(Operand dest, Operand source) {
        instructions.add(new ThreeAddressInstruction(IROpcode.MOVE, dest, source));
        return this;
    }
    
    /**
     * Suma: dest = op1 + op2
     */
    public IRBuilder add(Operand dest, Operand op1, Operand op2) {
        instructions.add(new ThreeAddressInstruction(IROpcode.ADD, dest, op1, op2));
        return this;
    }
    
    /**
     * Resta: dest = op1 - op2
     */
    public IRBuilder sub(Operand dest, Operand op1, Operand op2) {
        instructions.add(new ThreeAddressInstruction(IROpcode.SUB, dest, op1, op2));
        return this;
    }
    
    /**
     * Multiplicación: dest = op1 * op2
     */
    public IRBuilder mul(Operand dest, Operand op1, Operand op2) {
        instructions.add(new ThreeAddressInstruction(IROpcode.MUL, dest, op1, op2));
        return this;
    }
    
    /**
     * División: dest = op1 / op2
     */
    public IRBuilder div(Operand dest, Operand op1, Operand op2) {
        instructions.add(new ThreeAddressInstruction(IROpcode.DIV, dest, op1, op2));
        return this;
    }
    
    /**
     * Menor que: dest = (op1 < op2)
     */
    public IRBuilder lt(Operand dest, Operand op1, Operand op2) {
        instructions.add(new ThreeAddressInstruction(IROpcode.LT, dest, op1, op2));
        return this;
    }
    
    /**
     * Salto incondicional.
     */
    public IRBuilder jump(String labelName) {
        instructions.add(new ThreeAddressInstruction(IROpcode.JUMP, Operand.label(labelName)));
        return this;
    }
    
    /**
     * Salto si falso.
     */
    public IRBuilder jumpIfFalse(String labelName, Operand cond) {
        instructions.add(new ThreeAddressInstruction(IROpcode.JUMP_IF_FALSE, Operand.label(labelName), cond));
        return this;
    }
    
    /**
     * Salto si verdadero.
     */
    public IRBuilder jumpIfTrue(String labelName, Operand cond) {
        instructions.add(new ThreeAddressInstruction(IROpcode.JUMP_IF_TRUE, Operand.label(labelName), cond));
        return this;
    }
    
    /**
     * Comando de tortuga: avanzar.
     */
    public IRBuilder forward(Operand distance) {
        instructions.add(new ThreeAddressInstruction(IROpcode.FORWARD, distance));
        return this;
    }
    
    /**
     * Comando de tortuga: girar derecha.
     */
    public IRBuilder turnRight(ExprNode angle) {
        Operand value = evaluate(angle);
        instructions.add(ThreeAddressInstruction.turnRight(value));
        return this;
    }
    
    /**
     * Comando de tortuga: girar izquierda.
     */
    public IRBuilder turnLeft(Operand angle) {
        instructions.add(new ThreeAddressInstruction(IROpcode.TURN_LEFT, angle));
        return this;
    }
    
    /**
     * Comando de tortuga: subir lápiz.
     */
    public IRBuilder penUp() {
        instructions.add(ThreeAddressInstruction.penUp());
        return this;
    }

    /**
     * Comando de tortuga: bajar lápiz.
     */
    public IRBuilder penDown() {
        instructions.add(ThreeAddressInstruction.penDown());
        return this;
    }

    /**
     * Comando de tortuga: centrar.
     */
    public IRBuilder center() {
        instructions.add(ThreeAddressInstruction.center());
        return this;
    }
    
    // ==================== GETTERS ====================
    
    /**
     * Obtiene la lista de instrucciones construida (copia).
     */
    public List<ThreeAddressInstruction> build() {
        return new ArrayList<>(instructions);
    }
    
    /**
     * Obtiene la lista de instrucciones (referencia directa).
     */
    public List<ThreeAddressInstruction> getInstructions() {
        return instructions;
    }
    
    /**
     * Limpia todas las instrucciones.
     */
    public void clear() {
        instructions.clear();
    }
    
    /**
     * Obtiene el número de instrucciones.
     */
    public int size() {
        return instructions.size();
    }

    private Operand evaluate(ExprNode expr) {
        // TODO: implementar generación de temporales a partir de ExprNode
        throw new UnsupportedOperationException("evaluate(ExprNode) aún no está implementado");
    }
}

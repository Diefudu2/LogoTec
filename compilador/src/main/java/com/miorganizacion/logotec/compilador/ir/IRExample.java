package com.miorganizacion.logotec.compilador.ir;

import java.util.List;

/**
 * Clase de ejemplo que demuestra cómo usar el sistema de IR.
 * Genera código IR para diferentes construcciones de LogoTec.
 */
public class IRExample {
    
    /**
     * Ejemplo 1: Código simple con variables
     * LogoTec:
     *   haz lado 50
     *   avanza lado
     */
    public static List<ThreeAddressInstruction> example1_SimpleVariable() {
        IRBuilder builder = new IRBuilder();
        
        builder.comment("haz lado 50");
        Operand t1 = builder.getTempGen().nextOperand();
        builder.loadConst(t1, 50);
        builder.store("lado", t1);
        
        builder.comment("avanza lado");
        Operand t2 = builder.getTempGen().nextOperand();
        builder.loadVar(t2, "lado");
        builder.forward(t2);
        
        return builder.getInstructions();
    }
    
    /**
     * Ejemplo 2: Expresión aritmética
     * LogoTec:
     *   haz x 10 + 20 * 2
     *   avanza x
     */
    public static List<ThreeAddressInstruction> example2_Arithmetic() {
        IRBuilder builder = new IRBuilder();
        
        builder.comment("haz x 10 + 20 * 2");
        Operand t1 = builder.getTempGen().nextOperand(); // t1 = 20
        Operand t2 = builder.getTempGen().nextOperand(); // t2 = 2
        Operand t3 = builder.getTempGen().nextOperand(); // t3 = t1 * t2 (40)
        Operand t4 = builder.getTempGen().nextOperand(); // t4 = 10
        Operand t5 = builder.getTempGen().nextOperand(); // t5 = t4 + t3 (50)
        
        builder.loadConst(t1, 20);
        builder.loadConst(t2, 2);
        builder.mul(t3, t1, t2);
        builder.loadConst(t4, 10);
        builder.add(t5, t4, t3);
        builder.store("x", t5);
        
        builder.comment("avanza x");
        Operand t6 = builder.getTempGen().nextOperand();
        builder.loadVar(t6, "x");
        builder.forward(t6);
        
        return builder.getInstructions();
    }
    
    /**
     * Ejemplo 3: Loop simple (Repite)
     * LogoTec:
     *   Repite 4 [
     *     avanza 50
     *     giraderecha 90
     *   ]
     */
    public static List<ThreeAddressInstruction> example3_SimpleLoop() {
        IRBuilder builder = new IRBuilder();
        
        builder.comment("Repite 4 [...]");
        
        // Inicializar contador
        Operand counter = Operand.temp("counter");
        builder.loadConst(counter, 0);
        
        // Etiquetas
        Operand loopStart = builder.getLabelGen().nextOperand("loop_start");
        Operand loopEnd = builder.getLabelGen().nextOperand("loop_end");
        
        // Inicio del loop
        builder.label(loopStart);
        
        // Condición: counter < 4
        Operand limit = builder.getTempGen().nextOperand();
        builder.loadConst(limit, 4);
        
        Operand condition = builder.getTempGen().nextOperand();
        builder.lt(condition, counter, limit);
        builder.jumpIfFalse(loopEnd, condition);
        
        // Cuerpo del loop
        builder.comment("avanza 50");
        Operand t1 = builder.getTempGen().nextOperand();
        builder.loadConst(t1, 50);
        builder.forward(t1);
        
        builder.comment("giraderecha 90");
        Operand t2 = builder.getTempGen().nextOperand();
        builder.loadConst(t2, 90);
        builder.turnRight(t2);
        
        // Incrementar contador
        Operand one = builder.getTempGen().nextOperand();
        builder.loadConst(one, 1);
        builder.add(counter, counter, one);
        
        // Volver al inicio
        builder.jump(loopStart);
        
        // Fin del loop
        builder.label(loopEnd);
        
        return builder.getInstructions();
    }
    
    /**
     * Ejemplo 4: Condicional (Si)
     * LogoTec:
     *   Si x > 50 [
     *     avanza 100
     *   ] [
     *     avanza 50
     *   ]
     */
    public static List<ThreeAddressInstruction> example4_Conditional() {
        IRBuilder builder = new IRBuilder();
        
        builder.comment("Si x > 50 [...]");
        
        // Evaluar condición
        Operand t1 = builder.getTempGen().nextOperand();
        builder.loadVar(t1, "x");
        
        Operand t2 = builder.getTempGen().nextOperand();
        builder.loadConst(t2, 50);
        
        Operand condition = builder.getTempGen().nextOperand();
        builder.gt(condition, t1, t2);
        
        // Etiquetas
        Operand elseBranch = builder.getLabelGen().nextOperand("else");
        Operand endIf = builder.getLabelGen().nextOperand("endif");
        
        // Saltar a else si falso
        builder.jumpIfFalse(elseBranch, condition);
        
        // Then branch
        builder.comment("avanza 100");
        Operand t3 = builder.getTempGen().nextOperand();
        builder.loadConst(t3, 100);
        builder.forward(t3);
        builder.jump(endIf);
        
        // Else branch
        builder.label(elseBranch);
        builder.comment("avanza 50");
        Operand t4 = builder.getTempGen().nextOperand();
        builder.loadConst(t4, 50);
        builder.forward(t4);
        
        // Fin del if
        builder.label(endIf);
        
        return builder.getInstructions();
    }
    
    /**
     * Ejemplo 5: Cuadrado completo
     * LogoTec:
     *   haz lado 100
     *   Repite 4 [
     *     avanza lado
     *     giraderecha 90
     *   ]
     */
    public static List<ThreeAddressInstruction> example5_Square() {
        IRBuilder builder = new IRBuilder();
        
        // Definir variable lado
        builder.comment("haz lado 100");
        Operand t1 = builder.getTempGen().nextOperand();
        builder.loadConst(t1, 100);
        builder.store("lado", t1);
        
        // Loop
        builder.comment("Repite 4 [...]");
        Operand counter = Operand.temp("i");
        builder.loadConst(counter, 0);
        
        Operand loopStart = builder.getLabelGen().nextOperand("loop_start");
        Operand loopEnd = builder.getLabelGen().nextOperand("loop_end");
        
        builder.label(loopStart);
        
        // Condición
        Operand limit = builder.getTempGen().nextOperand();
        builder.loadConst(limit, 4);
        Operand cond = builder.getTempGen().nextOperand();
        builder.lt(cond, counter, limit);
        builder.jumpIfFalse(loopEnd, cond);
        
        // Cuerpo
        builder.comment("avanza lado");
        Operand t2 = builder.getTempGen().nextOperand();
        builder.loadVar(t2, "lado");
        builder.forward(t2);
        
        builder.comment("giraderecha 90");
        Operand t3 = builder.getTempGen().nextOperand();
        builder.loadConst(t3, 90);
        builder.turnRight(t3);
        
        // Incrementar
        Operand one = builder.getTempGen().nextOperand();
        builder.loadConst(one, 1);
        builder.add(counter, counter, one);
        builder.jump(loopStart);
        
        builder.label(loopEnd);
        
        return builder.getInstructions();
    }
    
    /**
     * Ejecuta todos los ejemplos y muestra el resultado
     */
    public static void main(String[] args) {
        String separator = "============================================================";
        
        System.out.println("╔═══════════════════════════════════════════════════════╗");
        System.out.println("║       EJEMPLOS DE REPRESENTACIÓN INTERMEDIA (IR)     ║");
        System.out.println("╚═══════════════════════════════════════════════════════╝\n");
        
        // Ejemplo 1
        System.out.println("\n" + separator);
        System.out.println("EJEMPLO 1: Variable simple");
        System.out.println(separator);
        List<ThreeAddressInstruction> ir1 = example1_SimpleVariable();
        IRUtils.printCode(ir1);
        
        // Ejemplo 2
        System.out.println("\n" + separator);
        System.out.println("EJEMPLO 2: Expresión aritmética");
        System.out.println(separator);
        List<ThreeAddressInstruction> ir2 = example2_Arithmetic();
        IRUtils.printCode(ir2);
        
        // Ejemplo 3
        System.out.println("\n" + separator);
        System.out.println("EJEMPLO 3: Loop simple (Repite 4)");
        System.out.println(separator);
        List<ThreeAddressInstruction> ir3 = example3_SimpleLoop();
        IRUtils.printCode(ir3);
        IRUtils.validate(ir3);
        
        // Ejemplo 4
        System.out.println("\n" + separator);
        System.out.println("EJEMPLO 4: Condicional (Si)");
        System.out.println(separator);
        List<ThreeAddressInstruction> ir4 = example4_Conditional();
        IRUtils.printCode(ir4);
        IRUtils.validate(ir4);
        
        // Ejemplo 5
        System.out.println("\n" + separator);
        System.out.println("EJEMPLO 5: Cuadrado completo");
        System.out.println(separator);
        List<ThreeAddressInstruction> ir5 = example5_Square();
        IRUtils.printCode(ir5);
        IRUtils.validate(ir5);
        IRUtils.printStatistics(ir5);
    }
}

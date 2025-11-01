package com.miorganizacion.logotec.compilador.ir;

import java.util.List;

/**
 * Ejemplo de uso del sistema de IR.
 * Demuestra cómo construir código IR manualmente.
 */
public class IRExample {

    public static void main(String[] args) {
        runAllExamples();
    }

    private static void runAllExamples() {
        System.out.println("╔═══════════════════════════════════════════════════════╗");
        System.out.println("║      Ejemplo: Construcción Manual de IR              ║");
        System.out.println("╚═══════════════════════════════════════════════════════╝\n");

        // Ejemplo 1: Variable simple y avance
        ejemplo1_VariableYAvance();

        // Ejemplo 2: Cuadrado con ciclo
        ejemplo2_CuadradoConCiclo();

        // Ejemplo 3: Expresión aritmética
        ejemplo3_ExpresionAritmetica();
    }

    /**
     * Ejemplo 1: haz lado 100; avanza lado
     */
    private static void ejemplo1_VariableYAvance() {
        System.out.println("\n=== Ejemplo 1: Variable y Avance ===");
        System.out.println("Código LogoTec: haz lado 100; avanza lado\n");

        IRBuilder builder = new IRBuilder();
        TempGenerator tempGen = new TempGenerator();

        // haz lado 100
        builder.comment("haz lado 100");
        Operand t0 = tempGen.nextOperand();
        builder.add(new ThreeAddressInstruction(IROpcode.LOAD_CONST, t0, Operand.constant(100)));
        builder.add(new ThreeAddressInstruction(IROpcode.STORE, Operand.variable("lado"), t0));

        // avanza lado
        builder.comment("avanza lado");
        Operand t1 = tempGen.nextOperand();
        builder.add(new ThreeAddressInstruction(IROpcode.LOAD_VAR, t1, Operand.variable("lado")));
        builder.add(new ThreeAddressInstruction(IROpcode.FORWARD, t1));

        // Mostrar IR
        printIR(builder.build());
    }

    /**
     * Ejemplo 2: Cuadrado con Repite
     * haz lado 100
     * Repite 4 [avanza lado; giraderecha 90]
     */
    private static void ejemplo2_CuadradoConCiclo() {
        System.out.println("\n=== Ejemplo 2: Cuadrado con Ciclo ===");
        System.out.println("Código LogoTec: haz lado 100; Repite 4 [avanza lado; giraderecha 90]\n");

        IRBuilder builder = new IRBuilder();
        TempGenerator tempGen = new TempGenerator();
        LabelGenerator labelGen = new LabelGenerator();

        // haz lado 100
        builder.comment("haz lado 100");
        Operand t0 = tempGen.nextOperand();
        builder.add(new ThreeAddressInstruction(IROpcode.LOAD_CONST, t0, Operand.constant(100)));
        builder.add(new ThreeAddressInstruction(IROpcode.STORE, Operand.variable("lado"), t0));

        // Repite 4 [...]
        builder.comment("Repite 4");
        String labelStart = labelGen.next("loop_start");
        String labelEnd = labelGen.next("loop_end");

        // Inicializar contador y límite
        Operand counter = tempGen.nextOperand();
        Operand limit = tempGen.nextOperand();
        builder.add(new ThreeAddressInstruction(IROpcode.LOAD_CONST, counter, Operand.constant(0)));
        builder.add(new ThreeAddressInstruction(IROpcode.LOAD_CONST, limit, Operand.constant(4)));

        // Etiqueta inicio del ciclo
        builder.label(labelStart);

        // Condición: counter < limit
        Operand cond = tempGen.nextOperand();
        builder.add(new ThreeAddressInstruction(IROpcode.LT, cond, counter, limit));
        builder.add(new ThreeAddressInstruction(IROpcode.JUMP_IF_FALSE, Operand.label(labelEnd), cond));

        // Cuerpo del ciclo
        builder.comment("avanza lado");
        Operand t1 = tempGen.nextOperand();
        builder.add(new ThreeAddressInstruction(IROpcode.LOAD_VAR, t1, Operand.variable("lado")));
        builder.add(new ThreeAddressInstruction(IROpcode.FORWARD, t1));

        builder.comment("giraderecha 90");
        Operand t2 = tempGen.nextOperand();
        builder.add(new ThreeAddressInstruction(IROpcode.LOAD_CONST, t2, Operand.constant(90)));
        builder.add(new ThreeAddressInstruction(IROpcode.TURN_RIGHT, t2));

        // Incrementar contador
        Operand one = Operand.constant(1);
        Operand newCounter = tempGen.nextOperand();
        builder.add(new ThreeAddressInstruction(IROpcode.ADD, newCounter, counter, one));
        builder.add(new ThreeAddressInstruction(IROpcode.MOVE, counter, newCounter));

        // Saltar al inicio
        builder.add(new ThreeAddressInstruction(IROpcode.JUMP, Operand.label(labelStart)));

        // Etiqueta fin del ciclo
        builder.label(labelEnd);
        builder.comment("Fin Repite");

        // Mostrar IR
        printIR(builder.build());
    }

    // Ejemplo 3: Expresión aritmética
    // haz x 10 + 20 * 2
    // avanza x
    private static void ejemplo3_ExpresionAritmetica() {
        System.out.println("\n=== Ejemplo 3: Expresión Aritmética ===");
        System.out.println("Código LogoTec: haz x 10 + 20 * 2; avanza x\n");

        IRBuilder builder = new IRBuilder();
        TempGenerator tempGen = new TempGenerator();

        // haz x 10 + 20 * 2
        // Primero: 20 * 2
        builder.comment("haz x 10 + 20 * 2");
        Operand t0 = tempGen.nextOperand();
        builder.add(new ThreeAddressInstruction(IROpcode.LOAD_CONST, t0, Operand.constant(20)));
        
        Operand t1 = tempGen.nextOperand();
        builder.add(new ThreeAddressInstruction(IROpcode.LOAD_CONST, t1, Operand.constant(2)));
        
        Operand t2 = tempGen.nextOperand();
        builder.add(new ThreeAddressInstruction(IROpcode.MUL, t2, t0, t1));

        // Luego: 10 + (resultado anterior)
        Operand t3 = tempGen.nextOperand();
        builder.add(new ThreeAddressInstruction(IROpcode.LOAD_CONST, t3, Operand.constant(10)));
        
        Operand t4 = tempGen.nextOperand();
        builder.add(new ThreeAddressInstruction(IROpcode.ADD, t4, t3, t2));

        // Guardar en x
        builder.add(new ThreeAddressInstruction(IROpcode.STORE, Operand.variable("x"), t4));

        // avanza x
        builder.comment("avanza x");
        Operand t5 = tempGen.nextOperand();
        builder.add(new ThreeAddressInstruction(IROpcode.LOAD_VAR, t5, Operand.variable("x")));
        builder.add(new ThreeAddressInstruction(IROpcode.FORWARD, t5));

        // Mostrar IR
        printIR(builder.build());
    }

    /**
     * Imprime el código IR generado
     */
    private static void printIR(List<ThreeAddressInstruction> instructions) {
        System.out.println("Código IR generado:");
        System.out.println("----------------------------------------------------------------------");
        int addr = 0;
        for (ThreeAddressInstruction instr : instructions) {
            System.out.printf("%4d: %s%n", addr++, instr);
        }
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Total: " + instructions.size() + " instrucciones\n");
    }
}

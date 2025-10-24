package com.miorganizacion.logotec.compilador.backend;

import com.miorganizacion.logotec.compilador.CompiladorRealAdapter;
import com.miorganizacion.logotec.compilador.ast.ProgramNode;
import com.miorganizacion.logotec.compilador.ir.*;
import com.miorganizacion.logotec.interfaz.modelo.AccionTortuga;

import java.util.List;

/**
 * Programa de prueba para el intérprete de bytecode.
 * Compila LogoTec → AST → IR → Assembly → Bytecode → [EJECUTA] → AccionTortuga
 * 
 * FASE 5: Tests del Bytecode Interpreter (Máquina Virtual)
 */
public class BytecodeInterpreterTest {

    public static void main(String[] args) {
        System.out.println("╔═══════════════════════════════════════════════════════╗");
        System.out.println("║   TEST: Ejecución de Bytecode (Máquina Virtual)      ║");
        System.out.println("╚═══════════════════════════════════════════════════════╝\n");

        // Test 1: Programa simple
        testProgram("Test 1: Variable y avance", 
            "// Programa simple\n" +
            "haz lado 50\n" +
            "avanza lado\n",
            false  // debug mode
        );

        // Test 2: Cuadrado (con debug)
        testProgram("Test 2: Cuadrado con Repite",
            "// Dibuja un cuadrado\n" +
            "haz lado 100\n" +
            "Repite 4 [\n" +
            "  avanza lado\n" +
            "  giraderecha 90\n" +
            "]\n",
            true   // debug mode activado
        );

        // Test 3: Expresión aritmética
        testProgram("Test 3: Expresión aritmética",
            "// Operaciones\n" +
            "haz x 10 + 20\n" +
            "haz y x * 2\n" +
            "avanza y\n",
            false
        );
        
        // Test 4: Condicional
        testProgram("Test 4: Condicional SI",
            "// Test condicional\n" +
            "haz x 100\n" +
            "SI (x > 50) [\n" +
            "  avanza 100\n" +
            "] [\n" +
            "  avanza 50\n" +
            "]\n",
            false
        );
    }

    private static void testProgram(String testName, String sourceCode, boolean debugMode) {
        String separator = "======================================================================";
        
        System.out.println("\n" + separator);
        System.out.println(testName);
        System.out.println(separator);
        System.out.println("\n📝 Código LogoTec:");
        System.out.println(sourceCode);
        
        try {
            // PASO 1: Compilar a AST
            System.out.println("\n🔧 PASO 1: Compilando a AST...");
            ProgramNode ast = CompiladorRealAdapter.compile(sourceCode);
            
            if (ast == null) {
                System.err.println("❌ Error: AST es null");
                return;
            }
            System.out.println("✅ AST generado correctamente");
            
            // PASO 2: Generar IR
            System.out.println("\n🔧 PASO 2: Generando código IR...");
            ASTtoIRTranslator irTranslator = new ASTtoIRTranslator();
            ASTtoIRTranslator.Result irResult = irTranslator.generate(ast);
            
            if (irResult == null || irResult.instructions == null) {
                System.err.println("❌ Error: No se generó IR");
                return;
            }
            
            System.out.println("✅ IR generado: " + irResult.instructions.size() + " instrucciones");
            
            // PASO 3: Generar Assembly
            System.out.println("\n🔧 PASO 3: Generando código Assembly...");
            AssemblyGenerator asmGen = new AssemblyGenerator();
            List<AssemblyInstruction> asmCode = asmGen.generate(irResult.instructions);
            
            if (asmCode == null || asmCode.isEmpty()) {
                System.err.println("❌ Error: No se generó Assembly");
                return;
            }
            
            System.out.println("✅ Assembly generado: " + asmCode.size() + " instrucciones");
            
            // PASO 4: Generar Object Code (Bytecode)
            System.out.println("\n🔧 PASO 4: Generando Object Code (Bytecode)...");
            ObjectCodeGenerator objGen = new ObjectCodeGenerator();
            ObjectCodeGenerator.Result objResult = objGen.generate(asmCode);
            
            if (objResult == null || objResult.bytecode == null || objResult.bytecode.isEmpty()) {
                System.err.println("❌ Error: No se generó Object Code");
                return;
            }
            
            System.out.println("✅ Bytecode generado: " + objResult.bytecode.size() + " instrucciones");
            
            // PASO 5: EJECUTAR BYTECODE EN LA VM
            System.out.println("\n🔧 PASO 5: Ejecutando bytecode en la VM...");
            BytecodeInterpreter vm = new BytecodeInterpreter();
            vm.setDebugMode(debugMode);
            vm.loadProgram(objResult);
            
            // Ejecutar
            long startTime = System.currentTimeMillis();
            vm.execute();
            long endTime = System.currentTimeMillis();
            
            System.out.println("✅ Ejecución completada en " + (endTime - startTime) + "ms");
            
            // PASO 6: Obtener acciones de tortuga
            List<AccionTortuga> acciones = vm.getAcciones();
            
            System.out.println("\n🎨 Acciones de Tortuga Generadas:");
            System.out.println("----------------------------------------------------------------------");
            System.out.println("Total de acciones: " + acciones.size());
            
            if (acciones.isEmpty()) {
                System.out.println("⚠️  No se generaron acciones de dibujo");
            } else {
                System.out.println("\nLista de acciones:");
                for (int i = 0; i < acciones.size(); i++) {
                    AccionTortuga accion = acciones.get(i);
                    System.out.printf("  %2d. %-20s → %s%n", 
                                    i + 1, 
                                    accion.getTipo(), 
                                    accion.getValor());
                }
            }
            
            // Estado final de registros (solo si debug)
            if (debugMode) {
                System.out.println("\n📊 Estado Final de Registros:");
                int[] regs = vm.getRegisters();
                for (int i = 0; i < 10; i++) {
                    if (regs[i] != 0) {
                        System.out.printf("  $t%d = %d%n", i, regs[i]);
                    }
                }
            }
            
            System.out.println("\n✅ TEST COMPLETADO EXITOSAMENTE");
            
        } catch (Exception e) {
            System.err.println("\n❌ Error durante la ejecución:");
            System.err.println("   " + e.getMessage());
            e.printStackTrace();
        }
    }
}

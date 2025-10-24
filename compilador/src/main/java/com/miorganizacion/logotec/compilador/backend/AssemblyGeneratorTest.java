package com.miorganizacion.logotec.compilador.backend;

import com.miorganizacion.logotec.compilador.CompiladorRealAdapter;
import com.miorganizacion.logotec.compilador.ast.ProgramNode;
import com.miorganizacion.logotec.compilador.ir.*;

import java.io.IOException;
import java.util.List;

/**
 * Programa de prueba para el generador de Assembly.
 * Compila LogoTec → AST → IR → Assembly
 * 
 * FASE 3: Tests de generación de Assembly
 */
public class AssemblyGeneratorTest {

    public static void main(String[] args) {
        System.out.println("╔═══════════════════════════════════════════════════════╗");
        System.out.println("║      TEST: Generación de Código Assembly             ║");
        System.out.println("╚═══════════════════════════════════════════════════════╝\n");

        // Test 1: Programa simple
        testProgram("Test 1: Variable y avance", 
            "// Programa simple\n" +
            "haz lado 50\n" +
            "avanza lado\n"
        );

        // Test 2: Cuadrado
        testProgram("Test 2: Cuadrado con Repite",
            "// Dibuja un cuadrado\n" +
            "haz lado 100\n" +
            "Repite 4 [\n" +
            "  avanza lado\n" +
            "  giraderecha 90\n" +
            "]\n"
        );

        // Test 3: Expresión aritmética
        testProgram("Test 3: Expresión aritmética",
            "// Operaciones\n" +
            "haz x 10 + 20\n" +
            "haz y x * 2\n" +
            "avanza y\n"
        );
    }

    private static void testProgram(String testName, String sourceCode) {
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
            
            // Mostrar IR (opcional, comentado para brevedad)
            // System.out.println("\n📄 Código IR:");
            // IRUtils.printCode(irResult.instructions);
            
            // PASO 3: Generar Assembly
            System.out.println("\n🔧 PASO 3: Generando código Assembly...");
            AssemblyGenerator asmGen = new AssemblyGenerator();
            List<AssemblyInstruction> asmCode = asmGen.generate(irResult.instructions);
            
            if (asmCode == null || asmCode.isEmpty()) {
                System.err.println("❌ Error: No se generó Assembly");
                return;
            }
            
            System.out.println("✅ Assembly generado: " + asmCode.size() + " instrucciones");
            
            // Mostrar Assembly
            System.out.println("\n📄 Código Assembly Generado:");
            System.out.println("----------------------------------------------------------------------");
            AssemblyUtils.printCode(asmCode);
            
            // Estadísticas
            AssemblyUtils.printStatistics(asmCode);
            
            // Guardar en archivo
            String filename = "test_" + testName.replaceAll("[^a-zA-Z0-9]", "_") + ".asm";
            try {
                AssemblyUtils.saveToFile(asmCode, filename);
                System.out.println("\n💾 Archivo guardado: " + filename);
            } catch (IOException e) {
                System.err.println("⚠️ No se pudo guardar archivo: " + e.getMessage());
            }
            
            System.out.println("\n✅ TEST COMPLETADO EXITOSAMENTE");
            
        } catch (Exception e) {
            System.err.println("\n❌ Error durante la compilación:");
            System.err.println("   " + e.getMessage());
            e.printStackTrace();
        }
    }
}

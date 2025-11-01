package com.miorganizacion.logotec.compilador.ir;

import com.miorganizacion.logotec.compilador.CompiladorRealAdapter;
import com.miorganizacion.logotec.compilador.ast.ProgramNode;

/**
 * Programa de prueba para el generador de IR.
 * Compila LogoTec → AST → IR
 */
public class IRGeneratorTest {

    public static void main(String[] args) {
        System.out.println("╔═══════════════════════════════════════════════════════╗");
        System.out.println("║      TEST: Generación de Código Intermedio (IR)      ║");
        System.out.println("╚═══════════════════════════════════════════════════════╝\n");

        // Test 1: Programa simple
        testProgram("Test 1: Variable y avance", 
            "// Programa simple\n" +
            "Haz lado 50\n" +
            "avanza lado\n"
        );

        // Test 2: Cuadrado
        testProgram("Test 2: Cuadrado con Repite",
            "// Dibuja un cuadrado\n" +
            "Haz lado 100\n" +
            "Repite 4 [\n" +
            "  avanza lado\n" +
            "  giraderecha 90\n" +
            "]\n"
        );

        // Test 3: Expresión aritmética
        testProgram("Test 3: Expresión aritmética",
            "// Operaciones\n" +
            "Haz x 10 + 20\n" +
            "Haz y x * 2\n" +
            "avanza y\n"
        );
        
        // Test 4: Procedimiento
        testProgram("Test 4: Procedimiento simple",
            "// Procedimiento\n" +
            "Haz lado 80\n" +
            "Para cuadrado [tam] [\n" +
            "  Repite 4 [\n" +
            "    avanza tam\n" +
            "    giraderecha 90\n" +
            "  ]\n" +
            "] fin\n" +
            "cuadrado lado\n"
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
            
            System.out.println("✅ IR generado: " + irResult.instructions.size() + " instrucciones totales");
            
            // Mostrar IR
            System.out.println("\n📄 Código IR Generado:");
            System.out.println("----------------------------------------------------------------------");
            int addr = 0;
            for (ThreeAddressInstruction instr : irResult.instructions) {
                System.out.printf("%4d: %s%n", addr++, instr);
            }
            
            System.out.println(separator);
            System.out.println("\n✅ TEST COMPLETADO EXITOSAMENTE");
            
        } catch (Exception e) {
            System.err.println("\n❌ Error durante la compilación:");
            System.err.println("   " + e.getMessage());
            e.printStackTrace();
        }
    }
}

package com.miorganizacion.logotec.compilador.ir;

import com.miorganizacion.logotec.compilador.CompiladorRealAdapter;
import com.miorganizacion.logotec.compilador.ast.ProgramNode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Programa de prueba para el generador de IR.
 * Compila programas LogoTec y genera código IR.
 */
public class IRGeneratorTest {

    public static void main(String[] args) {
        System.out.println("╔═══════════════════════════════════════════════════════╗");
        System.out.println("║         TEST: Generación de Código Intermedio        ║");
        System.out.println("╚═══════════════════════════════════════════════════════╝\n");

        // Test 1: Programa simple con variable
        testProgram("Test 1: Variable y avance", 
            "/* Programa simple */\n" +
            "haz lado 50\n" +
            "avanza lado\n"
        );

        // Test 2: Cuadrado con Repite
        testProgram("Test 2: Cuadrado con Repite",
            "/* Dibuja un cuadrado */\n" +
            "haz lado 100\n" +
            "Repite 4 [\n" +
            "  avanza lado\n" +
            "  giraderecha 90\n" +
            "]\n"
        );

        // Test 3: Expresión aritmética
        testProgram("Test 3: Expresión aritmética",
            "/* Operaciones */\n" +
            "haz x 10 + 20 * 2\n" +
            "avanza x\n"
        );

        // Test 4: Condicional
        testProgram("Test 4: Condicional Si",
            "/* Condicional */\n" +
            "haz lado 50\n" +
            "Si lado > 30 [\n" +
            "  avanza 100\n" +
            "] [\n" +
            "  avanza 50\n" +
            "]\n"
        );

        // Test 5: DoWhile
        testProgram("Test 5: HazMientras",
            "/* DoWhile */\n" +
            "haz i 0\n" +
            "hazMientras [\n" +
            "  avanza 10\n" +
            "  haz i i + 1\n" +
            "] [i < 5]\n"
        );
    }

    private static void testProgram(String testName, String sourceCode) {
        String separator = "======================================================================";
        String separator2 = "----------------------------------------------------------------------";
        
        System.out.println("\n" + separator);
        System.out.println(testName);
        System.out.println(separator);
        System.out.println("\n📝 Código LogoTec:");
        System.out.println(sourceCode);
        
        try {
            // Compilar a AST
            System.out.println("\n🔧 Compilando a AST...");
            ProgramNode ast = CompiladorRealAdapter.compile(sourceCode);
            
            if (ast == null) {
                System.err.println("❌ Error: AST es null");
                return;
            }
            
            System.out.println("✅ AST generado correctamente");
            
            // Generar IR
            System.out.println("\n🔧 Generando código IR...");
            ASTtoIRTranslator translator = new ASTtoIRTranslator();
            ASTtoIRTranslator.Result result = translator.generate(ast);
            
            if (result == null || result.instructions == null) {
                System.err.println("❌ Error: No se generó IR");
                return;
            }
            
            System.out.println("✅ IR generado: " + result.instructions.size() + " instrucciones");
            System.out.println("📦 Variables declaradas: " + result.declaredVars);
            
            // Mostrar IR
            System.out.println("\n📄 Código IR Generado:");
            System.out.println(separator2);
            IRUtils.printCode(result.instructions);
            
            // Validar IR
            System.out.println("\n🔍 Validando IR...");
            boolean valid = IRUtils.validate(result.instructions);
            
            if (valid) {
                System.out.println("✅ Código IR válido");
            } else {
                System.out.println("⚠️ Advertencias en validación");
            }
            
            // Guardar en archivo (opcional)
            String filename = "test_" + testName.replaceAll("[^a-zA-Z0-9]", "_") + ".ir";
            try {
                IRUtils.saveToFile(result.instructions, filename);
                System.out.println("💾 Archivo guardado: " + filename);
            } catch (IOException e) {
                System.err.println("⚠️ No se pudo guardar archivo: " + e.getMessage());
            }
            
        } catch (Exception e) {
            System.err.println("❌ Error durante la compilación:");
            System.err.println("   " + e.getMessage());
            e.printStackTrace();
        }
    }
}

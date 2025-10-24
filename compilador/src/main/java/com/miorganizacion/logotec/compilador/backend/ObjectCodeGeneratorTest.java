package com.miorganizacion.logotec.compilador.backend;

import com.miorganizacion.logotec.compilador.CompiladorRealAdapter;
import com.miorganizacion.logotec.compilador.ast.ProgramNode;
import com.miorganizacion.logotec.compilador.ir.*;

import java.io.IOException;
import java.util.List;

/**
 * Programa de prueba para el generador de Object Code.
 * Compila LogoTec → AST → IR → Assembly → Bytecode
 * 
 * FASE 4: Tests de generación de Bytecode
 */
public class ObjectCodeGeneratorTest {

    public static void main(String[] args) {
        System.out.println("╔═══════════════════════════════════════════════════════╗");
        System.out.println("║      TEST: Generación de Object Code (Bytecode)      ║");
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
            
            // Mostrar Object Code
            System.out.println("\n📄 Object Code Generado:");
            System.out.println("----------------------------------------------------------------------");
            ObjectCodeUtils.printCode(objResult);
            
            // Estadísticas
            ObjectCodeUtils.printStatistics(objResult);
            
            // Guardar en archivo binario
            String filename = "test_" + testName.replaceAll("[^a-zA-Z0-9]", "_") + ".obj";
            try {
                ObjectCodeUtils.saveToFile(objResult, filename);
                System.out.println("\n💾 Archivo binario guardado: " + filename);
                
                // Verificar que se puede leer
                ObjectCodeUtils.LoadedProgram loaded = ObjectCodeUtils.loadFromFile(filename);
                System.out.println("✅ Archivo verificado: " + loaded.bytecode.size() + " instrucciones");
                
            } catch (IOException e) {
                System.err.println("⚠️ No se pudo guardar/cargar archivo: " + e.getMessage());
            }
            
            // Hex dump
            System.out.println("\n📊 Hex Dump:");
            System.out.println(ObjectCodeUtils.toHexDump(objResult.bytecode));
            
            System.out.println("\n✅ TEST COMPLETADO EXITOSAMENTE");
            
        } catch (Exception e) {
            System.err.println("\n❌ Error durante la compilación:");
            System.err.println("   " + e.getMessage());
            e.printStackTrace();
        }
    }
}

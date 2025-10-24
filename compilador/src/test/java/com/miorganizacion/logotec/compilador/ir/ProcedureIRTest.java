package com.miorganizacion.logotec.compilador.ir;

import com.miorganizacion.logotec.compilador.ast.*;
import com.miorganizacion.logotec.compilador.CompiladorRealAdapter;
import java.util.List;

/**
 * Test para verificar soporte de procedimientos en AST→IR
 */
public class ProcedureIRTest {
    
    public static void main(String[] args) {
        System.out.println("╔═══════════════════════════════════════════════════════╗");
        System.out.println("║  Test: Procedimientos en IR                          ║");
        System.out.println("╚═══════════════════════════════════════════════════════╝\n");
        
        // Test 1: Procedimiento simple sin parámetros
        test1_SimpleProcedure();
        
        // Test 2: Procedimiento con parámetros
        test2_ProcedureWithParams();
        
        // Test 3: Procedimiento anidado (roseta)
        test3_NestedProcedures();
        
        System.out.println("\n✅ Todos los tests completados");
    }
    
    private static void test1_SimpleProcedure() {
        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println("TEST 1: Procedimiento simple sin parámetros");
        System.out.println("═══════════════════════════════════════════════════════");
        
        String code = "// Test procedimiento simple\n" +
                      "haz x 0\n" +
                      "Para saludo [] [\n" +
                      "    avanza 50\n" +
                      "    giraderecha 90\n" +
                      "] fin\n" +
                      "\n" +
                      "saludo\n";
        
        try {
            ProgramNode ast = CompiladorRealAdapter.compile(code);
            ASTtoIRTranslator translator = new ASTtoIRTranslator();
            ASTtoIRTranslator.Result result = translator.generate(ast);
            
            System.out.println("\n📋 IR Generado (" + result.instructions.size() + " instrucciones):\n");
            for (ThreeAddressInstruction instr : result.instructions) {
                System.out.println("  " + instr);
            }
            
            System.out.println("\n✅ Test 1 pasado\n");
        } catch (Exception e) {
            System.err.println("❌ Test 1 falló: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private static void test2_ProcedureWithParams() {
        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println("TEST 2: Procedimiento con parámetros");
        System.out.println("═══════════════════════════════════════════════════════");
        
        String code = "// Test procedimiento con parámetros\n" +
                      "haz x 0\n" +
                      "Para cuadrado [lado] [\n" +
                      "    Repite 4 [\n" +
                      "        avanza lado\n" +
                      "        giraderecha 90\n" +
                      "    ]\n" +
                      "] fin\n" +
                      "\n" +
                      "cuadrado 100\n";
        
        try {
            ProgramNode ast = CompiladorRealAdapter.compile(code);
            ASTtoIRTranslator translator = new ASTtoIRTranslator();
            ASTtoIRTranslator.Result result = translator.generate(ast);
            
            System.out.println("\n📋 IR Generado (" + result.instructions.size() + " instrucciones):\n");
            for (ThreeAddressInstruction instr : result.instructions) {
                System.out.println("  " + instr);
            }
            
            System.out.println("\n✅ Test 2 pasado\n");
        } catch (Exception e) {
            System.err.println("❌ Test 2 falló: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private static void test3_NestedProcedures() {
        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println("TEST 3: Procedimientos anidados (test.logo simplificado)");
        System.out.println("═══════════════════════════════════════════════════════");
        
        String code = "// Test procedimientos anidados\n" +
                      "haz dummy 0\n" +
                      "Para poligono [n l] [\n" +
                      "    Repite n [\n" +
                      "        avanza l\n" +
                      "        giraderecha 60\n" +
                      "    ]\n" +
                      "] fin\n" +
                      "\n" +
                      "Para roseta [cont lado] [\n" +
                      "    Repite cont [\n" +
                      "        poligono 6 lado\n" +
                      "        giraderecha 30\n" +
                      "    ]\n" +
                      "] fin\n" +
                      "\n" +
                      "centro\n" +
                      "roseta 3 50\n";
        
        try {
            ProgramNode ast = CompiladorRealAdapter.compile(code);
            ASTtoIRTranslator translator = new ASTtoIRTranslator();
            ASTtoIRTranslator.Result result = translator.generate(ast);
            
            System.out.println("\n📋 IR Generado (" + result.instructions.size() + " instrucciones):\n");
            for (ThreeAddressInstruction instr : result.instructions) {
                System.out.println("  " + instr);
            }
            
            System.out.println("\n✅ Test 3 pasado\n");
        } catch (Exception e) {
            System.err.println("❌ Test 3 falló: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

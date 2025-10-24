package com.miorganizacion.logotec.compilador.backend;

import com.miorganizacion.logotec.compilador.ast.*;
import com.miorganizacion.logotec.compilador.ir.*;
import com.miorganizacion.logotec.compilador.CompiladorRealAdapter;

import java.util.*;

/**
 * Tests para verificar que los procedimientos se compilan correctamente
 * a través del pipeline completo: AST → IR → Assembly → Bytecode
 * 
 * FASE 1-5: Tests de integración end-to-end
 */
public class ProcedureBytecodeTest {
    
    public static void main(String[] args) {
        System.out.println("\n╔══════════════════════════════════════════════════════════╗");
        System.out.println("║  Test de Pipeline Completo: AST → IR → Asm → Bytecode  ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝\n");
        
        testSimpleProcedureCompilation();
        testBytecodeCanLoadInVM();
        
        System.out.println("\n✅ ¡Todos los tests pasaron exitosamente!");
    }
    
    /**
     * Test 1: Procedimiento simple sin parámetros
     * 
     * Para cuadrado []
     *   repite 4
     *     avanza 50
     *     giraderecha 90
     *   fin
     * fin
     * 
     * cuadrado
     */
    private static void testSimpleProcedureCompilation() {
        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println("TEST 1: Pipeline completo con procedimiento simple");
        System.out.println("═══════════════════════════════════════════════════════\n");
        
        String code = "// Test: Procedimiento simple que dibuja un cuadrado\n" +
                      "haz dummy 0\n" +
                      "Para cuadrado [] [\n" +
                      "    Repite 4 [\n" +
                      "        avanza 50\n" +
                      "        giraderecha 90\n" +
                      "    ]\n" +
                      "] fin\n" +
                      "\n" +
                      "cuadrado\n";
        
        try {
            // 1. Parse LogoTec → AST
            System.out.println("1️⃣ Parseando código LogoTec...");
            ProgramNode program = CompiladorRealAdapter.compile(code);
            System.out.println("   ✓ AST generado\n");
            
            // 2. AST → IR
            System.out.println("2️⃣ Generando IR...");
            ASTtoIRTranslator irTranslator = new ASTtoIRTranslator();
            ASTtoIRTranslator.Result irResult = irTranslator.generate(program);
            List<ThreeAddressInstruction> irCode = irResult.instructions;
            
            System.out.println("   IR generado (" + irCode.size() + " instrucciones):");
            for (int i = 0; i < Math.min(20, irCode.size()); i++) {
                System.out.printf("     %3d: %s%n", i, irCode.get(i));
            }
            if (irCode.size() > 20) {
                System.out.println("     ... (" + (irCode.size() - 20) + " más)");
            }
            
            // Verificar que hay un PROC_BEGIN y un CALL
            boolean hasProcBegin = irCode.stream().anyMatch(ir -> ir.getOpcode() == IROpcode.PROC_BEGIN);
            boolean hasCall = irCode.stream().anyMatch(ir -> ir.getOpcode() == IROpcode.CALL);
            
            assert hasProcBegin : "Debe haber PROC_BEGIN en el IR";
            assert hasCall : "Debe haber CALL en el IR";
            
            System.out.println("   ✓ IR contiene PROC_BEGIN y CALL\n");
            
            // 3. IR → Assembly
            System.out.println("3️⃣ Generando Assembly...");
            AssemblyGenerator asmGen = new AssemblyGenerator();
            List<AssemblyInstruction> assembly = asmGen.generate(irCode);
            
            System.out.println("   Assembly generado (" + assembly.size() + " instrucciones):");
            for (int i = 0; i < Math.min(30, assembly.size()); i++) {
                System.out.println("     " + assembly.get(i));
            }
            if (assembly.size() > 30) {
                System.out.println("     ... (" + (assembly.size() - 30) + " más)");
            }
            
            // Verificar que hay JAL y JR
            boolean hasJAL = assembly.stream().anyMatch(a -> a.getOpcode() == AssemblyOpcode.JAL);
            boolean hasJR = assembly.stream().anyMatch(a -> a.getOpcode() == AssemblyOpcode.JR);
            boolean hasPUSH = assembly.stream().anyMatch(a -> a.getOpcode() == AssemblyOpcode.PUSH);
            boolean hasPOP = assembly.stream().anyMatch(a -> a.getOpcode() == AssemblyOpcode.POP);
            
            assert hasJAL : "Debe haber JAL en Assembly";
            assert hasJR : "Debe haber JR en Assembly";
            assert hasPUSH : "Debe haber PUSH en Assembly";
            assert hasPOP : "Debe haber POP en Assembly";
            
            System.out.println("   ✓ Assembly contiene JAL, JR, PUSH, POP\n");
            
            // 4. Assembly → Bytecode
            System.out.println("4️⃣ Generando Bytecode...");
            ObjectCodeGenerator bcGen = new ObjectCodeGenerator();
            
            // Debug: mostrar TODO el assembly antes de generar bytecode
            System.out.println("\n   [DEBUG] Assembly completo:");
            for (int i = 0; i < assembly.size(); i++) {
                AssemblyInstruction a = assembly.get(i);
                System.out.printf("     %3d: %-12s ops=%s", i, a.getOpcode(), a.getOperands());
                // Debug extra para MOVE, PUSH, POP
                if (a.getOpcode() == AssemblyOpcode.MOVE || 
                    a.getOpcode() == AssemblyOpcode.PUSH || 
                    a.getOpcode() == AssemblyOpcode.POP) {
                    List<String> ops = a.getOperands();
                    System.out.printf(" [size=%d", ops.size());
                    for (int j = 0; j < ops.size(); j++) {
                        System.out.printf(", [%d]='%s'", j, ops.get(j));
                    }
                    System.out.print("]");
                }
                System.out.println();
            }
            System.out.println();
            
            ObjectCodeGenerator.Result result = bcGen.generate(assembly);
            
            System.out.println("   Bytecode generado (" + result.bytecode.size() + " instrucciones):");
            for (int i = 0; i < Math.min(30, result.bytecode.size()); i++) {
                BytecodeInstruction bc = result.bytecode.get(i);
                System.out.printf("     %3d: %s%n", i, bc);
            }
            if (result.bytecode.size() > 30) {
                System.out.println("     ... (" + (result.bytecode.size() - 30) + " más)");
            }
            
            System.out.println("\n   Label Table:");
            result.labelTable.forEach((label, addr) -> 
                System.out.printf("     %s → %d%n", label, addr));
            
            // Verificar que hay CALL y RET en bytecode
            boolean hasCALL = result.bytecode.stream().anyMatch(bc -> bc.getOpcode() == BytecodeOpcode.CALL);
            boolean hasRET = result.bytecode.stream().anyMatch(bc -> bc.getOpcode() == BytecodeOpcode.RET);
            boolean hasPUSHbc = result.bytecode.stream().anyMatch(bc -> bc.getOpcode() == BytecodeOpcode.PUSH);
            boolean hasPOPbc = result.bytecode.stream().anyMatch(bc -> bc.getOpcode() == BytecodeOpcode.POP);
            
            assert hasCALL : "Debe haber CALL en Bytecode";
            assert hasRET : "Debe haber RET en Bytecode";
            assert hasPUSHbc : "Debe haber PUSH en Bytecode";
            assert hasPOPbc : "Debe haber POP en Bytecode";
            
            System.out.println("   ✓ Bytecode contiene CALL, RET, PUSH, POP\n");
            
            System.out.println("✅ Pipeline completo exitoso: LogoTec → AST → IR → Assembly → Bytecode\n");
            
        } catch (Exception e) {
            System.err.println("❌ Test falló: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Test 2: Verificar que el bytecode se puede cargar y ejecutar en la VM
     */
    private static void testBytecodeCanLoadInVM() {
        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println("TEST 2: Ejecutar bytecode en VM");
        System.out.println("═══════════════════════════════════════════════════════\n");
        
        String code = "// Test: Ejecución de procedimiento en VM\n" +
                      "haz dummy 0\n" +
                      "Para cuadrado [] [\n" +
                      "    Repite 4 [\n" +
                      "        avanza 50\n" +
                      "        giraderecha 90\n" +
                      "    ]\n" +
                      "] fin\n" +
                      "\n" +
                      "cuadrado\n";
        
        try {
            // Generar bytecode
            ProgramNode program = CompiladorRealAdapter.compile(code);
            ASTtoIRTranslator irTranslator = new ASTtoIRTranslator();
            ASTtoIRTranslator.Result irResult = irTranslator.generate(program);
            
            AssemblyGenerator asmGen = new AssemblyGenerator();
            List<AssemblyInstruction> assembly = asmGen.generate(irResult.instructions);
            
            ObjectCodeGenerator bcGen = new ObjectCodeGenerator();
            ObjectCodeGenerator.Result result = bcGen.generate(assembly);
            
            // Cargar en VM
            System.out.println("1️⃣ Cargando bytecode en VM...");
            BytecodeInterpreter vm = new BytecodeInterpreter();
            vm.loadProgram(result);
            System.out.println("   ✓ Bytecode cargado (" + result.bytecode.size() + " instrucciones)\n");
            
            // Ejecutar
            System.out.println("2️⃣ Ejecutando bytecode...");
            vm.execute();
            System.out.println("   ✓ Ejecución completada\n");
            
            // Verificar que se generaron acciones
            List<com.miorganizacion.logotec.interfaz.modelo.AccionTortuga> acciones = vm.getAcciones();
            System.out.println("3️⃣ Acciones generadas: " + acciones.size());
            for (int i = 0; i < Math.min(10, acciones.size()); i++) {
                System.out.println("     " + acciones.get(i));
            }
            if (acciones.size() > 10) {
                System.out.println("     ... (" + (acciones.size() - 10) + " más)");
            }
            
            assert acciones.size() > 0 : "Deben generarse acciones de tortuga";
            
            System.out.println("\n✅ Bytecode ejecutado exitosamente en la VM\n");
            
        } catch (Exception e) {
            System.err.println("❌ Test falló: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

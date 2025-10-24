package com.miorganizacion.logotec.compilador.backend;

import com.miorganizacion.logotec.compilador.ast.*;
import com.miorganizacion.logotec.compilador.ir.*;
import com.miorganizacion.logotec.compilador.CompiladorRealAdapter;

import java.util.*;

/**
 * Test de debug para la roseta de hexágonos
 */
public class RosetaDebugTest {
    
    public static void main(String[] args) {
        System.out.println("\n╔══════════════════════════════════════════════════════════╗");
        System.out.println("║  Debug: Roseta de Hexágonos (test.logo)                 ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝\n");
        
        String code = "// Dibuja una roseta de hexágonos\n" +
                      "\n" +
                      "haz pasos 12\n" +
                      "haz lado 50\n" +
                      "Para poligono [n l] [Repite n [avanza l giraderecha 360 / n]] fin\n" +
                      "Para roseta [cont l] [Repite cont [poligono 6 l giraderecha 360 / cont]] fin\n" +
                      "\n" +
                      "centro\n" +
                      "bajalapiz\n" +
                      "roseta pasos lado\n" +
                      "subelapiz\n";
        
        try {
            // 1. Parse
            System.out.println("1️⃣ Parseando código...");
            ProgramNode program = CompiladorRealAdapter.compile(code);
            System.out.println("   ✓ AST generado\n");
            
            // 2. AST → IR
            System.out.println("2️⃣ Generando IR...");
            ASTtoIRTranslator irTranslator = new ASTtoIRTranslator();
            ASTtoIRTranslator.Result irResult = irTranslator.generate(program);
            List<ThreeAddressInstruction> irCode = irResult.instructions;
            
            System.out.println("   IR generado (" + irCode.size() + " instrucciones):");
            
            // Mostrar solo las partes importantes
            boolean inProc = false;
            int mainStart = -1;
            
            for (int i = 0; i < irCode.size(); i++) {
                ThreeAddressInstruction ir = irCode.get(i);
                String line = String.format("     %3d: %s", i, ir);
                
                if (ir.getOpcode() == IROpcode.PROC_BEGIN) {
                    System.out.println("\n   [PROCEDURE: " + ir.getDest().getValue() + "]");
                    inProc = true;
                } else if (ir.getOpcode() == IROpcode.PROC_END) {
                    inProc = false;
                } else if (ir.getOpcode() == IROpcode.LABEL && ir.getDest().getValue().equals("main")) {
                    mainStart = i;
                    System.out.println("\n   [MAIN PROGRAM]");
                }
                
                // Mostrar primeras 5 líneas de cada proc y primeras 15 del main
                if (inProc || (mainStart >= 0 && i < mainStart + 20)) {
                    if (ir.getOpcode() != IROpcode.COMMENT) {
                        System.out.println(line);
                    }
                }
            }
            
            // Buscar CALLs
            System.out.println("\n   [CALLS ENCONTRADOS]:");
            for (int i = 0; i < irCode.size(); i++) {
                if (irCode.get(i).getOpcode() == IROpcode.CALL) {
                    System.out.printf("     %3d: %s%n", i, irCode.get(i));
                }
            }
            
            // 3. IR → Assembly
            System.out.println("\n3️⃣ Generando Assembly...");
            AssemblyGenerator asmGen = new AssemblyGenerator();
            List<AssemblyInstruction> assembly = asmGen.generate(irCode);
            System.out.println("   Assembly generado (" + assembly.size() + " instrucciones)");
            
            // Buscar JALs
            System.out.println("\n   [JALs ENCONTRADOS]:");
            for (int i = 0; i < assembly.size(); i++) {
                AssemblyInstruction a = assembly.get(i);
                if (a.getOpcode() == AssemblyOpcode.JAL) {
                    System.out.printf("     %3d: JAL %s%n", i, a.getOperands());
                }
            }
            
            // 4. Assembly → Bytecode
            System.out.println("\n4️⃣ Generando Bytecode...");
            ObjectCodeGenerator bcGen = new ObjectCodeGenerator();
            ObjectCodeGenerator.Result result = bcGen.generate(assembly);
            System.out.println("   Bytecode generado (" + result.bytecode.size() + " instrucciones)");
            
            System.out.println("\n   [LABEL TABLE]:");
            result.labelTable.forEach((label, addr) -> 
                System.out.printf("     %20s → addr=%d%n", label, addr));
            
            // Buscar CALLs en bytecode
            System.out.println("\n   [CALLs EN BYTECODE]:");
            for (int i = 0; i < result.bytecode.size(); i++) {
                BytecodeInstruction bc = result.bytecode.get(i);
                if (bc.getOpcode() == BytecodeOpcode.CALL) {
                    int addr = bc.getImmediate16();
                    System.out.printf("     %3d: CALL addr=%d (0x%04X)%n", i, addr, addr);
                }
            }
            
            // 5. Ejecutar en VM
            System.out.println("\n5️⃣ Ejecutando en VM...");
            BytecodeInterpreter vm = new BytecodeInterpreter();
            vm.setDebugMode(true); // Activar debug
            vm.loadProgram(result);
            
            System.out.println("\n[INICIO EJECUCIÓN]");
            vm.execute();
            
            List<com.miorganizacion.logotec.interfaz.modelo.AccionTortuga> acciones = vm.getAcciones();
            System.out.println("\n✅ Acciones generadas: " + acciones.size());
            
            if (acciones.size() > 0) {
                System.out.println("\n   Primeras 20 acciones:");
                for (int i = 0; i < Math.min(20, acciones.size()); i++) {
                    System.out.println("     " + acciones.get(i));
                }
            } else {
                System.out.println("\n⚠️  NO se generaron acciones");
            }
            
        } catch (Exception e) {
            System.err.println("\n❌ Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

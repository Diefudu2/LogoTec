package com.miorganizacion.logotec.compilador;

import java.nio.file.*;
import java.io.*;
import java.util.*;
import com.miorganizacion.logotec.compilador.ast.*;
import com.miorganizacion.logotec.compilador.ir.*;
import com.miorganizacion.logotec.compilador.backend.*;

/**
 * Compilador completo de LogoTec.
 * Pipeline: LogoTec → AST → IR → Assembly
 * 
 * Este compilador usa la nueva arquitectura con:
 * - CompiladorRealAdapter para generar AST
 * - ASTtoIRTranslator para generar IR
 * - AssemblyGenerator para generar Assembly
 */
public class Compiler {

    public void compile(Path sourceFile, Path outAsm) throws Exception {
        // Leer el código fuente
        String src = readFile(sourceFile);

        // PASO 1: Compilar a AST usando CompiladorRealAdapter
        System.out.println("🔧 Compilando a AST...");
        ProgramNode ast = CompiladorRealAdapter.compile(src);
        
        if (ast == null) {
            throw new RuntimeException("Error: No se pudo generar el AST");
        }
        System.out.println("✅ AST generado correctamente");

        // PASO 2: Generar IR desde AST
        System.out.println("🔧 Generando código IR...");
        ASTtoIRTranslator irTranslator = new ASTtoIRTranslator();
        ASTtoIRTranslator.Result irResult = irTranslator.generate(ast);
        
        if (irResult == null || irResult.instructions == null) {
            throw new RuntimeException("Error: No se pudo generar el código IR");
        }
        System.out.println("✅ IR generado: " + irResult.instructions.size() + " instrucciones");

        // PASO 3: Generar Assembly desde IR
        System.out.println("🔧 Generando código Assembly...");
        AssemblyGenerator asmGen = new AssemblyGenerator();
        List<AssemblyInstruction> asmCode = asmGen.generate(irResult.instructions);
        
        if (asmCode == null || asmCode.isEmpty()) {
            throw new RuntimeException("Error: No se pudo generar el código Assembly");
        }
        System.out.println("✅ Assembly generado: " + asmCode.size() + " instrucciones");

        // PASO 4: Guardar el archivo Assembly
        AssemblyUtils.saveToFile(asmCode, outAsm.toString());
        System.out.println("💾 Archivo guardado: " + outAsm);
    }

    /**
     * Lee un archivo de texto (compatible con Java 8)
     */
    private String readFile(Path path) throws IOException {
        StringBuilder content = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(path.toFile()));
        String line;
        while ((line = reader.readLine()) != null) {
            content.append(line).append("\n");
        }
        reader.close();
        return content.toString();
    }

    public static void main(String[] args) throws Exception {
        System.out.println("╔═══════════════════════════════════════════════════════╗");
        System.out.println("║      Compilador LogoTec - Pipeline Completo          ║");
        System.out.println("╚═══════════════════════════════════════════════════════╝\n");
        
        if (args.length < 2) {
            System.err.println("Uso: Compiler <input.logo> <output.asm>");
            System.err.println("\nEjemplo:");
            System.err.println("  Compiler programa.logo programa.asm");
            System.exit(1);
        }
        
        Path inputFile = Paths.get(args[0]);
        Path outputFile = Paths.get(args[1]);
        
        if (!java.nio.file.Files.exists(inputFile)) {
            System.err.println("❌ Error: El archivo de entrada no existe: " + inputFile);
            System.exit(1);
        }
        
        try {
            new Compiler().compile(inputFile, outputFile);
            System.out.println("\n✅ Compilación completada exitosamente!");
        } catch (Exception e) {
            System.err.println("\n❌ Error durante la compilación:");
            System.err.println("   " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
}

package com.miorganizacion.logotec.compilador.backend;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

/**
 * Utilidades para trabajar con código Assembly.
 */
public class AssemblyUtils {
    
    /**
     * Guarda código Assembly en un archivo.
     */
    public static void saveToFile(List<AssemblyInstruction> code, Path outputPath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath.toFile()))) {
            writer.write("# ========================================\n");
            writer.write("# Código Assembly Generado\n");
            writer.write("# LogoTec Compiler\n");
            writer.write("# ========================================\n");
            writer.write("# Total de instrucciones: " + code.size() + "\n");
            writer.write("# ========================================\n\n");
            
            for (AssemblyInstruction instr : code) {
                writer.write(instr.toString());
                writer.write("\n");
            }
            
            writer.write("\n# ========================================\n");
            writer.write("# Fin del código Assembly\n");
            writer.write("# ========================================\n");
        }
    }
    
    /**
     * Guarda Assembly en archivo (versión con String path).
     */
    public static void saveToFile(List<AssemblyInstruction> code, String outputPath) throws IOException {
        saveToFile(code, java.nio.file.Paths.get(outputPath));
    }
    
    /**
     * Formatea código Assembly como String.
     */
    public static String formatCode(List<AssemblyInstruction> code) {
        StringBuilder sb = new StringBuilder();
        sb.append("# ======================================== \n");
        sb.append("# Código Assembly\n");
        sb.append("# Total instrucciones: ").append(code.size()).append("\n");
        sb.append("# ======================================== \n\n");
        
        for (AssemblyInstruction instr : code) {
            sb.append(instr.toString());
            sb.append("\n");
        }
        
        return sb.toString();
    }
    
    /**
     * Imprime código Assembly en consola.
     */
    public static void printCode(List<AssemblyInstruction> code) {
        System.out.println(formatCode(code));
    }
    
    /**
     * Cuenta estadísticas del código Assembly.
     */
    public static void printStatistics(List<AssemblyInstruction> code) {
        System.out.println("\n=== Estadísticas del Assembly ===");
        System.out.println("Total de instrucciones: " + code.size());
        
        // Contar por tipo
        java.util.Map<AssemblyOpcode, Integer> counts = new java.util.HashMap<>();
        for (AssemblyInstruction instr : code) {
            counts.put(instr.getOpcode(), counts.getOrDefault(instr.getOpcode(), 0) + 1);
        }
        
        System.out.println("\nDistribución por opcode:");
        counts.entrySet().stream()
            .filter(e -> e.getKey() != AssemblyOpcode.COMMENT && e.getKey() != AssemblyOpcode.LABEL)
            .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
            .forEach(entry -> System.out.printf("  %-20s: %3d\n", entry.getKey(), entry.getValue()));
        
        System.out.println(String.format("\nEtiquetas: %d", counts.getOrDefault(AssemblyOpcode.LABEL, 0)));
        System.out.println(String.format("Comentarios: %d", counts.getOrDefault(AssemblyOpcode.COMMENT, 0)));
    }
}

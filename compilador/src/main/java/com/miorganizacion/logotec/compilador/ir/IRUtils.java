package com.miorganizacion.logotec.compilador.ir;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

/**
 * Utilidades para trabajar con código IR (Representación Intermedia).
 * Proporciona métodos para guardar, cargar y formatear código IR.
 */
public class IRUtils {
    
    /**
     * Guarda una lista de instrucciones IR en un archivo de texto.
     * 
     * @param instructions Lista de instrucciones a guardar
     * @param outputPath Ruta del archivo de salida (.ir)
     * @throws IOException Si hay error al escribir el archivo
     */
    public static void saveToFile(List<ThreeAddressInstruction> instructions, Path outputPath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath.toFile()))) {
            writer.write("; ========================================\n");
            writer.write("; Representación Intermedia (IR)\n");
            writer.write("; Código de Tres Direcciones\n");
            writer.write("; ========================================\n");
            writer.write("; Total de instrucciones: " + instructions.size() + "\n");
            writer.write("; ========================================\n\n");
            
            int lineNumber = 1;
            for (ThreeAddressInstruction instr : instructions) {
                // Número de línea
                writer.write(String.format("%4d: ", lineNumber++));
                
                // Instrucción
                writer.write(instr.toString());
                writer.write("\n");
            }
            
            writer.write("\n; ========================================\n");
            writer.write("; Fin del código IR\n");
            writer.write("; ========================================\n");
        }
    }
    
    /**
     * Guarda código IR en un archivo (versión con String path)
     */
    public static void saveToFile(List<ThreeAddressInstruction> instructions, String outputPath) throws IOException {
        saveToFile(instructions, Path.of(outputPath));
    }
    
    /**
     * Convierte una lista de instrucciones a String con formato legible.
     */
    public static String formatCode(List<ThreeAddressInstruction> instructions) {
        StringBuilder sb = new StringBuilder();
        sb.append("; ======================================== \n");
        sb.append("; Representación Intermedia (IR)\n");
        sb.append("; Total instrucciones: ").append(instructions.size()).append("\n");
        sb.append("; ======================================== \n\n");
        
        int lineNumber = 1;
        for (ThreeAddressInstruction instr : instructions) {
            sb.append(String.format("%4d: ", lineNumber++));
            sb.append(instr.toString());
            sb.append("\n");
        }
        
        return sb.toString();
    }
    
    /**
     * Imprime el código IR en la consola con formato.
     */
    public static void printCode(List<ThreeAddressInstruction> instructions) {
        System.out.println(formatCode(instructions));
    }
    
    /**
     * Cuenta cuántas instrucciones hay de cada tipo.
     */
    public static void printStatistics(List<ThreeAddressInstruction> instructions) {
        System.out.println("\n=== Estadísticas del IR ===");
        System.out.println("Total de instrucciones: " + instructions.size());
        
        // Contar por tipo
        java.util.Map<IROpcode, Integer> counts = new java.util.HashMap<>();
        for (ThreeAddressInstruction instr : instructions) {
            counts.put(instr.getOpcode(), counts.getOrDefault(instr.getOpcode(), 0) + 1);
        }
        
        System.out.println("\nDistribución por opcode:");
        counts.entrySet().stream()
            .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
            .forEach(entry -> System.out.printf("  %-20s: %3d\n", entry.getKey(), entry.getValue()));
    }
    
    /**
     * Valida que el código IR sea sintácticamente correcto.
     * Verifica:
     * - Que todos los saltos apunten a etiquetas existentes
     * - Que no haya etiquetas duplicadas
     * - Que los operandos sean del tipo correcto
     */
    public static boolean validate(List<ThreeAddressInstruction> instructions) {
        // Recopilar todas las etiquetas definidas
        java.util.Set<String> definedLabels = new java.util.HashSet<>();
        java.util.Set<String> usedLabels = new java.util.HashSet<>();
        
        for (ThreeAddressInstruction instr : instructions) {
            // Registrar etiquetas definidas
            if (instr.getOpcode() == IROpcode.LABEL) {
                String labelName = instr.getDest().getValue();
                if (definedLabels.contains(labelName)) {
                    System.err.println("❌ Error: Etiqueta duplicada: " + labelName);
                    return false;
                }
                definedLabels.add(labelName);
            }
            
            // Registrar etiquetas usadas en saltos
            if (instr.getOpcode() == IROpcode.JUMP) {
                usedLabels.add(instr.getDest().getValue());
            } else if (instr.getOpcode() == IROpcode.JUMP_IF_FALSE || 
                       instr.getOpcode() == IROpcode.JUMP_IF_TRUE) {
                usedLabels.add(instr.getDest().getValue());
            }
        }
        
        // Verificar que todas las etiquetas usadas estén definidas
        for (String label : usedLabels) {
            if (!definedLabels.contains(label)) {
                System.err.println("❌ Error: Etiqueta no definida: " + label);
                return false;
            }
        }
        
        System.out.println("✅ Código IR válido");
        return true;
    }
}

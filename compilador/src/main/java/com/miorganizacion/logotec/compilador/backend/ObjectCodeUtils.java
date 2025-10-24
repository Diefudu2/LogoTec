package com.miorganizacion.logotec.compilador.backend;

import java.io.*;
import java.util.*;

/**
 * Utilidades para trabajar con Object Code (Bytecode).
 * 
 * - Guardar bytecode en archivos .obj
 * - Cargar bytecode desde archivos
 * - Imprimir en formato legible
 * - Mostrar estadísticas
 * 
 * FASE 4: Object Code Generation
 */
public class ObjectCodeUtils {
    
    /**
     * Guarda el bytecode en un archivo binario
     * 
     * Formato del archivo .obj:
     * - 4 bytes: Magic number (0x4C4F474F = "LOGO")
     * - 4 bytes: Versión (0x00000001)
     * - 4 bytes: Número de instrucciones
     * - 4 bytes: Tamaño de symbol table
     * - N*4 bytes: Instrucciones de bytecode
     * - Symbol table: pares (nombre, dirección)
     */
    public static void saveToFile(ObjectCodeGenerator.Result result, String outputPath) 
            throws IOException {
        FileOutputStream fos = new FileOutputStream(outputPath);
        DataOutputStream dos = new DataOutputStream(fos);
        
        try {
            // Header: Magic number "LOGO"
            dos.writeInt(0x4C4F474F);
            
            // Versión
            dos.writeInt(0x00000001);
            
            // Número de instrucciones
            dos.writeInt(result.bytecode.size());
            
            // Tamaño de symbol table
            dos.writeInt(result.symbolTable.size());
            
            // Escribir bytecode
            for (BytecodeInstruction instr : result.bytecode) {
                byte[] bytes = instr.toBytes();
                dos.write(bytes);
            }
            
            // Escribir symbol table
            for (Map.Entry<String, Integer> entry : result.symbolTable.entrySet()) {
                dos.writeUTF(entry.getKey());
                dos.writeInt(entry.getValue());
            }
            
            dos.flush();
            
        } finally {
            dos.close();
            fos.close();
        }
    }
    
    /**
     * Carga bytecode desde un archivo
     */
    public static LoadedProgram loadFromFile(String inputPath) throws IOException {
        FileInputStream fis = new FileInputStream(inputPath);
        DataInputStream dis = new DataInputStream(fis);
        
        try {
            // Leer header
            int magic = dis.readInt();
            if (magic != 0x4C4F474F) {
                throw new IOException("Archivo no es un bytecode válido (magic number incorrecto)");
            }
            
            int version = dis.readInt();
            if (version != 0x00000001) {
                throw new IOException("Versión de bytecode no soportada: " + version);
            }
            
            // Leer número de instrucciones
            int numInstructions = dis.readInt();
            
            // Leer tamaño de symbol table
            int symbolTableSize = dis.readInt();
            
            // Leer instrucciones
            List<BytecodeInstruction> bytecode = new ArrayList<>();
            for (int i = 0; i < numInstructions; i++) {
                byte[] bytes = new byte[4];
                dis.readFully(bytes);
                bytecode.add(BytecodeInstruction.fromBytes(bytes));
            }
            
            // Leer symbol table
            Map<String, Integer> symbolTable = new HashMap<>();
            for (int i = 0; i < symbolTableSize; i++) {
                String name = dis.readUTF();
                int address = dis.readInt();
                symbolTable.put(name, address);
            }
            
            return new LoadedProgram(bytecode, symbolTable);
            
        } finally {
            dis.close();
            fis.close();
        }
    }
    
    /**
     * Imprime el bytecode en formato legible
     */
    public static void printCode(ObjectCodeGenerator.Result result) {
        System.out.println("# ======================================== ");
        System.out.println("# Código Object (Bytecode)");
        System.out.println("# Total instrucciones: " + result.bytecode.size());
        System.out.println("# ======================================== ");
        System.out.println();
        
        // Imprimir symbol table
        if (!result.symbolTable.isEmpty()) {
            System.out.println("Symbol Table:");
            List<String> sortedVars = new ArrayList<>(result.symbolTable.keySet());
            Collections.sort(sortedVars);
            for (String var : sortedVars) {
                System.out.printf("  %-15s → 0x%04X (%d)%n", 
                                var, 
                                result.symbolTable.get(var),
                                result.symbolTable.get(var));
            }
            System.out.println();
        }
        
        // Imprimir label table
        if (!result.labelTable.isEmpty()) {
            System.out.println("Label Table:");
            List<String> sortedLabels = new ArrayList<>(result.labelTable.keySet());
            Collections.sort(sortedLabels);
            for (String label : sortedLabels) {
                System.out.printf("  %-15s → Instrucción #%d%n", 
                                label,
                                result.labelTable.get(label));
            }
            System.out.println();
        }
        
        // Imprimir bytecode
        System.out.println("Bytecode:");
        System.out.println("Addr   Hex              Instrucción");
        System.out.println("------ ---------------- ------------------------------------");
        
        int addr = 0;
        for (BytecodeInstruction instr : result.bytecode) {
            System.out.printf("%04X   %-16s %s%n", 
                            addr, 
                            instr.toHex(),
                            instr.toString());
            addr++;
        }
    }
    
    /**
     * Imprime estadísticas del bytecode
     */
    public static void printStatistics(ObjectCodeGenerator.Result result) {
        System.out.println("\n=== Estadísticas del Object Code ===");
        System.out.println("Total de instrucciones: " + result.bytecode.size());
        System.out.println("Tamaño en bytes: " + (result.bytecode.size() * 4));
        System.out.println("Variables declaradas: " + result.symbolTable.size());
        System.out.println("Labels definidos: " + result.labelTable.size());
        
        // Distribución de opcodes
        Map<BytecodeOpcode, Integer> opcodeCount = new HashMap<>();
        for (BytecodeInstruction instr : result.bytecode) {
            BytecodeOpcode op = instr.getOpcode();
            opcodeCount.put(op, opcodeCount.getOrDefault(op, 0) + 1);
        }
        
        System.out.println("\nDistribución por opcode:");
        List<Map.Entry<BytecodeOpcode, Integer>> sortedOpcodes = 
            new ArrayList<>(opcodeCount.entrySet());
        sortedOpcodes.sort((a, b) -> b.getValue().compareTo(a.getValue()));
        
        for (Map.Entry<BytecodeOpcode, Integer> entry : sortedOpcodes) {
            System.out.printf("  %-20s: %3d%n", entry.getKey().name(), entry.getValue());
        }
    }
    
    /**
     * Convierte el bytecode a un dump hexadecimal
     */
    public static String toHexDump(List<BytecodeInstruction> bytecode) {
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < bytecode.size(); i++) {
            if (i % 4 == 0) {
                sb.append(String.format("%04X: ", i));
            }
            
            sb.append(bytecode.get(i).toHex()).append("  ");
            
            if ((i + 1) % 4 == 0) {
                sb.append("\n");
            }
        }
        
        return sb.toString();
    }
    
    /**
     * Programa cargado desde archivo
     */
    public static class LoadedProgram {
        public final List<BytecodeInstruction> bytecode;
        public final Map<String, Integer> symbolTable;
        
        public LoadedProgram(List<BytecodeInstruction> bytecode, 
                           Map<String, Integer> symbolTable) {
            this.bytecode = bytecode;
            this.symbolTable = symbolTable;
        }
    }
}

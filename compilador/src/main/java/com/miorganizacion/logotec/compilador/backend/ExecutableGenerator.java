package com.miorganizacion.logotec.compilador.backend;

import java.io.*;
import java.util.*;

/**
 * Generador de archivos objeto ejecutables para LogoTec.
 * 
 * Crea archivos .ltbc (LogoTec ByteCode) que pueden ejecutarse
 * independientemente del IDE usando la VM standalone.
 * 
 * Formato del archivo .ltbc:
 * ┌────────────────────────────────────────┐
 * │ HEADER                                 │
 * │   - Magic: "LTBC" (4 bytes)            │
 * │   - Version: 1 (4 bytes)               │
 * │   - Timestamp: long (8 bytes)          │
 * │                                        │
 * │ SYMBOL TABLE                           │
 * │   - Count: int (4 bytes)               │
 * │   - Entries: [name, address] pairs     │
 * │                                        │
 * │ BYTECODE SECTION                       │
 * │   - Count: int (4 bytes)               │
 * │   - Instructions: 4 bytes each         │
 * └────────────────────────────────────────┘
 * 
 * Uso:
 *   // En el IDE al compilar
 *   ExecutableGenerator.saveToFile(result, "output/cuadrado.ltbc");
 *   
 *   // Para ejecutar (desde terminal)
 *   java -jar logotec-vm.jar cuadrado.ltbc
 * 
 * @author Equipo LogoTec
 */
public class ExecutableGenerator {
    
    private static final byte[] MAGIC = {'L', 'T', 'B', 'C'};
    private static final int VERSION = 1;
    
    /**
     * Guarda el resultado de compilación a un archivo .ltbc ejecutable
     * 
     * @param result Resultado de ObjectCodeGenerator con bytecode y symbols
     * @param outputPath Ruta del archivo de salida (ej: "output/programa.ltbc")
     * @throws IOException Si hay error al escribir el archivo
     */
    public static void saveToFile(ObjectCodeGenerator.Result result, String outputPath) throws IOException {
        File outputFile = new File(outputPath);
        
        // Crear directorio si no existe
        File parentDir = outputFile.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }
        
        try (DataOutputStream out = new DataOutputStream(
                new BufferedOutputStream(new FileOutputStream(outputFile)))) {
            
            // === HEADER ===
            out.write(MAGIC);                           // Magic number "LTBC"
            out.writeInt(VERSION);                      // Version 1
            out.writeLong(System.currentTimeMillis());  // Timestamp
            
            // === SYMBOL TABLE ===
            Map<String, Integer> symbols = result.symbolTable;
            out.writeInt(symbols.size());               // Cantidad de símbolos
            
            for (Map.Entry<String, Integer> entry : symbols.entrySet()) {
                out.writeUTF(entry.getKey());           // Nombre de variable
                out.writeInt(entry.getValue());         // Dirección en memoria
            }
            
            // === BYTECODE INSTRUCTIONS ===
            List<BytecodeInstruction> bytecode = result.bytecode;
            out.writeInt(bytecode.size());              // Cantidad de instrucciones
            
            for (BytecodeInstruction instr : bytecode) {
                // Cada instrucción es de 4 bytes (opcode + 3 operandos)
                out.writeByte(instr.getOpcode().getCode());
                out.writeByte(instr.getOperand1() & 0xFF);
                out.writeByte(instr.getOperand2() & 0xFF);
                out.writeByte(instr.getOperand3() & 0xFF);
            }
        }
        
        System.out.println("✅ Archivo objeto generado: " + outputFile.getAbsolutePath());
        System.out.println("   Tamaño: " + outputFile.length() + " bytes");
        System.out.println("   Instrucciones: " + result.bytecode.size());
        System.out.println("   Variables: " + result.symbolTable.size());
    }
    
    /**
     * Carga un archivo .ltbc y retorna el resultado deserializado
     * 
     * @param filePath Ruta del archivo .ltbc
     * @return Resultado con bytecode y symbol table
     * @throws IOException Si hay error al leer el archivo
     */
    public static ObjectCodeGenerator.Result loadFromFile(String filePath) throws IOException {
        File inputFile = new File(filePath);
        
        if (!inputFile.exists()) {
            throw new FileNotFoundException("Archivo no encontrado: " + filePath);
        }
        
        try (DataInputStream in = new DataInputStream(
                new BufferedInputStream(new FileInputStream(inputFile)))) {
            
            // === VERIFICAR HEADER ===
            byte[] magic = new byte[4];
            in.readFully(magic);
            
            if (!Arrays.equals(magic, MAGIC)) {
                throw new IOException("Archivo no válido: magic number incorrecto");
            }
            
            int version = in.readInt();
            if (version != VERSION) {
                throw new IOException("Versión no soportada: " + version + 
                                    " (esperada: " + VERSION + ")");
            }
            
            long timestamp = in.readLong();
            
            // === SYMBOL TABLE ===
            int symbolCount = in.readInt();
            Map<String, Integer> symbolTable = new HashMap<>();
            
            for (int i = 0; i < symbolCount; i++) {
                String name = in.readUTF();
                int address = in.readInt();
                symbolTable.put(name, address);
            }
            
            // === BYTECODE INSTRUCTIONS ===
            int instrCount = in.readInt();
            List<BytecodeInstruction> bytecode = new ArrayList<>();
            
            for (int i = 0; i < instrCount; i++) {
                byte opcodeCode = in.readByte();
                byte op1 = in.readByte();
                byte op2 = in.readByte();
                byte op3 = in.readByte();
                
                BytecodeOpcode opcode = BytecodeOpcode.fromCode(opcodeCode & 0xFF);
                BytecodeInstruction instr = new BytecodeInstruction(
                    opcode,
                    op1 & 0xFF,
                    op2 & 0xFF,
                    op3 & 0xFF
                );
                
                bytecode.add(instr);
            }
            
            System.out.println("✅ Archivo cargado: " + inputFile.getName());
            System.out.println("   Instrucciones: " + bytecode.size());
            System.out.println("   Variables: " + symbolTable.size());
            System.out.println("   Fecha compilación: " + new Date(timestamp));
            
            // El labelTable está vacío porque las etiquetas ya fueron resueltas durante el ensamblado
            Map<String, Integer> labelTable = new HashMap<>();
            
            return new ObjectCodeGenerator.Result(bytecode, symbolTable, labelTable);
        }
    }
    
    /**
     * Valida que un archivo .ltbc sea correcto
     * 
     * @param filePath Ruta del archivo a validar
     * @return true si el archivo es válido
     */
    public static boolean validate(String filePath) {
        try {
            loadFromFile(filePath);
            return true;
        } catch (Exception e) {
            System.err.println("❌ Archivo inválido: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Verifica si un archivo es un .ltbc válido (alias de validate)
     * 
     * @param filePath Ruta del archivo a verificar
     * @return true si el archivo es un .ltbc válido
     */
    public static boolean isValidLTBC(String filePath) {
        return validate(filePath);
    }
    
    /**
     * Obtiene información sobre un archivo .ltbc sin cargarlo completamente
     * 
     * @param filePath Ruta del archivo
     * @return Información del archivo
     * @throws IOException Si hay error al leer
     */
    public static FileInfo getFileInfo(String filePath) throws IOException {
        File file = new File(filePath);
        
        try (DataInputStream in = new DataInputStream(
                new BufferedInputStream(new FileInputStream(file)))) {
            
            // Leer header
            byte[] magic = new byte[4];
            in.readFully(magic);
            
            int version = in.readInt();
            long timestamp = in.readLong();
            
            // Leer contadores
            int symbolCount = in.readInt();
            
            // Skip symbol table
            for (int i = 0; i < symbolCount; i++) {
                in.readUTF();   // name
                in.readInt();   // address
            }
            
            int instrCount = in.readInt();
            
            return new FileInfo(
                file.getName(),
                file.length(),
                version,
                new Date(timestamp),
                instrCount,
                symbolCount
            );
        }
    }
    
    /**
     * Información sobre un archivo .ltbc
     */
    public static class FileInfo {
        public final String fileName;
        public final long fileSize;
        public final int version;
        public final Date compilationDate;
        public final int instructionCount;
        public final int variableCount;
        
        public FileInfo(String fileName, long fileSize, int version, 
                       Date compilationDate, int instructionCount, int variableCount) {
            this.fileName = fileName;
            this.fileSize = fileSize;
            this.version = version;
            this.compilationDate = compilationDate;
            this.instructionCount = instructionCount;
            this.variableCount = variableCount;
        }
        
        @Override
        public String toString() {
            return String.format(
                "╔═══════════════════════════════════════════════╗\n" +
                "║  Archivo: %-36s║\n" +
                "║  Tamaño: %7d bytes                       ║\n" +
                "║  Versión: %-35d║\n" +
                "║  Compilado: %-33s║\n" +
                "║  Instrucciones: %-29d║\n" +
                "║  Variables: %-33d║\n" +
                "╚═══════════════════════════════════════════════╝",
                fileName, fileSize, version, compilationDate, 
                instructionCount, variableCount
            );
        }
    }
}

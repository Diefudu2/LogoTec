package com.miorganizacion.logotec.compilador.backend;

import com.miorganizacion.logotec.interfaz.modelo.AccionTortuga;
import com.miorganizacion.logotec.interfaz.modelo.AccionTortuga.Tipo;

import java.util.*;

/**
 * Máquina Virtual para ejecutar bytecode de LogoTec.
 * 
 * Simula una arquitectura de registros con 32 registros y memoria.
 * Ejecuta instrucciones de bytecode y genera acciones de tortuga.
 * 
 * Pipeline completo:
 *   LogoTec → AST → IR → Assembly → Bytecode → [VM EJECUTA] → AccionTortuga
 * 
 * FASE 5: Bytecode Interpreter
 */
public class BytecodeInterpreter {
    
    // === Arquitectura de la VM ===
    
    // 32 registros (estilo MIPS)
    private int[] registers;
    
    // Memoria (4KB = 1024 words de 4 bytes)
    private int[] memory;
    
    // Program Counter (dirección de instrucción actual)
    private int pc;
    
    // Bytecode a ejecutar
    private List<BytecodeInstruction> program;
    
    // Symbol table (variable → dirección)
    private Map<String, Integer> symbolTable;
    
    // Stack para llamadas a funciones
    private Stack<Integer> callStack;
    
    // ← NUEVO: Stack de argumentos para procedimientos
    private Stack<Map<String, Integer>> argumentStack;
    
    // ← NUEVO: Argumentos actuales del procedimiento en ejecución
    private Map<String, Integer> currentArgs;
    
    // === Estado de la Tortuga ===
    
    // Lista de acciones generadas
    private List<AccionTortuga> acciones;
    
    // Estado actual de la tortuga
    private double turtleX;
    private double turtleY;
    private double turtleAngle;  // En grados
    private boolean penDown;
    
    // === Constantes ===
    
    // Índices de registros especiales
    private static final int REG_ZERO = 0;   // $zero - siempre 0
    private static final int REG_V0 = 2;     // $v0 - syscall code
    private static final int REG_A0 = 4;     // $a0 - argumento 1
    private static final int REG_A1 = 5;     // $a1 - argumento 2
    private static final int REG_A2 = 6;     // $a2 - argumento 3
    private static final int REG_A3 = 7;     // $a3 - argumento 4
    private static final int REG_SP = 29;    // $sp - stack pointer
    private static final int REG_RA = 31;    // $ra - return address
    
    // Tamaño de memoria (en words de 4 bytes)
    private static final int MEMORY_SIZE = 1024;
    
    // Modo debug
    private boolean debugMode;
    
    /**
     * Constructor
     */
    public BytecodeInterpreter() {
        this.registers = new int[32];
        this.memory = new int[MEMORY_SIZE];
        this.callStack = new Stack<>();
        this.argumentStack = new Stack<>();  // ← NUEVO
        this.currentArgs = new HashMap<>();  // ← NUEVO
        this.acciones = new ArrayList<>();
        this.symbolTable = new HashMap<>();
        this.debugMode = false;
        
        // Inicializar estado de tortuga
        resetTurtle();
    }
    
    /**
     * Carga un programa para ejecutar
     */
    public void loadProgram(List<BytecodeInstruction> bytecode, Map<String, Integer> symbols) {
        this.program = new ArrayList<>(bytecode);
        this.symbolTable = new HashMap<>(symbols);
        this.pc = 0;
        
        // Reset de registros y memoria
        Arrays.fill(registers, 0);
        Arrays.fill(memory, 0);
        callStack.clear();
        acciones.clear();
        
        // Inicializar stack pointer al final de la memoria
        registers[REG_SP] = MEMORY_SIZE - 1;
        
        // DEBUG: Mostrar symbol table
        if (debugMode) {
            System.out.println("📋 Symbol Table cargada:");
            for (Map.Entry<String, Integer> entry : symbols.entrySet()) {
                System.out.println("   " + entry.getKey() + " → addr " + entry.getValue());
            }
        }
        
        resetTurtle();
    }
    
    /**
     * Carga desde un resultado de ObjectCodeGenerator
     */
    public void loadProgram(ObjectCodeGenerator.Result result) {
        loadProgram(result.bytecode, result.symbolTable);
    }
    
    /**
     * Ejecuta el programa completo
     */
    public void execute() {
        if (program == null || program.isEmpty()) {
            throw new IllegalStateException("No hay programa cargado");
        }
        
        if (debugMode) {
            System.out.println("\n╔═══════════════════════════════════════════════════════╗");
            System.out.println("║      Iniciando ejecución de Bytecode                 ║");
            System.out.println("╚═══════════════════════════════════════════════════════╝\n");
        }
        
        // Ejecutar hasta HALT o fin del programa
        while (pc < program.size()) {
            BytecodeInstruction instr = program.get(pc);
            
            if (debugMode) {
                System.out.printf("[PC=%04d] Ejecutando: %s%n", pc, instr);
            }
            
            // Ejecutar instrucción
            boolean shouldContinue = executeInstruction(instr);
            
            if (!shouldContinue) {
                break; // HALT o error
            }
            
            pc++;
            
            // Protección contra loops infinitos
            if (pc > 100000) {
                throw new RuntimeException("Posible loop infinito detectado (PC > 100000)");
            }
        }
        
        if (debugMode) {
            System.out.println("\n✅ Ejecución completada");
            System.out.println("   Acciones generadas: " + acciones.size());
        }
    }
    
    /**
     * Ejecuta una instrucción
     * @return true si debe continuar, false si debe terminar (HALT)
     */
    private boolean executeInstruction(BytecodeInstruction instr) {
        BytecodeOpcode opcode = instr.getOpcode();
        int op1 = instr.getOperand1();
        int op2 = instr.getOperand2();
        int op3 = instr.getOperand3();
        
        // ← NUEVO: Manejar instrucciones especiales de IR
        String instrStr = instr.toString();
        if (instrStr.contains("GET_ARG")) {
            executeGetArg(instr);
            return true;
        }
        if (instrStr.contains("PARAM")) {
            executeParam(instr);
            return true;
        }
        
        switch (opcode) {
            // === Movimiento de datos ===
            case LOAD_IMM: {
                // reg = immediate (16 bits)
                int immediate = instr.getImmediate16();
                registers[op1] = immediate;
                if (debugMode) {
                    System.out.println("     → $" + op1 + " = " + immediate);
                }
                break;
            }
            
            case LOAD_MEM: {
                // reg = memory[addr]
                int addr = instr.getImmediate16();
                int value = readMemory(addr);
                registers[op1] = value;
                if (debugMode) {
                    System.out.println("     → $" + op1 + " = mem[" + addr + "] = " + value);
                }
                break;
            }
            
            case STORE_MEM: {
                // memory[addr] = reg
                int addr = instr.getImmediate16();
                int value = registers[op1];
                writeMemory(addr, value);
                if (debugMode) {
                    System.out.println("     → mem[" + addr + "] = $" + op1 + " = " + value);
                }
                break;
            }
            
            case MOVE: {
                // dest = src
                registers[op1] = registers[op2];
                if (debugMode) {
                    System.out.println("     → $" + op1 + " = $" + op2 + " = " + registers[op1]);
                }
                break;
            }
            
            // === Operaciones aritméticas ===
            case ADD: {
                // dest = src1 + src2
                registers[op1] = registers[op2] + registers[op3];
                if (debugMode) {
                    System.out.println("     → $" + op1 + " = " + registers[op2] + " + " + registers[op3] + " = " + registers[op1]);
                }
                break;
            }
            
            case ADD_IMM: {
                registers[op1] = registers[op2] + op3;
                break;
            }
            
            case SUB: {
                registers[op1] = registers[op2] - registers[op3];
                if (debugMode) {
                    System.out.println("     → $" + op1 + " = " + registers[op2] + " - " + registers[op3] + " = " + registers[op1]);
                }
                break;
            }
            
            case MUL: {
                registers[op1] = registers[op2] * registers[op3];
                break;
            }
            
            case DIV: {
                if (registers[op3] == 0) {
                    throw new RuntimeException("División por cero");
                }
                registers[op1] = registers[op2] / registers[op3];
                break;
            }
            
            case REM: {
                if (registers[op3] == 0) {
                    throw new RuntimeException("Módulo por cero");
                }
                registers[op1] = registers[op2] % registers[op3];
                break;
            }
            
            // === Operaciones lógicas ===
            case AND: {
                registers[op1] = registers[op2] & registers[op3];
                break;
            }
            
            case OR: {
                registers[op1] = registers[op2] | registers[op3];
                break;
            }
            
            case XOR: {
                registers[op1] = registers[op2] ^ registers[op3];
                break;
            }
            
            case NOT: {
                registers[op1] = ~registers[op2];
                break;
            }
            
            // === Comparaciones ===
            case SEQ: {
                registers[op1] = (registers[op2] == registers[op3]) ? 1 : 0;
                break;
            }
            
            case SNE: {
                registers[op1] = (registers[op2] != registers[op3]) ? 1 : 0;
                break;
            }
            
            case SLT: {
                // Set if less than
                registers[op1] = (registers[op2] < registers[op3]) ? 1 : 0;
                if (debugMode) {
                    System.out.println("     → $" + op1 + " = (" + registers[op2] + " < " + registers[op3] + ") = " + registers[op1]);
                }
                break;
            }
            
            case SLE: {
                registers[op1] = (registers[op2] <= registers[op3]) ? 1 : 0;
                break;
            }
            
            case SGT: {
                registers[op1] = (registers[op2] > registers[op3]) ? 1 : 0;
                break;
            }
            
            case SGE: {
                registers[op1] = (registers[op2] >= registers[op3]) ? 1 : 0;
                break;
            }
            
            // === Control de flujo ===
            case JUMP: {
                int addr = instr.getImmediate16();
                if (debugMode) {
                    System.out.println("     → JUMP to " + addr);
                }
                pc = addr - 1; // -1 porque se incrementará después
                break;
            }
            
            case BEQZ: {
                // Branch if equal to zero
                if (registers[op1] == 0) {
                    int addr = instr.getImmediate16();
                    if (debugMode) {
                        System.out.println("     → BEQZ: $" + op1 + " == 0, jumping to " + addr);
                    }
                    pc = addr - 1;
                } else {
                    if (debugMode) {
                        System.out.println("     → BEQZ: $" + op1 + " = " + registers[op1] + " != 0, not jumping");
                    }
                }
                break;
            }
            
            case BNEZ: {
                // Branch if not equal to zero
                if (registers[op1] != 0) {
                    int addr = instr.getImmediate16();
                    if (debugMode) {
                        System.out.println("     → BNEZ: $" + op1 + " != 0, jumping to " + addr);
                    }
                    pc = addr - 1;
                }
                break;
            }
            
            case BEQ: {
                if (registers[op1] == registers[op2]) {
                    pc = op3 - 1;
                }
                break;
            }
            
            case BNE: {
                if (registers[op1] != registers[op2]) {
                    pc = op3 - 1;
                }
                break;
            }
            
            case CALL: {
                // Llamada a función
                callStack.push(pc + 1);
                int addr = instr.getImmediate16();
                if (debugMode) {
                    System.out.println("     → CALL to " + addr + ", return addr = " + (pc + 1));
                }
                pc = addr - 1;
                break;
            }
            
            case RET: {
                // Retorno de función
                if (callStack.isEmpty()) {
                    if (debugMode) {
                        System.out.println("     → RET: call stack empty, terminating");
                    }
                    return false; // Terminar programa
                }
                int returnAddr = callStack.pop();
                if (debugMode) {
                    System.out.println("     → RET to " + returnAddr);
                }
                pc = returnAddr - 1;
                break;
            }
            
            // === Stack ===
            case PUSH: {
                int sp = registers[REG_SP];
                memory[sp] = registers[op1];
                registers[REG_SP]--;
                break;
            }
            
            case POP: {
                registers[REG_SP]++;
                int sp = registers[REG_SP];
                registers[op1] = memory[sp];
                break;
            }
            
            // === Syscalls (Comandos de tortuga) ===
            case SYSCALL: {
                executeSyscall(op1);
                break;
            }
            
            // === Especiales ===
            case NOP: {
                break;
            }
            
            case HALT: {
                return false;
            }
            
            default:
                throw new RuntimeException("Opcode no implementado: " + opcode);
        }
        
        // $zero siempre debe ser 0
        registers[REG_ZERO] = 0;
        
        return true; // Continuar ejecución
    }
    
    /**
     * Ejecuta una llamada al sistema (syscall)
     */
    private void executeSyscall(int code) {
        int arg1 = registers[REG_A0];
        int arg2 = registers[REG_A1];
        int arg3 = registers[REG_A2];
        
        if (debugMode) {
            System.out.printf("   → SYSCALL %d (arg1=%d, arg2=%d, arg3=%d)%n", code, arg1, arg2, arg3);
        }
        
        switch (code) {
            case 1:  // FORWARD
                avanzar(arg1);
                break;
                
            case 2:  // BACKWARD
                retroceder(arg1);
                break;
                
            case 3:  // TURN_RIGHT
                girarDerecha(arg1);
                break;
                
            case 4:  // TURN_LEFT
                girarIzquierda(arg1);
                break;
                
            case 5:  // PEN_UP
                subirLapiz();
                break;
                
            case 6:  // PEN_DOWN
                bajarLapiz();
                break;
                
            case 7:  // CENTER
                centrar();
                break;
                
            case 8:  // SET_POS (ponxy x y)
                setPosicion(arg1, arg2);
                break;
                
            case 9:  // SET_COLOR (poncolorlapiz)
                setColor(arg1, arg2, arg3);
                break;
                
            case 10: // SET_HEADING (ponrumbo)
                setRumbo(arg1);
                break;
                
            case 11: // HIDE_TURTLE
                ocultarTortuga();
                break;
                
            case 12: // SHOW_TURTLE
                mostrarTortuga();
                break;
                
            default:
                System.err.println("⚠️  Syscall desconocido: " + code);
        }
    }
    
    // === Comandos de Tortuga ===
    
    private void avanzar(double distancia) {
        acciones.add(new AccionTortuga(Tipo.AVANZAR, distancia));
        
        // Actualizar posición interna
        double rad = Math.toRadians(turtleAngle);
        turtleX += distancia * Math.cos(rad);
        turtleY += distancia * Math.sin(rad);
    }
    
    private void retroceder(double distancia) {
        avanzar(-distancia);
    }
    
    private void girarDerecha(double grados) {
        acciones.add(new AccionTortuga(Tipo.GIRAR, grados));
        turtleAngle = (turtleAngle + grados) % 360;
    }
    
    private void girarIzquierda(double grados) {
        girarDerecha(-grados);
    }
    
    private void bajarLapiz() {
        if (!penDown) {
            acciones.add(new AccionTortuga(Tipo.BAJAR_LAPIZ, 0));
            penDown = true;
        }
    }
    
    private void subirLapiz() {
        if (penDown) {
            acciones.add(new AccionTortuga(Tipo.LEVANTAR_LAPIZ, 0));
            penDown = false;
        }
    }
    
    private void centrar() {
        turtleX = 0;
        turtleY = 0;
        turtleAngle = 0;
        // Acción especial de centrado
        acciones.add(new AccionTortuga(Tipo.AVANZAR, 0)); // Placeholder
    }
    
    private void setPosicion(double x, double y) {
        turtleX = x;
        turtleY = y;
    }
    
    private void setRumbo(double angulo) {
        turtleAngle = angulo % 360;
    }
    
    private void ocultarTortuga() {
        // Implementar si es necesario
    }
    
    private void mostrarTortuga() {
        // Implementar si es necesario
    }
    
    private void resetTurtle() {
        turtleX = 0;
        turtleY = 0;
        turtleAngle = 0;
        penDown = true;
    }
    
    // === Acceso a memoria ===
    
    private int readMemory(int address) {
        if (address < 0 || address >= memory.length) {
            throw new RuntimeException("Acceso a memoria inválido: 0x" + 
                                     Integer.toHexString(address));
        }
        return memory[address];
    }
    
    private void writeMemory(int address, int value) {
        if (address < 0 || address >= memory.length) {
            throw new RuntimeException("Acceso a memoria inválido: 0x" + 
                                     Integer.toHexString(address));
        }
        memory[address] = value;
    }
    
    // === Getters ===
    
    public List<AccionTortuga> getAcciones() {
        return new ArrayList<>(acciones);
    }
    
    public int[] getRegisters() {
        return Arrays.copyOf(registers, registers.length);
    }
    
    public int getRegister(int index) {
        return registers[index];
    }
    
    public int getProgramCounter() {
        return pc;
    }
    
    public void setDebugMode(boolean debug) {
        this.debugMode = debug;
    }
    
    public boolean isDebugMode() {
        return debugMode;
    }
    
    private void setColor(int r, int g, int b) {
        System.out.println("   → SET_COLOR RGB(" + r + ", " + g + ", " + b + ")");
        acciones.add(new AccionTortuga(Tipo.CAMBIAR_COLOR, r, g, b));
    }
    
    // ← NUEVO: Implementar GET_ARG
    private void executeGetArg(BytecodeInstruction instr) {
        String instrStr = instr.toString();
        if (debugMode) {
            System.out.println("   → Procesando GET_ARG: " + instrStr);
        }
        
        // Parsear: "GET_ARG VARIABLE(ancho), CONSTANT(0)"
        String[] parts = instrStr.split(",");
        if (parts.length < 2) {
            System.err.println("⚠️  GET_ARG mal formado: " + instrStr);
            return;
        }
        
        String varPart = parts[0].trim();
        String posPart = parts[1].trim();
        
        // Extraer nombre de variable: "GET_ARG VARIABLE(ancho)" → "ancho"
        int start = varPart.indexOf("VARIABLE(");
        if (start < 0) return;
        int end = varPart.indexOf(")", start);
        if (end < 0) return;
        String varName = varPart.substring(start + 9, end);
        
        // Extraer posición: "CONSTANT(0)" → 0
        start = posPart.indexOf("CONSTANT(");
        if (start < 0) return;
        end = posPart.indexOf(")", start);
        if (end < 0) return;
        String posStr = posPart.substring(start + 9, end);
        int argPos = (int) Double.parseDouble(posStr);
        
        if (debugMode) {
            System.out.println("     → GET_ARG: variable='" + varName + "', pos=" + argPos);
            System.out.println("     → Argumentos actuales: " + currentArgs);
        }
        
        // Obtener el valor del argumento
        if (currentArgs.containsKey(String.valueOf(argPos))) {
            int value = currentArgs.get(String.valueOf(argPos));
            
            // Guardar directamente en symbol table como valor
            Integer addr = symbolTable.get(varName);
            if (addr == null) {
                addr = symbolTable.size() + 100;
                symbolTable.put(varName, addr);
            }
            writeMemory(addr, value);
            
            if (debugMode) {
                System.out.println("     → Guardado: " + varName + " = " + value + " en addr " + addr);
            }
        } else {
            System.err.println("⚠️  Argumento " + argPos + " no encontrado en currentArgs: " + currentArgs.keySet());
        }
    }
    
    private void executeParam(BytecodeInstruction instr) {
        String instrStr = instr.toString();
        if (debugMode) {
            System.out.println("   → Procesando PARAM: " + instrStr);
        }
        
        // Parsear: "PARAM CONSTANT(0), TEMP(t5)"
        String[] parts = instrStr.split(",");
        if (parts.length < 2) return;
        
        String posPart = parts[0].trim();
        String valuePart = parts[1].trim();
        
        // Extraer posición
        int start = posPart.indexOf("CONSTANT(");
        if (start < 0) return;
        int end = posPart.indexOf(")", start);
        if (end < 0) return;
        String posStr = posPart.substring(start + 9, end);
        int argPos = (int) Double.parseDouble(posStr);
        
        // Extraer valor desde registro temporal
        start = valuePart.indexOf("TEMP(t");
        if (start >= 0) {
            end = valuePart.indexOf(")", start);
            String tempStr = valuePart.substring(start + 6, end);
            int tempNum = Integer.parseInt(tempStr);
            int reg = 8 + tempNum; // t0 = $8, t1 = $9, etc.
            int value = registers[reg];
            
            currentArgs.put(String.valueOf(argPos), value);
            
            if (debugMode) {
                System.out.println("     → PARAM[" + argPos + "] = " + value + " (desde registro $t" + tempNum + ")");
            }
        } else {
            // Puede ser CONSTANT directo
            start = valuePart.indexOf("CONSTANT(");
            if (start >= 0) {
                end = valuePart.indexOf(")", start);
                String constStr = valuePart.substring(start + 9, end);
                int value = (int) Double.parseDouble(constStr);
                
                currentArgs.put(String.valueOf(argPos), value);
                
                if (debugMode) {
                    System.out.println("     → PARAM[" + argPos + "] = " + value + " (constante)");
                }
            }
        }
    }
}

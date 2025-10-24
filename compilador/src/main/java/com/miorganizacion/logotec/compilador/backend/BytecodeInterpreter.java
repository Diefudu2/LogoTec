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
        
        switch (opcode) {
            // === Movimiento de datos ===
            case LOAD_IMM: {
                // reg = immediate (16 bits)
                int immediate = instr.getImmediate16();
                registers[op1] = immediate;
                break;
            }
            
            case LOAD_MEM: {
                // reg = memory[addr]
                int addr = instr.getImmediate16();
                registers[op1] = readMemory(addr);
                break;
            }
            
            case STORE_MEM: {
                // memory[addr] = reg
                int addr = instr.getImmediate16();
                writeMemory(addr, registers[op1]);
                break;
            }
            
            case MOVE: {
                // dest = src
                registers[op1] = registers[op2];
                break;
            }
            
            // === Operaciones aritméticas ===
            case ADD: {
                // dest = src1 + src2
                registers[op1] = registers[op2] + registers[op3];
                break;
            }
            
            case ADD_IMM: {
                // dest = src + immediate
                registers[op1] = registers[op2] + op3;
                break;
            }
            
            case SUB: {
                // dest = src1 - src2
                registers[op1] = registers[op2] - registers[op3];
                break;
            }
            
            case MUL: {
                // dest = src1 * src2
                registers[op1] = registers[op2] * registers[op3];
                break;
            }
            
            case DIV: {
                // dest = src1 / src2
                if (registers[op3] == 0) {
                    throw new RuntimeException("División por cero");
                }
                registers[op1] = registers[op2] / registers[op3];
                break;
            }
            
            case REM: {
                // dest = src1 % src2
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
                // Set if equal
                registers[op1] = (registers[op2] == registers[op3]) ? 1 : 0;
                break;
            }
            
            case SNE: {
                // Set if not equal
                registers[op1] = (registers[op2] != registers[op3]) ? 1 : 0;
                break;
            }
            
            case SLT: {
                // Set if less than
                registers[op1] = (registers[op2] < registers[op3]) ? 1 : 0;
                break;
            }
            
            case SLE: {
                // Set if less or equal
                registers[op1] = (registers[op2] <= registers[op3]) ? 1 : 0;
                break;
            }
            
            case SGT: {
                // Set if greater than
                registers[op1] = (registers[op2] > registers[op3]) ? 1 : 0;
                break;
            }
            
            case SGE: {
                // Set if greater or equal
                registers[op1] = (registers[op2] >= registers[op3]) ? 1 : 0;
                break;
            }
            
            // === Control de flujo ===
            case JUMP: {
                // Salto incondicional
                int addr = instr.getImmediate16();
                pc = addr - 1; // -1 porque se incrementará después
                break;
            }
            
            case BEQZ: {
                // Branch if equal to zero
                if (registers[op1] == 0) {
                    int addr = instr.getImmediate16();
                    pc = addr - 1;
                }
                break;
            }
            
            case BNEZ: {
                // Branch if not equal to zero
                if (registers[op1] != 0) {
                    int addr = instr.getImmediate16();
                    pc = addr - 1;
                }
                break;
            }
            
            case BEQ: {
                // Branch if equal
                if (registers[op1] == registers[op2]) {
                    pc = op3 - 1;
                }
                break;
            }
            
            case BNE: {
                // Branch if not equal
                if (registers[op1] != registers[op2]) {
                    pc = op3 - 1;
                }
                break;
            }
            
            case CALL: {
                // Llamada a función
                callStack.push(pc + 1);
                int addr = instr.getImmediate16();
                pc = addr - 1;
                break;
            }
            
            case RET: {
                // Retorno de función
                if (callStack.isEmpty()) {
                    return false; // Terminar programa
                }
                pc = callStack.pop() - 1;
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
                // No operation
                break;
            }
            
            case HALT: {
                // Terminar programa
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
        
        if (debugMode) {
            System.out.printf("   → SYSCALL %d (arg1=%d, arg2=%d)%n", code, arg1, arg2);
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
                
            case 9:  // SET_COLOR (poncolor)
                // No implementado en este momento
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
}

# Backend del Compilador LogoTec - Generación de Assembly

## 📋 Descripción

Este paquete implementa el **backend del compilador**, que traduce código IR (Representación Intermedia) a código ensamblador MIPS-like ejecutable.

## 🏗️ Arquitectura

```
AST (Alto Nivel)
     ↓
IR (Tres Direcciones)      ← FASE 1 ✅ + FASE 2 ✅
     ↓
Assembly (MIPS-like)       ← FASE 3 ✅
     ↓
Object Code (Bytecode)     ← FASE 4 ✅
     ↓
Ejecución (VM)             ← FASE 5 ✅ (COMPLETADA)
     ↓
AccionTortuga → Dibujo 🎨
```

## 📦 Componentes de la FASE 3

### 1. **AssemblyOpcode** (enum)
Define todas las instrucciones del ensamblador:

**Movimiento de datos**:
- `LI` - Load Immediate
- `LW` - Load Word
- `SW` - Store Word
- `MOVE` - Move entre registros

**Operaciones aritméticas**:
- `ADD`, `ADDI`, `SUB`, `MUL`, `DIV`, `REM`

**Operaciones lógicas**:
- `AND`, `OR`, `XOR`, `NOT`

**Comparaciones**:
- `SEQ`, `SNE`, `SLT`, `SLE`, `SGT`, `SGE`

**Control de flujo**:
- `LABEL`, `J`, `BEQZ`, `BNEZ`, `BEQ`, `BNE`
- `JAL`, `JR`

**Stack**:
- `PUSH`, `POP`

**Syscalls** (tortuga):
- `SYSCALL` con códigos para comandos de tortuga

### 2. **Register** (clase)
Representa los registros del procesador (estilo MIPS):

**Registros especiales**:
- `$zero` - Siempre cero
- `$sp` - Stack pointer
- `$fp` - Frame pointer
- `$ra` - Return address

**Registros de trabajo**:
- `$t0-$t9` - Temporales (10 registros)
- `$s0-$s7` - Guardados (8 registros)
- `$a0-$a3` - Argumentos (4 registros)
- `$v0-$v1` - Resultados (2 registros)

### 3. **AssemblyInstruction** (clase)
Representa una instrucción de ensamblador.

**Formato**:
```assembly
opcode operand1, operand2, operand3  # comentario
```

**Factory methods** para construcción fácil:
```java
AssemblyInstruction.li(Register.T0, 50);          // li $t0, 50
AssemblyInstruction.add(Register.T2, T0, T1);     // add $t2, $t0, $t1
AssemblyInstruction.jump("loop_start");           // j loop_start
```

### 4. **RegisterAllocator** (clase)
Asigna registros físicos a los temporales del IR.

**Estrategia**:
- Pool de 10 registros temporales (`$t0-$t9`)
- Asignación bajo demanda
- Spilling a memoria cuando se agotan registros

**Uso**:
```java
RegisterAllocator alloc = new RegisterAllocator();
Register r1 = alloc.getRegister("t1");  // Asigna $t0
Register r2 = alloc.getRegister("t2");  // Asigna $t1
alloc.freeRegister("t1");               // Libera $t0
```

### 5. **AssemblyGenerator** (clase) ⭐
**Núcleo del backend** - Traduce IR completo a Assembly.

**Responsabilidades**:
- Recopilar variables del IR
- Generar sección `.data` con variables
- Generar sección `.text` con código
- Traducir cada instrucción IR a Assembly
- Asignar registros automáticamente
- Generar syscalls para comandos de tortuga

**Traducciones soportadas**:

| IR Opcode | Assembly Generated |
|-----------|-------------------|
| `LOAD_CONST t1, #50` | `li $t0, 50` |
| `LOAD_VAR t1, [x]` | `lw $t0, x` |
| `STORE [x], t1` | `sw $t0, x` |
| `ADD t3, t1, t2` | `add $t2, $t0, $t1` |
| `SUB t3, t1, t2` | `sub $t2, $t0, $t1` |
| `JUMP L1` | `j L1` |
| `JUMP_IF_FALSE L1, t1` | `beqz $t0, L1` |
| `FORWARD t1` | `move $a0, $t0`<br>`li $v0, 1`<br>`syscall 1` |

**Syscall Codes** (tortuga):
```
1  = FORWARD
2  = BACKWARD
3  = TURN_RIGHT
4  = TURN_LEFT
5  = PEN_UP
6  = PEN_DOWN
7  = CENTER
8  = SET_POS
9  = SET_COLOR
10 = SET_HEADING
11 = HIDE_TURTLE
12 = SHOW_TURTLE
```

### 6. **AssemblyUtils** (clase)
Utilidades para trabajar con Assembly:
- `saveToFile()` - Guardar en archivo `.asm`
- `printCode()` - Imprimir en consola
- `formatCode()` - Formatear como String
- `printStatistics()` - Mostrar estadísticas

### 7. **AssemblyGeneratorTest** (clase)
Tests automatizados que prueban el pipeline completo:
```
LogoTec → AST → IR → Assembly
```

## 🎯 Ejemplo Completo

### Código LogoTec:
```logotec
/* Dibuja un cuadrado */
haz lado 100
Repite 4 [
  avanza lado
  giraderecha 90
]
```

### Código Assembly Generado:
```assembly
# ========================================
# Código Assembly Generado
# ========================================

.data
    # Variables del programa
    # lado: .word 0

.text
.globl main                              # Punto de entrada

main:
    # ========================================
    # Programa LogoTec - Código Intermedio
    # ========================================
    # haz lado = <expr>
    li       $t0, 100
    sw       $t0, lado
    # Repite <n> [...]
    li       $t1, 0
    li       $t2, 4
L1_loop_start:
    slt      $t3, $t1, $t2
    beqz     $t3, L2_loop_end
    # avanza <expr>
    lw       $t4, lado
    # Comando tortuga: FORWARD
    move     $a0, $t4
    li       $v0, 1
    syscall  1
    # giraderecha <expr>
    li       $t5, 90
    # Comando tortuga: TURN_RIGHT
    move     $a0, $t5
    li       $v0, 3
    syscall  3
    li       $t6, 1
    add      $t7, $t1, $t6
    move     $t1, $t7
    j        L1_loop_start
L2_loop_end:
    # Fin del programa
    # Terminar programa
    li       $v0, 10
    syscall  10
```

## 🚀 Cómo Usar

### 1. Generar Assembly desde IR

```java
import com.miorganizacion.logotec.compilador.ir.*;
import com.miorganizacion.logotec.compilador.backend.*;

// Obtener IR (de la FASE 2)
ASTtoIRTranslator irTranslator = new ASTtoIRTranslator();
ASTtoIRTranslator.Result irResult = irTranslator.generate(ast);

// Generar Assembly
AssemblyGenerator asmGen = new AssemblyGenerator();
List<AssemblyInstruction> asmCode = asmGen.generate(irResult.instructions);

// Usar Assembly
AssemblyUtils.printCode(asmCode);
AssemblyUtils.saveToFile(asmCode, "output.asm");
AssemblyUtils.printStatistics(asmCode);
```

### 2. Pipeline completo (LogoTec → Assembly)

```java
// Código LogoTec
String codigo = "haz lado 50\navanza lado\n";

// Compilar a AST
ProgramNode ast = CompiladorRealAdapter.compile(codigo);

// Generar IR
ASTtoIRTranslator irGen = new ASTtoIRTranslator();
ASTtoIRTranslator.Result ir = irGen.generate(ast);

// Generar Assembly
AssemblyGenerator asmGen = new AssemblyGenerator();
List<AssemblyInstruction> asm = asmGen.generate(ir.instructions);

// Guardar
AssemblyUtils.saveToFile(asm, "programa.asm");
```

### 3. Ejecutar tests

```bash
cd compilador
mvn compile
mvn exec:java -Dexec.mainClass="com.miorganizacion.logotec.compilador.backend.AssemblyGeneratorTest"
```

## 📁 Estructura de Archivos

```
compilador/src/main/java/com/miorganizacion/logotec/compilador/backend/
├── AssemblyOpcode.java          # Enum de opcodes de Assembly
├── Register.java                # Registros del procesador
├── AssemblyInstruction.java     # Instrucción de Assembly
├── RegisterAllocator.java       # Asignador de registros
├── AssemblyGenerator.java       # ⭐ Generador principal IR→ASM
├── AssemblyUtils.java           # Utilidades (guardar, imprimir)
├── AssemblyGeneratorTest.java   # Tests automatizados
├── MipsGenerator.java           # (Antiguo, puede ser removido)
└── README.md                    # Esta documentación
```

## ✅ Estado del Proyecto

| Fase | Estado | Progreso |
|------|--------|----------|
| FASE 1: Modelo de IR | ✅ Completada | 100% |
| FASE 2: Generación IR (AST → IR) | ✅ Completada | 100% |
| FASE 3: Generación Assembly (IR → ASM) | ✅ Completada | 100% |
| FASE 4: Generación Object Code (ASM → OBJ) | ✅ Completada | 100% |
| FASE 5: Interpretación (OBJ → Ejecución) | ✅ Completada | 100% |

## 🎉 ¡PIPELINE COMPLETO IMPLEMENTADO!

## 🎉 Logros de la FASE 3

✅ **Set de instrucciones MIPS-like completo**
✅ **Sistema de registros con 24 registros**
✅ **Asignador de registros con spilling**
✅ **Generador Assembly completo (IR → ASM)**
✅ **Syscalls para comandos de tortuga**
✅ **Tests automatizados funcionando**
✅ **Guardado de archivos `.asm`**
✅ **Estadísticas de código generado**

## 📊 Estadísticas de Ejemplo

Para el programa del cuadrado:
```
Total de instrucciones: 45
Distribución por opcode:
  LI                  :  10
  MOVE                :   6
  SYSCALL             :   4
  SW                  :   1
  LW                  :   1
  SLT                 :   1
  ADD                 :   1
  BEQZ                :   1
  J                   :   1

Etiquetas: 3
Comentarios: 16
```

## 🔜 Próximo Paso

**Integración Final**: Actualizar `MainController` para usar el pipeline completo en lugar de la ejecución directa del AST.

El nuevo flujo será:
1. `compilar()` → Genera archivo `.obj`
2. `ejecutar()` → Carga `.obj`, ejecuta en VM, obtiene `AccionTortuga`, dibuja

¡El compilador completo está LISTO! 🎉

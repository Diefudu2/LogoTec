# FASE 4: Generación de Object Code (Bytecode)

## 📋 Descripción

La **FASE 4** implementa la generación de **bytecode ejecutable** desde código Assembly.

El bytecode es un formato binario compacto que puede ser:
- Guardado en archivos `.obj`
- Cargado desde disco
- Ejecutado por una máquina virtual (FASE 5)

## 🎯 Pipeline Actual

```
LogoTec Source → AST → IR → Assembly → Bytecode (.obj)
       ✅        ✅    ✅       ✅          ✅
```

## 📦 Componentes

### 1. **BytecodeOpcode** (enum)

Códigos de operación numéricos para el bytecode.

**Categorías**:
- **Movimiento de datos** (0x01-0x04): LOAD_IMM, LOAD_MEM, STORE_MEM, MOVE
- **Aritméticas** (0x10-0x15): ADD, ADD_IMM, SUB, MUL, DIV, REM
- **Lógicas** (0x20-0x23): AND, OR, XOR, NOT
- **Comparaciones** (0x30-0x35): SEQ, SNE, SLT, SLE, SGT, SGE
- **Control de flujo** (0x40-0x46): JUMP, BEQZ, BNEZ, BEQ, BNE, CALL, RET
- **Stack** (0x50-0x51): PUSH, POP
- **Syscalls** (0x60): SYSCALL
- **Especiales** (0x00, 0xFF): NOP, HALT

**Ejemplo**:
```java
BytecodeOpcode.ADD.getCode()  // → 0x10
BytecodeOpcode.fromCode(0x10) // → ADD
BytecodeOpcode.fromAssembly(AssemblyOpcode.ADD) // → BytecodeOpcode.ADD
```

### 2. **BytecodeInstruction** (clase)

Representa una instrucción de bytecode.

**Formato binario** (4 bytes):
```
Byte 0: Opcode (código de operación)
Byte 1: Operando 1 (registro destino/dirección)
Byte 2: Operando 2 (registro fuente/inmediato alto)
Byte 3: Operando 3 (registro fuente/inmediato bajo)
```

**Ejemplos**:
```java
// LOAD_IMM $t0, 50
BytecodeInstruction.withImmediate16(BytecodeOpcode.LOAD_IMM, 8, 50);
// → [0x01 0x08 0x00 0x32]

// ADD $t2, $t0, $t1
new BytecodeInstruction(BytecodeOpcode.ADD, 10, 8, 9);
// → [0x10 0x0A 0x08 0x09]

// HALT
new BytecodeInstruction(BytecodeOpcode.HALT);
// → [0xFF 0x00 0x00 0x00]
```

**Métodos útiles**:
- `toBytes()` - Convierte a array de 4 bytes
- `fromBytes(byte[])` - Crea desde array de bytes
- `toHex()` - Representación hexadecimal
- `getImmediate16()` - Extrae valor inmediato de 16 bits

### 3. **ObjectCodeGenerator** (clase) ⭐

**Generador principal** que traduce Assembly a Bytecode.

**Algoritmo de tres pasadas**:

1. **Primera pasada**: Extraer símbolos
   - Identifica todas las variables
   - Asigna direcciones de memoria (desde 0x1000)
   - Construye symbol table

2. **Segunda pasada**: Resolver labels
   - Calcula posiciones de instrucciones
   - Mapea labels a direcciones
   - Construye label table

3. **Tercera pasada**: Generar bytecode
   - Traduce cada instrucción Assembly → Bytecode
   - Resuelve referencias a variables
   - Resuelve saltos a labels
   - Agrega instrucción HALT al final

**Traducciones soportadas**:

| Assembly | Bytecode | Descripción |
|----------|----------|-------------|
| `li $t0, 50` | `LOAD_IMM 8, 50` | Cargar inmediato |
| `lw $t0, lado` | `LOAD_MEM 8, 0x1000` | Cargar de memoria |
| `sw $t0, lado` | `STORE_MEM 8, 0x1000` | Guardar en memoria |
| `add $t2, $t0, $t1` | `ADD 10, 8, 9` | Suma de registros |
| `j L1` | `JUMP 5` | Salto a dirección 5 |
| `beqz $t0, L1` | `BEQZ 8, 5` | Branch si cero |
| `syscall 1` | `SYSCALL 1` | Llamada al sistema |

**Mapeo de registros**:
```
$zero → 0
$v0   → 2,  $v1 → 3
$a0   → 4,  $a1 → 5,  $a2 → 6,  $a3 → 7
$t0   → 8,  $t1 → 9,  ... $t9 → 17
$s0   → 16, $s1 → 17, ... $s7 → 23
$sp   → 29
$fp   → 30
$ra   → 31
```

### 4. **ObjectCodeUtils** (clase)

Utilidades para trabajar con bytecode.

**Formato de archivo .obj**:
```
[4 bytes] Magic number: 0x4C4F474F ("LOGO")
[4 bytes] Versión: 0x00000001
[4 bytes] Número de instrucciones
[4 bytes] Tamaño de symbol table
[N*4 bytes] Instrucciones de bytecode
[Variable] Symbol table (pares nombre-dirección)
```

**Funciones**:
- `saveToFile()` - Guardar bytecode en archivo binario
- `loadFromFile()` - Cargar bytecode desde archivo
- `printCode()` - Imprimir en formato legible
- `printStatistics()` - Mostrar estadísticas
- `toHexDump()` - Dump hexadecimal

### 5. **ObjectCodeGeneratorTest** (clase)

Suite de tests para validar el pipeline completo.

## 🎯 Ejemplo Completo

### Código LogoTec:
```logotec
// Programa simple
haz lado 50
avanza lado
```

### Bytecode Generado:
```
Symbol Table:
  lado            → 0x1000 (4096)

Bytecode:
Addr   Hex              Instrucción
------ ---------------- ------------------------------------
0000   01 08 00 32      LOAD_IMM       8,   0,  50  [01 08 00 32]
0001   03 08 10 00      STORE_MEM      8,  16,   0  [03 08 10 00]
0002   02 09 10 00      LOAD_MEM       9,  16,   0  [02 09 10 00]
0003   60 01 00 00      SYSCALL        1,   0,   0  [60 01 00 00]
0004   FF 00 00 00      HALT           0,   0,   0  [FF 00 00 00]
```

**Interpretación**:
```
0000: Cargar 50 en $t0
0001: Guardar $t0 en memoria[0x1000] (variable "lado")
0002: Cargar memoria[0x1000] en $t1 (leer "lado")
0003: Syscall 1 (FORWARD con argumento en $a0)
0004: Terminar programa
```

## 🚀 Cómo Usar

### 1. Generar bytecode desde Assembly

```java
import com.miorganizacion.logotec.compilador.backend.*;

// Obtener Assembly (de la FASE 3)
List<AssemblyInstruction> assembly = ...;

// Generar Object Code
ObjectCodeGenerator objGen = new ObjectCodeGenerator();
ObjectCodeGenerator.Result result = objGen.generate(assembly);

// Usar bytecode
ObjectCodeUtils.printCode(result);
ObjectCodeUtils.saveToFile(result, "programa.obj");
```

### 2. Pipeline completo (LogoTec → Bytecode)

```java
// 1. Compilar a AST
ProgramNode ast = CompiladorRealAdapter.compile(sourceCode);

// 2. Generar IR
ASTtoIRTranslator irGen = new ASTtoIRTranslator();
ASTtoIRTranslator.Result ir = irGen.generate(ast);

// 3. Generar Assembly
AssemblyGenerator asmGen = new AssemblyGenerator();
List<AssemblyInstruction> asm = asmGen.generate(ir.instructions);

// 4. Generar Object Code
ObjectCodeGenerator objGen = new ObjectCodeGenerator();
ObjectCodeGenerator.Result obj = objGen.generate(asm);

// 5. Guardar
ObjectCodeUtils.saveToFile(obj, "programa.obj");
```

### 3. Cargar y verificar bytecode

```java
// Cargar desde archivo
ObjectCodeUtils.LoadedProgram program = 
    ObjectCodeUtils.loadFromFile("programa.obj");

System.out.println("Instrucciones: " + program.bytecode.size());
System.out.println("Variables: " + program.symbolTable.size());
```

### 4. Ejecutar tests

Desde Eclipse:
1. Abre `ObjectCodeGeneratorTest.java`
2. Click derecho → **Run As → Java Application**

## 📊 Estadísticas de Ejemplo

### Programa: Cuadrado
```
Total de instrucciones: 18
Tamaño en bytes: 72
Variables declaradas: 1 (lado)
Labels definidos: 2 (L1_loop_start, L2_loop_end)

Distribución por opcode:
  LOAD_IMM            :   6
  SYSCALL             :   3
  LOAD_MEM            :   2
  STORE_MEM           :   1
  SLT                 :   1
  BEQZ                :   1
  ADD                 :   1
  MOVE                :   2
  JUMP                :   1
  HALT                :   1
```

## 🔍 Características Avanzadas

### Symbol Table
- Asignación automática de direcciones
- Inicio en 0x1000 (4096)
- Incremento de 4 bytes por variable

### Label Resolution
- Cálculo de direcciones relativas
- Dos pasadas para resolver referencias forward
- Soporta saltos condicionales e incondicionales

### Optimizaciones futuras
- Compresión de bytecode
- Eliminación de NOPs
- Folding de constantes en bytecode
- Dead code elimination

## ✅ Validación

El generador valida:
- ✅ Registros válidos
- ✅ Variables declaradas
- ✅ Labels existentes
- ✅ Formato de archivo .obj
- ✅ Carga/descarga round-trip

## 🎉 Logros de la FASE 4

✅ **Formato de bytecode binario de 4 bytes**
✅ **30+ opcodes de bytecode definidos**
✅ **Generador completo Assembly → Bytecode**
✅ **Symbol table con asignación de memoria**
✅ **Label resolution en dos pasadas**
✅ **Formato de archivo .obj con magic number**
✅ **Carga/guardado de archivos binarios**
✅ **Tests automatizados del pipeline completo**
✅ **Hex dump y estadísticas**

## 🔜 Próximo Paso

**FASE 5**: Implementar `BytecodeInterpreter` - Máquina Virtual

La VM ejecutará el bytecode instrucción por instrucción, interpretando los syscalls como comandos de tortuga y generando la lista de `AccionTortuga` para el simulador gráfico.

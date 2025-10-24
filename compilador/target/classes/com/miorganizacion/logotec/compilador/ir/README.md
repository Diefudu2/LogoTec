# Sistema de Representación Intermedia (IR)

## 📋 Descripción

Este paquete implementa la **Representación Intermedia de Tres Direcciones** para el compilador LogoTec. El IR es una representación de bajo nivel del programa que facilita la optimización y traducción a código ensamblador.

## 🏗️ Arquitectura

```
AST (Alto Nivel)
     ↓
IR (Tres Direcciones) ← FASE 1 ✅ + FASE 2 ✅ (COMPLETADAS)
     ↓
Assembly              ← FASE 3 (Pendiente)
     ↓
Object Code           ← FASE 4 (Pendiente)
     ↓
Ejecución             ← FASE 5 (Pendiente)
```

## 📦 Componentes

### FASE 1: Modelo de IR ✅

#### 1. **IROpcode** (enum)
Define todos los códigos de operación soportados:
- **Movimiento**: `LOAD_CONST`, `LOAD_VAR`, `STORE`, `MOVE`
- **Aritméticas**: `ADD`, `SUB`, `MUL`, `DIV`, `MOD`, `POW`, `NEG`
- **Comparación**: `EQ`, `NEQ`, `LT`, `GT`, `LTE`, `GTE`
- **Lógicas**: `AND`, `OR`, `NOT`
- **Control**: `LABEL`, `JUMP`, `JUMP_IF_FALSE`, `JUMP_IF_TRUE`
- **Tortuga**: `FORWARD`, `BACKWARD`, `TURN_RIGHT`, `TURN_LEFT`, `PEN_UP`, `PEN_DOWN`, etc.
- **I/O**: `PRINT`

#### 2. **Operand** (clase)
Representa un operando en una instrucción. Tipos:
- **TEMPORARY**: Variables temporales (`t1`, `t2`...)
- **VARIABLE**: Variables del usuario (`lado`, `color`...)
- **CONSTANT**: Valores literales (`#50`, `#3.14`)
- **LABEL**: Etiquetas de salto (`L1`, `L2`...)

#### 3. **ThreeAddressInstruction** (clase)
Instrucción de tres direcciones con formato:
```
opcode dest, op1, op2
```

Ejemplos:
```
ADD         t3, t1, t2              ; t3 = t1 + t2
LOAD_CONST  t1, #50                 ; t1 = 50
STORE       [lado], t1              ; lado = t1
FORWARD     t2                       ; avanza(t2)
JUMP        L1                       ; goto L1
```

#### 4. **TempGenerator** (clase)
Genera nombres únicos para variables temporales:
```java
TempGenerator gen = new TempGenerator();
gen.next();  // "t1"
gen.next();  // "t2"
gen.next();  // "t3"
```

#### 5. **LabelGenerator** (clase)
Genera nombres únicos para etiquetas:
```java
LabelGenerator gen = new LabelGenerator();
gen.next();              // "L1"
gen.next("loop_start");  // "L2_loop_start"
```

#### 6. **IRBuilder** (clase)
Builder pattern para construir código IR de forma fluida:
```java
IRBuilder builder = new IRBuilder();
Operand t1 = builder.getTempGen().nextOperand();
Operand t2 = builder.getTempGen().nextOperand();

builder.loadConst(t1, 50)
       .store("lado", t1)
       .loadVar(t2, "lado")
       .forward(t2);

List<ThreeAddressInstruction> code = builder.getInstructions();
```

#### 7. **IRUtils** (clase)
Utilidades para trabajar con IR:
- `saveToFile()`: Guardar IR en archivo `.ir`
- `formatCode()`: Formatear IR como String
- `printCode()`: Imprimir IR en consola
- `validate()`: Validar sintaxis del IR
- `printStatistics()`: Mostrar estadísticas

### FASE 2: Generación de IR desde AST ✅

#### 8. **ASTtoIRTranslator** (clase) - NUEVO
Traduce el AST de LogoTec a código IR de tres direcciones.

**Características**:
- Usa reflexión para acceder a campos privados del AST
- Genera código IR para todas las construcciones de LogoTec:
  - Variables (`haz`, asignaciones)
  - Expresiones aritméticas (`+`, `-`, `*`, `/`, `^`)
  - Expresiones lógicas (`y`, `o`, `no`)
  - Comparaciones (`=`, `<>`, `<`, `>`, `<=`, `>=`)
  - Estructuras de control (`Si`, `Repite`, `hazMientras`, `hazHasta`)
  - Comandos de tortuga (`avanza`, `retrocede`, `giraderecha`, etc.)
- Mantiene tabla de variables declaradas
- Genera comentarios para facilitar debugging

**Uso**:
```java
// Compilar código LogoTec a AST
ProgramNode ast = CompiladorRealAdapter.compile(sourceCode);

// Generar IR
ASTtoIRTranslator translator = new ASTtoIRTranslator();
ASTtoIRTranslator.Result result = translator.generate(ast);

// Acceder a instrucciones y variables
List<ThreeAddressInstruction> instructions = result.instructions;
Set<String> vars = result.declaredVars;
```

#### 9. **IRGeneratorTest** (clase) - NUEVO
Programa de prueba que compila programas LogoTec y genera IR.

**Tests incluidos**:
1. Variable simple y avance
2. Cuadrado con Repite
3. Expresión aritmética
4. Condicional Si
5. HazMientras

**Ejecución**:
```bash
mvn exec:java -Dexec.mainClass="com.miorganizacion.logotec.compilador.ir.IRGeneratorTest"
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

### Código IR Generado:
```
   1: COMMENT         ; ========================================
   2: COMMENT         ; Programa LogoTec - Código Intermedio
   3: COMMENT         ; ========================================
   4: LABEL           main
   5: COMMENT         ; haz lado = <expr>
   6: LOAD_CONST      t1, #100.0
   7: STORE           [lado], t1
   8: COMMENT         ; Repite <n> [...]
   9: LOAD_CONST      loop_counter, #0.0
  10: LOAD_CONST      t2, #4.0
  11: LABEL           L1_loop_start
  12: LT              t3, loop_counter, t2
  13: JUMP_IF_FALSE   L2_loop_end, t3
  14: COMMENT         ; avanza <expr>
  15: LOAD_VAR        t4, [lado]
  16: FORWARD         t4
  17: COMMENT         ; giraderecha <expr>
  18: LOAD_CONST      t5, #90.0
  19: TURN_RIGHT      t5
  20: LOAD_CONST      t6, #1.0
  21: ADD             t7, loop_counter, t6
  22: MOVE            loop_counter, t7
  23: JUMP            L1_loop_start
  24: LABEL           L2_loop_end
  25: COMMENT         ; Fin del programa
```

## 🚀 Cómo Usar

### 1. Generar IR desde código LogoTec

```java
import com.miorganizacion.logotec.compilador.CompiladorRealAdapter;
import com.miorganizacion.logotec.compilador.ast.ProgramNode;
import com.miorganizacion.logotec.compilador.ir.*;

// Código fuente
String codigo = "haz lado 50\navanza lado\n";

// Compilar a AST
ProgramNode ast = CompiladorRealAdapter.compile(codigo);

// Generar IR
ASTtoIRTranslator translator = new ASTtoIRTranslator();
ASTtoIRTranslator.Result result = translator.generate(ast);

// Usar IR
List<ThreeAddressInstruction> ir = result.instructions;
IRUtils.printCode(ir);
IRUtils.saveToFile(ir, "output.ir");
```

### 2. Validar IR

```java
boolean valid = IRUtils.validate(result.instructions);
if (valid) {
    System.out.println("✅ IR válido");
}
```

### 3. Ver estadísticas

```java
IRUtils.printStatistics(result.instructions);
```

## 📁 Estructura de Archivos

```
compilador/src/main/java/com/miorganizacion/logotec/compilador/ir/
├── IROpcode.java                 # Enum de opcodes
├── Operand.java                  # Clase Operand
├── ThreeAddressInstruction.java  # Clase de instrucción
├── TempGenerator.java            # Generador de temporales
├── LabelGenerator.java           # Generador de etiquetas
├── IRBuilder.java                # Builder para construir IR
├── IRUtils.java                  # Utilidades
├── IRExample.java                # Ejemplos manuales de IR
├── ASTtoIRTranslator.java        # ✨ NUEVO: Traductor AST → IR
├── IRGeneratorTest.java          # ✨ NUEVO: Tests de generación
├── IRGenerator.java              # (Antiguo, compatible con TAC)
├── TAC.java                      # (Antiguo, sistema previo)
└── README.md                     # Esta documentación
```

## ✅ Estado del Proyecto

### FASE 1: Modelo de IR ✅ COMPLETADA
- [x] Definir modelo de IR (Three Address Code)
- [x] Crear clase `ThreeAddressInstruction`
- [x] Crear enum `IROpcode`
- [x] Crear clase `Operand`
- [x] Crear `TempGenerator`
- [x] Crear `LabelGenerator`
- [x] Crear `IRBuilder` (builder pattern)
- [x] Crear `IRUtils` (guardar/validar)
- [x] Crear ejemplos de uso
- [x] Documentación completa

### FASE 2: Generación de IR desde AST ✅ COMPLETADA
- [x] Crear `ASTtoIRTranslator`
- [x] Traducir declaraciones de variables
- [x] Traducir expresiones aritméticas
- [x] Traducir expresiones lógicas y comparaciones
- [x] Traducir estructuras de control (Si, Repite, hazMientras, hazHasta)
- [x] Traducir comandos de tortuga
- [x] Crear tests de generación
- [x] Validación de IR generado
- [x] Guardar IR en archivos `.ir`

### FASE 3: Generación de Assembly ⏳ PENDIENTE
- [ ] Diseñar set de instrucciones ensamblador
- [ ] Crear `AssemblyGenerator`
- [ ] Implementar asignación de registros
- [ ] Traducir IR a Assembly
- [ ] Guardar en archivos `.asm`

### FASE 4: Generación de Código Objeto ⏳ PENDIENTE
- [ ] Diseñar formato bytecode
- [ ] Crear `ObjectCodeGenerator`
- [ ] Traducir Assembly a Bytecode
- [ ] Guardar en archivos `.obj`

### FASE 5: Interpretación y Ejecución ⏳ PENDIENTE
- [ ] Crear `BytecodeInterpreter`
- [ ] Implementar máquina virtual
- [ ] Ejecutar bytecode
- [ ] Generar `AccionTortuga` para dibujo

## 🎉 Logros Recientes

✅ **Sistema IR completo y funcional**
✅ **Generador AST → IR implementado**
✅ **Tests de generación funcionando**
✅ **Validación de IR**
✅ **Guardado de archivos `.ir`**

## 🔜 Próximo Paso

**FASE 3**: Implementar `AssemblyGenerator` para traducir IR a código ensamblador.


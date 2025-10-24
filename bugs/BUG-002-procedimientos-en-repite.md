# BUG-002: Procedimientos dentro de Bucles `Repite` Rompen la Iteración

**ID:** BUG-002  
**Fecha descubierta:** 28 de octubre de 2025  
**Severidad:** 🔴 CRÍTICA  
**Estado:** ❌ Pendiente de corrección  
**Impacto:** Comportamiento incorrecto - Solo ejecuta 1 iteración del bucle

---

## 📋 Descripción

Cuando se llama a un procedimiento (`Para...fin`) dentro de un bucle `Repite`, **solo se ejecuta la primera iteración** del bucle, ignorando completamente las iteraciones restantes.

### Síntoma Principal

```logo
Repite 10 [
  cuadrado lado      // ← Llamada a procedimiento
  giraderecha 36
]
```

**Resultado esperado:** 10 cuadrados  
**Resultado observado:** **1 cuadrado solamente** (el bucle se rompe después de la primera iteración)

---

## 🔬 Reproducción

### Paso 1: Crear procedimiento de prueba

**Archivo:** `compilador/test/cuadrados_rotados_procedimiento.logo`

```logo
// Patrón de cuadrados rotados - VERSIÓN CON PROCEDIMIENTOS
haz dummy 0
haz lado 80
haz cantidad 10
haz rotacion 36

Para cuadrado [tamano] [
  Repite 4 [
    avanza tamano
    giraderecha 90
  ]
] fin

centro
bajalapiz

// Este bucle debería ejecutarse 10 veces
Repite 10 [
  cuadrado lado
  giraderecha rotacion
]

subelapiz
```

### Paso 2: Compilar y ejecutar

1. Abrir el archivo en la interfaz LogoTec
2. Presionar "Compilar" → ✅ Compila sin errores
3. Presionar "Ejecutar" → ⚠️ **Ejecuta solo 1 iteración**

### Paso 3: Observar resultado incorrecto

**Salida de la consola:**
```
═══════════════════════════════════════════════════════
✅ EJECUCIÓN EXITOSA
   Acciones generadas: 11
═══════════════════════════════════════════════════════

🔍 DEBUG - Primeras 5 acciones:
   0: AVANZAR - 0.0
   1: AVANZAR - 80.0
   2: GIRAR - 90.0
   3: AVANZAR - 80.0
   4: GIRAR - 90.0
🔍 DEBUG - Últimas 5 acciones:
   6: GIRAR - 90.0
   7: AVANZAR - 80.0
   8: GIRAR - 90.0
   9: GIRAR - 36.0        ← Solo 1 cuadrado completado
   10: LEVANTAR_LAPIZ - 0.0
```

**Análisis:**
- ✅ Bytecode cargado: 58 instrucciones
- ❌ Acciones generadas: **11** (debería ser ~90)
- ❌ Solo dibuja **1 cuadrado** de 10

---

## 📊 Casos de Prueba Comparativos

| Código | Iteraciones Esperadas | Acciones Esperadas | Acciones Reales | Resultado |
|--------|----------------------|-------------------|----------------|-----------|
| `Repite 10 [ cuadrado lado; gira 36 ]` | 10 | 90 | **11** | ❌ Solo 1 iteración |
| `Repite 10 [ avanza 80; gira 90; ... ]` | 10 | 90 | **92** | ✅ Todas las iteraciones |
| `Repite cantidad [ cuadrado lado ]` | 10 | 90 | **11** | ❌ Solo 1 iteración (variable) |
| `Repite 10 [ cuadrado lado ]` | 10 | 90 | **11** | ❌ Solo 1 iteración (literal) |

**Conclusión:** El problema ocurre **siempre que se llama a un procedimiento dentro de un bucle**, independientemente de si se usa variable o literal.

---

## 🎯 Comportamiento Esperado vs. Observado

### ✅ Esperado

Con `Repite 10 [ cuadrado lado; giraderecha 36 ]`:

1. **Iteración 1:**
   - Llamar `cuadrado` → Dibujar 4 lados
   - Girar 36°
2. **Iteración 2:**
   - Llamar `cuadrado` → Dibujar 4 lados
   - Girar 36°
3. **... continuar hasta iteración 10**
4. **Total:** 10 cuadrados, 90 acciones

### ❌ Observado

Con `Repite 10 [ cuadrado lado; giraderecha 36 ]`:

1. **Iteración 1:**
   - Llamar `cuadrado` → Dibujar 4 lados ✅
   - Girar 36° ✅
2. **Iteración 2-10:**
   - **❌ NO SE EJECUTAN**
3. **Total:** 1 cuadrado solamente, 11 acciones

**El bucle se detiene prematuramente después de la primera llamada al procedimiento.**

---

## 🔍 Análisis Técnico

### Hipótesis Principal: Corrupción del Stack de Bucles en `RET`

**Evidencia:**

1. **Sin procedimientos funciona perfectamente:**
   - `cuadrados_rotados_sin_procedimiento.logo` → ✅ 92 acciones, 10 iteraciones completas

2. **Con procedimientos falla siempre:**
   - `cuadrados_rotados_procedimiento.logo` → ❌ 11 acciones, 1 iteración solamente

3. **El bytecode se genera correctamente:**
   - 58 instrucciones cargadas
   - Compilación exitosa sin errores

### Componente Sospechoso: **BytecodeInterpreter.java**

#### Problema probable en el manejo de `RET` (Return):

```java
// Pseudocódigo del problema sospechado
case RET:
    int returnAddress = callStack.pop();
    pc = returnAddress;  // ← AQUÍ: Posible corrupción
    break;
```

**Hipótesis:** Cuando `RET` ejecuta dentro de un bucle `Repite`, está restaurando el `PC` (Program Counter) a una dirección incorrecta, haciendo que el bucle piense que ya terminó.

#### Estructura del Stack esperada:

```
┌─────────────────┐
│ Loop Counter    │ ← Contador del Repite (debería ser 10)
├─────────────────┤
│ Loop End Label  │ ← Dirección de salida del bucle
├─────────────────┤
│ Return Address  │ ← Para el procedimiento
└─────────────────┘
```

**Problema sospechado:** Al hacer `RET`, el procedimiento podría estar:
1. ❌ Haciendo `pop()` del contador del bucle en lugar del return address
2. ❌ Sobrescribiendo el contador del bucle
3. ❌ Saltando incorrectamente fuera del bucle

### Componentes Relacionados

#### a) **AssemblyGenerator.java - Generación de CALL/RET**

```java
// Líneas ~XXX: translateCall
private void translateCall(IRInstruction instr) {
    String procName = instr.getOperand1();
    String procLabel = "proc_" + procName;
    
    // Guardar estado antes de llamar
    emit(AssemblyInstruction.push(Arrays.asList(Register.RA.toString())));
    emit(AssemblyInstruction.push(Arrays.asList(Register.FP.toString())));
    
    // ... configurar frame
    
    emit(AssemblyInstruction.jal(procLabel, Arrays.asList(procLabel)));
}

// Líneas ~XXX: translateReturn
private void translateReturn(IRInstruction instr) {
    // Restaurar estado
    emit(AssemblyInstruction.move(Register.SP, Register.FP));
    emit(AssemblyInstruction.pop(Arrays.asList(Register.FP.toString())));
    emit(AssemblyInstruction.pop(Arrays.asList(Register.RA.toString())));
    emit(AssemblyInstruction.jr(Register.RA, Arrays.asList(Register.RA.toString())));
}
```

**Posible problema:** Los `PUSH/POP` de `$ra` y `$fp` podrían interferir con el stack de control de bucles.

#### b) **BytecodeInterpreter.java - Manejo de CALL/RET**

```java
// Manejo de CALL
case CALL:
    int targetAddress = resolveAddress(operand1);
    callStack.push(pc + 1);  // Guardar dirección de retorno
    pc = targetAddress;
    break;

// Manejo de RET
case RET:
    if (callStack.isEmpty()) {
        throw new RuntimeException("Call stack vacío en RET");
    }
    pc = callStack.pop();  // ← AQUÍ: Posible problema
    break;
```

**Hipótesis detallada:**

Cuando un bucle `Repite` llama a un procedimiento:

```
1. Loop inicia con contador = 10
2. Loop body ejecuta:
   a. CALL cuadrado
      - callStack.push(dirección_después_de_call)
      - pc = proc_cuadrado
   b. Procedimiento ejecuta
   c. RET
      - pc = callStack.pop()  ← Restaura correctamente
   d. giraderecha 36
   e. Loop decrementa contador
   f. ¿Saltar a inicio del loop?  ← AQUÍ FALLA
```

**Posible bug:** El `RET` podría estar sobrescribiendo la dirección de salto del bucle, o el contador del bucle se pierde durante la llamada.

---

## 🛠️ Workarounds (Soluciones Temporales)

### Opción 1: Desenrollar código sin usar procedimientos ✅ **RECOMENDADO**

**Archivo:** `cuadrados_rotados_sin_procedimiento.logo`

```logo
haz dummy 0
haz lado 80

centro
bajalapiz

Repite 10 [
  avanza lado
  giraderecha 90
  avanza lado
  giraderecha 90
  avanza lado
  giraderecha 90
  avanza lado
  giraderecha 90
  giraderecha 36
]

subelapiz
```

**Resultado:** ✅ **Funciona perfectamente** (92 acciones, 10 cuadrados)

**Ventajas:**
- ✅ Solución confiable
- ✅ Rendimiento óptimo
- ✅ Sin bugs

**Desventajas:**
- ❌ Código repetitivo
- ❌ Difícil de mantener
- ❌ No reutilizable

---

### Opción 2: Usar procedimientos solo FUERA de bucles

```logo
Para dibujarPatron [n] [
  Repite n [
    // Código desenrollado aquí
    avanza 80
    giraderecha 90
    avanza 80
    giraderecha 90
    avanza 80
    giraderecha 90
    avanza 80
    giraderecha 90
    giraderecha 36
  ]
] fin

centro
bajalapiz
dibujarPatron 10  // ← Procedimiento NO dentro de Repite
subelapiz
```

**Ventajas:**
- ✅ Usa procedimientos (más organizado)
- ✅ Evita el bug

**Desventajas:**
- ❌ Menos flexible
- ❌ No funciona para composición compleja

---

## 🔧 Solución Propuesta

### Prioridad 1: Diagnosticar el problema exacto

#### Agregar logging extensivo en BytecodeInterpreter.java:

```java
case CALL:
    System.out.println("🔵 CALL: pc=" + pc + ", target=" + operand1);
    System.out.println("   CallStack antes: " + callStack);
    int targetAddress = resolveAddress(operand1);
    callStack.push(pc + 1);
    pc = targetAddress;
    System.out.println("   CallStack después: " + callStack);
    System.out.println("   Nuevo pc: " + pc);
    break;

case RET:
    System.out.println("🔴 RET: pc=" + pc);
    System.out.println("   CallStack antes: " + callStack);
    if (callStack.isEmpty()) {
        throw new RuntimeException("Call stack vacío en RET");
    }
    pc = callStack.pop();
    System.out.println("   CallStack después: " + callStack);
    System.out.println("   Nuevo pc: " + pc);
    break;

// También en manejo de bucles
case LOOP_START:  // o como se llame
    System.out.println("🟢 LOOP START: counter=" + loopCounter);
    break;

case LOOP_END:
    System.out.println("🟡 LOOP END: counter=" + loopCounter);
    break;
```

---

### Prioridad 2: Verificar implementación de bucles

#### Revisar cómo se implementan los bucles `Repite` en IR/Assembly:

1. **Verificar en ASTtoIRTranslator.java:**
   ```java
   @Override
   public void visit(RepeatNode node) {
       // ¿Cómo se genera el IR para el bucle?
       // ¿Se usa un contador en la stack?
       // ¿Cómo se maneja con CALL/RET?
   }
   ```

2. **Verificar en AssemblyGenerator.java:**
   ```java
   // ¿Cómo se traducen los bucles a Assembly?
   // ¿Se usa BEQ/BNE correctamente?
   // ¿Las etiquetas son únicas?
   ```

3. **Verificar en BytecodeInterpreter.java:**
   ```java
   // ¿Hay un stack separado para bucles?
   // ¿O se mezcla con el callStack?
   ```

---

### Prioridad 3: Separar stacks si es necesario

Si se confirma que el problema es mezclar el `callStack` con el control de bucles, implementar:

```java
// En BytecodeInterpreter.java
private Stack<Integer> callStack = new Stack<>();      // Para CALL/RET
private Stack<LoopContext> loopStack = new Stack<>();  // Para bucles

class LoopContext {
    int counter;          // Iteraciones restantes
    int startAddress;     // Dirección de inicio del bucle
    int endAddress;       // Dirección después del bucle
}
```

**Ventajas:**
- ✅ Separación clara de responsabilidades
- ✅ No hay interferencia entre procedimientos y bucles
- ✅ Más robusto

---

### Prioridad 4: Agregar tests unitarios

```java
@Test
public void testProcedureInsideLoop() {
    String code = """
        haz dummy 0
        Para cuadrado [lado] [
            avanza lado
            giraderecha 90
        ] fin
        
        Repite 5 [
            cuadrado 10
        ]
    """;
    
    List<AccionTortuga> acciones = compilarYEjecutar(code);
    
    // Debería generar 5 cuadrados × 4 lados × 2 acciones = 40 acciones
    assertEquals(40, acciones.size(), 
        "Debería ejecutar 5 iteraciones del bucle con procedimiento");
}
```

---

## 📁 Archivos Relacionados

### Archivos de prueba (en `compilador/test/`)

- ❌ `cuadrados_rotados_procedimiento.logo` - Demuestra el bug
- ✅ `cuadrados_rotados_sin_procedimiento.logo` - Workaround exitoso
- ✅ `test.logo` - Funciona porque los procedimientos NO están dentro de Repite en main

### Código fuente a revisar

**Prioridad Alta:**
- `compilador/src/main/java/com/miorganizacion/logotec/compilador/backend/BytecodeInterpreter.java`
  - Manejo de `CALL` y `RET`
  - Stack de llamadas
  - Control de bucles

**Prioridad Media:**
- `compilador/src/main/java/com/miorganizacion/logotec/compilador/ir/ASTtoIRTranslator.java`
  - Generación de IR para `RepeatNode`
  - Generación de IR para `ProcCallNode`

- `compilador/src/main/java/com/miorganizacion/logotec/compilador/backend/AssemblyGenerator.java`
  - `translateCall()` y `translateReturn()`
  - Traducción de bucles a Assembly

**Prioridad Baja:**
- `compilador/src/main/java/com/miorganizacion/logotec/compilador/backend/ObjectCodeGenerator.java`
  - Traducción de Assembly a bytecode

---

## ✅ Criterios de Aceptación para Solución

1. ✅ `Repite 10 [ cuadrado lado; gira 36 ]` debe ejecutar **10 iteraciones completas**
2. ✅ Generar **~90 acciones** (10 cuadrados × 9 acciones)
3. ✅ Dibujar **10 cuadrados** correctamente rotados
4. ✅ Funcionar con variables: `Repite cantidad [ procedimiento ]`
5. ✅ Funcionar con literales: `Repite 10 [ procedimiento ]`
6. ✅ Funcionar con procedimientos anidados: `Repite 5 [ proc1 que llama proc2 ]`
7. ✅ No romper casos que ya funcionan (test.logo, etc.)

---

## 🔗 Relación con Otros Bugs

### BUG-001: Límite de anidación en bucles `Repite`

**Diferencias:**
- BUG-001: `Repite 6 [ Repite 4 [...] ]` → **Crash**
- BUG-002: `Repite 10 [ procedimiento ]` → **Solo 1 iteración**

**Similitudes:**
- Ambos involucran bucles `Repite`
- Ambos son críticos
- Ambos descubiertos el mismo día

**¿Relacionados?**
- Posiblemente comparten causa raíz en el manejo del stack
- Resolver uno podría resolver el otro

---

## 📝 Notas Adicionales

### Observación importante del bytecode:

```
✅ Programa cargado: 58 instrucciones
```

**58 instrucciones parece correcto** para:
- Definición de procedimiento `cuadrado`
- Bucle `Repite 10` con llamada a procedimiento
- Comandos de setup (centro, bajalapiz, etc.)

Esto confirma que **el problema NO está en la compilación**, sino en **la ejecución** (BytecodeInterpreter).

### Test que funciona (test.logo):

```logo
Para roseta [cont l] [
  Repite cont [      // ← Bucle DENTRO del procedimiento
    poligono 6 l
    giraderecha 360 / cont
  ]
] fin

roseta 12 50         // ← Llamada SIN bucle externo
```

**Funciona porque:**
- El bucle `Repite` está **dentro** del procedimiento
- No hay bucle en `main` que llame al procedimiento

**Contrasta con el bug:**
```logo
Repite 10 [          // ← Bucle EN MAIN
  cuadrado lado      // ← Llamada a procedimiento
]
```

---

**Estado:** 🔴 CRÍTICO - Requiere investigación urgente  
**Próximo paso:** Agregar logging extensivo en BytecodeInterpreter para rastrear CALL/RET/LOOP  
**Prioridad:** ALTA - Este bug limita severamente el uso de procedimientos

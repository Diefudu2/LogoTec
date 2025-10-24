# 🔧 Guía de Investigación y Resolución de Bugs

Esta guía proporciona pasos concretos para investigar y resolver BUG-001 y BUG-002.

---

## 🎯 Preparación

### 1. Configurar entorno de debugging

**Habilitar logging detallado en Eclipse:**
1. Abrir Run Configurations
2. Agregar VM arguments: `-Dlogotec.debug=true`
3. Aumentar buffer de consola: Window → Preferences → Run/Debug → Console → Limit console output → 100000 characters

### 2. Crear branch de desarrollo

```bash
git checkout -b fix/bugs-001-002-bucles-procedimientos
```

---

## 🐛 BUG-002: Procedimientos en Bucles (PRIORIDAD 1)

### Fase 1: Agregar Logging Diagnóstico

#### Archivo: `BytecodeInterpreter.java`

Localizar el método `execute()` y agregar logging en:

**1. Manejo de CALL:**
```java
case CALL: // o el opcode correspondiente
    System.out.println("═══════════════════════════════════════");
    System.out.println("🔵 CALL ejecutado:");
    System.out.println("   PC actual: " + pc);
    System.out.println("   Procedimiento: " + operand1);
    System.out.println("   CallStack antes: " + callStack);
    
    int targetAddress = resolveAddress(operand1);
    callStack.push(pc + 1);
    pc = targetAddress;
    
    System.out.println("   CallStack después: " + callStack);
    System.out.println("   Nuevo PC: " + pc);
    System.out.println("═══════════════════════════════════════\n");
    break;
```

**2. Manejo de RET:**
```java
case RET: // o el opcode correspondiente
    System.out.println("═══════════════════════════════════════");
    System.out.println("🔴 RET ejecutado:");
    System.out.println("   PC actual: " + pc);
    System.out.println("   CallStack antes: " + callStack);
    
    if (callStack.isEmpty()) {
        throw new RuntimeException("❌ ERROR: Call stack vacío en RET");
    }
    
    int returnAddr = callStack.pop();
    System.out.println("   Dirección de retorno: " + returnAddr);
    System.out.println("   CallStack después: " + callStack);
    
    pc = returnAddr;
    System.out.println("   Nuevo PC: " + pc);
    System.out.println("═══════════════════════════════════════\n");
    break;
```

**3. Inicio y fin de bucles:**
```java
// Buscar el manejo de bucles (puede ser LOOP_START, BEQ, BNEZ, etc.)
// Agregar logging similar

case LOOP_START: // o nombre real del opcode
    System.out.println("🟢 LOOP START:");
    System.out.println("   PC: " + pc);
    System.out.println("   Contador: " + loopCounter); // o variable real
    System.out.println();
    break;

case LOOP_END: // o BNEZ si es el que controla el bucle
    System.out.println("🟡 LOOP END/CHECK:");
    System.out.println("   PC: " + pc);
    System.out.println("   Contador: " + loopCounter);
    System.out.println("   Saltar a inicio: " + (loopCounter > 0));
    System.out.println();
    break;
```

### Fase 2: Ejecutar Tests con Logging

**Test 1: Caso que funciona (sin procedimientos)**
```bash
# En Eclipse: compilar y ejecutar cuadrados_rotados_sin_procedimiento.logo
# Guardar output de consola como: logs/test1-sin-procedimientos.txt
```

**Test 2: Caso que falla (con procedimientos)**
```bash
# En Eclipse: compilar y ejecutar cuadrados_rotados_procedimiento.logo
# Guardar output de consola como: logs/test2-con-procedimientos.txt
```

### Fase 3: Analizar Logs

Comparar ambos logs y buscar:

1. **¿Cuántas veces se ejecuta LOOP_START?**
   - Test 1: Debería ser 10 veces
   - Test 2: Probablemente solo 1 vez

2. **¿El contador del bucle se decrementa correctamente?**
   - ¿Se mantiene el valor después de RET?

3. **¿El PC vuelve al inicio del bucle después de RET?**
   - O salta fuera del bucle incorrectamente

4. **¿Hay interferencia entre callStack y el stack de bucles?**

### Fase 4: Identificar la Causa Raíz

**Hipótesis A: RET sobrescribe el contador del bucle**

Si el log muestra que el contador se pierde después de RET:

```java
// ANTES del CALL:
Loop contador = 10

// DESPUÉS del RET:
Loop contador = 0 o 1  // ❌ PROBLEMA AQUÍ
```

**Solución:** Separar stacks

```java
// En BytecodeInterpreter.java
private Stack<Integer> callStack = new Stack<>();       // Para CALL/RET
private Stack<LoopContext> loopStack = new Stack<>();   // Para bucles

private static class LoopContext {
    int counter;           // Iteraciones restantes
    int startAddress;      // PC del inicio del bucle
    int endAddress;        // PC después del bucle
}
```

**Hipótesis B: RET salta fuera del bucle**

Si el log muestra que después de RET, el PC está fuera del bucle:

```java
// CALL desde PC = 100 (dentro del bucle que va de 95 a 120)
// RET debería volver a PC = 101
// Pero va a PC = 121 (fuera del bucle)  // ❌ PROBLEMA AQUÍ
```

**Solución:** Revisar cómo se calculan las direcciones de retorno

---

## 🐛 BUG-001: Límite de Anidación (PRIORIDAD 2)

### Fase 1: Agregar Try-Catch en MainController

#### Archivo: `MainController.java`

Localizar la creación de KeyFrames (línea ~260-300) y envolver en try-catch:

```java
try {
    for (int frameIdx = 0; frameIdx < totalFrames; frameIdx++) {
        System.out.println("🎬 Creando KeyFrame " + (frameIdx + 1) + "/" + totalFrames);
        
        final int startIdx = frameIdx * accionesPorFrame;
        final int endIdx = Math.min(startIdx + accionesPorFrame, acciones.size());

        KeyFrame frame = new KeyFrame(Duration.millis((frameIdx + 1) * delayMs), e -> {
            try {
                for (int i = startIdx; i < endIdx; i++) {
                    AccionTortuga accion = acciones.get(i);
                    // ... código existente
                }
                zonaDibujo.actualizarTortuga(tortuga.getX(), tortuga.getY(), tortuga.getAngulo());
            } catch (Exception ex) {
                System.err.println("❌ Error ejecutando acciones del frame " + frameIdx);
                ex.printStackTrace();
            }
        });

        timeline.getKeyFrames().add(frame);
    }
    
    System.out.println("✅ Todos los KeyFrames creados exitosamente");
    
} catch (OutOfMemoryError e) {
    System.err.println("❌ ERROR: Out of Memory al crear KeyFrames");
    System.err.println("   Total frames intentados: " + totalFrames);
    System.err.println("   Acciones totales: " + acciones.size());
    e.printStackTrace();
    throw new RuntimeException("Demasiados KeyFrames para JavaFX. Máximo: 150", e);
    
} catch (Exception e) {
    System.err.println("❌ ERROR: Excepción al crear KeyFrames");
    System.err.println("   Frame actual: " + frameIdx + "/" + totalFrames);
    e.printStackTrace();
    throw new RuntimeException("Error al crear animación", e);
}
```

### Fase 2: Ejecutar Tests con Try-Catch

**Test 1: Caso que funciona (5 cuadrados)**
```bash
# Modificar cuadrados_rotados.logo: haz cantidad 5
# Ejecutar y verificar que NO hay errores
```

**Test 2: Caso que crashea (6 cuadrados)**
```bash
# Modificar cuadrados_rotados.logo: haz cantidad 6
# Ejecutar y capturar el stack trace completo
```

### Fase 3: Analizar el Crash

**Si es OutOfMemoryError:**
→ El problema es memoria (demasiados closures/lambdas)  
**Solución:** Reducir límite de frames máximo a 100 o implementar modo instantáneo

**Si es otro tipo de error:**
→ Analizar el stack trace para identificar la causa

### Fase 4: Implementar Solución

**Opción A: Límite más estricto con reagrupación**

```java
// En MainController.java, antes del loop de creación de KeyFrames

final int MAX_FRAMES = 100; // Límite seguro para JavaFX

if (totalFrames > MAX_FRAMES) {
    System.out.println("⚠️  Frames exceden límite de " + MAX_FRAMES);
    System.out.println("   Reagrupando acciones...");
    
    accionesPorFrame = (int) Math.ceil((double) acciones.size() / MAX_FRAMES);
    totalFrames = (int) Math.ceil((double) acciones.size() / accionesPorFrame);
    
    System.out.println("   Nuevo agrupamiento: " + accionesPorFrame + " acciones/frame");
    System.out.println("   Total frames: " + totalFrames);
}
```

**Opción B: Modo instantáneo para patrones complejos**

```java
if (acciones.size() > 150 || totalFrames > 100) {
    System.out.println("⚡ Modo instantáneo: ejecutando sin animación");
    
    // Ejecutar todas las acciones inmediatamente
    for (AccionTortuga accion : acciones) {
        ejecutarAccionInmediata(accion);
    }
    
    zonaDibujo.actualizarTortuga(tortuga.getX(), tortuga.getY(), tortuga.getAngulo());
    System.out.println("✅ Patrón completado instantáneamente");
    return;
}
```

---

## 🧪 Tests Unitarios

Crear archivo: `BytecodeInterpreterTest.java`

```java
package com.miorganizacion.logotec.compilador.backend;

import org.junit.Test;
import static org.junit.Assert.*;

public class BytecodeInterpreterTest {
    
    @Test
    public void testSimpleLoop10Iterations() {
        String code = """
            haz dummy 0
            centro
            bajalapiz
            Repite 10 [
                avanza 10
                giraderecha 36
            ]
            subelapiz
        """;
        
        List<AccionTortuga> acciones = compilarYEjecutar(code);
        
        // 1 centro + 10 iteraciones × 2 acciones + 1 subelapiz = 22 acciones
        assertEquals("Debería ejecutar 10 iteraciones", 22, acciones.size());
    }
    
    @Test
    public void testProcedureInLoop() {
        String code = """
            haz dummy 0
            Para cuadrado [lado] [
                Repite 4 [
                    avanza lado
                    giraderecha 90
                ]
            ] fin
            
            centro
            bajalapiz
            Repite 5 [
                cuadrado 10
                giraderecha 72
            ]
            subelapiz
        """;
        
        List<AccionTortuga> acciones = compilarYEjecutar(code);
        
        // 1 centro + 5 cuadrados × (4 lados × 2 + 1 giro) + 1 subelapiz
        // = 1 + 5 × 9 + 1 = 47 acciones
        assertEquals("Debería ejecutar 5 iteraciones con procedimiento", 47, acciones.size());
    }
    
    @Test
    public void testNestedLoops() {
        String code = """
            haz dummy 0
            centro
            bajalapiz
            Repite 3 [
                Repite 2 [
                    avanza 10
                ]
                giraderecha 120
            ]
            subelapiz
        """;
        
        List<AccionTortuga> acciones = compilarYEjecutar(code);
        
        // 1 centro + 3 × (2 avanzar + 1 giro) + 1 subelapiz = 1 + 9 + 1 = 11
        assertEquals("Debería ejecutar bucles anidados", 11, acciones.size());
    }
    
    private List<AccionTortuga> compilarYEjecutar(String code) {
        // Implementar usando el pipeline completo
        // ...
    }
}
```

---

## ✅ Checklist de Validación

Antes de considerar los bugs resueltos, verificar:

### BUG-002 (Procedimientos en bucles):
- [ ] `Repite 10 [ procedimiento ]` ejecuta 10 iteraciones
- [ ] `Repite cantidad [ procedimiento ]` usa el valor correcto de `cantidad`
- [ ] Procedimientos con parámetros funcionan en bucles
- [ ] Bucles anidados con procedimientos funcionan
- [ ] test.logo sigue funcionando (regresión)

### BUG-001 (Límite de anidación):
- [ ] `Repite 10 [ Repite 4 [...] ]` no crashea
- [ ] Patrones con 100+ acciones se ejecutan correctamente
- [ ] Mensaje de error claro si hay límite infranqueable
- [ ] Modo alternativo (instantáneo) funciona si es necesario
- [ ] estrella_fractal_2niveles.logo sigue funcionando (regresión)

### General:
- [ ] Todos los tests unitarios pasan
- [ ] No hay nuevas advertencias en compilación
- [ ] Performance no degradado para casos simples
- [ ] Documentación actualizada

---

## 📝 Documentar Cambios

Después de resolver cada bug:

1. **Actualizar el reporte del bug:**
   ```markdown
   **Estado:** ✅ Resuelto (fecha)
   **Solución implementada:** [Descripción breve]
   **Commit:** [hash del commit]
   ```

2. **Crear nota de release:**
   ```markdown
   ## Fixed
   - BUG-001: Bucles anidados ahora soportan hasta 10+ iteraciones
   - BUG-002: Procedimientos dentro de bucles ejecutan todas las iteraciones
   ```

3. **Actualizar CHANGELOG.md:**
   ```markdown
   ## [1.1.0] - 2025-10-XX
   ### Fixed
   - Corregido crash con bucles anidados profundos
   - Corregido procedimientos en bucles ejecutando solo 1 iteración
   ```

---

## 🎯 Métricas de Éxito

Considerar los bugs resueltos cuando:

1. **Tests automatizados pasan:**
   - `testProcedureInLoop()` → PASS
   - `testNestedLoops()` → PASS

2. **Tests manuales exitosos:**
   - `cuadrados_rotados_procedimiento.logo` con 10 cuadrados → 92 acciones ✅
   - `cuadrados_rotados.logo` con cantidad = 10 → No crash ✅

3. **No regresiones:**
   - Todos los archivos que funcionaban antes siguen funcionando
   - Performance similar o mejor

---

**Última actualización:** 28 de octubre de 2025  
**Siguiente paso:** Implementar logging en BytecodeInterpreter (Fase 1 de BUG-002)

# BUG-001: Límite de Anidación en Bucles `Repite`

**ID:** BUG-001  
**Fecha descubierta:** 28 de octubre de 2025  
**Severidad:** 🔴 CRÍTICA  
**Estado:** ❌ Pendiente de corrección  
**Impacto:** Crash completo de la aplicación JavaFX

---

## 📋 Descripción

El compilador **crashea la aplicación JavaFX** cuando se ejecuta código con bucles `Repite` anidados donde el bucle externo tiene **6 o más iteraciones**.

### Límite Identificado

```
Repite N [ Repite M [...] ]
```

- ✅ **N ≤ 5:** Funciona correctamente
- ❌ **N ≥ 6:** Crashea la aplicación

---

## 🔬 Reproducción

### Paso 1: Crear archivo de prueba

**Archivo:** `compilador/test/cuadrados_rotados.logo`

```logo
// Patrón de cuadrados rotados
haz dummy 0
haz lado 80
haz cantidad 6
haz rotacion 60

centro
bajalapiz

Repite cantidad [
  Repite 4 [
    avanza lado
    giraderecha 90
  ]
  giraderecha rotacion
]

subelapiz
```

### Paso 2: Compilar y ejecutar

1. Abrir el archivo en la interfaz LogoTec
2. Presionar "Compilar" → ✅ Compila sin errores
3. Presionar "Ejecutar" → ❌ **CRASH**

### Paso 3: Observar el crash

La aplicación JavaFX se cierra inesperadamente sin mensaje de error claro.

---

## 📊 Casos de Prueba

| Iteraciones Externas | Iteraciones Internas | Acciones Totales | Resultado |
|---------------------|---------------------|------------------|-----------|
| 5 | 4 | 45 | ✅ Funciona |
| 6 | 4 | 54 | ❌ **CRASH** |
| 7 | 4 | 63 | ❌ **CRASH** |
| 10 | 4 | 90 | ❌ **CRASH** |
| 15 | 4 | 135 | ❌ **CRASH** |

**Conclusión:** El límite está en la **cantidad de iteraciones del bucle externo**, no en el número total de acciones.

---

## 🎯 Comportamiento Esperado vs. Observado

### ✅ Esperado

Con `cantidad = 6`:
1. Compilar exitosamente → ✅
2. Generar 54 acciones de tortuga (6 cuadrados × 9 acciones)
3. Ejecutar sin errores
4. Animar el dibujo correctamente
5. Mostrar 6 cuadrados rotados formando un patrón circular

### ❌ Observado

Con `cantidad = 6`:
1. Compilar exitosamente → ✅
2. Al presionar "Ejecutar" → **Crash inmediato**
3. No se genera animación
4. La aplicación se cierra

---

## 🔍 Análisis Técnico

### Hipótesis Principal: Límite en Timeline/KeyFrames de JavaFX

**Evidencia:**

1. **El número de acciones NO es el problema:**
   - `estrella_fractal_2niveles.logo` genera ~140 acciones y funciona ✅
   - `cuadrados_rotados.logo` con 6 cuadrados genera 54 acciones y crashea ❌

2. **La anidación es el factor crítico:**
   - `Repite 10` sin anidación → ✅ Funciona (92 acciones)
   - `Repite 6 [ Repite 4 [...] ]` → ❌ Crashea (54 acciones)

3. **Componentes sospechosos:**

#### a) **MainController.java - Creación de KeyFrames**
```java
// Línea ~260-295
for (int frameIdx = 0; frameIdx < totalFrames; frameIdx++) {
    final int startIdx = frameIdx * accionesPorFrame;
    final int endIdx = Math.min(startIdx + accionesPorFrame, acciones.size());

    KeyFrame frame = new KeyFrame(Duration.millis((frameIdx + 1) * delayMs), e -> {
        // Lambda closure con variables locales
        for (int i = startIdx; i < endIdx; i++) {
            AccionTortuga accion = acciones.get(i);
            // ... ejecutar acción
        }
    });

    timeline.getKeyFrames().add(frame);
}
```

**Posible problema:** Los lambdas crean closures sobre `startIdx` y `endIdx`. Con bucles anidados profundos, esto podría causar:
- Acumulación excesiva de lambdas en memoria
- Stack overflow en el recolector de basura
- Límite interno de JavaFX Timeline

#### b) **BytecodeInterpreter.java - Ejecución de bucles**
```java
// Manejo de bucles Repite con stack
// Posible corrupción del stack con anidación profunda
```

#### c) **AssemblyGenerator.java / ASTtoIRTranslator.java**
```java
// Generación de etiquetas para bucles anidados
// Posible conflicto en nombres de etiquetas o saltos
```

### Hipótesis Secundaria: Límite de Stack en la VM

La VM puede tener un límite de profundidad de stack para bucles anidados que no está documentado.

---

## 🛠️ Workarounds (Soluciones Temporales)

### Opción 1: Limitar iteraciones del bucle externo a ≤ 5

**Modificar:**
```logo
haz cantidad 6  // ❌ Crashea
```

**A:**
```logo
haz cantidad 5  // ✅ Funciona
```

**Limitación:** Solo funciona para patrones pequeños

---

### Opción 2: Usar bucle simple sin anidación ✅ **RECOMENDADO**

**Archivo de prueba:** `cuadrados_rotados_sin_procedimiento.logo`

```logo
// Patrón de cuadrados rotados - SIN ANIDACIÓN
haz dummy 0
haz lado 80

centro
bajalapiz

// Desenrollar el código del bucle interno
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

**Resultado:** ✅ **Funciona perfectamente con 10 cuadrados (92 acciones)**

**Ventajas:**
- ✅ Soporta muchas más iteraciones (probado con 10)
- ✅ Genera el mismo resultado visual
- ✅ No crashea

**Desventajas:**
- ❌ Código más verboso
- ❌ Difícil de mantener para patrones complejos

---

## 🔧 Solución Propuesta

### Prioridad 1: Investigar límite de KeyFrames

1. **Agregar logging detallado en MainController.java:**
   ```java
   System.out.println("🔍 Creando frame " + frameIdx + " de " + totalFrames);
   System.out.println("   Variables closure: startIdx=" + startIdx + ", endIdx=" + endIdx);
   ```

2. **Agregar try-catch alrededor de la creación de KeyFrames:**
   ```java
   try {
       KeyFrame frame = new KeyFrame(...);
       timeline.getKeyFrames().add(frame);
   } catch (Exception ex) {
       System.err.println("❌ Error al crear KeyFrame " + frameIdx);
       ex.printStackTrace();
   }
   ```

3. **Implementar límite de frames con reagrupación automática:**
   ```java
   if (totalFrames > 150) {
       accionesPorFrame = (int) Math.ceil((double) acciones.size() / 150);
       totalFrames = 150;
   }
   ```

### Prioridad 2: Investigar generación de IR/Assembly

1. **Verificar etiquetas de bucles anidados:**
   - Revisar `ASTtoIRTranslator.java` en el método `visit(RepeatNode)`
   - Asegurar que las etiquetas de bucles anidados no colisionen

2. **Agregar límite de profundidad de anidación:**
   ```java
   private int nestedLoopDepth = 0;
   
   @Override
   public void visit(RepeatNode node) {
       nestedLoopDepth++;
       if (nestedLoopDepth > 5) {
           throw new RuntimeException("Límite de anidación de bucles excedido (máximo 5)");
       }
       // ... código existente
       nestedLoopDepth--;
   }
   ```

### Prioridad 3: Modo de ejecución alternativo

Implementar modo "instantáneo" sin animación para patrones complejos:

```java
if (acciones.size() > 100 || detectNestedLoops()) {
    // Ejecutar todas las acciones instantáneamente
    for (AccionTortuga accion : acciones) {
        ejecutarAccion(accion);
    }
    zonaDibujo.actualizarTortuga(...);
} else {
    // Animación normal con KeyFrames
}
```

---

## 📁 Archivos Relacionados

### Archivos de prueba (en `compilador/test/`)

- ✅ `cuadrados_rotados.logo` - Demuestra el bug (cantidad = 5 funciona, 6+ crashea)
- ✅ `cuadrados_rotados_sin_procedimiento.logo` - Workaround exitoso
- ✅ `estrella_fractal_2niveles.logo` - Funciona con 140 acciones (sin anidación profunda)

### Código fuente a revisar

- `compilador/src/main/java/com/miorganizacion/logotec/interfaz/controller/MainController.java` (líneas 240-310)
- `compilador/src/main/java/com/miorganizacion/logotec/compilador/ir/ASTtoIRTranslator.java`
- `compilador/src/main/java/com/miorganizacion/logotec/compilador/backend/AssemblyGenerator.java`
- `compilador/src/main/java/com/miorganizacion/logotec/compilador/backend/BytecodeInterpreter.java`

---

## ✅ Criterios de Aceptación para Solución

1. ✅ `Repite 10 [ Repite 4 [...] ]` debe ejecutarse sin crash
2. ✅ Generar 90 acciones correctamente
3. ✅ Animación completa del patrón
4. ✅ Sin degradación de rendimiento para casos simples
5. ✅ Mensaje de error claro si hay límite técnico infranqueable

---

## 📝 Notas Adicionales

- **Optimización implementada:** KeyFrame grouping (líneas ~240-260 en MainController.java)
  - >200 acciones: 5 acciones/frame
  - 100-200 acciones: 2 acciones/frame
  - Esta optimización **NO resuelve** el problema de anidación

- **Protección agregada:** Límite de 150 frames máximo con reagrupación automática
  - Esta protección **NO previene** el crash con bucles anidados

---

**Estado:** 🔴 CRÍTICO - Requiere investigación urgente  
**Próximo paso:** Implementar logging detallado y límite de profundidad de anidación

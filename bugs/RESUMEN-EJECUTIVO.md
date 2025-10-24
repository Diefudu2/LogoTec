# 🚨 Resumen Ejecutivo de Bugs Críticos - LogoTec Compiler

**Fecha:** 28 de octubre de 2025  
**Estado del Proyecto:** 🟡 Funcional con limitaciones conocidas  
**Bugs Críticos Pendientes:** 2

---

## 📊 Resumen Rápido

| ID | Severidad | Impacto | Workaround | Prioridad |
|----|-----------|---------|------------|-----------|
| BUG-001 | 🔴 CRÍTICA | Crash de aplicación | ✅ Disponible | ALTA |
| BUG-002 | 🔴 CRÍTICA | Comportamiento incorrecto | ✅ Disponible | ALTA |

---

## 🔴 BUG-001: Límite de Anidación en Bucles `Repite`

### Problema
Bucles `Repite` anidados con **6+ iteraciones en el bucle externo** causan **crash completo** de la aplicación JavaFX.

### Ejemplo
```logo
Repite 6 [           // ❌ Crashea
  Repite 4 [
    avanza 80
    giraderecha 90
  ]
  giraderecha 60
]
```

### Límite Identificado
- ✅ `Repite 5 [ Repite 4 [...] ]` → Funciona
- ❌ `Repite 6 [ Repite 4 [...] ]` → **CRASH**

### Workaround
Desenrollar el código sin anidación:
```logo
Repite 10 [          // ✅ Funciona
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
```

### Archivo de prueba
- ✅ `compilador/test/cuadrados_rotados_sin_procedimiento.logo` (workaround exitoso)

### Causa probable
- Límite en Timeline/KeyFrames de JavaFX con closures
- Posible stack overflow con anidación profunda

---

## 🔴 BUG-002: Procedimientos dentro de Bucles `Repite` Rompen la Iteración

### Problema
Llamar a un procedimiento dentro de un bucle `Repite` causa que **solo se ejecute 1 iteración**, ignorando las restantes.

### Ejemplo
```logo
Para cuadrado [lado] [
  Repite 4 [
    avanza lado
    giraderecha 90
  ]
] fin

Repite 10 [          // ❌ Solo ejecuta 1 vez
  cuadrado 80
  giraderecha 36
]
```

**Resultado:**
- Esperado: 10 cuadrados (90 acciones)
- Observado: **1 cuadrado** (11 acciones)

### Workaround
Mismo que BUG-001: desenrollar sin usar procedimientos dentro del bucle.

### Archivo de prueba
- ✅ `compilador/test/cuadrados_rotados_sin_procedimiento.logo` (workaround exitoso)
- ❌ `compilador/test/cuadrados_rotados_procedimiento.logo` (demuestra el bug)

### Causa probable
- Bug en `BytecodeInterpreter.java` en el manejo de `RET`
- El retorno de procedimiento corrompe el contador del bucle
- Posible mezcla incorrecta de `callStack` con control de bucles

---

## 🎯 Impacto en el Proyecto

### Limitaciones Actuales

1. **No se pueden usar bucles anidados profundos**
   - Máximo seguro: `Repite 5 [ Repite N [...] ]`
   - Patrones complejos requieren desenrollar código

2. **No se pueden usar procedimientos dentro de bucles en main**
   - `Repite N [ procedimiento ]` solo ejecuta 1 vez
   - Reduce drásticamente la reutilización de código

### Funcionalidad No Afectada

✅ **Lo que SÍ funciona:**
- Bucles simples sin anidación (hasta valores altos)
- Procedimientos que contienen bucles internamente
- Llamadas a procedimientos fuera de bucles
- Bucles con código desenrollado directamente

### Ejemplos Funcionales

**✅ test.logo** - Funciona perfectamente:
```logo
Para roseta [cont l] [
  Repite cont [              // Bucle DENTRO del procedimiento
    poligono 6 l
    giraderecha 360 / cont
  ]
] fin

roseta 12 50                 // Llamada SIN bucle externo
```

**✅ cuadrados_rotados_sin_procedimiento.logo** - Workaround exitoso:
```logo
Repite 10 [                  // Bucle simple sin procedimientos
  avanza 80
  giraderecha 90
  // ... código desenrollado
]
```

---

## 🔧 Próximos Pasos Recomendados

### Fase 1: Diagnóstico (Estimado: 2-4 horas)

1. **Agregar logging extensivo en BytecodeInterpreter.java:**
   - Rastrear `CALL`, `RET`, inicio/fin de bucles
   - Monitorear estado del `callStack` y contadores de bucle
   
2. **Ejecutar pruebas con logging:**
   - `cuadrados_rotados.logo` con cantidad = 5 (funciona)
   - `cuadrados_rotados.logo` con cantidad = 6 (crashea)
   - `cuadrados_rotados_procedimiento.logo` (solo 1 iteración)

3. **Analizar outputs:**
   - Identificar punto exacto del crash en BUG-001
   - Identificar por qué el bucle se detiene en BUG-002

### Fase 2: Corrección (Estimado: 4-8 horas)

**Para BUG-001:**
1. Implementar límite de frames con reagrupación automática más agresiva
2. O implementar modo de ejecución "instantáneo" para patrones complejos
3. Agregar validación de profundidad de anidación con mensaje claro

**Para BUG-002:**
1. Separar `callStack` de control de bucles si es necesario
2. Corregir manejo de `RET` para preservar estado del bucle
3. Agregar tests unitarios para procedimientos en bucles

### Fase 3: Validación (Estimado: 2 horas)

1. **Tests de regresión:**
   - Verificar que test.logo sigue funcionando
   - Verificar que estrella_fractal_2niveles.logo sigue funcionando
   
2. **Tests de corrección:**
   - `cuadrados_rotados.logo` con cantidad = 10 debe funcionar
   - `cuadrados_rotados_procedimiento.logo` debe generar 90 acciones
   
3. **Tests de límites:**
   - Probar con 20, 50, 100 iteraciones
   - Probar con 3 niveles de anidación

---

## 📈 Priorización

### 🔥 Prioridad INMEDIATA: BUG-002
**Razón:** Limita severamente el uso de procedimientos (feature principal recién implementada)

**Impacto en usuarios:**
- No pueden reutilizar código efectivamente
- Frustrante descubrir que el código "correcto" no funciona
- Viola el principio de "menos sorpresas"

### 🔥 Prioridad ALTA: BUG-001
**Razón:** Causa crash (mala experiencia de usuario)

**Impacto en usuarios:**
- Crash sin mensaje de error claro
- Pérdida de trabajo no guardado
- Limita patrones complejos

---

## 💡 Recomendación Final

**Opción A: Fix Rápido (1 día)**
1. Agregar validación que rechace bucles anidados con N > 5
2. Agregar validación que rechace procedimientos en bucles
3. Mostrar mensajes de error claros con sugerencias de workaround

**Opción B: Fix Completo (2-3 días)**
1. Investigar y corregir BUG-002 primero (más crítico para usabilidad)
2. Luego investigar y corregir BUG-001
3. Agregar tests exhaustivos
4. Documentar limitaciones restantes si las hay

**Opción C: Híbrida (1-2 días)**
1. Fix rápido con validaciones y mensajes claros
2. Planificar fix completo para siguiente iteración
3. Documentar workarounds en manual de usuario

---

## 📝 Notas para Desarrolladores

### Archivos Clave a Revisar

1. **BytecodeInterpreter.java** 🔥 CRÍTICO
   - Manejo de `CALL` y `RET`
   - Control de bucles
   - Stack management

2. **MainController.java** 🔥 CRÍTICO
   - Creación de KeyFrames
   - Timeline management
   - Límites de frames

3. **ASTtoIRTranslator.java**
   - Generación de IR para `RepeatNode`
   - Generación de IR para `ProcCallNode`

4. **AssemblyGenerator.java**
   - `translateCall()` y `translateReturn()`
   - Traducción de bucles

### Testing

Crear suite de tests en `compilador/src/test/java/`:

```java
@Test
public void testNestedLoopsUpTo5Iterations() { ... }

@Test
public void testNestedLoopsWith6Iterations() { ... }  // Debería pasar después del fix

@Test
public void testProcedureInLoopMultipleIterations() { ... }  // Actualmente falla

@Test
public void testProcedureWithLoopInsideCalledOutsideLoop() { ... }  // Ya pasa
```

---

**Preparado por:** GitHub Copilot  
**Fecha:** 28 de octubre de 2025  
**Para más detalles, ver:** `BUG-001-limite-anidacion-repite.md` y `BUG-002-procedimientos-en-repite.md`

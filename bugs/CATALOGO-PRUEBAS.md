# 📁 Archivos de Prueba - Catálogo

Este documento cataloga todos los archivos `.logo` de prueba y su propósito.

---

## ✅ Archivos que Funcionan Correctamente

### test_procedimiento_simple.logo
**Propósito:** Prueba básica de procedimientos sin parámetros  
**Características:**
- Dibuja un cuadrado simple
- ~8 acciones
- Sin bucles anidados
- Sin parámetros

**Estado:** ✅ Funciona perfectamente

---

### test.logo (Roseta de hexágonos)
**Propósito:** Prueba completa del pipeline de procedimientos con parámetros  
**Características:**
- Procedimientos anidados (roseta llama a poligono)
- Parámetros: `roseta [cont l]`, `poligono [n l]`
- Expresiones aritméticas: `360 / n`, `360 / cont`
- ~40-60 acciones
- Bucles DENTRO de procedimientos (no en main)

**Estado:** ✅ Funciona perfectamente  
**Notas:** Este es el archivo objetivo original del proyecto

---

### estrella_superpuesta.logo
**Propósito:** Patrón de estrella simple  
**Características:**
- ~50-80 acciones
- Sin procedimientos
- Sin anidación profunda

**Estado:** ✅ Funciona perfectamente

---

### estrella_fractal_2niveles.logo
**Propósito:** Patrón complejo con muchas acciones  
**Características:**
- ~140 acciones
- 5 brazos con sub-estrellas
- Usa comando `atras` (agregado durante testing)
- Sin anidación profunda de bucles

**Estado:** ✅ Funciona perfectamente  
**Notas:** Demostró que el problema NO es el número de acciones

---

### estrella_capas.logo
**Propósito:** Múltiples capas concéntricas de estrellas  
**Características:**
- ~40 acciones
- 8 capas de estrellas
- Sin procedimientos

**Estado:** ✅ Funciona (probablemente, no confirmado en últimas pruebas)

---

### cuadrados_rotados_sin_procedimiento.logo ⭐ WORKAROUND
**Propósito:** Solución temporal para BUG-001 y BUG-002  
**Características:**
- 10 cuadrados rotados formando un patrón circular
- 92 acciones (1 centro + 10 cuadrados × 9 acciones)
- Código desenrollado sin procedimientos
- Bucle simple sin anidación

**Estado:** ✅ **Funciona perfectamente**  
**Uso:** Referencia para patrones que requieren >5 iteraciones

**Código:**
```logo
// Patrón de cuadrados rotados - SIN PROCEDIMIENTOS
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

---

## ❌ Archivos que Demuestran Bugs

### cuadrados_rotados.logo (BUG-001)
**Propósito:** Reproduce el límite de anidación de bucles  
**Características:**
- Bucles anidados: `Repite cantidad [ Repite 4 [...] ]`
- Variable `cantidad` controla iteraciones externas

**Estados:**
- `cantidad = 5` → ✅ Funciona (45 acciones)
- `cantidad = 6` → ❌ **CRASH** (debería ser 54 acciones)
- `cantidad = 10` → ❌ **CRASH** (debería ser 90 acciones)
- `cantidad = 15` → ❌ **CRASH** (debería ser 135 acciones)

**Bug reportado:** BUG-001  
**Archivo de reporte:** `bugs/BUG-001-limite-anidacion-repite.md`

---

### cuadrados_rotados_procedimiento.logo (BUG-002)
**Propósito:** Reproduce el bug de procedimientos en bucles  
**Características:**
- Define procedimiento `cuadrado [tamano]`
- Llama al procedimiento dentro de `Repite 10`
- Debería generar 90 acciones

**Estado:** ❌ Solo ejecuta **1 iteración** (11 acciones)

**Bug reportado:** BUG-002  
**Archivo de reporte:** `bugs/BUG-002-procedimientos-en-repite.md`

**Código:**
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

Repite 10 [           // ❌ Solo ejecuta 1 vez
  cuadrado lado
  giraderecha rotacion
]

subelapiz
```

---

### cuadrados_rotados_desenrollado.logo (EXPERIMENTO FALLIDO)
**Propósito:** Intento de workaround desenrollando bucle externo  
**Características:**
- Bucle simple `Repite 40`
- Intento de simular rotación entre cuadrados

**Estado:** ❌ No funciona correctamente (no rota entre cuadrados)  
**Notas:** Mantener solo como referencia histórica

---

## 📊 Matriz de Compatibilidad

| Característica | test.logo | cuadrados_sin_proc | cuadrados_proc | cuadrados_anidado |
|----------------|-----------|-------------------|----------------|-------------------|
| Procedimientos | ✅ | ❌ | ✅ | ❌ |
| Bucles en main | ❌ | ✅ | ✅ | ✅ |
| Bucles anidados | ❌ | ❌ | ❌ (interno) | ✅ |
| Proc en bucle | ❌ | ❌ | ✅ | ❌ |
| >5 iteraciones | ✅ | ✅ | ✅ (intento) | ❌ |
| **FUNCIONA** | **✅** | **✅** | **❌** | **❌** |

---

## 🎯 Casos de Uso Recomendados

### Para aprender procedimientos básicos
→ `test_procedimiento_simple.logo`

### Para procedimientos con parámetros y expresiones
→ `test.logo`

### Para patrones complejos con muchas acciones
→ `estrella_fractal_2niveles.logo`

### Para patrones circulares con >5 elementos
→ `cuadrados_rotados_sin_procedimiento.logo` ⭐

### Para reproducir bugs
→ `cuadrados_rotados.logo` (BUG-001)  
→ `cuadrados_rotados_procedimiento.logo` (BUG-002)

---

## 📝 Plantilla para Nuevos Tests

```logo
// [Descripción breve del propósito]
haz dummy 0
haz [variables necesarias]

centro
bajalapiz

// Código del patrón

subelapiz
```

**Convenciones:**
- Siempre incluir comentario inicial
- Siempre incluir `haz dummy 0` (requerimiento del compilador)
- Usar `centro` para posición inicial
- Terminar con `subelapiz`

---

**Última actualización:** 28 de octubre de 2025

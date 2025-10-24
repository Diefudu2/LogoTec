# 📚 Índice Visual - Documentación de Bugs

```
bugs/
│
├── 📄 README.md                    ← ⭐ EMPEZAR AQUÍ
│   └── Índice general y estado de todos los bugs
│
├── 📋 RESUMEN-EJECUTIVO.md         ← Para managers/decisores
│   ├── Resumen de 2 páginas
│   ├── Impacto en el proyecto
│   ├── Priorización
│   └── Próximos pasos
│
├── 🐛 BUG-001-limite-anidacion-repite.md     ← Bug Crítico #1
│   ├── Descripción: Bucles anidados con N≥6 crashean
│   ├── Reproducción paso a paso
│   ├── Análisis técnico detallado
│   ├── Workarounds disponibles
│   └── Solución propuesta
│
├── 🐛 BUG-002-procedimientos-en-repite.md    ← Bug Crítico #2
│   ├── Descripción: Procedimientos en bucles solo ejecutan 1 vez
│   ├── Reproducción paso a paso
│   ├── Análisis técnico detallado
│   ├── Workarounds disponibles
│   └── Solución propuesta
│
├── 🔧 GUIA-INVESTIGACION.md        ← Para desarrolladores
│   ├── Pasos concretos para debugging
│   ├── Código de logging a agregar
│   ├── Tests unitarios propuestos
│   └── Checklist de validación
│
├── 📁 CATALOGO-PRUEBAS.md          ← Referencia de archivos .logo
│   ├── Archivos que funcionan ✅
│   ├── Archivos que demuestran bugs ❌
│   ├── Matriz de compatibilidad
│   └── Casos de uso recomendados
│
└── 📝 PLANTILLA-BUG.md             ← Para reportar nuevos bugs
    └── Template estandarizado para futuros reportes
```

---

## 🎯 Guía de Lectura por Rol

### 👨‍💼 Si eres Project Manager / Product Owner:
1. **Lee primero:** `RESUMEN-EJECUTIVO.md`
   - Impacto en el proyecto
   - Priorización
   - Estimaciones de tiempo
2. **Opcional:** `README.md` para estado actualizado

---

### 👨‍💻 Si eres Desarrollador (vas a arreglar los bugs):
1. **Lee primero:** `README.md`
2. **Luego:** `BUG-002-procedimientos-en-repite.md` (Prioridad 1)
3. **Después:** `BUG-001-limite-anidacion-repite.md` (Prioridad 2)
4. **Implementa:** Sigue pasos en `GUIA-INVESTIGACION.md`
5. **Consulta:** `CATALOGO-PRUEBAS.md` para saber qué archivos usar

---

### 🧪 Si eres QA / Tester:
1. **Lee primero:** `CATALOGO-PRUEBAS.md`
   - Conocer todos los archivos de prueba
   - Entender qué funciona y qué no
2. **Luego:** `BUG-001` y `BUG-002` para casos de reproducción
3. **Usa:** Los archivos .logo mencionados para testing

---

### 📚 Si eres nuevo en el proyecto:
1. **Lee primero:** `README.md`
2. **Luego:** `RESUMEN-EJECUTIVO.md` para contexto
3. **Finalmente:** `CATALOGO-PRUEBAS.md` para saber qué probar

---

## 📊 Mapa Mental

```
                    ┌─────────────────┐
                    │   README.md     │
                    │  (Entrada)      │
                    └────────┬────────┘
                             │
                ┌────────────┴────────────┐
                │                         │
        ┌───────▼────────┐       ┌───────▼────────┐
        │  RESUMEN-       │       │   CATALOGO-    │
        │  EJECUTIVO.md   │       │   PRUEBAS.md   │
        │  (Decisión)     │       │   (Testing)    │
        └───────┬─────────┘       └────────────────┘
                │
        ┌───────┴────────┐
        │                │
┌───────▼───────┐  ┌────▼──────────┐
│   BUG-001     │  │   BUG-002     │
│ (Anidación)   │  │ (Procedim.)   │
└───────┬───────┘  └────┬──────────┘
        │                │
        └────────┬───────┘
                 │
        ┌────────▼─────────┐
        │  GUIA-           │
        │  INVESTIGACION   │
        │  (Solución)      │
        └──────────────────┘
```

---

## 🔍 Búsqueda Rápida

### ¿Necesitas...?

**Ver estado de todos los bugs?**
→ `README.md`

**Entender el impacto en el proyecto?**
→ `RESUMEN-EJECUTIVO.md`

**Reproducir el bug de crash con bucles anidados?**
→ `BUG-001-limite-anidacion-repite.md` + `cuadrados_rotados.logo`

**Reproducir el bug de procedimientos en bucles?**
→ `BUG-002-procedimientos-en-repite.md` + `cuadrados_rotados_procedimiento.logo`

**Saber qué archivos de prueba funcionan?**
→ `CATALOGO-PRUEBAS.md`

**Instrucciones para arreglar los bugs?**
→ `GUIA-INVESTIGACION.md`

**Reportar un nuevo bug?**
→ `PLANTILLA-BUG.md`

---

## 📈 Estado Actual

```
┌─────────────────────────────────────────────────────┐
│  Bugs Críticos:    2                                │
│  Bugs Resueltos:   0                                │
│  Workarounds:      ✅ Disponibles para ambos       │
│  Tests afectados:  2 de 6                          │
│  Documentación:    ✅ Completa                     │
└─────────────────────────────────────────────────────┘
```

**Próxima acción recomendada:**  
Comenzar con BUG-002 (procedimientos en bucles) siguiendo `GUIA-INVESTIGACION.md`

---

## 📞 Contacto y Contribución

**Para reportar nuevos bugs:**
1. Copiar `PLANTILLA-BUG.md`
2. Renombrar a `BUG-XXX-descripcion-corta.md`
3. Llenar todos los campos
4. Agregar al índice en `README.md`

**Para actualizar documentación:**
- Mantener el formato consistente
- Usar emojis para categorías (🐛 🔧 📁 etc.)
- Actualizar fecha de "Última actualización"

---

**Creado:** 28 de octubre de 2025  
**Versión:** 1.0  
**Archivos totales:** 7 documentos de referencia

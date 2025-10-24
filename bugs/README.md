# 🐛 Bugs y Limitaciones Conocidas - LogoTec Compiler

Este directorio contiene reportes detallados de bugs descubiertos durante el desarrollo y testing del compilador LogoTec.

## Índice de Bugs

1. **[BUG-001: Límite de anidación en bucles `Repite`](./BUG-001-limite-anidacion-repite.md)** ⚠️ CRÍTICO
   - **Estado:** Pendiente de corrección
   - **Severidad:** Alta - Causa crash de la aplicación
   - **Fecha descubierta:** 28 de octubre de 2025

2. **[BUG-002: Procedimientos dentro de bucles `Repite` rompen iteración](./BUG-002-procedimientos-en-repite.md)** ⚠️ CRÍTICO
   - **Estado:** Pendiente de corrección
   - **Severidad:** Alta - Comportamiento incorrecto
   - **Fecha descubierta:** 28 de octubre de 2025

---

## Estructura de Reportes

Cada reporte incluye:
- **Descripción:** Explicación clara del problema
- **Reproducción:** Pasos exactos para reproducir el bug
- **Archivos de prueba:** Código que demuestra el bug
- **Comportamiento esperado vs. Observado:** Comparación detallada
- **Análisis técnico:** Hipótesis sobre la causa raíz
- **Workarounds:** Soluciones temporales disponibles
- **Solución propuesta:** Pasos necesarios para corregir el bug

---

## Estado de los Bugs

### 🔴 Críticos (Requieren atención inmediata)
- BUG-001: Límite de anidación en bucles `Repite`
- BUG-002: Procedimientos dentro de bucles `Repite`

### 🟡 Medios
- *(Ninguno por el momento)*

### 🟢 Bajos
- *(Ninguno por el momento)*

---

## Cómo Reportar un Nuevo Bug

1. Crear archivo `BUG-XXX-descripcion-corta.md` en este directorio
2. Usar la plantilla proporcionada en `PLANTILLA-BUG.md`
3. Incluir archivos `.logo` de prueba en `test/` con nombre descriptivo
4. Actualizar este README.md con el nuevo bug en el índice

---

**Última actualización:** 28 de octubre de 2025

# LogoTec - Compilador e Intérprete

Implementación completa de un compilador para el lenguaje LogoTec con todas las fases de compilación y generación de archivos objeto ejecutables.

## 🚀 Quick Start

### 1. Compilar un Programa (usando el IDE)

1. Abre el IDE LogoTec
2. Carga o escribe tu código `.logo`
3. Presiona **[COMPILAR]**
4. El IDE genera automáticamente el archivo `output/programa.ltbc`

### 2. Ejecutar el Archivo Objeto (sin el IDE)

```bash
# Método 1: Usando la VM standalone
java -jar logotec-vm.jar output/programa.ltbc

# Método 2: Con modo debug
java -jar logotec-vm.jar output/programa.ltbc --debug

# Método 3: Ver información del archivo
java -jar logotec-vm.jar output/programa.ltbc --info
```

## 📋 Fases del Compilador

El compilador implementa las siguientes fases:

```
LogoTec Source Code (.logo)
    ↓
[1] Análisis Léxico/Sintáctico (ANTLR)
    ↓
[2] Abstract Syntax Tree (AST)
    ↓
[3] Optimización de AST
    ├─ Constant Folding
    ├─ Constant Propagation
    ├─ Dead Code Elimination
    ├─ Loop Unrolling (max 16 iteraciones)
    └─ Algebraic Simplification
    ↓
[4] Representación Intermedia (IR)
    ↓
[5] Generación de Assembly (MIPS-like)
    ↓
[6] Ensamblado y Enlazado
    ├─ Pass 1: Extract Symbols
    ├─ Pass 2: Resolve Labels
    └─ Pass 3: Generate Bytecode
    ↓
[7] Archivo Objeto Ejecutable (.ltbc)
    ↓
[8] Máquina Virtual (Bytecode Interpreter)
    ↓
Acciones de Tortuga + Visualización Gráfica
```

## 🏗️ Arquitectura del Sistema

### Archivo Objeto `.ltbc` (LogoTec ByteCode)

Formato binario ejecutable que contiene:

```
╔════════════════════════════════════╗
║ HEADER                             ║
║   - Magic: "LTBC" (4 bytes)        ║
║   - Version: 1 (4 bytes)           ║
║   - Timestamp: long (8 bytes)      ║
╠════════════════════════════════════╣
║ SYMBOL TABLE                       ║
║   - Variable names → Memory addr   ║
╠════════════════════════════════════╣
║ BYTECODE SECTION                   ║
║   - Executable instructions        ║
║   - VM-compatible format           ║
╚════════════════════════════════════╝
```

### Máquina Virtual

- **Arquitectura**: 32 registros (estilo MIPS)
- **Memoria**: 4KB (1024 words)
- **Formato**: Stack-based + Register-based hybrid
- **Syscalls**: 12 comandos de tortuga (avanzar, girar, etc.)

## 📦 Construcción del Proyecto

### Compilar el Proyecto Completo

```bash
cd compilador
mvn clean compile
```

### Construir la VM Standalone

```bash
# Windows
build-vm.bat

# Linux/Mac
./build-vm.sh
```

Esto genera `logotec-vm.jar` en la raíz del proyecto.

### Asociar Archivos .ltbc en Windows (Doble Clic)

Para poder ejecutar archivos `.ltbc` con doble clic en Windows:

```bash
# 1. Construir el VM JAR
build-vm.bat

# 2. Asociar la extensión (COMO ADMINISTRADOR)
asociar-ltbc.bat
```

Después de esto, puedes:
- **Doble clic** en cualquier archivo `.ltbc` para ejecutarlo
- **Click derecho** → "Ejecutar con LogoTec VM"

**Nota**: Requiere que Java esté instalado y en el PATH del sistema.

## 📁 Estructura de Archivos

```
LogoTec/
├── compilador/
│   ├── src/main/java/com/miorganizacion/logotec/
│   │   ├── compilador/        # Frontend (ANTLR, AST)
│   │   │   ├── ast/           # Nodos del AST
│   │   │   ├── opt/           # Optimizaciones
│   │   │   ├── ir/            # Representación Intermedia
│   │   │   ├── codegen/       # Generación de Assembly
│   │   │   └── backend/       # Bytecode + VM
│   │   ├── interfaz/          # IDE (JavaFX)
│   │   └── vm/                # VM Standalone
│   ├── test/                  # Programas .logo de prueba
│   └── pom.xml
├── output/                    # Archivos .ltbc generados
├── logotec-vm.jar             # VM ejecutable
└── build-vm.bat               # Script de construcción
```

## 🎯 Ejemplos de Uso

### Ejemplo 1: Cuadrado Simple

**Archivo: `cuadrado.logo`**
```logo
// Dibuja un cuadrado
haz lado 100
Repite 4 [
  avanza lado
  giraderecha 90
]
```

**Compilar y ejecutar:**
```bash
# 1. Compilar en el IDE → genera output/cuadrado.ltbc
# 2. Ejecutar
java -jar logotec-vm.jar output/cuadrado.ltbc
```

### Ejemplo 2: Casa con Procedimientos

**Archivo: `casa.logo`**
```logo
// Dibuja una casa
Para cuadrado []
  Repite 4 [
    avanza 100
    giraderecha 90
  ]
fin

Para triangulo []
  Repite 3 [
    avanza 100
    giraderecha 120
  ]
fin

// Programa principal
cuadrado
avanza 100
triangulo
```

### Ejemplo 3: Roseta Compleja

Ver `test/EXITO_roseta_cuadrados_variables.logo` para un ejemplo avanzado con:
- Variables
- Procedimientos con parámetros
- Loops anidados
- 135 acciones de tortuga

## 🔧 Decisiones Técnicas

### ¿Por qué MIPS-like Assembly?

- **Razón**: MIPS es una arquitectura RISC simple y educativa
- **Ventaja**: Fácil de generar desde IR de 3 direcciones
- **Resultado**: Assembly legible y debuggeable

### ¿Por qué Linking Integrado?

- **Contexto**: LogoTec solo soporta programas single-file
- **Decisión**: Integrar linking en el assembler (2-pass)
- **Beneficio**: Simplifica el pipeline, no necesita linker separado

### ¿Por qué Máquina Virtual y no Código Nativo?

- **Portabilidad**: Un .ltbc funciona en Windows, Linux, Mac
- **Debugging**: Más fácil de depurar y visualizar
- **Cumplimiento**: Genera "archivo objeto ejecutable" como requiere el enunciado

## 📊 Optimizaciones Implementadas

1. **Constant Folding**: `2 + 3` → `5`
2. **Constant Propagation**: `x = 5; y = x * 2` → `y = 10`
3. **Dead Branch Elimination**: `SI (falso) [codigo]` → eliminado
4. **Loop Unrolling**: `Repite 4 [avanza 10]` → 4 instrucciones `avanza`
5. **Algebraic Simplification**: `x * 1` → `x`, `x + 0` → `x`
6. **Dead Code Elimination**: Código inalcanzable eliminado
7. **Block Flattening**: Bloques anidados innecesarios colapsados

## 🐛 Bugs Conocidos

Ver `bugs/` para documentación de bugs conocidos y sus soluciones.

## 📝 Documentación Adicional

- `DOCUMENTACION_OPTIMIZACION.md` - Detalles de optimizaciones
- `DOCUMENTACION_GENERACION_CODIGO.md` - Pipeline completo
- `bugs/README.md` - Catálogo de bugs y fixes

## 👥 Equipo

- Implementación: Equipo LogoTec
- Curso: Compiladores e Intérpretes - TEC
- Año: 2025

## 📄 Licencia

Proyecto académico - TEC 2025

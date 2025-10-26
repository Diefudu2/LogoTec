package com.miorganizacion.logotec.interfaz.controller;

import com.miorganizacion.logotec.compilador.CompiladorRealAdapter;
import com.miorganizacion.logotec.compilador.ast.ProgramNode;
import com.miorganizacion.logotec.compilador.opt.AstOptimizer;
import com.miorganizacion.logotec.compilador.ir.*;
import com.miorganizacion.logotec.compilador.backend.*;
import com.miorganizacion.logotec.interfaz.modelo.AccionTortuga;
import com.miorganizacion.logotec.interfaz.modelo.Tortuga;
import com.miorganizacion.logotec.interfaz.ui.Botonera;
import com.miorganizacion.logotec.interfaz.ui.GestorArchivos;
import com.miorganizacion.logotec.interfaz.ui.ZonaDibujo;
import com.miorganizacion.logotec.interfaz.ui.ZonaEditor;
import com.miorganizacion.logotec.interfaz.ui.ZonaErrores;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

public class MainController {

    private final ZonaEditor zonaEditor;
    private final ZonaDibujo zonaDibujo;
    private final ZonaErrores zonaErrores;
    private final Botonera botonera;
    private final GestorArchivos gestorArchivos;
    
    // 🆕 Almacenar el bytecode compilado para ejecución
    private ObjectCodeGenerator.Result bytecodeCargado;

    public MainController(ZonaEditor zonaEditor, ZonaDibujo zonaDibujo, ZonaErrores zonaErrores, Botonera botonera, Stage stage) {
        this.zonaEditor = zonaEditor;
        this.zonaDibujo = zonaDibujo;
        this.zonaErrores = zonaErrores;
        this.botonera = botonera;
        this.gestorArchivos = new GestorArchivos(stage);

        configurarEventos();
    }

    private void configurarEventos() {
        botonera.setOnCompilar(v -> compilar());
        botonera.setOnEjecutar(v -> ejecutar());

        botonera.setOnCargar(v -> {
            String contenido = gestorArchivos.cargarArchivo();
            zonaEditor.setCodigoFuente(contenido);
        });

        botonera.setOnGuardar(v -> {
            String contenido = zonaEditor.getCodigoFuente();
            gestorArchivos.guardarArchivo(contenido);
        });
    }

    private void compilar() {
        zonaErrores.limpiar();
        zonaDibujo.limpiar();

        String codigo = zonaEditor.getCodigoFuente();

        // Capturar la salida de System.out y System.err
        ByteArrayOutputStream baosOut = new ByteArrayOutputStream();
        ByteArrayOutputStream baosErr = new ByteArrayOutputStream();
        PrintStream psOut = new PrintStream(baosOut);
        PrintStream psErr = new PrintStream(baosErr);
        PrintStream oldOut = System.out;
        PrintStream oldErr = System.err;
        
        System.setOut(psOut);
        System.setErr(psErr);

        try {
            System.out.println("╔═══════════════════════════════════════════════════════╗");
            System.out.println("║      Pipeline Completo de Compilación                ║");
            System.out.println("╚═══════════════════════════════════════════════════════╝\n");
            
            // PASO 1: Compilar LogoTec → AST
            System.out.println("🔧 PASO 1: Compilando a AST...");
            ProgramNode ast = CompiladorRealAdapter.compile(codigo);
            System.out.println("✅ AST generado correctamente\n");
            
            // PASO 1.5: Optimizar AST
            System.out.println("🔧 PASO 1.5: Optimizando AST...");
            ProgramNode astOptimizado = AstOptimizer.optimize(ast);
            System.out.println("✅ AST optimizado (constant folding, propagation, dead code elimination)\n");
            
            // PASO 2: Generar IR (Código Intermedio)
            System.out.println("🔧 PASO 2: Generando código IR...");
            ASTtoIRTranslator irTranslator = new ASTtoIRTranslator();
            ASTtoIRTranslator.Result irResult = irTranslator.generate(astOptimizado);
            System.out.println("✅ IR generado: " + irResult.instructions.size() + " instrucciones\n");
            
            // PASO 3: Generar Assembly
            System.out.println("🔧 PASO 3: Generando código Assembly...");
            AssemblyGenerator asmGen = new AssemblyGenerator();
            List<AssemblyInstruction> asmCode = asmGen.generate(irResult.instructions);
            System.out.println("✅ Assembly generado: " + asmCode.size() + " instrucciones\n");
            
            // PASO 4: Generar Bytecode (Object Code)
            System.out.println("🔧 PASO 4: Generando bytecode ejecutable...");
            ObjectCodeGenerator objGen = new ObjectCodeGenerator();
            ObjectCodeGenerator.Result objResult = objGen.generate(asmCode);
            System.out.println("✅ Bytecode generado: " + objResult.bytecode.size() + " instrucciones\n");
            
            // Guardar bytecode para ejecución
            this.bytecodeCargado = objResult;
            
            // PASO 5: Guardar archivo objeto .ltbc
            System.out.println("🔧 PASO 5: Generando archivo objeto ejecutable...");
            try {
                String nombreArchivo = gestorArchivos.getUltimoArchivoCargado();
                if (nombreArchivo == null || nombreArchivo.isEmpty()) {
                    nombreArchivo = "programa";
                } else {
                    // Remover extensión .logo si existe
                    if (nombreArchivo.endsWith(".logo")) {
                        nombreArchivo = nombreArchivo.substring(0, nombreArchivo.length() - 5);
                    }
                }
                
                String outputPath = "output/" + nombreArchivo + ".ltbc";
                ExecutableGenerator.saveToFile(objResult, outputPath);
                System.out.println("✅ Archivo objeto guardado: " + outputPath + "\n");
                
                // PASO 5.5: Construir JAR de la VM si no existe
                verificarYConstruirVMJar();
                
                System.out.println("   Para ejecutar independientemente:");
                System.out.println("   java -jar ../logotec-vm.jar " + outputPath);
                System.out.println("   O hacer doble clic en el archivo .ltbc");
            } catch (Exception e) {
                System.err.println("⚠️  Advertencia: No se pudo guardar el archivo .ltbc");
                System.err.println("   " + e.getMessage());
            }
            
            System.out.println("\n═══════════════════════════════════════════════════════");
            System.out.println("✅ COMPILACIÓN EXITOSA");
            System.out.println("   Variables declaradas: " + objResult.symbolTable.size());
            System.out.println("   Labels generados: " + objResult.labelTable.size());
            System.out.println("   Tamaño bytecode: " + (objResult.bytecode.size() * 4) + " bytes");
            System.out.println("═══════════════════════════════════════════════════════");
            
        } catch (RuntimeException ex) {
            System.err.println("\n❌ Error en la compilación:");
            String mensajeError = ex.getMessage();
            if (mensajeError != null && !mensajeError.isEmpty()) {
                System.err.println("   " + mensajeError);
            } else {
                System.err.println("   Error desconocido");
                ex.printStackTrace(System.err);
            }
            this.bytecodeCargado = null;
        } catch (Error err) {
            // Capturar errores de compilación Java (como PROCEDURE not resolved)
            System.err.println("\n❌ Error crítico en la compilación:");
            System.err.println("   " + err.getMessage());
            System.err.println("\n⚠️  Este error indica un problema interno del compilador.");
            System.err.println("   El código fuente contiene sintaxis no reconocida o inválida.");
            this.bytecodeCargado = null;
        } catch (Throwable t) {
            // Último recurso: capturar cualquier otra excepción
            System.err.println("\n❌ Error inesperado:");
            System.err.println("   " + t.getClass().getSimpleName() + ": " + t.getMessage());
            t.printStackTrace(System.err);
            this.bytecodeCargado = null;
        } finally {
            // Restaurar System.out y System.err
            System.out.flush();
            System.err.flush();
            System.setOut(oldOut);
            System.setErr(oldErr);
            
            // Mostrar la salida capturada en la consola
            String salidaOut = baosOut.toString();
            String salidaErr = baosErr.toString();
            
            if (!salidaOut.isEmpty()) {
                zonaErrores.agregarMensaje(salidaOut);
            }
            if (!salidaErr.isEmpty()) {
                zonaErrores.agregarMensaje(salidaErr);
            }
        }
    }

    private void ejecutar() {
        zonaDibujo.limpiar();
        zonaErrores.limpiar();

        // Capturar la salida de System.out y System.err
        ByteArrayOutputStream baosOut = new ByteArrayOutputStream();
        ByteArrayOutputStream baosErr = new ByteArrayOutputStream();
        PrintStream psOut = new PrintStream(baosOut);
        PrintStream psErr = new PrintStream(baosErr);
        PrintStream oldOut = System.out;
        PrintStream oldErr = System.err;
        
        System.setOut(psOut);
        System.setErr(psErr);

        try {
            // Intentar cargar desde archivo .ltbc primero (ejecución standalone)
            ObjectCodeGenerator.Result programToExecute = null;
            String nombreArchivo = gestorArchivos.getUltimoArchivoCargado();
            
            if (nombreArchivo != null && !nombreArchivo.isEmpty()) {
                // Quitar extensión .logo y agregar .ltbc
                String nombreBase = nombreArchivo.replace(".logo", "");
                String archivoLTBC = "output/" + nombreBase + ".ltbc";
                File ltbcFile = new File(archivoLTBC);
                
                if (ltbcFile.exists()) {
                    System.out.println("╔═══════════════════════════════════════════════════════╗");
                    System.out.println("║   Ejecutando desde archivo objeto .ltbc              ║");
                    System.out.println("╚═══════════════════════════════════════════════════════╝\n");
                    System.out.println("📁 Cargando: " + archivoLTBC);
                    
                    try {
                        programToExecute = ExecutableGenerator.loadFromFile(archivoLTBC);
                        System.out.println("✅ Archivo .ltbc cargado exitosamente\n");
                    } catch (IOException e) {
                        System.err.println("⚠️  Error al cargar .ltbc: " + e.getMessage());
                        System.err.println("   Usando bytecode en memoria...\n");
                        programToExecute = bytecodeCargado;
                    }
                } else {
                    System.out.println("ℹ️  Archivo .ltbc no encontrado: " + archivoLTBC);
                    System.out.println("   Usando bytecode en memoria...\n");
                    programToExecute = bytecodeCargado;
                }
            } else {
                programToExecute = bytecodeCargado;
            }
            
            // Verificar que hay bytecode para ejecutar
            if (programToExecute == null) {
                System.err.println("❌ Error: Primero debes COMPILAR el programa");
                System.err.println("   Presiona el botón 'Compilar' antes de ejecutar");
                System.out.flush();
                System.err.flush();
                System.setOut(oldOut);
                System.setErr(oldErr);
                
                String salidaOut = baosOut.toString();
                String salidaErr = baosErr.toString();
                if (!salidaOut.isEmpty()) zonaErrores.agregarMensaje(salidaOut);
                if (!salidaErr.isEmpty()) zonaErrores.agregarMensaje(salidaErr);
                return;
            }
            
            System.out.println("╔═══════════════════════════════════════════════════════╗");
            System.out.println("║      Ejecutando Bytecode en Máquina Virtual          ║");
            System.out.println("╚═══════════════════════════════════════════════════════╝\n");
            
            // PASO 5: Ejecutar bytecode en la VM
            System.out.println("🔧 Cargando programa en la VM...");
            BytecodeInterpreter vm = new BytecodeInterpreter();
            vm.loadProgram(programToExecute);
            System.out.println("✅ Programa cargado: " + programToExecute.bytecode.size() + " instrucciones\n");
            
            System.out.println("🚀 Ejecutando bytecode...");
            long startTime = System.currentTimeMillis();
            vm.execute();
            long endTime = System.currentTimeMillis();
            System.out.println("✅ Ejecución completada en " + (endTime - startTime) + "ms\n");
            
            // Obtener las acciones generadas por la VM
            List<AccionTortuga> acciones = vm.getAcciones();
            
            System.out.println("═══════════════════════════════════════════════════════");
            System.out.println("✅ EJECUCIÓN EXITOSA");
            System.out.println("   Acciones generadas: " + acciones.size());
            System.out.println("═══════════════════════════════════════════════════════\n");

            if (acciones.isEmpty()) {
                System.out.println("⚠️  No se generaron acciones de dibujo");
                // Restaurar y mostrar salida antes de retornar
                System.out.flush();
                System.err.flush();
                System.setOut(oldOut);
                System.setErr(oldErr);
                
                String salidaOut = baosOut.toString();
                String salidaErr = baosErr.toString();
                if (!salidaOut.isEmpty()) zonaErrores.agregarMensaje(salidaOut);
                if (!salidaErr.isEmpty()) zonaErrores.agregarMensaje(salidaErr);
                return;
            }
            
            // 🐛 DEBUG: Mostrar primeras y últimas acciones
            System.out.println("🔍 DEBUG - Primeras 5 acciones:");
            for (int i = 0; i < Math.min(5, acciones.size()); i++) {
                AccionTortuga a = acciones.get(i);
                System.out.println("   " + i + ": " + a.getTipo() + " - " + a.getValor());
            }
            if (acciones.size() > 10) {
                System.out.println("🔍 DEBUG - Últimas 5 acciones:");
                for (int i = Math.max(0, acciones.size() - 5); i < acciones.size(); i++) {
                    AccionTortuga a = acciones.get(i);
                    System.out.println("   " + i + ": " + a.getTipo() + " - " + a.getValor());
                }
            }
            System.out.println();

            System.out.println("🎨 Iniciando animación del dibujo...");

            // Crear la tortuga visual (posición inicial centrada en el canvas)
            Tortuga tortuga = new Tortuga(150, 150);
            zonaDibujo.dibujarTortuga(tortuga.getX(), tortuga.getY(), tortuga.getAngulo());
            
            Timeline timeline = new Timeline();
            
            // Ajustar delay y agrupación según cantidad de acciones
            int delayMs;
            int accionesPorFrame; // Cuántas acciones ejecutar por KeyFrame
            
            if (acciones.size() > 200) {
                delayMs = 50;  // Aumentado de 30 a 50ms para mejor visualización
                accionesPorFrame = 5; // Agrupar 5 acciones por frame
                System.out.println("⚡ Modo ultra-rápido: " + acciones.size() + " acciones agrupadas");
            } else if (acciones.size() > 100) {
                delayMs = 80;  // Aumentado de 50 a 80ms para animación más suave
                accionesPorFrame = 2; // Agrupar 2 acciones por frame
                System.out.println("⚡ Modo rápido: " + acciones.size() + " acciones agrupadas");
            } else if (acciones.size() > 50) {
                delayMs = 100;
                accionesPorFrame = 1; // 1 acción por frame
            } else {
                delayMs = 300;
                accionesPorFrame = 1; // 1 acción por frame (animación normal)
            }
            
            int totalFrames = (int) Math.ceil((double) acciones.size() / accionesPorFrame);
            System.out.println("⏱️  Delay: " + delayMs + "ms, Acciones/frame: " + accionesPorFrame);
            System.out.println("⏱️  Total frames: " + totalFrames + ", Duración estimada: " + (totalFrames * delayMs / 1000.0) + " segundos\n");

            // 🛡️ Protección: Limitar a 150 frames máximo
            if (totalFrames > 150) {
                System.err.println("⚠️  ADVERTENCIA: " + totalFrames + " frames excede el límite de 150");
                System.err.println("   Aumentando agrupación para evitar crash...");
                accionesPorFrame = (int) Math.ceil((double) acciones.size() / 150);
                totalFrames = (int) Math.ceil((double) acciones.size() / accionesPorFrame);
                System.out.println("   Nuevo agrupamiento: " + accionesPorFrame + " acciones/frame → " + totalFrames + " frames\n");
            }

            // Crear KeyFrames agrupados
            try {
                for (int frameIdx = 0; frameIdx < totalFrames; frameIdx++) {
                    final int startIdx = frameIdx * accionesPorFrame;
                    final int endIdx = Math.min(startIdx + accionesPorFrame, acciones.size());

                    KeyFrame frame = new KeyFrame(Duration.millis((frameIdx + 1) * delayMs), e -> {
                        // Ejecutar múltiples acciones en este frame
                        for (int i = startIdx; i < endIdx; i++) {
                            AccionTortuga accion = acciones.get(i);
                            double xAnterior = tortuga.getX();
                            double yAnterior = tortuga.getY();
                            
                            switch (accion.getTipo()) {
                                case AVANZAR:
                                    tortuga.avanzar(accion.getValor());
                                    // Si el lápiz está abajo, dibujar la línea
                                    if (tortuga.isLapizAbajo()) {
                                        zonaDibujo.dibujarLinea(xAnterior, yAnterior, tortuga.getX(), tortuga.getY());
                                    }
                                    break;
                                case GIRAR:
                                    tortuga.girar(accion.getValor());
                                    break;
                                case BAJAR_LAPIZ:
                                    tortuga.bajarLapiz();
                                    break;
                                case LEVANTAR_LAPIZ:
                                    tortuga.levantarLapiz();
                                    break;
                            }
                        }

                        zonaDibujo.actualizarTortuga(tortuga.getX(), tortuga.getY(), tortuga.getAngulo());
                    });

                    timeline.getKeyFrames().add(frame);
                }
            } catch (Exception ex) {
                System.err.println("❌ ERROR al crear KeyFrames:");
                System.err.println("   Frame count: " + totalFrames);
                System.err.println("   Acciones: " + acciones.size());
                System.err.println("   Error: " + ex.getMessage());
                ex.printStackTrace(System.err);
                throw new RuntimeException("Error al crear animación con " + totalFrames + " frames", ex);
            }

            timeline.setCycleCount(1);
            timeline.setOnFinished(e -> {
                System.out.println("✅ Animación finalizada");
                // Mostrar la salida final
                System.out.flush();
                System.err.flush();
                System.setOut(oldOut);
                System.setErr(oldErr);
                
                String salidaOut = baosOut.toString();
                String salidaErr = baosErr.toString();
                if (!salidaOut.isEmpty()) {
                    zonaErrores.agregarMensaje(salidaOut);
                }
                if (!salidaErr.isEmpty()) {
                    zonaErrores.agregarMensaje(salidaErr);
                }
            });
            timeline.play();

        } catch (RuntimeException ex) {
            String mensaje = ex.getMessage() != null ? ex.getMessage() : "Error inesperado durante la ejecución.";
            System.err.println("❌ Error en la ejecución: " + mensaje);
            ex.printStackTrace(System.err);
            
            // Restaurar System.out/err y mostrar error
            System.out.flush();
            System.err.flush();
            System.setOut(oldOut);
            System.setErr(oldErr);
            
            String salidaOut = baosOut.toString();
            String salidaErr = baosErr.toString();
            if (!salidaOut.isEmpty()) {
                zonaErrores.agregarMensaje(salidaOut);
            }
            if (!salidaErr.isEmpty()) {
                zonaErrores.agregarMensaje(salidaErr);
            }
        }
    }
    
    /**
     * Verifica si existe logotec-vm.jar y lo construye si es necesario
     */
    private void verificarYConstruirVMJar() {
        try {
            // Verificar si el JAR ya existe
            File jarFile = new File("../logotec-vm.jar");
            if (jarFile.exists()) {
                System.out.println("✅ VM JAR encontrado: " + jarFile.getAbsolutePath());
                return;
            }
            
            System.out.println("📦 Construyendo logotec-vm.jar (primera vez)...");
            
            // Crear directorio temporal para el JAR
            File tempDir = new File("target/vm-jar");
            if (!tempDir.exists()) {
                tempDir.mkdirs();
            }
            
            // Copiar clases compiladas necesarias
            File classesDir = new File("target/classes/com");
            File destDir = new File("target/vm-jar/com");
            
            if (classesDir.exists()) {
                copiarDirectorio(classesDir, destDir);
            }
            
            // Crear MANIFEST.MF
            File manifestFile = new File("target/vm-jar/MANIFEST.MF");
            try (PrintStream ps = new PrintStream(manifestFile)) {
                ps.println("Manifest-Version: 1.0");
                ps.println("Main-Class: com.miorganizacion.logotec.vm.VMMain");
                ps.println();
            }
            
            // Crear JAR usando java.util.jar
            File jarOutput = new File("target/vm-jar/logotec-vm.jar");
            crearJar(new File("target/vm-jar"), jarOutput, manifestFile);
            
            // Mover a la raíz del proyecto
            File finalJar = new File("../logotec-vm.jar");
            if (jarOutput.exists()) {
                copiarArchivo(jarOutput, finalJar);
                System.out.println("✅ VM JAR creado: " + finalJar.getAbsolutePath());
            }
            
        } catch (Exception e) {
            System.err.println("⚠️  No se pudo construir el VM JAR automáticamente");
            System.err.println("   Ejecuta manualmente: build-vm.bat");
        }
    }
    
    private void copiarDirectorio(File origen, File destino) throws IOException {
        if (origen.isDirectory()) {
            if (!destino.exists()) {
                destino.mkdirs();
            }
            
            String[] archivos = origen.list();
            if (archivos != null) {
                for (String archivo : archivos) {
                    copiarDirectorio(
                        new File(origen, archivo),
                        new File(destino, archivo)
                    );
                }
            }
        } else {
            copiarArchivo(origen, destino);
        }
    }
    
    private void copiarArchivo(File origen, File destino) throws IOException {
        if (!destino.getParentFile().exists()) {
            destino.getParentFile().mkdirs();
        }
        
        try (java.io.FileInputStream fis = new java.io.FileInputStream(origen);
             java.io.FileOutputStream fos = new java.io.FileOutputStream(destino)) {
            byte[] buffer = new byte[8192];
            int length;
            while ((length = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, length);
            }
        }
    }
    
    private void crearJar(File baseDir, File jarFile, File manifestFile) throws IOException {
        try (java.util.jar.JarOutputStream jos = new java.util.jar.JarOutputStream(
                new java.io.FileOutputStream(jarFile),
                new java.util.jar.Manifest(new java.io.FileInputStream(manifestFile)))) {
            
            agregarAlJar(new File(baseDir, "com"), "com/", jos);
        }
    }
    
    private void agregarAlJar(File archivo, String rutaEnJar, java.util.jar.JarOutputStream jos) throws IOException {
        if (archivo.isDirectory()) {
            String[] archivos = archivo.list();
            if (archivos != null) {
                for (String nombre : archivos) {
                    agregarAlJar(
                        new File(archivo, nombre),
                        rutaEnJar + nombre + (new File(archivo, nombre).isDirectory() ? "/" : ""),
                        jos
                    );
                }
            }
        } else {
            java.util.jar.JarEntry entry = new java.util.jar.JarEntry(rutaEnJar);
            jos.putNextEntry(entry);
            
            try (java.io.FileInputStream fis = new java.io.FileInputStream(archivo)) {
                byte[] buffer = new byte[8192];
                int length;
                while ((length = fis.read(buffer)) > 0) {
                    jos.write(buffer, 0, length);
                }
            }
            
            jos.closeEntry();
        }
    }
}

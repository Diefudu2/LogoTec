package com.miorganizacion.logotec.interfaz.controller;

import com.miorganizacion.logotec.compilador.CompiladorRealAdapter;
import com.miorganizacion.logotec.compilador.ast.ProgramNode;
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
            
            // PASO 2: Generar IR (Código Intermedio)
            System.out.println("🔧 PASO 2: Generando código IR...");
            ASTtoIRTranslator irTranslator = new ASTtoIRTranslator();
            ASTtoIRTranslator.Result irResult = irTranslator.generate(ast);
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
            
            System.out.println("═══════════════════════════════════════════════════════");
            System.out.println("✅ COMPILACIÓN EXITOSA");
            System.out.println("   Variables declaradas: " + objResult.symbolTable.size());
            System.out.println("   Labels generados: " + objResult.labelTable.size());
            System.out.println("   Tamaño bytecode: " + (objResult.bytecode.size() * 4) + " bytes");
            System.out.println("═══════════════════════════════════════════════════════");
            
        } catch (RuntimeException ex) {
            System.err.println("❌ Error en la compilación:");
            String mensajeError = ex.getMessage();
            if (mensajeError != null && !mensajeError.isEmpty()) {
                System.err.println("   " + mensajeError);
            }
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
            // Verificar que hay bytecode compilado
            if (bytecodeCargado == null) {
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
            vm.loadProgram(bytecodeCargado);
            System.out.println("✅ Programa cargado: " + bytecodeCargado.bytecode.size() + " instrucciones\n");
            
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
}

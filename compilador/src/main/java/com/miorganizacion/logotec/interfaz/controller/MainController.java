package com.miorganizacion.logotec.interfaz.controller;

import com.miorganizacion.logotec.compilador.CompiladorRealAdapter;
import com.miorganizacion.logotec.compilador.ast.ProgramNode;
import com.miorganizacion.logotec.interfaz.modelo.AccionTortuga;
import com.miorganizacion.logotec.interfaz.modelo.Tortuga;
import com.miorganizacion.logotec.interfaz.ui.Botonera;
import com.miorganizacion.logotec.interfaz.ui.GestorArchivos;
import com.miorganizacion.logotec.interfaz.ui.ZonaDibujo;
import com.miorganizacion.logotec.interfaz.ui.ZonaEditor;
import com.miorganizacion.logotec.interfaz.ui.ZonaErrores;
import com.miorganizacion.logotec.simulador.EjecutorAST;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Arrays;
import java.util.List;

public class MainController {

    private final ZonaEditor zonaEditor;
    private final ZonaDibujo zonaDibujo;
    private final ZonaErrores zonaErrores;
    private final Botonera botonera;
    private final GestorArchivos gestorArchivos;

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

        try {
            CompiladorRealAdapter.compile(codigo);
            System.out.println("[MainController] Compilación exitosa.");
        } catch (RuntimeException ex) {
        	String mensaje = ex.getMessage() != null ? ex.getMessage() : "Error inesperado durante la ejecución.";
        	zonaErrores.mostrarErrores(Arrays.asList(mensaje));
        }
    }

    private void ejecutar() {
        zonaDibujo.limpiar();
        zonaErrores.limpiar();

        String codigo = zonaEditor.getCodigoFuente();

        try {
            // Compilar el código
            ProgramNode programa = CompiladorRealAdapter.compile(codigo);
            
            // Ejecutar el AST con el nuevo sistema
            List<AccionTortuga> acciones = EjecutorAST.ejecutar(programa);

            if (acciones.isEmpty()) {
                System.out.println("[MainController] No se generaron acciones.");
                return;
            }

            // Crear la tortuga visual (posición inicial centrada en el canvas)
            Tortuga tortuga = new Tortuga(150, 150);
            zonaDibujo.dibujarTortuga(tortuga.getX(), tortuga.getY(), tortuga.getAngulo());
            
            Timeline timeline = new Timeline();
            int delayMs = 500;

            for (int i = 0; i < acciones.size(); i++) {
                AccionTortuga accion = acciones.get(i);

                KeyFrame frame = new KeyFrame(Duration.millis((i + 1) * delayMs), e -> {
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

                    zonaDibujo.actualizarTortuga(tortuga.getX(), tortuga.getY(), tortuga.getAngulo());
                    System.out.println("[MainController] Ejecutando: " + accion);
                });

                timeline.getKeyFrames().add(frame);
            }

            timeline.setCycleCount(1);
            timeline.setOnFinished(e -> System.out.println("[MainController] Animación finalizada."));
            timeline.play();

        } catch (RuntimeException ex) {
            String mensaje = ex.getMessage() != null ? ex.getMessage() : "Error inesperado durante la ejecución.";
            zonaErrores.mostrarErrores(Arrays.asList(mensaje));
        }
    }
}

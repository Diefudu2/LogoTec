package com.logorduin.controller;

import com.logorduin.mock.CompiladorMock;
import com.logorduin.model.Trazo;
import com.logorduin.ui.*;
import javafx.stage.Stage;


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
        List<String> errores = CompiladorMock.simularCompilacion(codigo);

        if (!errores.isEmpty()) {
            zonaErrores.mostrarErrores(errores);
        }
    }

    private void ejecutar() {
        zonaDibujo.limpiar();

        String codigo = zonaEditor.getCodigoFuente();
        List<Trazo> trazos = CompiladorMock.simularEjecucion(codigo);

        for (Trazo trazo : trazos) {
            zonaDibujo.dibujarTriangulo(trazo.getX(), trazo.getY(), trazo.getTamaño());
        }
    }
}

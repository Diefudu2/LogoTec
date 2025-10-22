package com.miorganizacion.logotec.interfaz.controller;

import com.miorganizacion.logotec.interfaz.mock.CompiladorMock;

import com.miorganizacion.logotec.interfaz.modelo.Trazo;
import com.miorganizacion.logotec.interfaz.ui.Botonera;
import com.miorganizacion.logotec.interfaz.ui.GestorArchivos;
import com.miorganizacion.logotec.interfaz.ui.ZonaDibujo;
import com.miorganizacion.logotec.interfaz.ui.ZonaEditor;
import com.miorganizacion.logotec.interfaz.ui.ZonaErrores;
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

package com.miorganizacion.logotec.interfaz.ui;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.util.function.Consumer;

public class Botonera extends HBox {

    private final Button btnCompilar;
    private final Button btnEjecutar;

    private final Button btnCargar;
    private final Button btnGuardar;


    public Botonera() {
        btnCompilar = new Button("Compilar");
        btnEjecutar = new Button("Ejecutar");

        btnCargar = new Button("Cargar");
        btnGuardar = new Button("Guardar");
        this.getChildren().addAll(btnCargar, btnGuardar, btnCompilar, btnEjecutar);


        this.setSpacing(10);
        this.setStyle("-fx-padding: 10; -fx-alignment: center;");
        //this.getChildren().addAll(btnCompilar, btnEjecutar);
    }

    public void setOnCompilar(Consumer<Void> accion) {
        btnCompilar.setOnAction(e -> accion.accept(null));
    }

    public void setOnEjecutar(Consumer<Void> accion) {
        btnEjecutar.setOnAction(e -> accion.accept(null));
    }

    public void setOnCargar(Consumer<Void> accion) {
        btnCargar.setOnAction(e -> accion.accept(null));
    }

    public void setOnGuardar(Consumer<Void> accion) {
        btnGuardar.setOnAction(e -> accion.accept(null));
    }


    public Button getBtnCompilar() {
        return btnCompilar;
    }

    public Button getBtnEjecutar() {
        return btnEjecutar;
    }
}

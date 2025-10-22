package com.miorganizacion.logotec.interfaz.ui;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import java.util.List;

public class ZonaErrores extends VBox {

    private final ListView<String> listaErrores;

    public ZonaErrores() {
        Label titulo = new Label("Errores");
        listaErrores = new ListView<>();
        listaErrores.setPrefHeight(100);
        this.getChildren().addAll(titulo, listaErrores);
    }

    public void mostrarErrores(List<String> errores) {
        listaErrores.getItems().setAll(errores);
    }

    public void limpiar() {
        listaErrores.getItems().clear();
    }

    public ListView<String> getListView() {
        return listaErrores;
    }
}

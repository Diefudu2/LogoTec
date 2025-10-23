package com.miorganizacion.logotec.interfaz.ui;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

import java.util.List;

public class ZonaErrores extends VBox {

    private final TextArea consolaTexto;

    public ZonaErrores() {
        Label titulo = new Label("Consola de Salida");
        consolaTexto = new TextArea();
        consolaTexto.setPrefHeight(120);
        consolaTexto.setEditable(false); // Solo lectura
        consolaTexto.setWrapText(true);   // Ajustar texto
        consolaTexto.setStyle("-fx-font-family: 'Consolas', 'Courier New', monospace; -fx-font-size: 11px;");
        
        this.getChildren().addAll(titulo, consolaTexto);
    }

    public void mostrarErrores(List<String> errores) {
        consolaTexto.clear();
        for (String error : errores) {
            consolaTexto.appendText("❌ " + error + "\n");
        }
    }

    public void mostrarMensajes(List<String> mensajes) {
        consolaTexto.clear();
        for (String mensaje : mensajes) {
            consolaTexto.appendText(mensaje + "\n");
        }
    }

    public void agregarMensaje(String mensaje) {
        consolaTexto.appendText(mensaje + "\n");
    }

    public void limpiar() {
        consolaTexto.clear();
    }

    public TextArea getTextArea() {
        return consolaTexto;
    }
}

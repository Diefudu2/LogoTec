package com.miorganizacion.logotec.interfaz.ui;

import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public class ZonaEditor extends VBox {

    private final TextArea editor;

    public ZonaEditor() {
        editor = new TextArea();
        editor.setPromptText("Escribe tu código aquí...");
        editor.setPrefWidth(400);
        editor.setPrefHeight(300);
        this.getChildren().add(editor);
    }

    public String getCodigoFuente() {
        return editor.getText();
    }

    public void setCodigoFuente(String codigo) {
        editor.setText(codigo);
    }

    public TextArea getTextArea() {
        return editor;
    }
}

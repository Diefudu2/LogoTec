package com.miorganizacion.logotec.interfaz.ui;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class GestorArchivos {

    private final Stage stage;

    public GestorArchivos(Stage stage) {
        this.stage = stage;
    }

    public String cargarArchivo() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Cargar archivo de código");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos de texto", "*.txt"));
        File archivo = fileChooser.showOpenDialog(stage);

        if (archivo != null) {
            try (BufferedReader reader = new BufferedReader(new FileReader(archivo, StandardCharsets.UTF_8))) {
                StringBuilder contenido = new StringBuilder();
                String linea;
                while ((linea = reader.readLine()) != null) {
                    contenido.append(linea).append("\n");
                }
                return contenido.toString();
            } catch (IOException e) {
                return "Error al leer el archivo: " + e.getMessage();
            }
        }
        return "";
    }

    public void guardarArchivo(String contenido) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar archivo de código");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos de texto", "*.txt"));
        File archivo = fileChooser.showSaveDialog(stage);

        if (archivo != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, StandardCharsets.UTF_8))) {
                writer.write(contenido);
            } catch (IOException e) {
                System.out.println("Error al guardar el archivo: " + e.getMessage());
            }
        }
    }
}

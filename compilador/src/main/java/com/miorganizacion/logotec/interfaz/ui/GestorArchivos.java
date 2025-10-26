package com.miorganizacion.logotec.interfaz.ui;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class GestorArchivos {

    private final Stage stage;
    private String ultimoArchivoCargado = null;  // 🆕 Almacenar nombre del último archivo

    public GestorArchivos(Stage stage) {
        this.stage = stage;
    }

    public String cargarArchivo() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Cargar archivo de código");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos LogoTec", "*.logo", "*.txt"));
        File archivo = fileChooser.showOpenDialog(stage);

        if (archivo != null) {
            ultimoArchivoCargado = archivo.getName();  // 🆕 Guardar nombre
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(archivo), StandardCharsets.UTF_8))) {
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
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos LogoTec", "*.logo"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos de texto", "*.txt"));
        File archivo = fileChooser.showSaveDialog(stage);

        if (archivo != null) {
            ultimoArchivoCargado = archivo.getName();  // 🆕 Guardar nombre
            try (BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(archivo), StandardCharsets.UTF_8))) {
                writer.write(contenido);
            } catch (IOException e) {
                System.out.println("Error al guardar el archivo: " + e.getMessage());
            }
        }
    }
    
    /**
     * Obtiene el nombre del último archivo cargado/guardado
     * @return Nombre del archivo o null si no hay ninguno
     */
    public String getUltimoArchivoCargado() {
        return ultimoArchivoCargado;
    }
}

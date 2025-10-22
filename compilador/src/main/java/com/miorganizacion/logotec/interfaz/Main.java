package com.miorganizacion.logotec.interfaz;
/*
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        // 📝 Editor de código
        TextArea editor = new TextArea();
        editor.setPromptText("Escribe tu código aquí...");
        editor.setPrefWidth(400);

        // 🎨 Zona de dibujo
        Canvas canvas = new Canvas(300, 300);
        VBox zonaDibujo = new VBox(new Label("Zona de dibujo"), canvas);

        // ⚠️ Zona de errores
        ListView<String> zonaErrores = new ListView<>();
        zonaErrores.setPrefHeight(100);

        // 🧭 Botonera
        Button btnCompilar = new Button("Compilar");
        Button btnEjecutar = new Button("Ejecutar");
        HBox botonera = new HBox(10, btnCompilar, btnEjecutar);
        botonera.setStyle("-fx-padding: 10; -fx-alignment: center;");

        // 🧩 Layout principal
        BorderPane root = new BorderPane();
        root.setTop(botonera);
        root.setCenter(editor);
        root.setRight(zonaDibujo);
        root.setBottom(zonaErrores);

        // 🎬 Mostrar ventana
        Scene scene = new Scene(root, 800, 500);
        stage.setScene(scene);
        stage.setTitle("Logorduin - Interfaz Base");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
*/

import com.miorganizacion.logotec.interfaz.controller.MainController;
import com.miorganizacion.logotec.interfaz.ui.*;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        // 🧩 Instanciar módulos visuales
        ZonaEditor zonaEditor = new ZonaEditor();
        ZonaDibujo zonaDibujo = new ZonaDibujo();
        ZonaErrores zonaErrores = new ZonaErrores();
        Botonera botonera = new Botonera();

        // 🎯 Conectar controlador
        //new MainController(zonaEditor, zonaDibujo, zonaErrores, botonera);
        new MainController(zonaEditor, zonaDibujo, zonaErrores, botonera, stage);


        // 🧱 Layout principal
        BorderPane root = new BorderPane();
        root.setTop(botonera);
        root.setCenter(zonaEditor);
        root.setRight(zonaDibujo);
        root.setBottom(zonaErrores);

        // 🎬 Mostrar ventana
        Scene scene = new Scene(root, 800, 500);
        stage.setScene(scene);
        stage.setTitle("Logorduin - Interfaz Base");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

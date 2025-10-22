package com.miorganizacion.logotec.interfaz.ui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ZonaDibujo extends VBox {

    private final Canvas canvas;

    public ZonaDibujo() {
        canvas = new Canvas(300, 300);
        this.getChildren().add(canvas);
    }

    public void limpiar() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    public void dibujarTriangulo(double x, double y, double tamaño) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(2);

        double altura = Math.sqrt(3) / 2 * tamaño;
        double[] xPoints = {x, x + tamaño / 2, x - tamaño / 2};
        double[] yPoints = {y, y + altura, y + altura};

        gc.strokePolygon(xPoints, yPoints, 3);
    }

    public Canvas getCanvas() {
        return canvas;
    }
}

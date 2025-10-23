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

    public void dibujarLinea(double x1, double y1, double x2, double y2) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(2);
        gc.strokeLine(x1, y1, x2, y2);
    }

    public void dibujarTrianguloPorLineas(double x, double y, double tamaño) {
        double altura = Math.sqrt(3) / 2 * tamaño;
        double x1 = x;
        double y1 = y;
        double x2 = x + tamaño / 2;
        double y2 = y + altura;
        double x3 = x - tamaño / 2;
        double y3 = y + altura;

        dibujarLinea(x1, y1, x2, y2);
        dibujarLinea(x2, y2, x3, y3);
        dibujarLinea(x3, y3, x1, y1);
    }

    public void dibujarTortuga(double x, double y, double angulo) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.RED);
        gc.setStroke(Color.RED);
        gc.setLineWidth(1);

        double radio = 6;
        gc.fillOval(x - radio, y - radio, radio * 2, radio * 2);

        // Dibujar orientación como flecha
        double largo = 12;
        double rad = Math.toRadians(angulo);
        double x2 = x + largo * Math.cos(rad);
        double y2 = y + largo * Math.sin(rad);
        gc.strokeLine(x, y, x2, y2);
    }

    public void moverYTraza(double x1, double y1, double x2, double y2, boolean lapizAbajo, double angulo) {
        if (lapizAbajo) {
            dibujarLinea(x1, y1, x2, y2);
        }
        dibujarTortuga(x2, y2, angulo);
    }

    public void actualizarTortuga(double x, double y, double angulo) {
        dibujarTortuga(x, y, angulo);
    }

    public Canvas getCanvas() {
        return canvas;
    }
}

package com.miorganizacion.logotec.interfaz.modelo;

import javafx.scene.paint.Color;

public class Tortuga {

    private double x;
    private double y;
    private double angulo; // en grados, 0 = derecha
    private boolean lapizAbajo;
    private Color color;
    private double grosor;

    public Tortuga(double xInicial, double yInicial) {
        this.x = xInicial;
        this.y = yInicial;
        this.angulo = 0;
        this.lapizAbajo = true;
        this.color = Color.BLUE;
        this.grosor = 2.0;
    }

    // Movimiento absoluto
    public void moverA(double nuevoX, double nuevoY) {
        this.x = nuevoX;
        this.y = nuevoY;
    }

    // Movimiento relativo en dirección actual
    public void avanzar(double distancia) {
        double rad = Math.toRadians(angulo);
        this.x += distancia * Math.cos(rad);
        this.y += distancia * Math.sin(rad);
    }

    // Rotación
    public void girar(double grados) {
        this.angulo = (this.angulo + grados) % 360;
    }

    // Control del lápiz
    public void bajarLapiz() {
        this.lapizAbajo = true;
    }

    public void levantarLapiz() {
        this.lapizAbajo = false;
    }

    // Getters
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getAngulo() {
        return angulo;
    }

    public boolean isLapizAbajo() {
        return lapizAbajo;
    }

    public Color getColor() {
        return color;
    }

    public double getGrosor() {
        return grosor;
    }

    // Setters opcionales
    public void setColor(Color color) {
        this.color = color;
    }

    public void setGrosor(double grosor) {
        this.grosor = grosor;
    }
}

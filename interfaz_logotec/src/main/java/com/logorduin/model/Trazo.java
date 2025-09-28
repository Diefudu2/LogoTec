package com.logorduin.model;

public class Trazo {

    private final double x;
    private final double y;
    private final double tamaño;

    public Trazo(double x, double y, double tamaño) {
        this.x = x;
        this.y = y;
        this.tamaño = tamaño;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getTamaño() {
        return tamaño;
    }
}

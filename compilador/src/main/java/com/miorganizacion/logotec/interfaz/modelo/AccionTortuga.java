package com.miorganizacion.logotec.interfaz.modelo;

public class AccionTortuga {

    public enum Tipo {
        AVANZAR,
        GIRAR,
        BAJAR_LAPIZ,
        LEVANTAR_LAPIZ
        // Puedes agregar más: PONPOS, ESPERA, etc.
    }

    private final Tipo tipo;
    private final double valor; // usado para AVANZAR y GIRAR

    public AccionTortuga(Tipo tipo, double valor) {
        this.tipo = tipo;
        this.valor = valor;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public double getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return "AccionTortuga{" +
                "tipo=" + tipo +
                ", valor=" + valor +
                '}';
    }
}

package com.miorganizacion.logotec.interfaz.modelo;

/**
 * Representa una acción que la tortuga debe ejecutar.
 */
public class AccionTortuga {
    
    public enum Tipo {
        AVANZAR,
        RETROCEDER,
        GIRAR,
        GIRAR_DERECHA,
        GIRAR_IZQUIERDA,
        BAJAR_LAPIZ,
        LEVANTAR_LAPIZ,
        SUBIR_LAPIZ,        // Alias de LEVANTAR_LAPIZ
        CAMBIAR_COLOR,
        MOVER_A_POSICION,
        CAMBIAR_RUMBO,
        CENTRAR,
        OCULTAR_TORTUGA,
        MOSTRAR_TORTUGA
    }
    
    private final Tipo tipo;
    private final double valor;
    private final double valor2;  // Para posición Y o color G
    private final double valor3;  // Para color B
    
    // Constructor para acciones con un valor (avanzar, girar)
    public AccionTortuga(Tipo tipo, double valor) {
        this.tipo = tipo;
        this.valor = valor;
        this.valor2 = 0;
        this.valor3 = 0;
    }
    
    // Constructor para acciones con dos valores (mover a posición)
    public AccionTortuga(Tipo tipo, double valor1, double valor2) {
        this.tipo = tipo;
        this.valor = valor1;
        this.valor2 = valor2;
        this.valor3 = 0;
    }
    
    // Constructor para acciones con tres valores (cambiar color RGB)
    public AccionTortuga(Tipo tipo, double r, double g, double b) {
        this.tipo = tipo;
        this.valor = r;
        this.valor2 = g;
        this.valor3 = b;
    }
    
    public Tipo getTipo() {
        return tipo;
    }
    
    public double getValor() {
        return valor;
    }
    
    public double getValor2() {
        return valor2;
    }
    
    public double getValor3() {
        return valor3;
    }
    
    // Alias para claridad
    public double getX() { return valor; }
    public double getY() { return valor2; }
    public int getR() { return (int) valor; }
    public int getG() { return (int) valor2; }
    public int getB() { return (int) valor3; }
    
    @Override
    public String toString() {
        switch (tipo) {
            case CAMBIAR_COLOR:
                return tipo + " - RGB(" + (int)valor + ", " + (int)valor2 + ", " + (int)valor3 + ")";
            case MOVER_A_POSICION:
                return tipo + " - (" + valor + ", " + valor2 + ")";
            default:
                return tipo + " - " + valor;
        }
    }
}

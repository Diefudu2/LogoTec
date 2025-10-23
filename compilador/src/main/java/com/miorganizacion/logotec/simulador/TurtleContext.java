package com.miorganizacion.logotec.simulador;

import com.miorganizacion.logotec.interfaz.modelo.AccionTortuga;
import com.miorganizacion.logotec.interfaz.modelo.AccionTortuga.Tipo;

import java.util.ArrayList;
import java.util.List;

/**
 * Contexto de ejecución que mantiene el estado de la tortuga
 * y genera las acciones de dibujo durante la ejecución del AST.
 */
public class TurtleContext {
    
    // Estado actual de la tortuga
    private double x;
    private double y;
    private double angulo; // en grados
    private boolean lapizAbajo;
    
    // Lista de acciones generadas
    private final List<AccionTortuga> acciones;
    
    public TurtleContext() {
        this.x = 0;
        this.y = 0;
        this.angulo = 0;
        this.lapizAbajo = true;
        this.acciones = new ArrayList<>();
    }
    
    // Comandos de movimiento
    public void avanzar(double distancia) {
        System.out.println("  [TurtleContext] AVANZAR " + distancia);
        acciones.add(new AccionTortuga(Tipo.AVANZAR, distancia));
        
        // Actualizar posición interna
        double rad = Math.toRadians(angulo);
        x += distancia * Math.cos(rad);
        y += distancia * Math.sin(rad);
    }
    
    public void retroceder(double distancia) {
        System.out.println("  [TurtleContext] RETROCEDER " + distancia);
        avanzar(-distancia);
    }
    
    public void girarDerecha(double grados) {
        System.out.println("  [TurtleContext] GIRAR_DERECHA " + grados);
        acciones.add(new AccionTortuga(Tipo.GIRAR, grados));
        angulo = (angulo + grados) % 360;
    }
    
    public void girarIzquierda(double grados) {
        System.out.println("  [TurtleContext] GIRAR_IZQUIERDA " + grados);
        girarDerecha(-grados);
    }
    
    // Control del lápiz
    public void bajarLapiz() {
        System.out.println("  [TurtleContext] BAJAR_LAPIZ");
        if (!lapizAbajo) {
            acciones.add(new AccionTortuga(Tipo.BAJAR_LAPIZ, 0));
            lapizAbajo = true;
        }
    }
    
    public void subirLapiz() {
        System.out.println("  [TurtleContext] SUBIR_LAPIZ");
        if (lapizAbajo) {
            acciones.add(new AccionTortuga(Tipo.LEVANTAR_LAPIZ, 0));
            lapizAbajo = false;
        }
    }
    
    public void centrar() {
        System.out.println("  [TurtleContext] CENTRAR");
        // Mover al centro sin dibujar
        x = 0;
        y = 0;
        angulo = 0;
        // Podríamos agregar una acción especial para centrar visualmente
    }
    
    public void ocultarTortuga() {
        System.out.println("  [TurtleContext] OCULTAR_TORTUGA");
        // Implementar si es necesario
    }
    
    public void esperar(double milisegundos) {
        System.out.println("  [TurtleContext] ESPERAR " + milisegundos + "ms");
        // Implementar si es necesario
    }
    
    // Getters
    public List<AccionTortuga> getAcciones() {
        return acciones;
    }
    
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
}

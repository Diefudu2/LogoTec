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
    private boolean visible;
    
    // Estado del color del lápiz (RGB)
    private int colorR;
    private int colorG;
    private int colorB;
    
    // Lista de acciones generadas
    private final List<AccionTortuga> acciones;
    
    public TurtleContext() {
        this.x = 0;
        this.y = 0;
        this.angulo = 0;
        this.lapizAbajo = true;
        this.visible = true;
        // Color por defecto: negro
        this.colorR = 0;
        this.colorG = 0;
        this.colorB = 0;
        this.acciones = new ArrayList<AccionTortuga>();
    }
    
    // ==================== COMANDOS DE MOVIMIENTO ====================
    
    public void avanzar(double distancia) {
        System.out.println("  [TurtleContext] AVANZAR " + distancia + " (lapiz=" + (lapizAbajo ? "ABAJO" : "ARRIBA") + ")");
        acciones.add(new AccionTortuga(Tipo.AVANZAR, distancia));
        
        // Actualizar posición interna
        double rad = Math.toRadians(angulo);
        double oldX = x, oldY = y;
        x += distancia * Math.cos(rad);
        y += distancia * Math.sin(rad);
        System.out.println("    Posición: (" + oldX + ", " + oldY + ") → (" + x + ", " + y + ")");
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
    
    // ==================== CONTROL DEL LÁPIZ ====================
    
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
    
    // ==================== POSICIÓN ====================
    
    public void centrar() {
        System.out.println("  [TurtleContext] CENTRAR");
        x = 0;
        y = 0;
        angulo = 0;
        acciones.add(new AccionTortuga(Tipo.MOVER_A_POSICION, 0, 0));
    }
    
    /**
     * Mueve la tortuga a una posición específica (PONPOS / PONXY)
     */
    public void setPosicion(double newX, double newY) {
        System.out.println("  [TurtleContext] SET_POSICION (" + newX + ", " + newY + ")");
        this.x = newX;
        this.y = newY;
        acciones.add(new AccionTortuga(Tipo.MOVER_A_POSICION, newX, newY));
    }
    
    /**
     * Establece solo la coordenada X (PONX)
     */
    public void setPosX(double newX) {
        System.out.println("  [TurtleContext] SET_POS_X " + newX);
        this.x = newX;
        acciones.add(new AccionTortuga(Tipo.MOVER_A_POSICION, newX, this.y));
    }
    
    /**
     * Establece solo la coordenada Y (PONY)
     */
    public void setPosY(double newY) {
        System.out.println("  [TurtleContext] SET_POS_Y " + newY);
        this.y = newY;
        acciones.add(new AccionTortuga(Tipo.MOVER_A_POSICION, this.x, newY));
    }
    
    // ==================== RUMBO ====================
    
    /**
     * Establece el ángulo/rumbo de la tortuga (PONRUMBO)
     */
    public void setRumbo(double nuevoAngulo) {
        System.out.println("  [TurtleContext] SET_RUMBO " + nuevoAngulo);
        this.angulo = nuevoAngulo % 360;
        acciones.add(new AccionTortuga(Tipo.CAMBIAR_RUMBO, nuevoAngulo));
    }
    
    /**
     * Establece el color del lápiz por nombre.
     * Solo permite 3 colores: Negro, Azul, Rojo
     */
    public void setColorPorNombre(String nombreColor) {
        System.out.println("  [TurtleContext] SET_COLOR_NOMBRE " + nombreColor);
        
        String colorLower = nombreColor.toLowerCase();
        switch (colorLower) {
            case "negro":
                setColor(0, 0, 0);
                break;
            case "azul":
                setColor(0, 0, 255);
                break;
            case "rojo":
                setColor(255, 0, 0);
                break;
            default:
                System.err.println("ERROR: Color no permitido: '" + nombreColor + "'");
                throw new RuntimeException("Color no permitido: '" + nombreColor + "'. Solo se permiten: Negro, Azul, Rojo");
        }
    }

    // ==================== COLOR ====================
    
    /**
     * Establece el color del lápiz en RGB
     */
    public void setColor(int r, int g, int b) {
        System.out.println("  [TurtleContext] SET_COLOR RGB(" + r + ", " + g + ", " + b + ")");
        this.colorR = clampColor(r);
        this.colorG = clampColor(g);
        this.colorB = clampColor(b);
        acciones.add(new AccionTortuga(Tipo.CAMBIAR_COLOR, colorR, colorG, colorB));
        System.out.println("    Acción de color agregada: " + acciones.get(acciones.size() - 1));
    }
    
    /**
     * Asegura que el valor de color esté entre 0 y 255
     */
    private int clampColor(int value) {
        if (value < 0) return 0;
        if (value > 255) return 255;
        return value;
    }
    
    // ==================== VISIBILIDAD ====================
    
    public void ocultarTortuga() {
        System.out.println("  [TurtleContext] OCULTAR_TORTUGA");
        visible = false;
    }
    
    public void mostrarTortuga() {
        System.out.println("  [TurtleContext] MOSTRAR_TORTUGA");
        visible = true;
    }
    
    // ==================== UTILIDADES ====================
    
    public void esperar(double milisegundos) {
        System.out.println("  [TurtleContext] ESPERAR " + milisegundos + "ms");
        // Podría agregar una acción de espera si el renderizador lo soporta
    }
    
    // ==================== GETTERS ====================
    
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
    
    public boolean isVisible() {
        return visible;
    }
    
    public int getColorR() {
        return colorR;
    }
    
    public int getColorG() {
        return colorG;
    }
    
    public int getColorB() {
        return colorB;
    }
}

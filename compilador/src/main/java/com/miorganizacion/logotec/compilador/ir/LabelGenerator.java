package com.miorganizacion.logotec.compilador.ir;

/**
 * Generador de etiquetas únicas para control de flujo.
 */
public class LabelGenerator {
    private int counter = 0;
    
    /**
     * Genera una nueva etiqueta única con prefijo.
     */
    public String next(String prefix) {
        return "L" + (counter++) + "_" + prefix;
    }
    
    /**
     * Genera una nueva etiqueta única sin prefijo.
     */
    public String next() {
        return "L" + (counter++);
    }
    
    /**
     * Reinicia el contador.
     */
    public void reset() {
        counter = 0;
    }
    
    /**
     * Obtiene el contador actual (para debugging).
     */
    public int getCounter() {
        return counter;
    }
}

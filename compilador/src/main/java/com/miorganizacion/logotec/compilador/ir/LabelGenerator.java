package com.miorganizacion.logotec.compilador.ir;

/**
 * Generador de etiquetas únicas para control de flujo.
 */
public class LabelGenerator {
    private int counter = 0;
    private final String defaultPrefix;

    public LabelGenerator() {
        this("L");
    }

    public LabelGenerator(String defaultPrefix) {
        this.defaultPrefix = defaultPrefix;
    }

    /**
     * Genera una nueva etiqueta única con prefijo.
     */
    public String next() {
        return next(defaultPrefix);
    }

    public String next(String prefix) {
        return prefix + counter++;
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

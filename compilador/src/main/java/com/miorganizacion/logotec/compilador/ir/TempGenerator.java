package com.miorganizacion.logotec.compilador.ir;

/**
 * Generador de temporales únicos para IR.
 */
public class TempGenerator {
    private int counter = 0;
    private final String prefix;

    public TempGenerator() {
        this("t");
    }

    public TempGenerator(String prefix) {
        this.prefix = prefix;
    }

    /**
     * Genera un nuevo nombre de temporal.
     */
    public String next() {
        return prefix + counter++;
    }

    /**
     * Genera un nuevo Operand temporal.
     */
    public Operand nextOperand() {
        return Operand.temp(next());
    }

    /**
     * Reinicia el contador.
     */
    public void reset() {
        counter = 0;
    }

    /**
     * Obtiene el contador actual.
     */
    public int getCounter() {
        return counter;
    }
}

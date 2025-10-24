package com.miorganizacion.logotec.compilador.ir;

/**
 * Generador de nombres únicos para variables temporales.
 * Genera nombres en secuencia: t1, t2, t3, t4...
 * 
 * Es thread-safe y puede reiniciarse cuando sea necesario.
 */
public class TempGenerator {
    
    private int counter;
    private final String prefix;
    
    /**
     * Constructor con prefijo por defecto "t"
     */
    public TempGenerator() {
        this("t");
    }
    
    /**
     * Constructor con prefijo personalizado
     * @param prefix Prefijo para los nombres de temporales (ej: "temp", "t", "$t")
     */
    public TempGenerator(String prefix) {
        this.prefix = prefix;
        this.counter = 0;
    }
    
    /**
     * Genera el siguiente temporal único.
     * @return Nombre del temporal (ej: "t1", "t2", etc.)
     */
    public synchronized String next() {
        counter++;
        return prefix + counter;
    }
    
    /**
     * Genera el siguiente temporal como un Operand.
     * @return Operando de tipo TEMPORARY
     */
    public synchronized Operand nextOperand() {
        return Operand.temp(next());
    }
    
    /**
     * Obtiene el contador actual (número de temporales generados)
     */
    public int getCount() {
        return counter;
    }
    
    /**
     * Reinicia el contador a 0
     */
    public synchronized void reset() {
        counter = 0;
    }
    
    /**
     * Obtiene el último temporal generado sin incrementar el contador.
     * @return Nombre del último temporal, o null si no se ha generado ninguno
     */
    public String current() {
        if (counter == 0) {
            return null;
        }
        return prefix + counter;
    }
    
    /**
     * Obtiene el último temporal generado como Operand.
     */
    public Operand currentOperand() {
        String current = current();
        if (current == null) {
            throw new IllegalStateException("No se ha generado ningún temporal aún");
        }
        return Operand.temp(current);
    }
    
    @Override
    public String toString() {
        return "TempGenerator{prefix='" + prefix + "', counter=" + counter + "}";
    }
}

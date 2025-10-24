package com.miorganizacion.logotec.compilador.ir;

/**
 * Generador de nombres únicos para etiquetas (labels) en el código IR.
 * Genera nombres en secuencia: L1, L2, L3, L4...
 * 
 * Las etiquetas se usan para marcar puntos de salto en estructuras de control:
 * - Loops (while, do-while, for)
 * - Condicionales (if-then-else)
 * - Saltos incondicionales
 * 
 * Es thread-safe y puede reiniciarse cuando sea necesario.
 */
public class LabelGenerator {
    
    private int counter;
    private final String prefix;
    
    /**
     * Constructor con prefijo por defecto "L"
     */
    public LabelGenerator() {
        this("L");
    }
    
    /**
     * Constructor con prefijo personalizado
     * @param prefix Prefijo para las etiquetas (ej: "L", "label", ".L")
     */
    public LabelGenerator(String prefix) {
        this.prefix = prefix;
        this.counter = 0;
    }
    
    /**
     * Genera la siguiente etiqueta única.
     * @return Nombre de la etiqueta (ej: "L1", "L2", etc.)
     */
    public synchronized String next() {
        counter++;
        return prefix + counter;
    }
    
    /**
     * Genera la siguiente etiqueta como un Operand.
     * @return Operando de tipo LABEL
     */
    public synchronized Operand nextOperand() {
        return Operand.label(next());
    }
    
    /**
     * Genera una etiqueta con un nombre descriptivo.
     * Ejemplo: next("loop_start") -> "L1_loop_start"
     * 
     * @param description Descripción para hacer el código más legible
     * @return Etiqueta con descripción
     */
    public synchronized String next(String description) {
        counter++;
        return prefix + counter + "_" + description;
    }
    
    /**
     * Genera una etiqueta descriptiva como Operand.
     */
    public synchronized Operand nextOperand(String description) {
        return Operand.label(next(description));
    }
    
    /**
     * Obtiene el contador actual (número de etiquetas generadas)
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
     * Obtiene la última etiqueta generada sin incrementar el contador.
     * @return Nombre de la última etiqueta, o null si no se ha generado ninguna
     */
    public String current() {
        if (counter == 0) {
            return null;
        }
        return prefix + counter;
    }
    
    /**
     * Obtiene la última etiqueta generada como Operand.
     */
    public Operand currentOperand() {
        String current = current();
        if (current == null) {
            throw new IllegalStateException("No se ha generado ninguna etiqueta aún");
        }
        return Operand.label(current);
    }
    
    @Override
    public String toString() {
        return "LabelGenerator{prefix='" + prefix + "', counter=" + counter + "}";
    }
}

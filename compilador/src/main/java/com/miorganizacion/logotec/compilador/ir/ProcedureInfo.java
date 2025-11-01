package com.miorganizacion.logotec.compilador.ir;

/**
 * Información sobre un procedimiento para la generación de IR.
 */
public class ProcedureInfo {
    private final String name;
    private final int paramCount;
    private String label;
    
    public ProcedureInfo(String name, int paramCount) {
        this.name = name;
        this.paramCount = paramCount;
        this.label = "proc_" + name;
    }
    
    public String getName() {
        return name;
    }
    
    public int getParamCount() {
        return paramCount;
    }

    public int getParameters() {
        return paramCount;
    }
    
    public String getLabel() {
        return label;
    }
    
    public void setLabel(String label) {
        this.label = label;
    }
    
    @Override
    public String toString() {
        return "ProcedureInfo{name='" + name + "', params=" + paramCount + ", label='" + label + "'}";
    }
}

package com.miorganizacion.logotec.compilador.ir;

import java.util.List;

/**
 * Información sobre un procedimiento declarado en el programa.
 * Utilizado por ASTtoIRTranslator para gestionar declaraciones y llamadas.
 */
public class ProcedureInfo {
    private final String name;
    private final List<String> parameters;
    private final String label;
    
    public ProcedureInfo(String name, List<String> parameters, String label) {
        this.name = name;
        this.parameters = parameters;
        this.label = label;
    }
    
    public String getName() {
        return name;
    }
    
    public List<String> getParameters() {
        return parameters;
    }
    
    public String getLabel() {
        return label;
    }
    
    public int getParameterCount() {
        return parameters.size();
    }
    
    public int getParameterIndex(String paramName) {
        return parameters.indexOf(paramName);
    }
    
    @Override
    public String toString() {
        return "Procedure{" + name + "(" + String.join(", ", parameters) + ") @ " + label + "}";
    }
}

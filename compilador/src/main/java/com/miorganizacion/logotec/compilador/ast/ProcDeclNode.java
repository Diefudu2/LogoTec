package com.miorganizacion.logotec.compilador.ast;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

public class ProcDeclNode implements ASTNode {
    private final String name;
    private final List<String> params;
    private final List<StmtNode> body;

    public ProcDeclNode(String name, List<String> params, List<StmtNode> body) {
        this.name = name;
        this.params = params;
        this.body = body;
    }
    
    public String getName() { return name; }
    public List<String> getParams() { return params; }
    public List<StmtNode> getBody() { return body; }

    @Override
    public Object execute(Map<String,Object> st) {
        // Registrar el procedimiento en la tabla de símbolos
        System.out.println("[ProcDeclNode] Registrando procedimiento: " + name + " con " + params.size() + " parámetros");
        st.put(name, this);
        return null;
    }

    public Object call(Map<String,Object> st, List<Object> args) {
        System.out.println("[ProcDeclNode] Ejecutando " + name + " con args: " + args);
        
        if (args.size() != params.size()) {
            throw new RuntimeException("Procedimiento '" + name + "' espera " + params.size()
                                       + " argumento(s), recibió " + args.size());
        }

        // Guardar valores anteriores de los parámetros (si existían)
        Map<String,Object> previous = new HashMap<>();
        Set<String> newParams = new HashSet<>();

        for (int i = 0; i < params.size(); i++) {
            String paramName = params.get(i);
            Object argValue = args.get(i);
            
            System.out.println("  Asignando parámetro '" + paramName + "' = " + argValue);
            
            // Guardar valor anterior si existe
            if (st.containsKey(paramName)) {
                previous.put(paramName, st.get(paramName));
            } else {
                newParams.add(paramName);
            }
            
            // Asignar el valor del argumento al parámetro
            st.put(paramName, argValue);
        }

        try {
            // Ejecutar el cuerpo del procedimiento
            System.out.println("[ProcDeclNode] Ejecutando cuerpo de " + name + " (" + body.size() + " sentencias)");
            for (int i = 0; i < body.size(); i++) {
                StmtNode stmt = body.get(i);
                if (stmt != null) {
                    System.out.println("  [" + name + "] Sentencia " + (i+1) + ": " + stmt.getClass().getSimpleName());
                    stmt.execute(st);
                }
            }
            System.out.println("[ProcDeclNode] Finalizó " + name);
        } finally {
            // Restaurar valores anteriores de los parámetros
            for (String paramName : params) {
                if (previous.containsKey(paramName)) {
                    st.put(paramName, previous.get(paramName));
                } else if (newParams.contains(paramName)) {
                    st.remove(paramName);
                }
            }
        }
        return null;
    }
}

package com.miorganizacion.logotec.simulador;

import com.miorganizacion.logotec.compilador.ast.ProgramNode;
import com.miorganizacion.logotec.interfaz.modelo.AccionTortuga;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Ejecutor mejorado que usa el método execute() del AST
 * con un TurtleContext para generar las acciones de dibujo.
 */
public class EjecutorAST {

    public static List<AccionTortuga> ejecutar(ProgramNode programa) {
        System.out.println("[EjecutorAST] Iniciando ejecución del programa...");
        
        // Validar que el programa no sea null
        if (programa == null) {
            System.err.println("[EjecutorAST] Error: El programa es null. El compilador no generó un AST válido.");
            throw new RuntimeException("El programa compilado es null. Verifica que el código fuente sea válido.");
        }
        
        // Crear el contexto de la tortuga
        TurtleContext turtleContext = new TurtleContext();
        
        // Crear la tabla de símbolos
        Map<String, Object> symbolTable = new HashMap<>();
        
        // Agregar el contexto de tortuga a la tabla de símbolos
        symbolTable.put("__turtle__", turtleContext);
        
        try {
            // Ejecutar el programa usando el método execute() del AST
            programa.execute(symbolTable);
            
            System.out.println("[EjecutorAST] Ejecución completada exitosamente.");
            System.out.println("[EjecutorAST] Total de acciones generadas: " + turtleContext.getAcciones().size());
            
        } catch (Exception e) {
            System.err.println("[EjecutorAST] Error durante la ejecución: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error en la ejecución del programa: " + e.getMessage(), e);
        }
        
        // Retornar las acciones generadas
        return turtleContext.getAcciones();
    }
}

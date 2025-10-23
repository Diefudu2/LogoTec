package com.miorganizacion.logotec.simulador;

import com.miorganizacion.logotec.compilador.ast.*;
import com.miorganizacion.logotec.interfaz.modelo.AccionTortuga;
import com.miorganizacion.logotec.interfaz.modelo.AccionTortuga.Tipo;

import java.util.ArrayList;
import java.util.List;

public class TraductorASTaSimulacion {

    public static List<AccionTortuga> traducir(ProgramNode programa) {
        System.out.println("[TraductorASTaSimulacion] Traduciendo AST a acciones de tortuga...");
        List<AccionTortuga> acciones = new ArrayList<>();

        for (StmtNode stmt : programa.getBody()) {
            if (stmt instanceof ForwardNode) {
                double distancia = extraerValor(((ForwardNode) stmt).getExpr());
                acciones.add(new AccionTortuga(Tipo.AVANZAR, distancia));
                System.out.println("  → AVANZAR " + distancia);
            } else if (stmt instanceof TurnRightNode) {
                double grados = extraerValor(((TurnRightNode) stmt).getExpr());
                acciones.add(new AccionTortuga(Tipo.GIRAR, grados));
                System.out.println("  → GIRAR " + grados);
            } else if (stmt instanceof PenDownNode) {
                acciones.add(new AccionTortuga(Tipo.BAJAR_LAPIZ, 0));
                System.out.println("  → BAJAR_LAPIZ");
            } else if (stmt instanceof PenUpNode) {
                acciones.add(new AccionTortuga(Tipo.LEVANTAR_LAPIZ, 0));
                System.out.println("  → LEVANTAR_LAPIZ");
            } else {
                System.out.println("  ⚠ Instrucción no reconocida: " + stmt.getClass().getSimpleName());
            }
        }

        System.out.println("[TraductorASTaSimulacion] Total de acciones: " + acciones.size());
        return acciones;
    }

    private static double extraerValor(ExprNode expr) {
        if (expr instanceof ConstNode) {
            Object val = ((ConstNode) expr).getValue();
            if (val instanceof Number) {
                return ((Number) val).doubleValue();
            }
        }
        System.out.println("  ⚠ Valor no constante o no numérico, usando 0 por defecto.");
        return 0;
    }
}

package com.miorganizacion.logotec.interfaz.mock;

import com.miorganizacion.logotec.interfaz.modelo.Trazo;
import com.miorganizacion.logotec.interfaz.modelo.Linea;

import java.util.ArrayList;
import java.util.List;

public class CompiladorMock {

    public static List<String> simularCompilacion(String codigo) {
        List<String> errores = new ArrayList<>();

        if (codigo == null || codigo.trim().isEmpty()) {
            errores.add("El código está vacío.");
        }

        if (!codigo.contains("dibujar")) {
            errores.add("No se encontró ninguna instrucción 'dibujar'.");
        }

        return errores;
    }

    public static List<Trazo> simularEjecucion(String codigo) {
        List<Trazo> trazos = new ArrayList<>();

        // Simulación simple: por cada línea que contenga "dibujar", genera un trazo
        String[] lineas = codigo.split("\\n");
        double x = 50, y = 50, tamaño = 40;

        for (String linea : lineas) {
            if (linea.contains("dibujar")) {
                trazos.add(new Trazo(x, y, tamaño));
                x += 60; // desplazamiento horizontal
            }
        }

        return trazos;
    }

    public static List<Linea> simularEjecucionPorLineas(String codigo) {
        List<Linea> lineas = new ArrayList<>();

        String[] instrucciones = codigo.split("\\n");
        double x = 50, y = 50, tamaño = 40;

        for (String instruccion : instrucciones) {
            if (instruccion.contains("dibujar")) {
                double altura = Math.sqrt(3) / 2 * tamaño;

                double x1 = x;
                double y1 = y;
                double x2 = x + tamaño / 2;
                double y2 = y + altura;
                double x3 = x - tamaño / 2;
                double y3 = y + altura;

                lineas.add(new Linea(x1, y1, x2, y2)); // lado 1
                lineas.add(new Linea(x2, y2, x3, y3)); // lado 2
                lineas.add(new Linea(x3, y3, x1, y1)); // lado 3

                x += 60; // desplazamiento horizontal para el siguiente triángulo
            }
        }

        return lineas;
    }
}

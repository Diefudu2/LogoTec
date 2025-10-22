package com.miorganizacion.logotec.interfaz.mock;

import com.miorganizacion.logotec.interfaz.modelo.Trazo;

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
}

package com.bancodebogota.service;

import com.bancodebogota.dto.SolicitudMigracion;
import com.bancodebogota.dto.RespuestaMigracion;
import com.bancodebogota.engine.rules.ReglaAsignacion;
import com.bancodebogota.engine.rules.ReglaMigracion;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class ServicioMigracion {

    private final List<ReglaMigracion> reglas;

    // escaneo de componentes que implementan la interfaz ReglaMigracion
    public ServicioMigracion(List<ReglaMigracion> reglas) {
        this.reglas = reglas;
    }

    public RespuestaMigracion procesarMigracion(SolicitudMigracion solicitud) {
        String codigoLegado = solicitud.codigoLegado();
        if (codigoLegado == null || codigoLegado.isEmpty()) {
            return new RespuestaMigracion("", List.of(), List.of());
        }

        String[] lineas = codigoLegado.split("\n");
        StringBuilder codigoModerno = new StringBuilder();
        Set<String> reglasAplicadas = new LinkedHashSet<>();
        List<String> advertencias = new ArrayList<>();

        for (String linea : lineas) {
            String lineaActual = linea.replace("\r", "");
            if (lineaActual.trim().isEmpty()) {
                codigoModerno.append(lineaActual).append("\n");
                continue;
            }

            boolean reglaCoincideExceptoAsignacion = false;
            // se aplica la regla de asignacion para limpiar el numero de linea
            for (ReglaMigracion regla : reglas) {
                if (regla instanceof ReglaAsignacion && regla.coincide(lineaActual)) {
                    lineaActual = regla.transformar(lineaActual);
                    reglasAplicadas.add(regla.obtenerNombreRegla());
                    break;
                }
            }

            if (lineaActual.trim().isEmpty()) {
                codigoModerno.append(lineaActual).append("\n");
                continue;
            }

            // se iteran las reglas
            for (ReglaMigracion regla : reglas) {
                if (!(regla instanceof ReglaAsignacion) && regla.coincide(lineaActual)) {
                    lineaActual = regla.transformar(lineaActual);
                    reglasAplicadas.add(regla.obtenerNombreRegla());
                    reglaCoincideExceptoAsignacion = true;
                }
            }

            if (!reglaCoincideExceptoAsignacion) {
                advertencias.add("No se pudo traducir la línea: " + lineaActual.trim());
            }

            codigoModerno.append(lineaActual).append("\n");
        }

        // Eliminar el último salto de línea para mantener exactamente la estructura
        String codigoFinal = codigoModerno.toString();
        if (codigoFinal.endsWith("\n")) {
            codigoFinal = codigoFinal.substring(0, codigoFinal.length() - 1);
        }

        return new RespuestaMigracion(codigoFinal, new ArrayList<>(reglasAplicadas), advertencias);
    }
}

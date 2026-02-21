package com.bancodebogota.engine.rules;

public interface ReglaMigracion {
    boolean coincide(String linea);

    String transformar(String linea);

    String obtenerNombreRegla();
}

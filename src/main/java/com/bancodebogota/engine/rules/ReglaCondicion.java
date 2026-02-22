package com.bancodebogota.engine.rules;

import org.springframework.stereotype.Component;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ReglaCondicion implements ReglaMigracion {
    private static final Pattern PATTERN = Pattern.compile("^(.*?)(?i)IF\\s+(.*?)(?:\\s+(?i)THEN|\\.)?$");

    @Override
    public boolean coincide(String linea) {
        return PATTERN.matcher(linea).matches();
    }

    @Override
    public String transformar(String linea) {
        Matcher matcher = PATTERN.matcher(linea);
        if (matcher.matches()) {
            String indent = matcher.group(1);
            String condition = matcher.group(2).trim();

            condition = condition.replace("'", "\"");

            return indent + "if (" + condition.toLowerCase() + ") {";
        }
        return linea;
    }

    @Override
    public String obtenerNombreRegla() {
        return "ReglaCondicion";
    }
}

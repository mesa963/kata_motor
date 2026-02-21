package com.bancodebogota.engine.rules;

import org.springframework.stereotype.Component;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ReglaFinBloque implements ReglaMigracion {
    private static final Pattern PATTERN = Pattern.compile("^(.*?)(?i)END-IF\\.?$");

    @Override
    public boolean coincide(String linea) {
        return PATTERN.matcher(linea).matches();
    }

    @Override
    public String transformar(String linea) {
        Matcher matcher = PATTERN.matcher(linea);
        if (matcher.matches()) {
            String indent = matcher.group(1);
            return indent + "}";
        }
        return linea;
    }

    @Override
    public String obtenerNombreRegla() {
        return "ReglaFinBloque";
    }
}

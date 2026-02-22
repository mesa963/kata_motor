package com.bancodebogota.engine.rules;

import org.springframework.stereotype.Component;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ReglaWhen implements ReglaMigracion {
    // Busca WHEN <valor>
    private static final Pattern PATTERN = Pattern.compile("^(.*?)(?i)WHEN\\s+(.+?)(?:\\s*\\.)?$");

    @Override
    public boolean coincide(String linea) {
        return PATTERN.matcher(linea).matches();
    }

    @Override
    public String transformar(String linea) {
        Matcher matcher = PATTERN.matcher(linea);
        if (matcher.matches()) {
            String indent = matcher.group(1);
            String value = matcher.group(2).trim();
            value = value.replace("'", "\"");

            if (value.equalsIgnoreCase("OTHER")) {
                return indent + "default ->";
            }

            return indent + "case " + value + " ->";
        }
        return linea;
    }

    @Override
    public String obtenerNombreRegla() {
        return "ReglaWhen";
    }
}

package com.bancodebogota.engine.rules;

import org.springframework.stereotype.Component;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ReglaSwitch implements ReglaMigracion {
    // Busca EVALUATE seguido de una variable
    private static final Pattern PATTERN = Pattern.compile("^(.*?)(?i)EVALUATE\\s+([^\\s.]+)((?:\\s*\\.)?)$");

    @Override
    public boolean coincide(String linea) {
        return PATTERN.matcher(linea).matches();
    }

    @Override
    public String transformar(String linea) {
        Matcher matcher = PATTERN.matcher(linea);
        if (matcher.matches()) {
            String indent = matcher.group(1);
            String variable = matcher.group(2).trim();
            return indent + "switch (" + variable.toLowerCase() + ") {";
        }
        return linea;
    }

    @Override
    public String obtenerNombreRegla() {
        return "ReglaSwitch";
    }
}

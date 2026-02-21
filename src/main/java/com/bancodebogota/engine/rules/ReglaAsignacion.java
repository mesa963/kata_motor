package com.bancodebogota.engine.rules;

import org.springframework.stereotype.Component;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ReglaAsignacion implements ReglaMigracion {
    private static final Pattern PATTERN = Pattern.compile("^\\s*\\d+\\s*(.*)$");

    @Override
    public boolean coincide(String linea) {
        return PATTERN.matcher(linea).matches();
    }

    @Override
    public String transformar(String linea) {
        Matcher matcher = PATTERN.matcher(linea);
        if (matcher.matches()) {
            return matcher.group(1);
        }
        return linea;
    }

    @Override
    public String obtenerNombreRegla() {
        return "ReglaAsignacion";
    }
}

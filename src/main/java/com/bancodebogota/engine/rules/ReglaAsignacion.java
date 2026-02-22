package com.bancodebogota.engine.rules;

import org.springframework.stereotype.Component;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ReglaAsignacion implements ReglaMigracion {
    // MOVE [valor] TO [variable]
    private static final Pattern PATTERN = Pattern.compile("^(.*?)(?i)MOVE\\s+(.+?)\\s+TO\\s+(.*?)\\.?\\s*$");

    @Override
    public boolean coincide(String linea) {
        return PATTERN.matcher(linea).matches();
    }

    @Override
    public String transformar(String linea) {
        Matcher matcher = PATTERN.matcher(linea);
        if (matcher.matches()) {
            String indent = matcher.group(1);
            String valor = matcher.group(2).trim();
            String variable = matcher.group(3).trim().toLowerCase();
            // Transformar "MOVE 10 TO AMOUNT" en "var amount = 10;"
            return indent + "var " + variable + " = " + valor + ";";
        }
        return linea;
    }

    @Override
    public String obtenerNombreRegla() {
        return "ReglaAsignacion";
    }
}

package com.bancodebogota.engine.rules;

import org.springframework.stereotype.Component;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ReglaCicloFor implements ReglaMigracion {
    // Busca PERFORM VARYING <var> FROM <inicio> BY <paso> UNTIL <condicion>
    private static final Pattern PATTERN = Pattern.compile(
            "^(.*?)(?i)PERFORM\\s+VARYING\\s+([^\\s]+)\\s+FROM\\s+([^\\s]+)\\s+BY\\s+([^\\s]+)\\s+UNTIL\\s+(.+?)(?:\\s*\\.)?$");

    @Override
    public boolean coincide(String linea) {
        return PATTERN.matcher(linea).matches();
    }

    @Override
    public String transformar(String linea) {
        Matcher matcher = PATTERN.matcher(linea);
        if (matcher.matches()) {
            String indent = matcher.group(1);
            String variable = matcher.group(2).trim().toLowerCase();
            String start = matcher.group(3).trim();
            String step = matcher.group(4).trim();
            String condition = matcher.group(5).trim().toLowerCase();
            condition = condition.replace(">", "<=");

            return indent + "for (int " + variable + " = " + start + "; " + condition + "; " + variable + " += " + step
                    + ") {";
        }
        return linea;
    }

    @Override
    public String obtenerNombreRegla() {
        return "ReglaCicloFor";
    }
}

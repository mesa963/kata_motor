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

            Pattern stringEqPattern = Pattern.compile("([a-zA-Z_0-9]+)\\s*=\\s*('[^']+')");
            Matcher m = stringEqPattern.matcher(condition);
            if (m.find()) {
                condition = m.replaceAll(matchResult -> matchResult.group(1).toLowerCase() + ".equals("
                        + matchResult.group(2).replace("'", "\"") + ")");
            } else {

                condition = condition.replace("=", "==");
                condition = condition.toLowerCase();
            }

            return indent + "if (" + condition + ") {";
        }
        return linea;
    }

    @Override
    public String obtenerNombreRegla() {
        return "ReglaCondicion";
    }
}

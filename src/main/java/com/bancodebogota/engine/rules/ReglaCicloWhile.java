package com.bancodebogota.engine.rules;

import org.springframework.stereotype.Component;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ReglaCicloWhile implements ReglaMigracion {
    // Busca PERFORM UNTIL <condicion>
    private static final Pattern PATTERN = Pattern.compile("^(.*?)(?i)PERFORM\\s+UNTIL\\s+(.+?)(?:\\s*\\.)?$");

    @Override
    public boolean coincide(String linea) {
        return PATTERN.matcher(linea).matches();
    }

    @Override
    public String transformar(String linea) {
        Matcher matcher = PATTERN.matcher(linea);
        if (matcher.matches()) {
            String indent = matcher.group(1);
            String condition = matcher.group(2).trim().toLowerCase();

            // Transformamos el "until" (hasta que sea verdadero) a un while (!condicion)
            // Ya que en COBOL se repite "hasta que" cambie, en Java es "mientras que" no se
            // cumpla
            return indent + "while (!(" + condition + ")) {";
        }
        return linea;
    }

    @Override
    public String obtenerNombreRegla() {
        return "ReglaCicloWhile";
    }
}

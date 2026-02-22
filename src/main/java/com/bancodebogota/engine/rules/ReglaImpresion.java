package com.bancodebogota.engine.rules;

import org.springframework.stereotype.Component;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ReglaImpresion implements ReglaMigracion {
    private static final Pattern PATTERN = Pattern.compile("^(.*?)(?i)DISPLAY\\s+(.*?)\\.?$");

    @Override
    public boolean coincide(String linea) {
        return PATTERN.matcher(linea).matches();
    }

    @Override
    public String transformar(String linea) {
        Matcher matcher = PATTERN.matcher(linea);
        if (matcher.matches()) {
            String indent = matcher.group(1);
            String content = matcher.group(2).trim();

            String[] parts = content.split("'");
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < parts.length; i++) {
                if (i % 2 == 1) {
                    sb.append("\"").append(parts[i]).append("\"");
                } else {
                    sb.append(parts[i].toLowerCase());
                }
            }
            content = sb.toString();

            content = content.replaceAll("(\"\\s+)(?=[a-zA-Z])", "\" + ");
            content = content.replaceAll("([a-zA-Z]\\s+)(?=\")", " + \"");

            return indent + "System.out.println(" + content.trim() + ");";
        }
        return linea;
    }

    @Override
    public String obtenerNombreRegla() {
        return "ReglaImpresion";
    }
}

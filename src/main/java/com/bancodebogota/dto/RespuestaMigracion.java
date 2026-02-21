package com.bancodebogota.dto;

import java.util.List;

public record RespuestaMigracion(String codigoModerno, List<String> reglasAplicadas, List<String> advertencias) {
}

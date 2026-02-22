package com.bancodebogota.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
//mapear los objetos Json en el record

public record SolicitudMigracion(
        @JsonProperty("codigoLegado") String codigoLegado,
        @JsonProperty("lenguajeDestino") String lenguajeDestino) {
}

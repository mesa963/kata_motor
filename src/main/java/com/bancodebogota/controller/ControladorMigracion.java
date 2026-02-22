package com.bancodebogota.controller;

import com.bancodebogota.dto.RespuestaMigracion;
import com.bancodebogota.dto.SolicitudMigracion;
import com.bancodebogota.service.ServicioMigracion;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api")
@Tag(name = "Migración COBOL", description = "API para la transformación de código COBOL legado a Java")
public class ControladorMigracion {

    private final ServicioMigracion servicioMigracion;

    public ControladorMigracion(ServicioMigracion servicioMigracion) {
        this.servicioMigracion = servicioMigracion;
    }

    @PostMapping("/migrar")
    @Operation(summary = "Migrar código COBOL", description = "Recibe código legacy COBOL y lo traduce aplicando las reglas de migración registradas.")
    public ResponseEntity<RespuestaMigracion> migrar(@RequestBody SolicitudMigracion solicitud) {
        RespuestaMigracion respuesta = servicioMigracion.procesarMigracion(solicitud);
        return ResponseEntity.ok(respuesta);
    }
}

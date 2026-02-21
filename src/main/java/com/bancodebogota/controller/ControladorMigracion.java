package com.bancodebogota.controller;

import com.bancodebogota.dto.RespuestaMigracion;
import com.bancodebogota.dto.SolicitudMigracion;
import com.bancodebogota.service.ServicioMigracion;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ControladorMigracion {

    private final ServicioMigracion servicioMigracion;

    public ControladorMigracion(ServicioMigracion servicioMigracion) {
        this.servicioMigracion = servicioMigracion;
    }

    @PostMapping("/migrar")
    public ResponseEntity<RespuestaMigracion> migrar(@RequestBody SolicitudMigracion solicitud) {
        RespuestaMigracion respuesta = servicioMigracion.procesarMigracion(solicitud);
        return ResponseEntity.ok(respuesta);
    }
}

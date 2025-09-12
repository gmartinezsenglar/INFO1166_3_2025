package com.bne.postulaciones_service.infrastructure.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "Usuarios", description = "Endpoints de apoyo para usuarios (si aplica)")
public class UsuarioController {

    @Operation(summary = "Obtener perfil de usuario (si el equipo de aplicación lo expone)")
    @GetMapping("/{usuarioId}")
    public ResponseEntity<?> obtener(@PathVariable Long usuarioId) {
        // TODO: conectar con el use case/servicio de aplicación que devuelva el DTO de usuario
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED,
                "TODO: conectar con use case de perfil/usuario (devolver DTO)");
    }
}

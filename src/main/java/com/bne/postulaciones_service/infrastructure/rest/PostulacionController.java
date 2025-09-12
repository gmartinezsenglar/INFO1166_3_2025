package com.bne.postulaciones_service.infrastructure.rest;

import com.bne.postulaciones_service.application.dto.PostulacionRequestDto;
import com.bne.postulaciones_service.application.dto.PostulacionResponseDto;
import com.bne.postulaciones_service.application.service.CrearPostulacionUseCase;
import com.bne.postulaciones_service.application.service.CancelarPostulacionUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/postulaciones")
@Validated
@Tag(name = "Postulaciones", description = "Endpoints oficiales de Postulaciones")
@RequiredArgsConstructor
public class PostulacionController {

    private final CrearPostulacionUseCase crearPostulacionUseCase;
    private final CancelarPostulacionUseCase cancelarPostulacionUseCase;

    @Operation(summary = "Crear una postulación")
    @PostMapping
    public ResponseEntity<PostulacionResponseDto> crear(@Valid @RequestBody PostulacionRequestDto request) {
        PostulacionResponseDto resp = crearPostulacionUseCase.ejecutar(request);
        // 201 Created (podrías añadir Location con la URL de detalle si quisieras)
        return ResponseEntity.status(201).body(resp);
    }

    @Operation(summary = "Cancelar una postulación")
    @DeleteMapping("/{postulacionId}")
    public ResponseEntity<Void> cancelar(@PathVariable Long postulacionId,
                                         @RequestParam Long usuarioId) {
        cancelarPostulacionUseCase.ejecutar(postulacionId, usuarioId);
        return ResponseEntity.noContent().build();
    }
}
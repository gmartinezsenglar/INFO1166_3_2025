package com.bne.postulaciones_service.infrastructure.rest;

import com.bne.postulaciones_service.application.dto.OfertaDto;
import com.bne.postulaciones_service.application.service.BuscarOfertasUseCase;
import com.bne.postulaciones_service.application.service.DetalleOfertaUseCase;
import com.bne.postulaciones_service.domain.model.vo.OfertaId;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/ofertas")
@Tag(name = "Ofertas", description = "Endpoints oficiales de Ofertas")
@RequiredArgsConstructor
public class OfertaController {

    private final BuscarOfertasUseCase buscarOfertasUseCase;   // ← dejar inyectado para conectarlo luego
    private final DetalleOfertaUseCase detalleOfertaUseCase;

    @Operation(summary = "Detalle de oferta por origen (BNE/EXTERNA) e id")
    @GetMapping("/{origen}/{valor}")
    public ResponseEntity<Object> detalle(@PathVariable String origen, @PathVariable Long valor) {
        OfertaId.Origen origenEnum;
        try {
            origenEnum = OfertaId.Origen.valueOf(origen.toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Origen inválido. Use BNE o EXTERNA");
        }
        var resp = detalleOfertaUseCase.ejecutar(new OfertaId(valor, origenEnum));
        return ResponseEntity.ok(resp); // Puede ser DetalleOfertaBneDto o DetalleOfertaExternaDto
    }

    @Operation(summary = "Buscar ofertas con filtros (paginado)")
    @GetMapping
    public ResponseEntity<?> buscar(
            @RequestParam(required = false) String q,
            @RequestParam(required = false) String region,
            @RequestParam(required = false) String ciudad,
            @RequestParam(required = false) String jornada,
            @RequestParam(required = false) String contrato,
            @RequestParam(required = false) Boolean practica,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        // TODO: conectar cuando confirmen la firma exacta del use case.
        // Ejemplos comunes:
        // 1) List<OfertaDto> resp = buscarOfertasUseCase.ejecutar(q, region, ciudad, jornada, contrato, practica, page, size);
        //    return ResponseEntity.ok(resp);
        // 2) Page<OfertaDto> resp = buscarOfertasUseCase.ejecutar(new BuscarOfertasRequestDto(...));
        //    return ResponseEntity.ok(resp);
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED,
                "Conectar con BuscarOfertasUseCase cuando definan firma (devolver DTOs OfertaDto)");
    }
}

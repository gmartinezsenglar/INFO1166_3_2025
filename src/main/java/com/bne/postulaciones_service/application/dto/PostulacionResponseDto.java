package com.bne.postulaciones_service.application.dto;
import com.bne.postulaciones_service.domain.model.EstadoPostulacion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostulacionResponseDto {
    private Long postulacionId;
    private Long usuarioId;
    private Long ofertaId;
    private EstadoPostulacion estado;
    private LocalDateTime fechaPostulacion;
    private LocalDateTime fechaUltimaActualizacion;
}

package com.bne.postulaciones_service.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.bne.postulaciones_service.domain.model.EstadoPostulacion;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PostulacionResponseDto {
    private String postulacionId;
    private EstadoPostulacion estado;
}
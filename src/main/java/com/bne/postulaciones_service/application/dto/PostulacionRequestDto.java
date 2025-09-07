package com.bne.postulaciones_service.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostulacionRequestDto {
    private Long usuarioId;
    private Long ofertaId;
}

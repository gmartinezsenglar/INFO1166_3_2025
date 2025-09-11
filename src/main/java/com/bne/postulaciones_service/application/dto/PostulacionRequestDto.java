package com.bne.postulaciones_service.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.bne.postulaciones_service.domain.model.vo.OfertaId;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostulacionRequestDto {
    private Long usuarioId;
    private Long empresaId;
    private OfertaId ofertaId;
}

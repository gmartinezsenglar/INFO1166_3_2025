package com.bne.postulaciones_service.application.dto;

import com.bne.postulaciones_service.domain.model.vo.OfertaId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostulacionResponseDto {
    private Long id;
    private Long usuarioId;
    private Long empresaId;
    private OfertaId ofertaId;
    private LocalDate fechaPostulacion;
    private String estado;
}

package com.bne.postulaciones_service.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PostulacionRequestDto {
    private Long ofertaId;
    private String usuarioId;

    public long GetIdOferta() { return ofertaId}
    public long GetIdUsuario() { return usuarioId}

}
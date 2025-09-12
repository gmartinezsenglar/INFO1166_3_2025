package com.bne.postulaciones_service.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OfertaDto {
    private Long id;
    private String origen;
    private String nombre;
    private String descripcion;
    private String tipoContrato;
    private Long empresaId;
    private String empresaNombre;
}

package com.bne.postulaciones_service.application.dto;
import com.bne.postulaciones_service.domain.model.PeriodoVigencia;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfertaDetalleDto {
    private Long id;
    private String titulo;
    private String descripcion;
    private String ubicacion;
    private String tipoContrato;
    private String rubro;
    private PeriodoVigencia vigencia;
}
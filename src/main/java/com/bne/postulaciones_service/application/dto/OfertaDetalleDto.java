package com.bne.postulaciones_service.application.dto;

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
    private LocalDate fechaPublicacion;
    private String empresa; //otros detalles mas depende del modelo
}
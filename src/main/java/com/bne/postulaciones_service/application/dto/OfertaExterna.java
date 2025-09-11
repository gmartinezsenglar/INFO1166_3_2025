package com.bne.postulaciones_service.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class OfertaExterna {
    private Long id;
    private String nombre; // Título de la oferta
    private String nombreEmpresa;
    private String ciudad;
    private String origenOferta; // Útil para saber de qué portal viene
    private String rangoSalarial;
}

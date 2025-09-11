package com.bne.postulaciones_service.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class OfertaBNE {
    private Long id;
    private String nombre; // Título de la oferta
    private String Empresa;
    private String ciudad;
    private String tipoJornada;
    private String rangoSalarial; // Un string simple, ej: "$500.000 - $700.000"
    private boolean esPracticaProfesional;
}

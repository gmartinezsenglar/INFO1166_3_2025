package com.bne.postulaciones_service.domain.repository;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OfertaFilter {

    private String nombre;
    private String ciudad;
    private String region;
    private Integer vacantesDisp;
    private Integer pagaMinima;
    private Integer pagaMaxima;
    private Boolean reqExperiencia;
    private Boolean isPracticaPro;
    private Boolean isLey21015;

    private String tipoContrato;
    private String nivelEducacional;
    private String tipoJornada;
    private String origenOferta;
}
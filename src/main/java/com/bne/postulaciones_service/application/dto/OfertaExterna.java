package com.bne.postulaciones_service.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class OfertaExterna {
    private Long id;
    private String nombreEmpresa;
    private String nombre;
    private String descripcion;
    private Integer vacantesDisponibles;
    private String ciudad;
    private String región;
    private int minimoSalarial;
    private int maximoSalarial;
    private LocalDate fechaInicioVigencia;
    private LocalDate fechaTerminoVigencia;
    private String nombrePublicador;
    private boolean requiereExperiencia;
    private String tipoNivelEducacional;
    private String tipoContrato;
    private String tipoJornada;
    private String nivelCargo;
    private String Origen;
    private String urlFuente;
    private boolean vigente;
    private boolean tieneVacantesDisponibles;
}

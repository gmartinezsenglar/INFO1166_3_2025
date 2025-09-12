package com.bne.postulaciones_service.application.dto;

import lombok.*;
import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class DetalleOfertaBneDto {
    private Long id;
    private Long empresaId;
    private String nombreEmpresa;
    private String nombre;
    private String descripcion;
    private Integer vacantesDisponibles;
    private String ciudad;
    private String region; // <— renombrado
    private Integer minimoSalarial;
    private Integer maximoSalarial;
    private LocalDate fechaInicioVigencia;
    private LocalDate fechaTerminoVigencia;
    private boolean requiereExperiencia;
    private boolean requiereNivelEducacional;
    private String tipoNivelEducacional;
    private String tipoContrato;
    private String tipoJornada;
    private String nivelCargo;
    private boolean practicaProfesional;
    private boolean vigente;
    private boolean tieneVacantesDisponibles;
}

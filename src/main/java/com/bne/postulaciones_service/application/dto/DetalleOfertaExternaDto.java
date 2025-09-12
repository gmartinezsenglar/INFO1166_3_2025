package com.bne.postulaciones_service.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data @AllArgsConstructor @NoArgsConstructor
public class DetalleOfertaExternaDto {
    private Long id;
    private Long empresaId;           // <— agregado
    private String nombreEmpresa;
    private String nombre;
    private String descripcion;
    private Integer vacantesDisponibles;
    private String ciudad;
    private String region;            // <— sin tilde
    private Integer minimoSalarial;
    private Integer maximoSalarial;
    private LocalDate fechaInicioVigencia;
    private LocalDate fechaTerminoVigencia;
    private String nombrePublicador;
    private boolean requiereExperiencia;
    private String tipoNivelEducacional;
    private String tipoContrato;
    private String tipoJornada;
    private String nivelCargo;
    private String origen;            // <— camelcase normal
    private String urlFuente;
    private boolean vigente;
    private boolean tieneVacantesDisponibles;
}

package com.bne.postulaciones_service.application.dto;

import com.bne.postulaciones_service.domain.model.vo.PeriodoVigencia;
import com.bne.postulaciones_service.domain.model.vo.RangoSalarial;
import com.bne.postulaciones_service.domain.model.vo.Ubicacion;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetalleOfertaBneDto {

    private Long id;
    private Long empresaId;
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
    private boolean requiereExperiencia;
    private boolean requiereNivelEducacional;
    private String tipoNivelEducacional;
    private String tipoContrato;
    private String tipoJornada;
    private String nivelCargo;
    private String origenOferta;
    private boolean practicaProfesional;
    private boolean vigente;
    private boolean tieneVacantesDisponibles;
}
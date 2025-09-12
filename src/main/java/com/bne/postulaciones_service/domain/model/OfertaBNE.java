package com.bne.postulaciones_service.domain.model;

import com.bne.postulaciones_service.domain.model.vo.PeriodoVigencia;
import com.bne.postulaciones_service.domain.model.vo.RangoSalarial;
import com.bne.postulaciones_service.domain.model.vo.Ubicacion;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ofertas_bne")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OfertaBNE {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    private String nombre;

    @Column(name = "vacantes_disponibles")
    private Integer vacantesDisponibles;

    private String descripcion;

    @Embedded
    private Ubicacion ubicacion;

    @Embedded
    private RangoSalarial rangoSalarial;

    @Embedded
    private PeriodoVigencia periodoVigencia;

    private boolean requiereExperiencia;

    private boolean requiereNivelEducacional;

    private String tipoNivelEducacional;

    private String tipoContrato;

    private String tipoJornada;

    private String nivelCargo;

    private String origenOferta;

    @Column(name = "practica_profesional")
    private boolean practicaProfesional;

    @Column(name = "ley_21015")
    private boolean ley21015;

    // --- Métodos de comportamiento útiles ---

    public boolean estaVigente() {
        return periodoVigencia != null && periodoVigencia.estaVigente();
    }

    public boolean tieneVacantesDisponibles() {
        return vacantesDisponibles != null && vacantesDisponibles > 0;
    }

    public void reducirVacante() {
        if (!tieneVacantesDisponibles()) {
            throw new IllegalStateException("No hay vacantes disponibles.");
        }
        this.vacantesDisponibles--;
    }

    public boolean esPracticaProfesional() {
        return practicaProfesional;
    }

    public boolean rangoSalarialValido() {
        if (rangoSalarial.getMinimo() == null || rangoSalarial.getMaximo() == null) return true;
        return rangoSalarial.getMinimo() <= rangoSalarial.getMaximo();
    }

    public boolean requiereExperiencia() {
        return this.requiereExperiencia;
    }

    public boolean requiereNivelEducacional() {
        return this.requiereNivelEducacional;
    }

    public boolean fechasValidas() {
        if (periodoVigencia.getFechaInicio() == null || periodoVigencia.getFechaTermino() == null) return true;
        return !periodoVigencia.getFechaInicio().isAfter(periodoVigencia.getFechaTermino());
    }
    
    public boolean datosBasicosCompletos() {
        return nombre != null && !nombre.isBlank()
        && descripcion != null && !descripcion.isBlank()
        && ubicacion.getRegion() != null && !ubicacion.getRegion().isBlank()
        && ubicacion.getCiudad() != null && !ubicacion.getCiudad().isBlank();
    }
}

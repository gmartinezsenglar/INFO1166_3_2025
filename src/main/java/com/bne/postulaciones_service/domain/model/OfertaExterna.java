package com.bne.postulaciones_service.domain.model;

import com.bne.postulaciones_service.domain.model.vo.PeriodoVigencia;
import com.bne.postulaciones_service.domain.model.vo.RangoSalarial;
import com.bne.postulaciones_service.domain.model.vo.Ubicacion;
import jakarta.persistence.*;
import lombok.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;

@Entity
@Table(name = "ofertas_externas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OfertaExterna {

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

    private String nombrePublicador;

    private boolean requiereExperiencia;

    private boolean requiereNivelEducacional;

    private String tipoNivelEducacional;

    private String tipoContrato;

    private String tipoJornada;

    private String nivelCargo;

    private String origenOferta;

    @Column(name = "practica_profesional")
    private boolean practicaProfesional;

    @Column(name = "url_fuente")
    private String urlFuente;

    // --- Métodos de comportamiento útiles ---

    public boolean estaVigente() {
        return periodoVigencia != null && periodoVigencia.estaVigente();
    }

    public boolean tieneVacantesDisponibles() {
        return vacantesDisponibles != null && vacantesDisponibles > 0;
    }

    public void reducirVacante() {
        if (!tieneVacantes()) {
            throw new IllegalStateException("No hay vacantes disponibles.");
        }
        this.vacantesDisponibles--;
    }

    public boolean rangoSalarialValido() {
        if (pagaMinima == null || pagaMaxima == null) return true;
        return pagaMinima <= pagaMaxima;
    }

    public boolean tieneUrlValida() {
        return urlFuente != null && (urlFuente.startsWith("http://") || urlFuente.startsWith("https://"));
    }

    public boolean datosBasicosCompletos() {
        return nombre != null && !nombre.isBlank()
            && descripcion != null && !descripcion.isBlank()
            && region != null && !region.isBlank()
            && ciudad != null && !ciudad.isBlank();
    }

    public boolean tieneNombrePublicador() {
        return nombrePublicador != null && !nombrePublicador.isBlank();
    }

    public boolean empresaAsociada() {
        return empresa != null;
    }

    public boolean tipoJornadaValida() {
        return tipoJornada != null && (
            tipoJornada.equalsIgnoreCase("COMPLETA") ||
            tipoJornada.equalsIgnoreCase("PART_TIME") ||
            tipoJornada.equalsIgnoreCase("POR_TURNOS")
        );
    }

    public boolean requiereExperienciaLaboral() {
        return this.requiereExperiencia;
    }

    public boolean nivelEducacionalValido() {
        if (!requiereNivelEducacional) return true;
        return tipoNivelEducacional != null && !tipoNivelEducacional.isBlank();
    }

    public boolean tipoContratoValido() {
        return tipoContrato != null && (
            tipoContrato.equalsIgnoreCase("PLAZO_FIJO") ||
            tipoContrato.equalsIgnoreCase("INDEFINIDO") ||
            tipoContrato.equalsIgnoreCase("HONORARIOS")
        );
    }

    public boolean nivelCargoValido() {
        return nivelCargo != null && (
            nivelCargo.equalsIgnoreCase("OPERARIO") ||
            nivelCargo.equalsIgnoreCase("TECNICO") ||
            nivelCargo.equalsIgnoreCase("PROFESIONAL") ||
            nivelCargo.equalsIgnoreCase("EJECUTIVO")
        );
    }

    public boolean origenOfertaValido() {
        return origenOferta != null && !origenOferta.isBlank();
    }
}

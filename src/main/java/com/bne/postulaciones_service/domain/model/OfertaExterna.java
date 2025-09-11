package com.bne.postulaciones_service.domain.model;

import com.bne.postulaciones_service.domain.model.vo.PeriodoVigencia;
import com.bne.postulaciones_service.domain.model.vo.RangoSalarial;
import com.bne.postulaciones_service.domain.model.vo.Ubicacion;
import jakarta.persistence.*;
import lombok.*;

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

    public boolean tieneUrlValida() {
        return urlFuente != null && (urlFuente.startsWith("http://") || urlFuente.startsWith("https://"));
    }
}

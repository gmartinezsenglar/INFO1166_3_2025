package com.bne.postulaciones_service.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

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

    private String region;

    private String ciudad;

    @Column(name = "paga_minima")
    private Integer pagaMinima;

    @Column(name = "paga_maxima")
    private Integer pagaMaxima;

    private String tipoJornada;

    private LocalDate fechaInicio;

    private LocalDate fechaTermino;

    private boolean requiereExperiencia;

    private boolean requiereNivelEducacional;

    private String tipoNivelEducacional;

    private String tipoContrato;

    private String nivelCargo;

    private String origenOferta;

    @Column(name = "practica_profesional")
    private boolean practicaProfesional;

    @Column(name = "ley_21015")
    private boolean ley21015;

}

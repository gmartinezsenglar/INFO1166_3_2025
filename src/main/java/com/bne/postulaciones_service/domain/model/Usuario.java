package com.bne.postulaciones_service.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombres;

    private String apellidos;

    @Column(name = "info_academica_completa")
    private boolean infoAcademicaCompleta;

    @Column(name = "experiencia_laboral_completa")
    private boolean experienciaLaboralCompleta;

    public boolean tieneInfoAcademica() {
        return this.infoAcademicaCompleta;
    }

    public boolean tieneExperienciaLaboral() {
        return this.experienciaLaboralCompleta;
    }

    public boolean perfilCompleto() {
        return tieneInfoAcademica() && tieneExperienciaLaboral();
    }

    public String nombreCompleto() {
        return (nombres != null ? nombres : "") + " " +
               (apellidos != null ? apellidos : "");
    }

}

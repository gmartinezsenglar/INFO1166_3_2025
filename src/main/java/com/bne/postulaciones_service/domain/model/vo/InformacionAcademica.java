package com.bne.postulaciones.domain.model.vo;

public record InformacionAcademica(String titulo, String institucion, NivelEducacional nivel) {

    public InformacionAcademica {
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("El título no puede estar vacío.");
        }
        if (institucion == null || institucion.isBlank()) {
            throw new IllegalArgumentException("La institución no puede estar vacía.");
        }
        if (nivel == null) {
            throw new IllegalArgumentException("El nivel educacional es obligatorio.");
        }
    }
}

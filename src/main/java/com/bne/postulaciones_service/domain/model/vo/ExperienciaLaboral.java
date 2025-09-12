package com.bne.postulaciones_service.domain.model.vo;

public record ExperienciaLaboral(int anios, String area) {

    public ExperienciaLaboral {
        if (anios < 0) {
            throw new IllegalArgumentException("Los años de experiencia no pueden ser negativos.");
        }
        if (area == null || area.isBlank()) {
            throw new IllegalArgumentException("El área de experiencia no puede estar vacía.");
        }
    }

    public boolean cumpleCon(int aniosMinimos) {
        return this.anios >= aniosMinimos;
    }
}

package com.bne.postulaciones_service.domain.model;

public interface Oferta {
    boolean estaVigente();
    boolean tieneVacantesDisponibles();
    boolean esPracticaProfesional();
}

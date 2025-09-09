package com.bne.postulaciones_service.domain.model;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.time.LocalDate;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PeriodoVigencia {

    private LocalDate fechaInicio;
    private LocalDate fechaTermino;

    public boolean estaVigente() {
        if (fechaInicio == null || fechaTermino == null) return false;
        LocalDate hoy = LocalDate.now();
        return (hoy.isEqual(fechaInicio) || hoy.isAfter(fechaInicio)) &&
                (hoy.isEqual(fechaTermino) || hoy.isBefore(fechaTermino));
    }
}
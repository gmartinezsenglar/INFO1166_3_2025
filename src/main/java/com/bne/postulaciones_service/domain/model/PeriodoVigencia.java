package com.bne.postulaciones_service.domain.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Value Object que representa un periodo de vigencia de una oferta de empleo
 */
public final class PeriodoVigencia {

    private final LocalDate fechaPublicacion;
    private final LocalDate fechaExpiracion;

    public PeriodoVigencia(LocalDate fechaPublicacion, LocalDate fechaExpiracion) {
        this.fechaPublicacion = Objects.requireNonNull(fechaPublicacion, "La fecha de publicación no puede ser nula");
        this.fechaExpiracion = Objects.requireNonNull(fechaExpiracion, "La fecha de expiración no puede ser nula");

        if (fechaExpiracion.isBefore(fechaPublicacion)) {
            throw new IllegalArgumentException("La fecha de expiración no puede ser anterior a la de publicación");
        }
    }

    public boolean estaActiva() {
        LocalDate hoy = LocalDate.now();
        return !hoy.isBefore(fechaPublicacion) && !hoy.isAfter(fechaExpiracion);
    }

    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    public LocalDate getFechaExpiracion() {
        return fechaExpiracion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PeriodoVigencia)) return false;
        PeriodoVigencia that = (PeriodoVigencia) o;
        return fechaPublicacion.equals(that.fechaPublicacion) &&
               fechaExpiracion.equals(that.fechaExpiracion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fechaPublicacion, fechaExpiracion);
    }
}

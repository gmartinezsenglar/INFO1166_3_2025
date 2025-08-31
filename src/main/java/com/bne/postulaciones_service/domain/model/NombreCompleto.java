package com.bne.postulaciones_service.domain.model;

import java.util.Objects;

/**
 * Value Object que representa un nombre de postulante.
 */
public final class NombreCompleto {

    private final String value;

    public NombreCompleto(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        this.value = value.trim();
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NombreCompleto)) return false;
        NombreCompleto that = (NombreCompleto) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }
}

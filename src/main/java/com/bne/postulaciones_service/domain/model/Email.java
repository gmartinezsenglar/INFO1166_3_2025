package com.bne.postulaciones_service.domain.model;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Value Object para representar y validar un correo electronico
 */
public final class Email {

    private final String value;

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    public Email(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("El email no puede estar vacío");
        }
        if (!EMAIL_PATTERN.matcher(value).matches()) {
            throw new IllegalArgumentException("El formato del email no es válido");
        }
        this.value = value.toLowerCase().trim();
    }

    public String getValue() {
        return value;
    }

    // Equals & HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Email)) return false;
        Email email = (Email) o;
        return value.equals(email.value);
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

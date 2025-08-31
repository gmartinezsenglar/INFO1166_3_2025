package com.bne.postulaciones_service.domain.model;

import java.util.Objects;


public class UsuarioPostulante {

    private final Long id;                // Identidad unica
    private final NombreCompleto nombre;  // VO
    private final Email email;            // VO

    public UsuarioPostulante(Long id, NombreCompleto nombre, Email email) {
        this.id = Objects.requireNonNull(id, "El id no puede ser nulo");
        this.nombre = Objects.requireNonNull(nombre, "El nombre no puede ser nulo");
        this.email = Objects.requireNonNull(email, "El email no puede ser nulo");
    }


    // ======== Getters ======== //
    public Long getId() {
        return id;
    }

    public NombreCompleto getNombre() {
        return nombre;
    }

    public Email getEmail() {
        return email;
    }


    // ======== Equals & HashCode (por identidad) ======== //
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UsuarioPostulante)) return false;
        UsuarioPostulante that = (UsuarioPostulante) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

package com.bne.postulaciones_service.domain.model;


import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Entidad principal del dominio (Aggregate Root).
 * Representa una postulacion realizada por un usuario a una oferta de empleo.
 */
public class Postulacion {

    private final Long id;                          // Identificador unico (inmutable)
    private final UsuarioPostulante postulante;     // El postulante que aplica
    private final OfertaEmpleo oferta;              // La oferta a la que se postula
    private EstadoPostulacion estado;               // VO simple (enum)
    private final LocalDateTime fechaPostulacion;   // Fecha en la que se creo la postulacion
    private LocalDateTime fechaUltimaActualizacion; // Fecha de ultimo cambio de estado

    // Constructor principal 
    public Postulacion(Long id, UsuarioPostulante postulante, OfertaEmpleo oferta) {
        this.id = Objects.requireNonNull(id, "El id no puede ser nulo");
        this.postulante = Objects.requireNonNull(postulante, "El postulante no puede ser nulo");
        this.oferta = Objects.requireNonNull(oferta, "La oferta no puede ser nula");
        this.estado = EstadoPostulacion.PENDIENTE;
        this.fechaPostulacion = LocalDateTime.now();
        this.fechaUltimaActualizacion = this.fechaPostulacion;
    }


    // ======== Metodos de negocio (reglas del dominio) ======== //
    public void aceptar() {
        validarTransicion();
        this.estado = EstadoPostulacion.ACEPTADA;
        this.fechaUltimaActualizacion = LocalDateTime.now();
    }

    public void rechazar() {
        validarTransicion();
        this.estado = EstadoPostulacion.RECHAZADA;
        this.fechaUltimaActualizacion = LocalDateTime.now();
    }

    public void retirar() {
        if (estado == EstadoPostulacion.ACEPTADA) {
            throw new IllegalStateException("No se puede retirar una postulación ya aceptada");
        }
        this.estado = EstadoPostulacion.RETIRADA;
        this.fechaUltimaActualizacion = LocalDateTime.now();
    }

    // Reglas de transicion vlidas
    private void validarTransicion() {
        if (estado == EstadoPostulacion.ACEPTADA || estado == EstadoPostulacion.RECHAZADA) {
            throw new IllegalStateException("No se puede modificar una postulación ya cerrada");
        }
    }



    // ======== Getters ======== //
    public Long getId() {
        return id;
    }

    public UsuarioPostulante getPostulante() {
        return postulante;
    }

    public OfertaEmpleo getOferta() {
        return oferta;
    }

    public EstadoPostulacion getEstado() {
        return estado;
    }

    public LocalDateTime getFechaPostulacion() {
        return fechaPostulacion;
    }

    public LocalDateTime getFechaUltimaActualizacion() {
        return fechaUltimaActualizacion;
    }

    
    // ======== Equals & HashCode (por identidad) ======== //
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Postulacion)) return false;
        Postulacion that = (Postulacion) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

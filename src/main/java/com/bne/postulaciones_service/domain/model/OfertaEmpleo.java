package com.bne.postulaciones_service.domain.model;

import java.util.Objects;

/**
 * Entidad del dominio.
 * Representa una oferta de empleo disponible en la BNE.
 */
public class OfertaEmpleo {

    private final Long id;                // Identificador unico
    private final String titulo;          // Ej: "Desarrollador Java Senior"
    private final String descripcion;     // Detalle de responsabilidades
    private final String ubicacion;       // Ej: "Santiago, Chile"
    private final String tipoContrato;    // Ej: "Full-time", "Part-time", "Freelance"
    private final String rubro;           // Ej: "Tecnologia", "Salud", "Educacion"
    private final PeriodoVigencia vigencia; // Value Object para publicacion/expiracion

    // Constructor principal (inmutable)
    public OfertaEmpleo(Long id, String titulo, String descripcion, String ubicacion,
                        String tipoContrato, String rubro, PeriodoVigencia vigencia) {
        this.id = Objects.requireNonNull(id, "El id no puede ser nulo");
        this.titulo = Objects.requireNonNull(titulo, "El título no puede ser nulo");
        this.descripcion = Objects.requireNonNull(descripcion, "La descripción no puede ser nula");
        this.ubicacion = Objects.requireNonNull(ubicacion, "La ubicación no puede ser nula");
        this.tipoContrato = Objects.requireNonNull(tipoContrato, "El tipo de contrato no puede ser nulo");
        this.rubro = Objects.requireNonNull(rubro, "El rubro no puede ser nulo");
        this.vigencia = Objects.requireNonNull(vigencia, "El período de vigencia no puede ser nulo");
    }


    // ======== Logica de negocio delegada a VO ======== //
    public boolean estaActiva() {
        return vigencia.estaActiva();
    }

    public boolean expirada() {
        return !vigencia.estaActiva();
    }


    // ======== Getters ======== //
    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public String getTipoContrato() {
        return tipoContrato;
    }

    public String getRubro() {
        return rubro;
    }

    public PeriodoVigencia getVigencia() {
        return vigencia;
    }


    // ======== Equals & HashCode (por identidad) ======== //
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OfertaEmpleo)) return false;
        OfertaEmpleo that = (OfertaEmpleo) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

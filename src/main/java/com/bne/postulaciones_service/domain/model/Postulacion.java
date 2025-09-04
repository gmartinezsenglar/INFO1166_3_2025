package com.bne.postulaciones_service.domain.model;

import jakarta.persistence.*;

@Entity
public class Postulacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private UsuarioPostulante postulante;

    @ManyToOne
    private OfertaEmpleo oferta;

    private String estado; // Ejemplo: "ENVIADA", "ACEPTADA", "RECHAZADA"

    // Constructores
    public Postulacion() {}
    public Postulacion(UsuarioPostulante postulante, OfertaEmpleo oferta, String estado) {
        this.postulante = postulante;
        this.oferta = oferta;
        this.estado = estado;
    }

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public UsuarioPostulante getPostulante() { return postulante; }
    public void setPostulante(UsuarioPostulante postulante) { this.postulante = postulante; }

    public OfertaEmpleo getOferta() { return oferta; }
    public void setOferta(OfertaEmpleo oferta) { this.oferta = oferta; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}

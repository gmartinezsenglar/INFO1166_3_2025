package com.bne.postulaciones_service.domain.event;

import java.time.Instant;

public class PostulacionEstadoActualizado implements DomainEvent {
    private final Long postulacionId;
    private final Long usuarioId;
    private final String nuevoEstado;
    private final Instant occurredOn = Instant.now();

    public PostulacionEstadoActualizado(Long postulacionId, Long usuarioId, String nuevoEstado) {
        this.postulacionId = postulacionId;
        this.usuarioId = usuarioId;
        this.nuevoEstado = nuevoEstado;
    }
    public Long getPostulacionId() { return postulacionId; }
    public Long getUsuarioId() { return usuarioId; }
    public String getNuevoEstado() { return nuevoEstado; }
    @Override public Instant occurredOn() { return occurredOn; }
}

package com.bne.postulaciones_service.domain.event;
import java.time.Instant;

public class PostulacionCancelada implements DomainEvent {
    private final Long postulacionId;
    private final Long usuarioId;
    private final Long ofertaId;
    private final Instant occurredOn;

    public PostulacionCancelada(Long postulacionId, Long usuarioId, Long ofertaId) {
        this.postulacionId = postulacionId;
        this.usuarioId = usuarioId;
        this.ofertaId = ofertaId;
        this.occurredOn = Instant.now();
    }

    public Long getPostulacionId() {
        return postulacionId;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public Long getOfertaId() {
        return ofertaId;
    }

    @Override
    public Instant occurredOn() {
        return occurredOn;
    }
}

package com.bne.postulaciones_service.domain.event;
import java.time.Instant;

public class OfertaCerrada implements DomainEvent {
    private final Long ofertaId;
    private final Instant occurredOn;

    public OfertaCerrada(Long ofertaId) {
        this.ofertaId = ofertaId;
        this.occurredOn = Instant.now();
    }

    public Long getOfertaId() {
        return ofertaId;
    }

    @Override
    public Instant occurredOn() {
        return occurredOn;
    }
}

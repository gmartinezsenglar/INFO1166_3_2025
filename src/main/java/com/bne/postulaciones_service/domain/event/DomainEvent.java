package com.bne.postulaciones_service.domain.event;

import java.time.Instant;

public interface DomainEvent {
    Instant occurredOn();
}

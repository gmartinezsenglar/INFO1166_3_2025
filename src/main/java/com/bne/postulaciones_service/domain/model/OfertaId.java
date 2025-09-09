package com.bne.postulaciones_service.domain.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OfertaId {

    private Long valor;

    @Enumerated(EnumType.STRING)
    private Origen origen;

    public enum Origen {
        BNE, EXTERNA
    }
}

package com.bne.postulaciones_service.domain.model.vo;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RangoSalarial {

    private Integer minimo;
    private Integer maximo;

    public boolean esValido() {
        return minimo != null && maximo != null && minimo <= maximo;
    }
}

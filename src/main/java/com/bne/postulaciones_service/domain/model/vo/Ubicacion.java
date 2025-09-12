package com.bne.postulaciones_service.domain.model.vo;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ubicacion {

    private String region;
    private String ciudad;

    public boolean esValida() {
        return region != null && !region.isBlank()
                && ciudad != null && !ciudad.isBlank();
    }
}

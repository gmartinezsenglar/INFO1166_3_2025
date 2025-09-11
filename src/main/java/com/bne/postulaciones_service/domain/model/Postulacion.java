package com.bne.postulaciones_service.domain.model;

import com.bne.postulaciones_service.domain.model.vo.OfertaId;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "postulaciones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Postulacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private OfertaId ofertaId;

    @Column(name = "empresa_id", nullable = false)
    private Long empresaId;

    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;

    @Column(name = "fecha_postulacion", nullable = false)
    private LocalDate fechaPostulacion;

    @Enumerated(EnumType.STRING)
    private EstadoPostulacion estado;

    public enum EstadoPostulacion {
        ENVIADA, ACEPTADA, RECHAZADA, RETIRADA
    }
}

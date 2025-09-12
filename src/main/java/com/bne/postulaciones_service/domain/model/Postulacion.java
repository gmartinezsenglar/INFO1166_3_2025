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

    public void enviar() {
        this.estado=EstadoPostulacion.ENVIADA;
        this.fechaPostulacion=LocalDate.now();
    }

    public void cambiarEstado(EstadoPostulacion nuevoEstado) {
        if (this.estado == EstadoPostulacion.RETIRADA) {
            throw new IllegalStateException("No se puede modificar una postulación retirada.");
        }
        this.estado=nuevoEstado;
    }

    public boolean fueAceptada() {
        return this.estado == EstadoPostulacion.ACEPTADA;
    }

    public boolean fueRechazada() {
        return this.estado == EstadoPostulacion.RECHAZADA;
    }

    public boolean perteneceAUsuario(Long usuarioId) {
        return this.usuarioId.equals(usuarioId);
    }
}

package com.bne.postulaciones_service.domain.service;

import com.bne.postulaciones_service.domain.model.*;
import com.bne.postulaciones_service.domain.event.*;
import com.bne.postulaciones_service.domain.model.vo.OfertaId;


import java.time.LocalDate;

public class PostulacionDomainService {

    public Postulacion crearPostulacion(Usuario usuario, OfertaBNE oferta) {
        if (!usuario.perfilCompleto()) {
            throw new IllegalStateException("El usuario no tiene su perfil completo para postular.");
        }
        if (!oferta.estaVigente()) {
            throw new IllegalStateException("La oferta no está vigente.");
        }
        if (!oferta.tieneVacantesDisponibles()) {
            throw new IllegalStateException("La oferta no tiene vacantes disponibles.");
        }
        oferta.reducirVacante();

        Postulacion postulacion = Postulacion.builder()
                .ofertaId(new OfertaId(oferta.getId(), OfertaId.Origen.BNE))
                .empresaId(oferta.getEmpresa().getId())
                .usuarioId(usuario.getId())
                .fechaPostulacion(LocalDate.now())
                .estado(Postulacion.EstadoPostulacion.ENVIADA)
                .build();

        PostulacionCreada evento = new PostulacionCreada(
                postulacion.getId(),
                usuario.getId(),
                oferta.getId()
        );

        return postulacion;
    }

    public Postulacion crearPostulacionExterna(Usuario usuario, OfertaExterna oferta) {
        if (!usuario.perfilCompleto()) {
            throw new IllegalStateException("El usuario no tiene su perfil completo para postular.");
        }
        if (!oferta.estaVigente()) {
            throw new IllegalStateException("La oferta no está vigente.");
        }
        if (!oferta.tieneVacantesDisponibles()) {
            throw new IllegalStateException("La oferta no tiene vacantes disponibles.");
        }

        oferta.reducirVacante();

        Postulacion postulacion = Postulacion.builder()
                .ofertaId(new OfertaId(oferta.getId(), OfertaId.Origen.EXTERNA))
                .empresaId(oferta.getEmpresa().getId())
                .usuarioId(usuario.getId())
                .fechaPostulacion(LocalDate.now())
                .estado(Postulacion.EstadoPostulacion.ENVIADA)
                .build();

        PostulacionCreada evento = new PostulacionCreada(
                postulacion.getId(),
                usuario.getId(),
                oferta.getId()
        );
        // publicar evento

        return postulacion;
    }

    public void cancelarPostulacion(Postulacion postulacion, Usuario usuario) {
        if (!postulacion.getUsuarioId().equals(usuario.getId())) {
            throw new IllegalStateException("Solo el usuario dueño de la postulación puede cancelarla.");
        }
        if (postulacion.getEstado() != Postulacion.EstadoPostulacion.ENVIADA) {
            throw new IllegalStateException("Solo se pueden cancelar postulaciones en estado ENVIADA.");
        }
        postulacion.setEstado(Postulacion.EstadoPostulacion.RETIRADA);

        PostulacionCancelada evento = new PostulacionCancelada(
                postulacion.getId(),
                usuario.getId(),
                postulacion.getOfertaId().getValor()
        );
        // publicar evento
    }

    public void actualizarEstado(Postulacion postulacion, Postulacion.EstadoPostulacion nuevoEstado) {
        postulacion.setEstado(nuevoEstado);

        PostulacionEstadoActualizado evento = new PostulacionEstadoActualizado(
                postulacion.getId(),
                postulacion.getUsuarioId(),
                nuevoEstado.name()
        );
        // publicar evento
    }
}

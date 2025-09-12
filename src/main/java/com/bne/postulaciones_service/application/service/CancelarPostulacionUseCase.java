package com.bne.postulaciones_service.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bne.postulaciones_service.application.dto.PostulacionResponseDto;
import com.bne.postulaciones_service.application.exception.ConflictException;
import com.bne.postulaciones_service.application.exception.NotFoundException;
import com.bne.postulaciones_service.domain.model.Postulacion;
import com.bne.postulaciones_service.domain.model.Usuario;
import com.bne.postulaciones_service.domain.repository.PostulacionRepository;
import com.bne.postulaciones_service.domain.repository.UsuarioRepository;
import com.bne.postulaciones_service.domain.service.PostulacionDomainService;

@Service
public class CancelarPostulacionUseCase {

    private final PostulacionRepository postulacionRepository;
    private final UsuarioRepository usuarioRepository;
    private final PostulacionDomainService postulacionDomainService;

    public CancelarPostulacionUseCase(
            PostulacionRepository postulacionRepository,
            UsuarioRepository usuarioRepository,
            PostulacionDomainService postulacionDomainService
    ) {
        this.postulacionRepository = postulacionRepository;
        this.usuarioRepository = usuarioRepository;
        this.postulacionDomainService = postulacionDomainService;
    }

    /**
     * Método existente (void). Se mantiene para compatibilidad.
     */
    @Transactional
    public void cancelar(Long postulacionId, Long usuarioId) {
        cancelarYDevolver(postulacionId, usuarioId);
    }

    /**
     * NUEVO: el que está usando tu controller.
     * Cancela la postulación y devuelve un DTO con el estado actualizado.
     */
    @Transactional
    public PostulacionResponseDto ejecutar(Long postulacionId, Long usuarioId) {
        Postulacion p = cancelarYDevolver(postulacionId, usuarioId);
        return toDto(p);
    }

    // --------- Privados de apoyo ---------

    private Postulacion cancelarYDevolver(Long postulacionId, Long usuarioId) {
        Postulacion postulacion = postulacionRepository.findById(postulacionId)
                .orElseThrow(() -> new NotFoundException("Postulación no encontrada"));

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));

        try {
            postulacionDomainService.cancelarPostulacion(postulacion, usuario);
        } catch (IllegalStateException e) {
            throw new ConflictException(e.getMessage());
        }

        return postulacionRepository.save(postulacion);
    }

    private PostulacionResponseDto toDto(Postulacion p) {
        return new PostulacionResponseDto(
                p.getId(),
                p.getUsuarioId(),
                p.getEmpresaId(),
                p.getOfertaId(),
                p.getFechaPostulacion(),
                p.getEstado() != null ? p.getEstado().name() : null
        );
    }
}

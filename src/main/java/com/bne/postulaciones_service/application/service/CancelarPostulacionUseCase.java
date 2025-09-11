package com.bne.postulaciones_service.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bne.postulaciones_service.domain.repository.*;
import com.bne.postulaciones_service.domain.service.PostulacionDomainService;
import com.bne.postulaciones_service.domain.model.Postulacion;
import com.bne.postulaciones_service.domain.model.Usuario;
import com.bne.postulaciones_service.application.exception.*;


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

    @Transactional
    public void cancelar(Long postulacionId, Long usuarioId) {
        Postulacion postulacion = postulacionRepository.findById(postulacionId)
                .orElseThrow(() -> new NotFoundException("Postulación no encontrada"));

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));

        try {
            postulacionDomainService.cancelarPostulacion(postulacion, usuario);
        } catch (IllegalStateException e) {
            throw new ConflictException(e.getMessage());
        }

        postulacionRepository.save(postulacion);
    }

}

package com.bne.postulaciones_service.application.service;


import com.bne.postulaciones_service.application.exception.NotFoundException;
import com.bne.postulaciones_service.domain.model.Postulacion;
import com.bne.postulaciones_service.domain.repository.PostulacionRepository;
import com.bne.postulaciones_service.domain.repository.UsuarioRepository;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional(readOnly = true)
public class ListarPostulacionesPorUsuarioUseCase {
    private final PostulacionRepository postulacionRepository;
    private final UsuarioRepository usuarioRepository;

    public ListarPostulacionesPorUsuarioUseCase(PostulacionRepository postulacionRepository, UsuarioRepository usuarioRepository) {
        this.postulacionRepository = postulacionRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<Postulacion> ejecutar(Long usuarioId) {
        usuarioRepository.findById(usuarioId).orElseThrow(()->new NotFoundException("Usuario no encontrado"));
        return postulacionRepository.findByUsuarioId(usuarioId);
    }

}

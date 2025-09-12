package com.bne.postulaciones_service.application.service;


import com.bne.postulaciones_service.application.exception.NotFoundException;
import com.bne.postulaciones_service.domain.model.Postulacion;
import com.bne.postulaciones_service.domain.repository.PostulacionRepository;
import com.bne.postulaciones_service.domain.repository.UsuarioRepository;
import java.util.List;


public class ListarPostulacionesPorUsuarioUseCase {
    private final PostulacionRepository postulacionRepository;
    private final UsuarioRepository usuarioRepository;

    public ListarPostulacionesPorUsuarioUseCase(PostulacionRepository postulacionRepository, UsuarioRepository usuarioRepository) {
        this.postulacionRepository = postulacionRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<Postulacion> ejecutar(String usuarioId) {
        // Validar que el usuario existe
        if (!usuarioRepository.findById(Long.valueOf(usuarioId))) {
            throw new NotFoundException("Usuario no encontrado");
        }

        // Obtener las postulaciones del usuario
        return postulacionRepository.findByUsuarioId(usuarioId);
    }

}
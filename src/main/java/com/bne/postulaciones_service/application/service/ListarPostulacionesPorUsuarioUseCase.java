package com.bne.postulaciones_service.application.service;


import com.bne.postulaciones_service.application.exception.NotFoundException;
import com.bne.postulaciones_service.domain.model.Postulacion;
import com.bne.postulaciones_service.domain.repository.PostulacionRepository;
import com.bne.postulaciones_service.domain.repository.UsersRepository;
import java.util.List;


public class ListarPostulacionesPorUsuarioUseCase {
    private final PostulacionRepository postulacionRepository;
    private final UsersRepository usersRepository;

    public ListarPostulacionesPorUsuarioUseCase(PostulacionRepository postulacionRepository, UsersRepository usersRepository) {
        this.postulacionRepository = postulacionRepository;
        this.usersRepository = usersRepository;
    }

    public List<Postulacion> ejecutar(String usuarioId) {
        // Validar que el usuario existe
        if (!usersRepository.existeUsuario(usuarioId)) {
            throw new NotFoundException("Usuario no encontrado");
        }

        // Obtener las postulaciones del usuario
        return postulacionRepository.findByUsuarioID(usuarioId);
    }

}

package com.bne.postulaciones_service.domain.repository;

import com.bne.postulaciones.domain.model.Postulacion;
import java.util.List;
import java.util.Optional;

/**
 * Repositorio de dominio para gestionar las postulaciones
 */
public interface PostulacionRepository {

    /**
     * Guarda o actualiza una postulacion
     */
    Postulacion save(Postulacion postulacion);

    /**
     * Busca una postulacion por su id
     */
    Optional<Postulacion> findById(Long id);

    /**
     * Obtiene todas las postulaciones asociadas a un usuario
     */
    List<Postulacion> findByUsuarioId(Long usuarioId);

    /**
     * Elimina una postulacion
     */
    void deleteById(Long id);
}

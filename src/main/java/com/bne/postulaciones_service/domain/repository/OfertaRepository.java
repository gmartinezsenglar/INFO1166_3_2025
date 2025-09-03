package com.bne.postulaciones_service.domain.repository;

import com.bne.postulaciones.domain.model.OfertaEmpleo;
import java.util.List;
import java.util.Optional;

/**
 * Repositorio de dominio para gestionar las ofertas de empleo
 */
public interface OfertaRepository {

    /**
     * Busca una oferta por su id
     */
    Optional<OfertaEmpleo> findById(Long id);

    /**
     * Lista todas las ofertas disponibles
     */
    List<OfertaEmpleo> findAll();

    /**
     * Guarda o actualiza una oferta de empleo
     */
    OfertaEmpleo save(OfertaEmpleo oferta);

    /**
     * Elimina una oferta por su id
     */
    void deleteById(Long id);
}

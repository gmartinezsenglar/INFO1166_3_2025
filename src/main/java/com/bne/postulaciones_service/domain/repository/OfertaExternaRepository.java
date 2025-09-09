package com.bne.postulaciones_service.domain.repository;

import com.bne.postulaciones_service.domain.model.OfertaExterna;

import java.util.List;
import java.util.Optional;

public interface OfertaExternaRepository {
    Optional<OfertaExterna> findById(Long id);
    List<OfertaExterna> buscarConFiltros(OfertaFilter filter);
    OfertaExterna save(OfertaExterna oferta);
}
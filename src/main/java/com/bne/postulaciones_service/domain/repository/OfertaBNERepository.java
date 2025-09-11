package com.bne.postulaciones_service.domain.repository;

import com.bne.postulaciones_service.domain.model.OfertaBNE;

import java.util.List;
import java.util.Optional;

public interface OfertaBNERepository {
    Optional<OfertaBNE> findById(Long id);
    List<OfertaBNE> buscarConFiltros(OfertaFilter filter);
    OfertaBNE save(OfertaBNE oferta);
}
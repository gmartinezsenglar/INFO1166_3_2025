package com.bne.postulaciones_service.infrastructure.repository;

import com.bne.postulaciones_service.domain.model.OfertaBNE;
import com.bne.postulaciones_service.domain.repository.OfertaBNERepository;
import com.bne.postulaciones_service.domain.repository.OfertaFilter;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OfertaBNERepositoryAdapter implements OfertaBNERepository {
    private final JpaOfertaBNERepository jpa;
    public OfertaBNERepositoryAdapter(JpaOfertaBNERepository jpa) { this.jpa = jpa; }

    @Override public Optional<OfertaBNE> findById(Long id) { return jpa.findById(id); }

    @Override
    public List<OfertaBNE> buscarConFiltros(OfertaFilter f) {
        // TODO: implementar filtros (Specification/Criteria/queries derivadas)
        return jpa.findAll();
    }

    @Override public OfertaBNE save(OfertaBNE oferta) { return jpa.save(oferta); }
}

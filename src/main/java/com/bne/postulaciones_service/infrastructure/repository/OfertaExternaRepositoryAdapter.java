package com.bne.postulaciones_service.infrastructure.repository;

import com.bne.postulaciones_service.domain.model.OfertaExterna;
import com.bne.postulaciones_service.domain.repository.OfertaExternaRepository;
import com.bne.postulaciones_service.domain.repository.OfertaFilter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.bne.postulaciones_service.infrastructure.repository.spec.OfertaSpecifications.externaFrom;

@Repository
public class OfertaExternaRepositoryAdapter implements OfertaExternaRepository {
    private final JpaOfertaExternaRepository jpa;
    public OfertaExternaRepositoryAdapter(JpaOfertaExternaRepository jpa) { this.jpa = jpa; }

    @Override public Optional<OfertaExterna> findById(Long id) { return jpa.findById(id); }

    @Override
    public List<OfertaExterna> buscarConFiltros(OfertaFilter f) {
        Specification<OfertaExterna> spec = externaFrom(f);
        return jpa.findAll(spec);
    }

    @Override public OfertaExterna save(OfertaExterna oferta) { return jpa.save(oferta); }
}

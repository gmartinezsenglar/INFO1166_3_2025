package com.bne.postulaciones_service.infrastructure.repository;

import com.bne.postulaciones_service.domain.model.OfertaBNE;
import com.bne.postulaciones_service.domain.repository.OfertaBNERepository;
import com.bne.postulaciones_service.domain.repository.OfertaFilter;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// infraestructura/repository/OfertaBNERepositoryAdapter.java
import static com.bne.postulaciones_service.infrastructure.repository.spec.OfertaSpecifications.bneFrom;
import org.springframework.data.domain.*;

@Repository
public class OfertaBNERepositoryAdapter implements OfertaBNERepository {
    private final JpaOfertaBNERepository jpa;
    public OfertaBNERepositoryAdapter(JpaOfertaBNERepository jpa) { this.jpa = jpa; }

    @Override public Optional<OfertaBNE> findById(Long id) { return jpa.findById(id); }

    // Versión simple (lista completa)
    @Override public List<OfertaBNE> buscarConFiltros(OfertaFilter f) { return jpa.findAll(bneFrom(f)); }

    // **Sugerido**: agrega sobrecarga paginada (útil para Aplicación/REST)
    public Page<OfertaBNE> buscarConFiltros(OfertaFilter f, Pageable pageable) {
        return jpa.findAll(bneFrom(f), pageable);
    }

    @Override public OfertaBNE save(OfertaBNE oferta) { return jpa.save(oferta); }
}

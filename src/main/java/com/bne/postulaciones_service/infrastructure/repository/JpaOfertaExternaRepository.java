package com.bne.postulaciones_service.infrastructure.repository;

import com.bne.postulaciones_service.domain.model.OfertaExterna;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaOfertaExternaRepository extends JpaRepository<OfertaExterna, Long> { }

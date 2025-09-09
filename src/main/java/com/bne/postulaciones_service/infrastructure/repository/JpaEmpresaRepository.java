package com.bne.postulaciones_service.infrastructure.repository;

import com.bne.postulaciones_service.domain.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaEmpresaRepository extends JpaRepository<Empresa, Long> {
}

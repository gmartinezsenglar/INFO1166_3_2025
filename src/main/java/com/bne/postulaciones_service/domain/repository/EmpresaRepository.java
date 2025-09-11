package com.bne.postulaciones_service.domain.repository;

import com.bne.postulaciones_service.domain.model.Empresa;

import java.util.List;
import java.util.Optional;

public interface EmpresaRepository {
    Optional<Empresa> findById(Long id);
    List<Empresa> findAll();
    Empresa save(Empresa empresa);
}
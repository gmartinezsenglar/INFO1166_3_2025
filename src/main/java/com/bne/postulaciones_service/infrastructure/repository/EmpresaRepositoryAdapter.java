package com.bne.postulaciones_service.infrastructure.repository;

import com.bne.postulaciones_service.domain.model.Empresa;
import com.bne.postulaciones_service.domain.repository.EmpresaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EmpresaRepositoryAdapter implements EmpresaRepository {
    private final JpaEmpresaRepository jpa;
    public EmpresaRepositoryAdapter(JpaEmpresaRepository jpa) { this.jpa = jpa; }

    @Override public Optional<Empresa> findById(Long id) { return jpa.findById(id); }
    @Override public List<Empresa> findAll() { return jpa.findAll(); }
    @Override public Empresa save(Empresa empresa) { return jpa.save(empresa); }
}

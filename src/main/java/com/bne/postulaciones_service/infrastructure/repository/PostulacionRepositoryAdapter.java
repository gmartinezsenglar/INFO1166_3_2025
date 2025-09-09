package com.bne.postulaciones_service.infrastructure.repository;

import com.bne.postulaciones_service.domain.model.Postulacion;
import com.bne.postulaciones_service.domain.repository.PostulacionRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PostulacionRepositoryAdapter implements PostulacionRepository {
    private final JpaPostulacionRepository jpa;
    public PostulacionRepositoryAdapter(JpaPostulacionRepository jpa) { this.jpa = jpa; }

    @Override public Optional<Postulacion> findById(Long id) { return jpa.findById(id); }
    @Override public List<Postulacion> findByUsuarioId(Long usuarioId) { return jpa.findByUsuarioId(usuarioId); }
    @Override public List<Postulacion> findByEmpresaId(Long empresaId) { return jpa.findByEmpresaId(empresaId); }
    @Override public Postulacion save(Postulacion postulacion) { return jpa.save(postulacion); }
}

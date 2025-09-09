package com.bne.postulaciones_service.infrastructure.repository;

import com.bne.postulaciones_service.domain.model.Usuario;
import com.bne.postulaciones_service.domain.repository.UsuarioRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UsuarioRepositoryAdapter implements UsuarioRepository {
    private final JpaUsuarioRepository jpa;
    public UsuarioRepositoryAdapter(JpaUsuarioRepository jpa) { this.jpa = jpa; }

    @Override public Optional<Usuario> findById(Long id) { return jpa.findById(id); }
    @Override public List<Usuario> findAll() { return jpa.findAll(); }
    @Override public Usuario save(Usuario usuario) { return jpa.save(usuario); }
}

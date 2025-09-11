package com.bne.postulaciones_service.domain.repository;

import com.bne.postulaciones_service.domain.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository {
    Optional<Usuario> findById(Long id);
    List<Usuario> findAll();
    Usuario save(Usuario usuario);
}
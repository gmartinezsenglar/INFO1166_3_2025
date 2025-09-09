package com.bne.postulaciones_service.domain.repository;

import com.bne.postulaciones_service.domain.model.Postulacion;

import java.util.List;
import java.util.Optional;

public interface PostulacionRepository {
    Optional<Postulacion> findById(Long id);
    List<Postulacion> findByUsuarioId(Long usuarioId);
    List<Postulacion> findByEmpresaId(Long empresaId);
    Postulacion save(Postulacion postulacion);
}
package com.bne.postulaciones_service.infrastructure.repository;

import com.bne.postulaciones_service.domain.model.Postulacion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface JpaPostulacionRepository extends JpaRepository<Postulacion, Long> {
    List<Postulacion> findByUsuarioId(Long usuarioId);
    List<Postulacion> findByEmpresaId(Long empresaId);
}

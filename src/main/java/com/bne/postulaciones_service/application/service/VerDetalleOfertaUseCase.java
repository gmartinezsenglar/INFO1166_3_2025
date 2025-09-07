package com.bne.postulaciones_service.application.service;

import com.bne.postulaciones_service.application.dto.OfertaDetalleDto;
import com.bne.postulaciones_service.domain.model.OfertaEmpleo;
import com.bne.postulaciones_service.domain.repository.OfertaRepository;
import com.bne.postulaciones_service.shared.exceptions.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class VerDetalleOfertaUseCase {

    private final OfertaRepository ofertaRepository;

    public VerDetalleOfertaUseCase(OfertaRepository ofertaRepository) {
        this.ofertaRepository = ofertaRepository;
    }

    public OfertaDetalleDto ejecutar(Long id) {
        Oferta oferta = ofertaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("La oferta con id " + id + " no existe."));

        return convertirDetalleDto(oferta);
    }

    private OfertaDetalleDto convertirDetalleDto(OfertaEmpleo oferta) {
        return new OfertaDetalleDto(
                oferta.getId(),
                oferta.getTitulo(),
                oferta.getDescripcion(),
                oferta.getFechaPublicacion().toString(),
                oferta.getEmpresa()
        );
    }
}

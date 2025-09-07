package com.bne.postulaciones_service.application.service;

import com.bne.postulaciones_service.application.dto.OfertaDto;
import com.bne.postulaciones_service.application.exception.NotFoundException;
import com.bne.postulaciones_service.domain.model.OfertaEmpleo;
import com.bne.postulaciones_service.domain.repository.OfertaFilter;
import com.bne.postulaciones_service.domain.repository.OfertaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class BuscarOfertasUseCase {

    private final OfertaRepository ofertaRepository;

    public BuscarOfertasUseCase(OfertaRepository ofertaRepository) {

        this.ofertaRepository = ofertaRepository;
    }

    public List<OfertaDto> buscarConFiltros(OfertaFilter filter) {
        List<OfertaEmpleo> ofertas = ofertaRepository.buscarConFiltros(filter);

        return ofertas.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public OfertaDto buscarPorId(Long id) {
        return ofertaRepository.findById(id)
                .map(this::mapToDto)
                .orElseThrow(() -> new NotFoundException("La oferta con id " + id + " no existe"));
    }

    private OfertaDto mapToDto(OfertaEmpleo oferta) {
        return new OfertaDto(
                oferta.getId(),
                oferta.getNombre(),
                oferta.getDescripcion(),
                oferta.getTipoContrato(),
                oferta.getEmpresa().getId(),
                oferta.getEmpresa().getNombre()
        );
    }
}



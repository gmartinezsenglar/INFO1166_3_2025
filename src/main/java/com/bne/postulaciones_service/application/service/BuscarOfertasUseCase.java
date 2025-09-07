package com.bne.postulaciones_service.application.service;

import com.bne.postulaciones_service.application.dto.OfertaDto;
import com.bne.postulaciones_service.domain.model.OfertaEmpleo;
import com.bne.postulaciones_service.domain.repository.OfertaRepository;
import com.bne.postulaciones_service.shared.exceptions.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)

public class BuscarOfertasUseCase {

    private final OfertaRepository ofertaRepository;

    public BuscarOfertasUseCase(OfertaRepository ofertaRepository) {
        this.ofertaRepository = ofertaRepository;
    }

    public OfertaDto buscarPorId(Long id) {
        OfertaEmpleo oferta = ofertaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("La oferta con id " + id + " no existe."));

        return new OfertaDto(
                oferta.getId(),
                oferta.getTitulo(),
                oferta.getDescripcion()
        );
    }

    public List<OfertaDto> buscarTodas() {
        List <OfertaEmpleo> ofertas = ofertaRepository.findAll();

        return ofertas.stream().map(oferta -> new OfertaDto(oferta.getId(),
                oferta.getTitulo(),oferta.getDescripcion())).collect(Collectors.toList());
    }
}

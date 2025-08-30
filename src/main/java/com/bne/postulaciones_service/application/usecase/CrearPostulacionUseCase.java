package com.bne.postulaciones_service.application.usecase;

import com.bne.postulaciones_service.application.dto.PostulacionRequestDto;
import com.bne.postulaciones_service.application.dto.PostulacionResponseDto;
import com.bne.postulaciones_service.domain.repository.PostulacionRepository;
import com.bne.postulaciones_service.domain.repository.OfertaRepository;
import com.bne.postulaciones_service.domain.model.Postulacion;
import com.bne.postulaciones_service.shared.exception.NotFoundException;
import com.bne.postulaciones_service.shared.exception.ConflictException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CrearPostulacionUseCase {

    private final PostulacionRepository postulacionRepository;
    private final OfertaRepository ofertaRepository;

    public CrearPostulacionUseCase(PostulacionRepository postulacionRepository,
                                   OfertaRepository ofertaRepository) {
        this.postulacionRepository = postulacionRepository;
        this.ofertaRepository = ofertaRepository;
    }

    @Transactional
    public PostulacionResponseDto ejecutar(PostulacionRequestDto request) {
        var oferta = ofertaRepository.findById(request.getOfertaId())
                .orElseThrow(() -> new NotFoundException("La oferta no existe."));

        if (postulacionRepository.existsByUsuarioIdAndOfertaId(request.getUsuarioId(), oferta.getId())) {
            throw new ConflictException("El usuario ya se ha postulado a esta oferta.");
        }

        Postulacion postulacion = new Postulacion(request.getUsuarioId(), oferta);
        postulacionRepository.save(postulacion);

        return new PostulacionResponseDto(postulacion.getId(), postulacion.getEstado().name());
    }
}
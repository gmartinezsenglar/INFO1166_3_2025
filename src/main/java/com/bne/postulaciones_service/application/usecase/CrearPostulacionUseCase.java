package com.bne.postulaciones_service.application.usecase;

import com.bne.postulaciones_service.application.dto.PostulacionRequestDto;
import com.bne.postulaciones_service.application.dto.PostulacionResponseDto;
import com.bne.postulaciones_service.domain.repository.PostulacionRepository;
import com.bne.postulaciones_service.domain.repository.OfertaRepository;
import com.bne.postulaciones_service.domain.model.Postulacion;
import com.bne.postulaciones_service.domain.model.UsuarioPostulante;
import com.bne.postulaciones_service.domain.model.OfertaEmpleo;
import com.bne.postulaciones_service.shared.exceptions.NotFoundException;
import com.bne.postulaciones_service.shared.exceptions.ConflictException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CrearPostulacionUseCase {

    private final PostulacionRepository postulacionRepository;
    private final OfertaRepository ofertaRepository;

    public CrearPostulacionUseCase(PostulacionRepository postulacionRepository, OfertaRepository ofertaRepository) {
        this.postulacionRepository = postulacionRepository;
        this.ofertaRepository = ofertaRepository;
    }

    @Transactional
    public PostulacionResponseDto ejecutar(PostulacionRequestDto request) {
        OfertaEmpleo oferta = ofertaRepository.findById(request.getOfertaId())
                .orElseThrow(() -> new NotFoundException("La oferta no existe."));

        UsuarioPostulante postulante = new UsuarioPostulante(request.getUsuarioId());

        if (postulacionRepository.existsByUsuarioIdAndOfertaId(request.getUsuarioId(), oferta.getId())) {
            throw new ConflictException("El usuario ya se ha postulado a esta oferta.");
        }

        Postulacion postulacion = new Postulacion(postulante, oferta);
        postulacionRepository.save(postulacion);

        return new PostulacionResponseDto(
                postulacion.getId(),
                postulante.getId(),
                oferta.getId(),
                postulacion.getEstado(),
                postulacion.getFechaPostulacion(),
                postulacion.getFechaUltimaActualizacion()
        );
    }
}

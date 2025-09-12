package com.bne.postulaciones_service.application.service;

import com.bne.postulaciones_service.application.dto.PostulacionRequestDto;
import com.bne.postulaciones_service.application.dto.PostulacionResponseDto;
import com.bne.postulaciones_service.application.exception.ConflictException;
import com.bne.postulaciones_service.application.exception.NotFoundException;
import com.bne.postulaciones_service.domain.model.*;
import com.bne.postulaciones_service.domain.repository.*;
import com.bne.postulaciones_service.domain.service.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CrearPostulacionUseCase {

    private final PostulacionRepository postulacionRepository;
    private final UsuarioRepository usuarioRepository;
    private final OfertaBNERepository ofertaBNERepository;
    private final OfertaExternaRepository ofertaExternaRepository;
    private final PostulacionDomainService postulacionDomainService;

    public CrearPostulacionUseCase(PostulacionRepository postulacionRepository,
                                   UsuarioRepository usuarioRepository,
                                   OfertaBNERepository ofertaBNERepository,
                                   OfertaExternaRepository ofertaExternaRepository,
                                   PostulacionDomainService postulacionDomainService) {
        this.postulacionRepository = postulacionRepository;
        this.usuarioRepository = usuarioRepository;
        this.ofertaBNERepository = ofertaBNERepository;
        this.ofertaExternaRepository = ofertaExternaRepository;
        this.postulacionDomainService = postulacionDomainService;
    }

    public PostulacionResponseDto ejecutar(PostulacionRequestDto request) {
        Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() -> new NotFoundException("El usuario no existe."));

        Postulacion postulacion;

        try {
            switch (request.getOfertaId().getOrigen()) {
                case BNE -> {
                    OfertaBNE oferta = ofertaBNERepository.findById(request.getOfertaId().getValor())
                            .orElseThrow(() -> new NotFoundException("La oferta BNE no existe"));
                    postulacion = postulacionDomainService.crearPostulacion(usuario, oferta);
                }
                case EXTERNA -> {
                    OfertaExterna oferta = ofertaExternaRepository.findById(request.getOfertaId().getValor())
                            .orElseThrow(() -> new NotFoundException("La oferta externa no existe"));
                    postulacion = postulacionDomainService.crearPostulacionExterna(usuario, oferta);
                }
                default -> throw new ConflictException("Origen de oferta no soportado.");
            }
        } catch (IllegalStateException e) {
            throw new ConflictException(e.getMessage());
        }

        postulacionRepository.save(postulacion);

        return new PostulacionResponseDto(
                postulacion.getId(),
                postulacion.getUsuarioId(),
                postulacion.getEmpresaId(),
                postulacion.getOfertaId(),
                postulacion.getFechaPostulacion(),
                postulacion.getEstado().name()
        );
    }
}

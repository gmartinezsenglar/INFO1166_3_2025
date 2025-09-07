package com.bne.postulaciones_service.application.service;
import com.bne.postulaciones_service.domain.model.OfertaEmpleo;
import com.bne.postulaciones_service.domain.model.Postulacion;
import com.bne.postulaciones_service.domain.model.Usuario;
import com.bne.postulaciones_service.application.dto.PostulacionRequestDto;
import com.bne.postulaciones_service.application.dto.PostulacionResponseDto;
import com.bne.postulaciones_service.domain.repository.PostulacionRepository;
import com.bne.postulaciones_service.domain.repository.OfertaRepository;
import com.bne.postulaciones_service.domain.repository.UsuarioRepository;
import com.bne.postulaciones_service.application.exception.NotFoundException;
import com.bne.postulaciones_service.application.exception.ConflictException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CrearPostulacionUseCase {

    private final PostulacionRepository postulacionRepository;
    private final OfertaRepository ofertaRepository;
    private final UsuarioRepository usuarioRepository;

    public CrearPostulacionUseCase(
            PostulacionRepository postulacionRepository,
            OfertaRepository ofertaRepository,
            UsuarioRepository usuarioRepository
    ) {
        this.postulacionRepository = postulacionRepository;
        this.ofertaRepository = ofertaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public PostulacionResponseDto ejecutar(PostulacionRequestDto request) {
        OfertaEmpleo oferta = ofertaRepository.findById(request.getOfertaId())
                .orElseThrow(() -> new NotFoundException("La oferta no existe."));

        Usuario postulante = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() -> new NotFoundException("El usuario no existe."));

        if (oferta.getEmpresa() == null) {
            throw new ConflictException("La oferta no tiene empresa asociada.");
        }

        oferta.validarDisponibilidadParaPostulacion();

        if (postulacionRepository.existsByUsuarioIdAndOfertaId(postulante.getId(), oferta.getId())) {
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

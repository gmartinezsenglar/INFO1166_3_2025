package com.bne.postulaciones_service.application.service;

import com.bne.postulaciones_service.application.dto.*;
import com.bne.postulaciones_service.application.exception.NotFoundException;
import com.bne.postulaciones_service.domain.model.OfertaBNE;
import com.bne.postulaciones_service.domain.model.OfertaExterna;
import com.bne.postulaciones_service.domain.model.vo.OfertaId;
import com.bne.postulaciones_service.domain.repository.OfertaBNERepository;
import com.bne.postulaciones_service.domain.repository.OfertaExternaRepository;
import com.bne.postulaciones_service.domain.service.OfertaDomainService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class DetalleOfertaUseCase {

    private final OfertaBNERepository ofertaBNERepository;
    private final OfertaExternaRepository ofertaExternaRepository;
    private final OfertaDomainService ofertaDomainService;

    public DetalleOfertaUseCase(OfertaBNERepository ofertaBNERepository,
                                OfertaExternaRepository ofertaExternaRepository,
                                OfertaDomainService ofertaDomainService) {
        this.ofertaBNERepository = ofertaBNERepository;
        this.ofertaExternaRepository = ofertaExternaRepository;
        this.ofertaDomainService = ofertaDomainService;
    }

    public Object ejecutar(OfertaId ofertaId) {
        return switch (ofertaId.getOrigen()) {
            case BNE -> obtenerDetalleBNE(ofertaId.getValor());
            case EXTERNA -> obtenerDetalleExterna(ofertaId.getValor());
            default -> throw new NotFoundException("Origen de oferta no soportado");
        };
    }

    private DetalleOfertaBneDto obtenerDetalleBNE(Long id) {
        OfertaBNE oferta = ofertaBNERepository.findById(id)
                .orElseThrow(() -> new NotFoundException("La oferta BNE con id " + id + " no existe"));

        if (!ofertaDomainService.esPublicable(oferta)) {
            throw new NotFoundException("La oferta BNE con id " + id + " no es publicable");
        }

        String ciudad = Optional.ofNullable(oferta.getUbicacion())
                .filter(u -> u.esValida())
                .map(u -> u.getCiudad())
                .orElse("Sin ciudad");

        String region = Optional.ofNullable(oferta.getUbicacion())
                .filter(u -> u.esValida())
                .map(u -> u.getRegion())
                .orElse("Sin región");

        Integer minimo = Optional.ofNullable(oferta.getRangoSalarial())
                .map(r -> r.getMinimo())
                .orElse(null);

        Integer maximo = Optional.ofNullable(oferta.getRangoSalarial())
                .map(r -> r.getMaximo())
                .orElse(null);

        LocalDate fechaInicio = Optional.ofNullable(oferta.getPeriodoVigencia())
                .map(p -> p.getFechaInicio())
                .orElse(null);

        LocalDate fechaFin = Optional.ofNullable(oferta.getPeriodoVigencia())
                .map(p -> p.getFechaTermino())
                .orElse(null);

        return new DetalleOfertaBneDto(
                oferta.getId(),
                oferta.getEmpresa().getId(),
                oferta.getEmpresa().getNombre(),
                oferta.getNombre(),
                oferta.getDescripcion(),
                oferta.getVacantesDisponibles(),
                ciudad,
                region,
                minimo,
                maximo,
                fechaInicio,
                fechaFin,
                oferta.requiereExperiencia(),
                oferta.requiereNivelEducacional(),
                oferta.getTipoNivelEducacional(),
                oferta.getTipoContrato(),
                oferta.getTipoJornada(),
                oferta.getNivelCargo(),
                oferta.esPracticaProfesional(),
                oferta.estaVigente(),
                oferta.tieneVacantesDisponibles()
        );
    }

    private DetalleOfertaExternaDto obtenerDetalleExterna(Long id) {
        OfertaExterna oferta = ofertaExternaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("La oferta externa con id " + id + " no existe"));

        if (!ofertaDomainService.esPublicable(oferta)) {
            throw new NotFoundException("La oferta externa con id " + id + " no es publicable");
        }

        String ciudad = (oferta.getUbicacion() != null && oferta.getUbicacion().esValida())
                ? oferta.getUbicacion().getCiudad() : "Sin ciudad";

        String region = (oferta.getUbicacion() != null && oferta.getUbicacion().esValida())
                ? oferta.getUbicacion().getRegion() : "Sin región";

        Integer minimo = (oferta.getRangoSalarial() != null) ? oferta.getRangoSalarial().getMinimo() : null;
        Integer maximo = (oferta.getRangoSalarial() != null) ? oferta.getRangoSalarial().getMaximo() : null;

        var periodo = oferta.getPeriodoVigencia();
        var fechaInicio = (periodo != null) ? periodo.getFechaInicio() : null;
        var fechaFin = (periodo != null) ? periodo.getFechaTermino() : null;

        String urlFuente = oferta.getUrlFuente();

        return new DetalleOfertaExternaDto(
                oferta.getId(),
                oferta.getEmpresa().getId(),
                oferta.getEmpresa().getNombre(),
                oferta.getNombre(),
                oferta.getDescripcion(),
                oferta.getVacantesDisponibles(),
                ciudad,
                region,
                minimo,
                maximo,
                fechaInicio,
                fechaFin,
                oferta.getNombrePublicador(),
                oferta.requiereExperienciaLaboral(),
                oferta.getTipoNivelEducacional(),
                oferta.getTipoContrato(),
                oferta.getTipoJornada(),
                oferta.getNivelCargo(),
                oferta.getOrigenOferta(),
                urlFuente,
                oferta.estaVigente(),
                oferta.tieneVacantesDisponibles()
        );
    }
}
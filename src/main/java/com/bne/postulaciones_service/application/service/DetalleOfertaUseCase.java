package com.bne.postulaciones_service.application.service;

import com.bne.postulaciones_service.application.dto.DetalleOfertaBneDto;
import com.bne.postulaciones_service.application.dto.DetalleOfertaExternaDto;
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

        String ciudad = (oferta.getUbicacion() != null && oferta.getUbicacion().esValida())
                ? oferta.getUbicacion().getCiudad() : "Sin ciudad";

        String region = (oferta.getUbicacion() != null && oferta.getUbicacion().esValida())
                ? oferta.getUbicacion().getRegion() : "Sin región";

        Integer minimo = (oferta.getRangoSalarial() != null) ? oferta.getRangoSalarial().getMinimo() : null;
        Integer maximo = (oferta.getRangoSalarial() != null) ? oferta.getRangoSalarial().getMaximo() : null;

        LocalDate fechaInicio = (oferta.getPeriodoVigencia() != null) ? oferta.getPeriodoVigencia().getFechaInicio() : null;
        LocalDate fechaFin = (oferta.getPeriodoVigencia() != null) ? oferta.getPeriodoVigencia().getFechaTermino() : null;

        if (ofertaDomainService.esPublicable(oferta)) {
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
                    oferta.getOrigenOferta(),
                    oferta.esPracticaProfesional(),
                    oferta.estaVigente(),
                    oferta.tieneVacantesDisponibles()
            );
        } else {
            throw new NotFoundException("La oferta BNE con id " + id + " no es publicable");
        }
    }

    private DetalleOfertaExternaDto obtenerDetalleExterna(Long id) {
        OfertaExterna oferta = ofertaExternaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("La oferta externa con id " + id + " no existe"));

        String ciudad = (oferta.getUbicacion() != null && oferta.getUbicacion().esValida())
                ? oferta.getUbicacion().getCiudad() : "Sin ciudad";

        String region = (oferta.getUbicacion() != null && oferta.getUbicacion().esValida())
                ? oferta.getUbicacion().getRegion() : "Sin región";

        Integer minimo = (oferta.getRangoSalarial() != null) ? oferta.getRangoSalarial().getMinimo() : null;
        Integer maximo = (oferta.getRangoSalarial() != null) ? oferta.getRangoSalarial().getMaximo() : null;

        LocalDate fechaInicio = (oferta.getPeriodoVigencia() != null) ? oferta.getPeriodoVigencia().getFechaInicio() : null;
        LocalDate fechaFin = (oferta.getPeriodoVigencia() != null) ? oferta.getPeriodoVigencia().getFechaTermino() : null;

        String urlFuente = (oferta.getUrlFuente() != null) ? oferta.getUrlFuente().name() : null;
        String nombrePublicador = (oferta.getNombrePublicador() != null) ? oferta.getNombrePublicador() : null;

        if (ofertaDomainService.esPublicable(oferta)) {
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
                    nombrePublicador,
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
        } else {
            throw new NotFoundException("La oferta externa con id " + id + " no es publicable");
        }
    }
}
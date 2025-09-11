package com.bne.postulaciones_service.application.service;

import com.bne.postulaciones_service.application.dto.OfertaDto;
import com.bne.postulaciones_service.application.exception.*;
import com.bne.postulaciones_service.domain.model.OfertaBNE;
import com.bne.postulaciones_service.domain.model.OfertaExterna;
import com.bne.postulaciones_service.domain.model.vo.OfertaId;
import com.bne.postulaciones_service.domain.repository.OfertaBNERepository;
import com.bne.postulaciones_service.domain.repository.OfertaExternaRepository;
import com.bne.postulaciones_service.domain.repository.OfertaFilter;
import com.bne.postulaciones_service.domain.service.OfertaDomainService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class BuscarOfertasUseCase {

    private final OfertaBNERepository ofertaBNERepository;
    private final OfertaExternaRepository ofertaExternaRepository;
    private final OfertaDomainService ofertaDomainService;

    public BuscarOfertasUseCase(OfertaBNERepository ofertaBNERepository,
                                OfertaExternaRepository ofertaExternaRepository,
                                OfertaDomainService ofertaDomainService) {
        this.ofertaBNERepository = ofertaBNERepository;
        this.ofertaExternaRepository = ofertaExternaRepository;
        this.ofertaDomainService = ofertaDomainService;
    }

    public List<OfertaDto> buscarConFiltros(OfertaFilter filter, OfertaId.Origen origen) {
        return switch (origen) {
            case BNE -> ofertaBNERepository.buscarConFiltros(filter).stream()
                    .filter(oferta -> ofertaDomainService.esPublicable(oferta))
                    .map(oferta -> mapToDto(oferta))
                    .collect(Collectors.toList());

            case EXTERNA -> ofertaExternaRepository.buscarConFiltros(filter).stream()
                    .filter(oferta -> ofertaDomainService.esPublicable(oferta))
                    .map(oferta -> mapToDto(oferta))
                    .collect(Collectors.toList());
        };
    }

    public OfertaDto buscarPorId(OfertaId ofertaId) {
        return switch (ofertaId.getOrigen()) {
            case BNE -> ofertaBNERepository.findById(ofertaId.getValor())
                    .filter(oferta -> ofertaDomainService.esPublicable(oferta))
                    .map(oferta -> mapToDto(oferta))
                    .orElseThrow(() -> new NotFoundException(
                            "La oferta BNE con id " + ofertaId.getValor() + " no existe o no es publicable"));

            case EXTERNA -> ofertaExternaRepository.findById(ofertaId.getValor())
                    .filter(oferta -> ofertaDomainService.esPublicable(oferta))
                    .map(oferta -> mapToDto(oferta))
                    .orElseThrow(() -> new NotFoundException(
                            "La oferta externa con id " + ofertaId.getValor() + " no existe o no es publicable"));
        };
    }

    private OfertaDto mapToDto(OfertaBNE bne) {
        return new OfertaDto(
                bne.getId(),
                OfertaId.Origen.BNE.name(),
                bne.getNombre(),
                bne.getDescripcion(),
                bne.getTipoContrato(),
                bne.getEmpresa().getId(),
                bne.getEmpresa().getNombre()
        );
    }

    private OfertaDto mapToDto(OfertaExterna externa) {
        return new OfertaDto(
                externa.getId(),
                OfertaId.Origen.EXTERNA.name(),
                externa.getNombre(),
                externa.getDescripcion(),
                externa.getTipoContrato(),
                externa.getEmpresa().getId(),
                externa.getEmpresa().getNombre()
        );
    }
}

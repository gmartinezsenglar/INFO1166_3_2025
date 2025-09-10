package com.bne.postulaciones_service.application.service;

import com.bne.postulaciones_service.application.dto.OfertaDto;
import com.bne.postulaciones_service.application.exception.ConflictException;
import com.bne.postulaciones_service.application.exception.NotFoundException;
import com.bne.postulaciones_service.domain.repository.OfertaFilter;
import com.bne.postulaciones_service.domain.repository.OfertaBNERepository;
import com.bne.postulaciones_service.domain.repository.OfertaExternaRepository;
import com.bne.postulaciones_service.domain.model.OfertaId;
import com.bne.postulaciones_service.domain.model.OfertaBNE;
import com.bne.postulaciones_service.domain.model.OfertaExterna;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class BuscarOfertasUseCase {

    private final OfertaBNERepository ofertaBNERepository;
    private final OfertaExternaRepository ofertaExternaRepository;

    public BuscarOfertasUseCase(OfertaBNERepository ofertaBNERepository, OfertaExternaRepository ofertaExternaRepository) {
        this.ofertaBNERepository = ofertaBNERepository;
        this.ofertaExternaRepository = ofertaExternaRepository;
    }

    public List<OfertaDto> buscarConFiltros(OfertaFilter filter, OfertaId.Origen origen) {
        return switch (origen) {
            case BNE -> ofertaBNERepository.buscarConFiltros(filter)
                    .stream()
                    .map(oferta -> mapToDto(oferta, origen))
                    .collect(Collectors.toList());

            case EXTERNA -> ofertaExternaRepository.buscarConFiltros(filter)
                    .stream()
                    .map(oferta -> mapToDto(oferta, origen))
                    .collect(Collectors.toList());
        };
    }

    public OfertaDto buscarPorId(OfertaId ofertaId) {
        return switch (ofertaId.getOrigen()) {
            case BNE -> ofertaBNERepository.findById(ofertaId.getValor())
                    .map(oferta -> mapToDto(oferta, OfertaId.Origen.BNE))
                    .orElseThrow(() -> new NotFoundException("La oferta BNE con id " + ofertaId.getValor() + " no existe"));

            case EXTERNA -> ofertaExternaRepository.findById(ofertaId.getValor())
                    .map(oferta -> mapToDto(oferta, OfertaId.Origen.EXTERNA))
                    .orElseThrow(() -> new NotFoundException("La oferta externa con id " + ofertaId.getValor() + " no existe"));
        };
    }

    private OfertaDto mapToDto(Object oferta, OfertaId.Origen origen) {
        String origenStr = origen.name();

        if (oferta instanceof OfertaBNE bne) {
            return new OfertaDto(bne.getId(), origenStr, bne.getNombre(), bne.getDescripcion(), bne.getTipoContrato(),
                    bne.getEmpresa().getId(), bne.getEmpresa().getNombre());
        } else if (oferta instanceof OfertaExterna externa){
            return new OfertaDto(externa.getId(), origenStr, externa.getNombre(), externa.getDescripcion(), externa.getTipoContrato(),
                    externa.getEmpresa().getId(), externa.getEmpresa().getNombre());
        }
        throw new ConflictException("Tipo de oferta no soportado");
    }
}



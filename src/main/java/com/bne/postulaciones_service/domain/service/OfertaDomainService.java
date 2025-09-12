package com.bne.postulaciones_service.domain.service;

import com.bne.postulaciones_service.domain.model.OfertaBNE;
import com.bne.postulaciones_service.domain.model.OfertaExterna;
import com.bne.postulaciones_service.domain.event.OfertaCerrada;

import org.springframework.stereotype.Service;

@Service
public class OfertaDomainService{

    public boolean esPublicable(OfertaBNE oferta){
        return oferta.datosBasicosCompletos() &&
               oferta.rangoSalarialValido() &&
               oferta.estaVigente();
    }

    public boolean esPublicable(OfertaExterna oferta){
        return oferta.datosBasicosCompletos() &&
               oferta.rangoSalarialValido() &&
               oferta.tieneUrlValida() &&
               oferta.estaVigente();
    }

    public OfertaCerrada cerrarOfertaSiCorresponde(OfertaBNE oferta) {
        if (!oferta.estaVigente() || !oferta.tieneVacantesDisponibles()) {
            return new OfertaCerrada(oferta.getId());
        }
        return null;
    }

    public OfertaCerrada cerrarOfertaSiCorresponde(OfertaExterna oferta) {
        if (!oferta.estaVigente() || !oferta.tieneVacantesDisponibles()) {
            return new OfertaCerrada(oferta.getId());
        }
        return null;
    }
}

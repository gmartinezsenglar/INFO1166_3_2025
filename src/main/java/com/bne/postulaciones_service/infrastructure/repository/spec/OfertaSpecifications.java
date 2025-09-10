// infraestructura/repository/spec/OfertaSpecifications.java
package com.bne.postulaciones_service.infrastructure.repository.spec;

import com.bne.postulaciones_service.domain.model.OfertaBNE;
import com.bne.postulaciones_service.domain.model.OfertaExterna;
import com.bne.postulaciones_service.domain.repository.OfertaFilter;
import org.springframework.data.jpa.domain.Specification;

public class OfertaSpecifications {

    // Para strings: like case-insensitive
    private static Specification<?> likeIgnoreCase(String attr, String value) {
        return (root, q, cb) ->
                cb.like(cb.lower(root.get(attr)), "%" + value.toLowerCase() + "%");
    }

    public static Specification<OfertaBNE> bneFrom(OfertaFilter f) {
        Specification<OfertaBNE> spec = Specification.where(null);
        if (f.getNombre() != null && !f.getNombre().isBlank())
            spec = spec.and((Specification<OfertaBNE>) likeIgnoreCase("nombre", f.getNombre()));

        if (f.getRegion() != null && !f.getRegion().isBlank())
            spec = spec.and((r, q, cb) -> cb.equal(cb.lower(r.get("region")), f.getRegion().toLowerCase()));

        if (f.getCiudad() != null && !f.getCiudad().isBlank())
            spec = spec.and((Specification<OfertaBNE>) likeIgnoreCase("ciudad", f.getCiudad()));

        if (f.getPagaMinima() != null)
            spec = spec.and((r, q, cb) -> cb.greaterThanOrEqualTo(r.get("pagaMaxima"), f.getPagaMinima()));

        if (f.getPagaMaxima() != null)
            spec = spec.and((r, q, cb) -> cb.lessThanOrEqualTo(r.get("pagaMinima"), f.getPagaMaxima()));

        if (f.getReqExperiencia() != null)
            spec = spec.and((r, q, cb) -> cb.equal(r.get("requiereExperiencia"), f.getReqExperiencia()));

        if (f.getIsPracticaPro() != null)
            spec = spec.and((r, q, cb) -> cb.equal(r.get("practicaProfesional"), f.getIsPracticaPro()));

        if (f.getIsLey21015() != null)
            spec = spec.and((r, q, cb) -> cb.equal(r.get("ley21015"), f.getIsLey21015()));

        if (f.getTipoContrato() != null && !f.getTipoContrato().isBlank())
            spec = spec.and((r, q, cb) -> cb.equal(cb.lower(r.get("tipoContrato")), f.getTipoContrato().toLowerCase()));

        if (f.getNivelEducacional() != null && !f.getNivelEducacional().isBlank())
            spec = spec.and((r, q, cb) ->
                    cb.equal(cb.lower(r.get("tipoNivelEducacional")), f.getNivelEducacional().toLowerCase()));

        if (f.getTipoJornada() != null && !f.getTipoJornada().isBlank())
            spec = spec.and((r, q, cb) -> cb.equal(cb.lower(r.get("tipoJornada")), f.getTipoJornada().toLowerCase()));

        if (f.getOrigenOferta() != null && !f.getOrigenOferta().isBlank())
            spec = spec.and((r, q, cb) -> cb.equal(cb.lower(r.get("origenOferta")), f.getOrigenOferta().toLowerCase()));

        return spec;
    }

    public static Specification<OfertaExterna> externaFrom(OfertaFilter f) {
        // Mismos criterios, cambiando la clase objetivo
        Specification<OfertaExterna> spec = Specification.where(null);
        if (f.getNombre() != null && !f.getNombre().isBlank())
            spec = spec.and((r, q, cb) -> cb.like(cb.lower(r.get("nombre")), "%" + f.getNombre().toLowerCase() + "%"));
        // … replica el resto igual que en bneFrom(...)
        return spec;
    }
}

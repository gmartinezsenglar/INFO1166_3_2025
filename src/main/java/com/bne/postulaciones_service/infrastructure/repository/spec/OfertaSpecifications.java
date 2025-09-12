package com.bne.postulaciones_service.infrastructure.repository.spec;

import com.bne.postulaciones_service.domain.model.OfertaBNE;
import com.bne.postulaciones_service.domain.model.OfertaExterna;
import com.bne.postulaciones_service.domain.repository.OfertaFilter;
import org.springframework.data.jpa.domain.Specification;

public class OfertaSpecifications {

    private static <T> Specification<T> likeIgnoreCase(String attr, String value) {
        return (root, q, cb) -> cb.like(cb.lower(root.get(attr)), "%" + value.toLowerCase() + "%");
    }

    public static Specification<OfertaBNE> bneFrom(OfertaFilter f) {
        Specification<OfertaBNE> spec = Specification.where(null);

        if (has(f.getNombre())) spec = spec.and(likeIgnoreCase("nombre", f.getNombre()));

        // ubicacion.region / ubicacion.ciudad
        if (has(f.getRegion()))
            spec = spec.and((r, q, cb) -> cb.equal(cb.lower(r.get("ubicacion").get("region")),
                    f.getRegion().toLowerCase()));
        if (has(f.getCiudad()))
            spec = spec.and((r, q, cb) -> cb.like(cb.lower(r.get("ubicacion").get("ciudad")),
                    "%" + f.getCiudad().toLowerCase() + "%"));

        // rangoSalarial.minimo / .maximo
        if (f.getPagaMinima() != null)
            spec = spec.and((r, q, cb) -> cb.greaterThanOrEqualTo(
                    r.get("rangoSalarial").get("maximo"), f.getPagaMinima()));

        if (f.getPagaMaxima() != null)
            spec = spec.and((r, q, cb) -> cb.lessThanOrEqualTo(
                    r.get("rangoSalarial").get("minimo"), f.getPagaMaxima()));

        if (f.getReqExperiencia() != null)
            spec = spec.and((r, q, cb) -> cb.equal(r.get("requiereExperiencia"), f.getReqExperiencia()));

        if (f.getIsPracticaPro() != null)
            spec = spec.and((r, q, cb) -> cb.equal(r.get("practicaProfesional"), f.getIsPracticaPro()));

        if (f.getIsLey21015() != null)
            spec = spec.and((r, q, cb) -> cb.equal(r.get("ley21015"), f.getIsLey21015()));

        if (has(f.getTipoContrato()))
            spec = spec.and((r, q, cb) -> cb.equal(cb.lower(r.get("tipoContrato")), f.getTipoContrato().toLowerCase()));

        if (has(f.getNivelEducacional()))
            spec = spec.and((r, q, cb) -> cb.equal(cb.lower(r.get("tipoNivelEducacional")),
                    f.getNivelEducacional().toLowerCase()));

        if (has(f.getTipoJornada()))
            spec = spec.and((r, q, cb) -> cb.equal(cb.lower(r.get("tipoJornada")), f.getTipoJornada().toLowerCase()));

        if (has(f.getOrigenOferta()))
            spec = spec.and((r, q, cb) -> cb.equal(cb.lower(r.get("origenOferta")), f.getOrigenOferta().toLowerCase()));

        return spec;
    }

    public static Specification<OfertaExterna> externaFrom(OfertaFilter f) {
        Specification<OfertaExterna> spec = Specification.where(null);

        if (has(f.getNombre())) spec = spec.and(likeIgnoreCase("nombre", f.getNombre()));

        if (has(f.getRegion()))
            spec = spec.and((r, q, cb) -> cb.equal(cb.lower(r.get("ubicacion").get("region")),
                    f.getRegion().toLowerCase()));
        if (has(f.getCiudad()))
            spec = spec.and((r, q, cb) -> cb.like(cb.lower(r.get("ubicacion").get("ciudad")),
                    "%" + f.getCiudad().toLowerCase() + "%"));

        if (f.getPagaMinima() != null)
            spec = spec.and((r, q, cb) -> cb.greaterThanOrEqualTo(
                    r.get("rangoSalarial").get("maximo"), f.getPagaMinima()));

        if (f.getPagaMaxima() != null)
            spec = spec.and((r, q, cb) -> cb.lessThanOrEqualTo(
                    r.get("rangoSalarial").get("minimo"), f.getPagaMaxima()));

        if (f.getReqExperiencia() != null)
            spec = spec.and((r, q, cb) -> cb.equal(r.get("requiereExperiencia"), f.getReqExperiencia()));

        if (has(f.getTipoContrato()))
            spec = spec.and((r, q, cb) -> cb.equal(cb.lower(r.get("tipoContrato")), f.getTipoContrato().toLowerCase()));

        if (has(f.getNivelEducacional()))
            spec = spec.and((r, q, cb) -> cb.equal(cb.lower(r.get("tipoNivelEducacional")),
                    f.getNivelEducacional().toLowerCase()));

        if (has(f.getTipoJornada()))
            spec = spec.and((r, q, cb) -> cb.equal(cb.lower(r.get("tipoJornada")), f.getTipoJornada().toLowerCase()));

        if (has(f.getOrigenOferta()))
            spec = spec.and((r, q, cb) -> cb.equal(cb.lower(r.get("origenOferta")), f.getOrigenOferta().toLowerCase()));

        return spec;
    }

    private static boolean has(String s) { return s != null && !s.isBlank(); }
}

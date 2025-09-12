package com.bne.postulaciones_service.infrastructure.repository.spec;

import com.bne.postulaciones_service.domain.model.OfertaBNE;
import com.bne.postulaciones_service.domain.model.OfertaExterna;
import com.bne.postulaciones_service.domain.repository.OfertaFilter;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public final class OfertaSpecifications {

    private OfertaSpecifications() {}

    /* ====================== Helpers ====================== */

    private static boolean has(String s) {
        return s != null && !s.isBlank();
    }

    private static <T> Specification<T> and(Specification<T> base, Specification<T> next) {
        if (next == null) return base;
        return (base == null) ? next : base.and(next);
    }

    private static <T> Specification<T> likeIgnoreCase(String attr, String value) {
        if (!has(value)) return null;
        return (root, q, cb) -> cb.like(cb.lower(root.get(attr)), "%" + value.toLowerCase() + "%");
    }

    /* ====================== BNE (desde OfertaFilter) ====================== */

    public static Specification<OfertaBNE> bneFrom(OfertaFilter f) {
        Specification<OfertaBNE> spec = null;

        // nombre (titulo/descripcion simple)
        if (has(f.getNombre())) {
            spec = and(spec, likeIgnoreCase("nombre", f.getNombre()));
        }

        // ubicacion.region / ubicacion.ciudad
        if (has(f.getRegion())) {
            final String reg = f.getRegion().toLowerCase();
            spec = and(spec, (r, q, cb) -> cb.equal(cb.lower(r.get("ubicacion").get("region")), reg));
        }
        if (has(f.getCiudad())) {
            final String c = "%" + f.getCiudad().toLowerCase() + "%";
            spec = and(spec, (r, q, cb) -> cb.like(cb.lower(r.get("ubicacion").get("ciudad")), c));
        }

        // rangoSalarial.minimo / maximo
        if (f.getPagaMinima() != null) {
            spec = and(spec, (r, q, cb) ->
                    cb.greaterThanOrEqualTo(r.get("rangoSalarial").get("maximo"), f.getPagaMinima()));
        }
        if (f.getPagaMaxima() != null) {
            spec = and(spec, (r, q, cb) ->
                    cb.lessThanOrEqualTo(r.get("rangoSalarial").get("minimo"), f.getPagaMaxima()));
        }

        // flags
        if (f.getReqExperiencia() != null) {
            spec = and(spec, (r, q, cb) -> cb.equal(r.get("requiereExperiencia"), f.getReqExperiencia()));
        }
        if (f.getIsPracticaPro() != null) {
            spec = and(spec, (r, q, cb) -> cb.equal(r.get("practicaProfesional"), f.getIsPracticaPro()));
        }
        if (f.getIsLey21015() != null) {
            spec = and(spec, (r, q, cb) -> cb.equal(r.get("ley21015"), f.getIsLey21015()));
        }

        // contrato / nivel educacional / jornada
        if (has(f.getTipoContrato())) {
            final String v = f.getTipoContrato().toLowerCase();
            spec = and(spec, (r, q, cb) -> cb.equal(cb.lower(r.get("tipoContrato")), v));
        }
        if (has(f.getNivelEducacional())) {
            final String v = f.getNivelEducacional().toLowerCase();
            spec = and(spec, (r, q, cb) -> cb.equal(cb.lower(r.get("tipoNivelEducacional")), v));
        }
        if (has(f.getTipoJornada())) {
            final String v = f.getTipoJornada().toLowerCase();
            spec = and(spec, (r, q, cb) -> cb.equal(cb.lower(r.get("tipoJornada")), v));
        }

        // origenOferta (si aplica en tu modelo de BNE)
        if (has(f.getOrigenOferta())) {
            final String v = f.getOrigenOferta().toLowerCase();
            spec = and(spec, (r, q, cb) -> cb.equal(cb.lower(r.get("origenOferta")), v));
        }

        // NOTE: si tu OfertaFilter tiene nivelCargo, puedes descomentar esto:
        // if (has(f.getNivelCargo())) {
        //     final String v = f.getNivelCargo().toLowerCase();
        //     spec = and(spec, (r, q, cb) -> cb.equal(cb.lower(r.get("nivelCargo")), v));
        // }

        return spec;
    }

    /* ====================== EXTERNA (desde OfertaFilter) ====================== */

    public static Specification<OfertaExterna> externaFrom(OfertaFilter f) {
        Specification<OfertaExterna> spec = null;

        if (has(f.getNombre())) {
            spec = and(spec, likeIgnoreCase("nombre", f.getNombre()));
        }

        if (has(f.getRegion())) {
            final String reg = f.getRegion().toLowerCase();
            spec = and(spec, (r, q, cb) -> cb.equal(cb.lower(r.get("ubicacion").get("region")), reg));
        }
        if (has(f.getCiudad())) {
            final String c = "%" + f.getCiudad().toLowerCase() + "%";
            spec = and(spec, (r, q, cb) -> cb.like(cb.lower(r.get("ubicacion").get("ciudad")), c));
        }

        if (f.getPagaMinima() != null) {
            spec = and(spec, (r, q, cb) ->
                    cb.greaterThanOrEqualTo(r.get("rangoSalarial").get("maximo"), f.getPagaMinima()));
        }
        if (f.getPagaMaxima() != null) {
            spec = and(spec, (r, q, cb) ->
                    cb.lessThanOrEqualTo(r.get("rangoSalarial").get("minimo"), f.getPagaMaxima()));
        }

        if (f.getReqExperiencia() != null) {
            spec = and(spec, (r, q, cb) -> cb.equal(r.get("requiereExperiencia"), f.getReqExperiencia()));
        }

        if (has(f.getTipoContrato())) {
            final String v = f.getTipoContrato().toLowerCase();
            spec = and(spec, (r, q, cb) -> cb.equal(cb.lower(r.get("tipoContrato")), v));
        }
        if (has(f.getNivelEducacional())) {
            final String v = f.getNivelEducacional().toLowerCase();
            spec = and(spec, (r, q, cb) -> cb.equal(cb.lower(r.get("tipoNivelEducacional")), v));
        }
        if (has(f.getTipoJornada())) {
            final String v = f.getTipoJornada().toLowerCase();
            spec = and(spec, (r, q, cb) -> cb.equal(cb.lower(r.get("tipoJornada")), v));
        }

        if (has(f.getOrigenOferta())) {
            final String v = f.getOrigenOferta().toLowerCase();
            spec = and(spec, (r, q, cb) -> cb.equal(cb.lower(r.get("origenOferta")), v));
        }

        // NOTE: si tu OfertaFilter tiene nivelCargo, puedes descomentar esto:
        // if (has(f.getNivelCargo())) {
        //     final String v = f.getNivelCargo().toLowerCase();
        //     spec = and(spec, (r, q, cb) -> cb.equal(cb.lower(r.get("nivelCargo")), v));
        // }

        return spec;
    }

    /* ====================== PUENTES para el Controller ====================== */

    /** Arma un OfertaFilter y aplica fechas (BNE). Firma usada por tu OfertaController */
    public static Specification<OfertaBNE> bneAll(
            String q, String region, String ciudad, String jornada,
            String contrato, String nivelEdu, String /*nivelCargo*/ _nivelCargoNoUsado,
            Boolean ley21015, LocalDate desde, LocalDate hasta
    ) {
        OfertaFilter f = OfertaFilter.builder()
                .nombre(q)
                .region(region)
                .ciudad(ciudad)
                .tipoJornada(jornada)
                .tipoContrato(contrato)
                .nivelEducacional(nivelEdu)
                .isLey21015(ley21015)
                // .nivelCargo(_nivelCargoNoUsado) // <- Descomenta si tu OfertaFilter lo tiene
                .build();

        Specification<OfertaBNE> spec = bneFrom(f);

        // fechas
        if (desde != null) {
            spec = and(spec, (r, qy, cb) ->
                    cb.greaterThanOrEqualTo(r.get("periodoVigencia").get("fechaInicio"), desde));
        }
        if (hasta != null) {
            spec = and(spec, (r, qy, cb) ->
                    cb.lessThanOrEqualTo(r.get("periodoVigencia").get("fechaTermino"), hasta));
        }
        return spec;
    }

    /** Arma un OfertaFilter y aplica fechas (EXTERNA). Firma usada por tu OfertaController */
    public static Specification<OfertaExterna> extAll(
            String q, String region, String ciudad, String jornada,
            String contrato, String nivelEdu, String /*nivelCargo*/ _nivelCargoNoUsado,
            LocalDate desde, LocalDate hasta
    ) {
        OfertaFilter f = OfertaFilter.builder()
                .nombre(q)
                .region(region)
                .ciudad(ciudad)
                .tipoJornada(jornada)
                .tipoContrato(contrato)
                .nivelEducacional(nivelEdu)
                // .nivelCargo(_nivelCargoNoUsado) // <- Descomenta si tu OfertaFilter lo tiene
                .build();

        Specification<OfertaExterna> spec = externaFrom(f);

        if (desde != null) {
            spec = and(spec, (r, qy, cb) ->
                    cb.greaterThanOrEqualTo(r.get("periodoVigencia").get("fechaInicio"), desde));
        }
        if (hasta != null) {
            spec = and(spec, (r, qy, cb) ->
                    cb.lessThanOrEqualTo(r.get("periodoVigencia").get("fechaTermino"), hasta));
        }
        return spec;
    }
}

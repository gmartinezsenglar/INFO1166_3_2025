package com.bne.postulaciones_service.infrastructure.rest;

import com.bne.postulaciones_service.domain.model.OfertaBNE;
import com.bne.postulaciones_service.domain.model.OfertaExterna;
import com.bne.postulaciones_service.infrastructure.repository.JpaOfertaBNERepository;
import com.bne.postulaciones_service.infrastructure.repository.JpaOfertaExternaRepository;
import com.bne.postulaciones_service.infrastructure.repository.spec.OfertaSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.web.PageableDefault;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/ofertas", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class OfertaController {

    private final JpaOfertaBNERepository bneRepo;
    private final JpaOfertaExternaRepository extRepo;

    /* ======= Records internos para no crear más archivos ======= */
    private record Card(
            Long id, String origen, String titulo, String descripcionCorta,
            String empresa, String region, String ciudad, LocalDate fechaPublicacion
    ) {}
    private record PageResp<T>(List<T> content, int page, int size, long totalElements, int totalPages, Map<String,Object> appliedFilters) {
        static <T> PageResp<T> of(Page<T> p, Map<String,Object> filters){
            return new PageResp<>(p.getContent(), p.getNumber(), p.getSize(), p.getTotalElements(), p.getTotalPages(), filters);
        }
    }
    private record Facets(
            List<String> regiones,
            Map<String,List<String>> comunasPorRegion,
            List<String> jornadas,
            List<String> tiposContrato,
            List<String> nivelesEducativos,
            List<String> nivelesCargo
    ) {}

    /* ======= LISTADO DE CARDS (paginado) ======= */
    @GetMapping("/cards")
    public PageResp<Card> listCards(
            @RequestParam(required = false, defaultValue = "ALL") String origen, // BNE | EXTERNA | ALL
            @RequestParam(required = false) String q,
            @RequestParam(required = false) String region,
            @RequestParam(required = false) String ciudad,
            @RequestParam(required = false) String jornada,
            @RequestParam(required = false) String contrato,
            @RequestParam(required = false) String nivelEducativo,
            @RequestParam(required = false) String nivelCargo,
            @RequestParam(required = false) Boolean discapacidad, // ley21015 (solo BNE)
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate desde,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hasta,
            @PageableDefault(size = 10) Pageable pageable
    ) {
        // order por fechaPublicacion desc por defecto si no te llegó sort
        Pageable sorted = pageable.getSort().isUnsorted()
                ? PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.DESC, "periodoVigencia.fechaInicio"))
                : pageable;

        Page<Card> page;
        if ("BNE".equalsIgnoreCase(origen)) {
            Specification<OfertaBNE> s = OfertaSpecifications.bneAll(q, region, ciudad, jornada, contrato, nivelEducativo, nivelCargo, discapacidad, desde, hasta);
            Page<OfertaBNE> p = (s == null) ? bneRepo.findAll(sorted) : bneRepo.findAll(s, sorted);
            page = p.map(this::toCardBNE);
        } else if ("EXTERNA".equalsIgnoreCase(origen)) {
            Specification<OfertaExterna> s = OfertaSpecifications.extAll(q, region, ciudad, jornada, contrato, nivelEducativo, nivelCargo, desde, hasta);
            Page<OfertaExterna> p = (s == null) ? extRepo.findAll(sorted) : extRepo.findAll(s, sorted);
            page = p.map(this::toCardEXT);
        } else { // ALL: combinamos BNE + EXTERNA en memoria y paginamos (simple)
            var sB = OfertaSpecifications.bneAll(q, region, ciudad, jornada, contrato, nivelEducativo, nivelCargo, discapacidad, desde, hasta);
            var sE = OfertaSpecifications.extAll(q, region, ciudad, jornada, contrato, nivelEducativo, nivelCargo, desde, hasta);

            List<Card> all = new ArrayList<>();
            (sB == null ? bneRepo.findAll() : bneRepo.findAll(sB)).stream().map(this::toCardBNE).forEach(all::add);
            (sE == null ? extRepo.findAll() : extRepo.findAll(sE)).stream().map(this::toCardEXT).forEach(all::add);

            all.sort(Comparator.comparing(Card::fechaPublicacion, Comparator.nullsLast(Comparator.reverseOrder())));
            int from = (int) sorted.getOffset();
            int to = Math.min(from + sorted.getPageSize(), all.size());
            List<Card> slice = (from >= all.size()) ? List.of() : all.subList(from, to);
            page = new PageImpl<>(slice, sorted, all.size());
        }

        Map<String,Object> filters = new LinkedHashMap<>();
        filters.put("origen", origen); filters.put("q", q); filters.put("region", region); filters.put("ciudad", ciudad);
        filters.put("jornada", jornada); filters.put("contrato", contrato); filters.put("nivelEducativo", nivelEducativo);
        filters.put("nivelCargo", nivelCargo); filters.put("discapacidad", discapacidad); filters.put("desde", desde); filters.put("hasta", hasta);

        return PageResp.of(page, filters);
    }

    /* ======= FACETS (combos) ======= */
    @GetMapping("/facets")
    public Facets facets() {
        var regiones = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
        var comunasMap = new HashMap<String, Set<String>>();
        var jornadas = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
        var contratos = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
        var nivelesEdu = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
        var nivelesCargo = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);

        bneRepo.findAll().forEach(o -> collectFromBNE(o, regiones, comunasMap, jornadas, contratos, nivelesEdu, nivelesCargo));
        extRepo.findAll().forEach(o -> collectFromEXT(o, regiones, comunasMap, jornadas, contratos, nivelesEdu, nivelesCargo));

        Map<String, List<String>> comunasPorRegion = comunasMap.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().stream().sorted(String.CASE_INSENSITIVE_ORDER).toList()));

        return new Facets(
                regiones.stream().toList(),
                comunasPorRegion,
                jornadas.stream().toList(),
                contratos.stream().toList(),
                nivelesEdu.stream().toList(),
                nivelesCargo.stream().toList()
        );
    }

    /* ======= helpers ======= */
    private Card toCardBNE(OfertaBNE o) {
        String d = o.getDescripcion();
        String corta = (d == null) ? "" : (d.length() > 160 ? d.substring(0, 160) + "…" : d);
        return new Card(
                o.getId(), "BNE", o.getNombre(), corta,
                o.getEmpresa() != null ? o.getEmpresa().getNombre() : "Confidencial",
                o.getUbicacion() != null ? o.getUbicacion().getRegion() : null,
                o.getUbicacion() != null ? o.getUbicacion().getCiudad() : null,
                o.getPeriodoVigencia() != null ? o.getPeriodoVigencia().getFechaInicio() : null
        );
    }

    private Card toCardEXT(OfertaExterna o) {
        String d = o.getDescripcion();
        String corta = (d == null) ? "" : (d.length() > 160 ? d.substring(0, 160) + "…" : d);
        return new Card(
                o.getId(), "EXTERNA", o.getNombre(), corta,
                o.getEmpresa() != null ? o.getEmpresa().getNombre() : "Confidencial",
                o.getUbicacion() != null ? o.getUbicacion().getRegion() : null,
                o.getUbicacion() != null ? o.getUbicacion().getCiudad() : null,
                o.getPeriodoVigencia() != null ? o.getPeriodoVigencia().getFechaInicio() : null
        );
    }

    private static void collectFromBNE(OfertaBNE o,
                                       Set<String> regiones, Map<String, Set<String>> comunas,
                                       Set<String> jornadas, Set<String> contratos,
                                       Set<String> nivelesEdu, Set<String> nivelesCargo) {
        addUbicacion(regiones, comunas,
                o.getUbicacion() != null ? o.getUbicacion().getRegion() : null,
                o.getUbicacion() != null ? o.getUbicacion().getCiudad() : null);
        add(jornadas, o.getTipoJornada());
        add(contratos, o.getTipoContrato());
        add(nivelesEdu, o.getTipoNivelEducacional());
        add(nivelesCargo, o.getNivelCargo());
    }

    private static void collectFromEXT(OfertaExterna o,
                                       Set<String> regiones, Map<String, Set<String>> comunas,
                                       Set<String> jornadas, Set<String> contratos,
                                       Set<String> nivelesEdu, Set<String> nivelesCargo) {
        addUbicacion(regiones, comunas,
                o.getUbicacion() != null ? o.getUbicacion().getRegion() : null,
                o.getUbicacion() != null ? o.getUbicacion().getCiudad() : null);
        add(jornadas, o.getTipoJornada());
        add(contratos, o.getTipoContrato());
        add(nivelesEdu, o.getTipoNivelEducacional());
        add(nivelesCargo, o.getNivelCargo());
    }

    private static void add(Set<String> s, String v){ if (v!=null && !v.isBlank()) s.add(v); }
    private static void addUbicacion(Set<String> regiones, Map<String, Set<String>> comunas, String r, String c){
        if (r != null && !r.isBlank()) {
            regiones.add(r);
            comunas.computeIfAbsent(r, k -> new TreeSet<>(String.CASE_INSENSITIVE_ORDER));
            if (c != null && !c.isBlank()) comunas.get(r).add(c);
        }
    }
}

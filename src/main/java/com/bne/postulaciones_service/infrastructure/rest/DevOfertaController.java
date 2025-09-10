// infrastructure/rest/DevOfertaController.java momentaneo esta wea
package com.bne.postulaciones_service.infrastructure.rest;

import com.bne.postulaciones_service.domain.repository.OfertaFilter;
import com.bne.postulaciones_service.infrastructure.repository.OfertaBNERepositoryAdapter;
import com.bne.postulaciones_service.infrastructure.repository.OfertaExternaRepositoryAdapter;
import com.bne.postulaciones_service.domain.model.OfertaBNE;
import com.bne.postulaciones_service.domain.model.OfertaExterna;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dev")
public class DevOfertaController {
    private final OfertaBNERepositoryAdapter bne;
    private final OfertaExternaRepositoryAdapter ext;

    public DevOfertaController(OfertaBNERepositoryAdapter bne, OfertaExternaRepositoryAdapter ext) {
        this.bne = bne; this.ext = ext;
    }

    // lista simple (sin paginar) para probar rápido
    @GetMapping("/ofertas-bne")
    public List<OfertaBNE> bne(@RequestParam(required=false) String nombre,
                               @RequestParam(required=false) String region,
                               @RequestParam(required=false) String ciudad,
                               @RequestParam(required=false) Integer pagaMinima,
                               @RequestParam(required=false) Integer pagaMaxima,
                               @RequestParam(required=false) Boolean reqExperiencia,
                               @RequestParam(required=false) Boolean practica,
                               @RequestParam(required=false) Boolean ley21015,
                               @RequestParam(required=false) String tipoContrato,
                               @RequestParam(required=false) String nivelEducacional,
                               @RequestParam(required=false) String tipoJornada,
                               @RequestParam(required=false) String origenOferta) {
        var f = OfertaFilter.builder()
                .nombre(nombre).region(region).ciudad(ciudad)
                .pagaMinima(pagaMinima).pagaMaxima(pagaMaxima)
                .reqExperiencia(reqExperiencia).isPracticaPro(practica).isLey21015(ley21015)
                .tipoContrato(tipoContrato).nivelEducacional(nivelEducacional)
                .tipoJornada(tipoJornada).origenOferta(origenOferta)
                .build();
        return bne.buscarConFiltros(f);
    }

    // versión paginada de ejemplo
    @GetMapping("/ofertas-bne/page")
    public Page<OfertaBNE> bnePage(@RequestParam(defaultValue="0") int page,
                                   @RequestParam(defaultValue="10") int size,
                                   @RequestParam(required=false) String region) {
        var f = OfertaFilter.builder().region(region).build();
        return bne.buscarConFiltros(f, PageRequest.of(page, size));
    }

    @GetMapping("/ofertas-externas")
    public List<OfertaExterna> ext(@RequestParam(required=false) String nombre,
                                   @RequestParam(required=false) String region) {
        var f = OfertaFilter.builder().nombre(nombre).region(region).build();
        return ext.buscarConFiltros(f);
    }
}

package com.bne.postulaciones_service.infrastructure.controller;
import com.bne.postulaciones_service.application.dto.OfertaDto;
import com.bne.postulaciones_service.application.dto.PostulacionResponseDto;
import com.bne.postulaciones_service.application.usecase.BuscarOfertasUseCase;
import com.bne.postulaciones_service.application.usecase.CrearPostulacionUseCase;
import com.bne.postulaciones_service.application.usecase.VerDetalleOfertaUseCase;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")

public class PostulacionController {
    private final BuscarOfertasUseCase buscarOfertasUseCase;
    private final CrearPostulacionUseCase crearPostulacionUseCase;
    private final VerDetalleOfertaUseCase verDetalleOfertaUseCase;
    public PostulacionController(CrearPostulacionUseCase crearPostulacionUseCase,
                                 BuscarOfertasUseCase buscarOfertasUseCase,
                                 VerDetalleOfertaUseCase verDetalleOfertaUseCase) {
        this.crearPostulacionUseCase = crearPostulacionUseCase;
        this.buscarOfertasUseCase = buscarOfertasUseCase;
        this.verDetalleOfertaUseCase = verDetalleOfertaUseCase;
    }
    @PostMapping("/postulaciones")
    public PostulacionResponseDto crearPostulacion(@RequestBody PostulacionResponseDto request){
        return crearPostulacionUseCase.ejecutar(request);
    }

    @GetMapping("/ofertas")
    public List<OfertaDto> listarOfertas(){
        return buscarOfertasUseCase.ejecutar();
    }

    @GetMapping("/ofertas/{id}")
    public OfertaDto verDetalleOferta(@PathVariable Long id){
        return verDetalleOfertaUseCase.ejecutar(id);
    }
}

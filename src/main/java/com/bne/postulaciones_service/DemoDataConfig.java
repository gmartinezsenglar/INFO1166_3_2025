package com.bne.postulaciones_service;

import com.bne.postulaciones_service.domain.model.*;
import com.bne.postulaciones_service.infrastructure.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class DemoDataConfig {

    @Bean
    CommandLineRunner seed(JpaUsuarioRepository usuarioRepo,
                           JpaEmpresaRepository empresaRepo,
                           JpaOfertaBNERepository ofertaBNERepo,
                           JpaPostulacionRepository postulacionRepo) {
        return args -> {
            // Usuario
            var usuario = Usuario.builder()
                    .nombres("Cristian")
                    .apellidos("Tester")
                    .infoAcademicaCompleta(true)
                    .experienciaLaboralCompleta(false)
                    .build();
            usuario = usuarioRepo.save(usuario);

            // Empresa
            var empresa = Empresa.builder()
                    .nombre("BNE Labs")
                    .actividadEconomica("Tecnología")
                    .descripcion("Empresa de prueba")
                    .logo(null)
                    .build();
            empresa = empresaRepo.save(empresa);

            // Oferta BNE
            var oferta = OfertaBNE.builder()
                    .empresa(empresa)
                    .nombre("Desarrollador Java")
                    .vacantesDisponibles(1)
                    .descripcion("Remoto, tiempo completo")
                    .region("RM")
                    .ciudad("Santiago")
                    .pagaMinima(1000000)
                    .pagaMaxima(1500000)
                    .tipoJornada("Completa")
                    .fechaInicio(LocalDate.now())
                    .fechaTermino(LocalDate.now().plusDays(30))
                    .requiereExperiencia(false)
                    .requiereNivelEducacional(false)
                    .tipoNivelEducacional(null)
                    .tipoContrato("Indefinido")
                    .nivelCargo("Junior")
                    .origenOferta("BNE")
                    .practicaProfesional(false)
                    .ley21015(false)
                    .build();
            oferta = ofertaBNERepo.save(oferta);

            // Postulación (usa OfertaId embebido)
            var ofertaId = new OfertaId(oferta.getId(), OfertaId.Origen.BNE);
            var postulacion = Postulacion.builder()
                    .ofertaId(ofertaId)
                    .empresaId(empresa.getId())
                    .usuarioId(usuario.getId())
                    .fechaPostulacion(LocalDate.now())
                    .estado(Postulacion.EstadoPostulacion.ENVIADA)
                    .build();
            postulacionRepo.save(postulacion);

            System.out.println("Seed OK: usuario=" + usuario.getId() +
                    ", empresa=" + empresa.getId() +
                    ", oferta=" + oferta.getId());
        };
    }
}

// src/main/java/com/bne/postulaciones_service/DemoDataConfig.java
package com.bne.postulaciones_service;

import com.bne.postulaciones_service.domain.model.*;
import com.bne.postulaciones_service.domain.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;

@Configuration
@Profile("dev") // <-- SOLO se ejecuta cuando el perfil activo es 'dev'
public class DemoDataConfig {

    @Bean
    CommandLineRunner seed(
            UsuarioRepository usuarios,
            EmpresaRepository empresas,
            OfertaBNERepository ofertasBNE,
            PostulacionRepository postulaciones
    ) {
        return args -> {
            // usuario
            Usuario u = Usuario.builder()
                    .nombres("Cristian").apellidos("Alumno")
                    .infoAcademicaCompleta(true).experienciaLaboralCompleta(true)
                    .build();
            u = usuarios.save(u);

            // empresa
            Empresa e = Empresa.builder()
                    .nombre("Acme Ltda").actividadEconomica("Software")
                    .descripcion("Empresa de TI").logo("acme.png")
                    .build();
            e = empresas.save(e);

            // oferta BNE con embebidos
            var ubicacion = com.bne.postulaciones_service.domain.model.vo.Ubicacion.builder()
                    .region("RM").ciudad("Santiago").build();

            var rango = com.bne.postulaciones_service.domain.model.vo.RangoSalarial.builder()
                    .minimo(1000).maximo(1500).build();

            var vigencia = com.bne.postulaciones_service.domain.model.vo.PeriodoVigencia.builder()
                    .fechaInicio(LocalDate.now()).fechaTermino(LocalDate.now().plusDays(30)).build();

            OfertaBNE o = OfertaBNE.builder()
                    .empresa(e)
                    .nombre("Desarrollador Java")
                    .vacantesDisponibles(2)
                    .descripcion("Remoto, tiempo completo")
                    .ubicacion(ubicacion)
                    .rangoSalarial(rango)
                    .periodoVigencia(vigencia)
                    .requiereExperiencia(true)
                    .requiereNivelEducacional(false)
                    .tipoNivelEducacional(null)
                    .tipoContrato("INDEFINIDO")
                    .tipoJornada("COMPLETA")
                    .nivelCargo("Junior")
                    .origenOferta("BNE")
                    .practicaProfesional(false)
                    .ley21015(false)
                    .build();
            o = ofertasBNE.save(o);

            // postulacion
            Postulacion p = Postulacion.builder()
                    .ofertaId(new com.bne.postulaciones_service.domain.model.vo.OfertaId(
                            o.getId(), com.bne.postulaciones_service.domain.model.vo.OfertaId.Origen.BNE))
                    .empresaId(e.getId())
                    .usuarioId(u.getId())
                    .fechaPostulacion(LocalDate.now())
                    .estado(Postulacion.EstadoPostulacion.ENVIADA)
                    .build();
            postulaciones.save(p);

            System.out.println("Seed OK: usuario=" + u.getId() + ", empresa=" + e.getId() + ", oferta=" + o.getId());
        };
    }
}

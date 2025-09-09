package com.bne.postulaciones_service;

import com.bne.postulaciones_service.domain.model.*;
import com.bne.postulaciones_service.domain.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
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
                    .infoAcademicaCompleta(true).experienciaLaboralCompleta(false)
                    .build();
            u = usuarios.save(u);

            // empresa
            Empresa e = Empresa.builder()
                    .nombre("Acme Ltda").actividadEconomica("Software").descripcion("Empresa de TI").logo(null)
                    .build();
            e = empresas.save(e);

            // oferta BNE
            OfertaBNE o = OfertaBNE.builder()
                    .empresa(e).nombre("Desarrollador Java").vacantesDisponibles(2)
                    .descripcion("Remoto, tiempo completo").region("RM").ciudad("Santiago")
                    .pagaMinima(1000).pagaMaxima(1500).tipoJornada("Completa")
                    .fechaInicio(LocalDate.now()).fechaTermino(LocalDate.now().plusDays(30))
                    .requiereExperiencia(true).requiereNivelEducacional(false)
                    .tipoNivelEducacional(null).tipoContrato("Indefinido")
                    .nivelCargo("Junior").origenOferta("BNE").practicaProfesional(false).ley21015(false)
                    .build();
            o = ofertasBNE.save(o);

            // postulacion
            Postulacion p = Postulacion.builder()
                    .ofertaId(new OfertaId(o.getId(), OfertaId.Origen.BNE))
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

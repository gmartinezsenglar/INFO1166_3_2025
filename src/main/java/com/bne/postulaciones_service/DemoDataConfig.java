// src/main/java/com/bne/postulaciones_service/DemoDataConfig.java
package com.bne.postulaciones_service;

import com.bne.postulaciones_service.domain.model.*;
import com.bne.postulaciones_service.domain.model.vo.OfertaId;
import com.bne.postulaciones_service.domain.model.vo.PeriodoVigencia;
import com.bne.postulaciones_service.domain.model.vo.RangoSalarial;
import com.bne.postulaciones_service.domain.model.vo.Ubicacion;
import com.bne.postulaciones_service.domain.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;
import java.util.List;

@Configuration
@Profile("dev") // Se carga solo con el perfil 'dev'
public class DemoDataConfig {

    @Bean
    CommandLineRunner seed(
            UsuarioRepository usuarios,
            EmpresaRepository empresas,
            OfertaBNERepository ofertasBNE,
            OfertaExternaRepository ofertasEXT,
            PostulacionRepository postulaciones
    ) {
        return args -> {
            // ====== Usuarios ======
            Usuario u1 = usuarios.save(Usuario.builder()
                    .nombres("Cristian").apellidos("Alumno")
                    .infoAcademicaCompleta(true).experienciaLaboralCompleta(true)
                    .build());

            Usuario u2 = usuarios.save(Usuario.builder()
                    .nombres("María").apellidos("Prueba")
                    .infoAcademicaCompleta(true).experienciaLaboralCompleta(false)
                    .build());

            // ====== Empresas ======
            Empresa acme = empresas.save(Empresa.builder()
                    .nombre("Acme Ltda").actividadEconomica("Software")
                    .descripcion("Empresa de TI").logo("acme.png").build());

            Empresa report = empresas.save(Empresa.builder()
                    .nombre("REPORT LTDA").actividadEconomica("Recursos Humanos")
                    .descripcion("Servicios RRHH").logo("report.png").build());

            Empresa salud = empresas.save(Empresa.builder()
                    .nombre("SaludPlus SPA").actividadEconomica("Salud")
                    .descripcion("Clínicas y servicios de salud").logo("salud.png").build());

            // ==== Helpers de construcción ====
            // Ubicación(r, c)
            java.util.function.BiFunction<String,String, Ubicacion> U =
                    (r,c) -> Ubicacion.builder().region(r).ciudad(c).build();

            // Rango(min, max)
            java.util.function.BiFunction<Integer,Integer, RangoSalarial> R =
                    (min,max) -> RangoSalarial.builder().minimo(min).maximo(max).build();

            // Vigencia(inicio, fin)
            java.util.function.BiFunction<LocalDate,LocalDate, PeriodoVigencia> V =
                    (ini,fin) -> PeriodoVigencia.builder().fechaInicio(ini).fechaTermino(fin).build();

            LocalDate hoy = LocalDate.now();

            // ====== Ofertas BNE (6) ======
            List<OfertaBNE> bne = List.of(
                    OfertaBNE.builder()
                            .empresa(acme)
                            .nombre("Desarrollador Java")
                            .vacantesDisponibles(2)
                            .descripcion("Remoto, tiempo completo")
                            .ubicacion(U.apply("RM", "Santiago"))
                            .rangoSalarial(R.apply(1200000, 1800000))
                            .periodoVigencia(V.apply(hoy, hoy.plusDays(30)))
                            .requiereExperiencia(true)
                            .requiereNivelEducacional(false)
                            .tipoContrato("INDEFINIDO")
                            .tipoJornada("COMPLETA")
                            .nivelCargo("Junior")
                            .origenOferta("BNE")
                            .practicaProfesional(false)
                            .ley21015(false)
                            .build(),

                    OfertaBNE.builder()
                            .empresa(report)
                            .nombre("Administrativo RRHH")
                            .vacantesDisponibles(1)
                            .descripcion("Funciones acordes al área de RRHH")
                            .ubicacion(U.apply("Biobío", "Talcahuano"))
                            .rangoSalarial(R.apply(750000, 950000))
                            .periodoVigencia(V.apply(hoy.minusDays(1), hoy.plusDays(25)))
                            .requiereExperiencia(true)
                            .requiereNivelEducacional(true)
                            .tipoNivelEducacional("Técnico")
                            .tipoContrato("PLAZO_FIJO")
                            .tipoJornada("COMPLETA")
                            .nivelCargo("Semi Senior")
                            .origenOferta("BNE")
                            .practicaProfesional(false)
                            .ley21015(true)
                            .build(),

                    OfertaBNE.builder()
                            .empresa(salud)
                            .nombre("Terap. Ocupacional")
                            .vacantesDisponibles(3)
                            .descripcion("Inclusión de personas en situación de discapacidad…")
                            .ubicacion(U.apply("La Araucanía", "Temuco"))
                            .rangoSalarial(R.apply(900000, 1200000))
                            .periodoVigencia(V.apply(hoy, hoy.plusDays(40)))
                            .requiereExperiencia(false)
                            .requiereNivelEducacional(true)
                            .tipoNivelEducacional("Profesional")
                            .tipoContrato("INDEFINIDO")
                            .tipoJornada("PART_TIME")
                            .nivelCargo("Profesional")
                            .origenOferta("BNE")
                            .practicaProfesional(false)
                            .ley21015(true)
                            .build(),

                    OfertaBNE.builder()
                            .empresa(acme)
                            .nombre("QA Tester")
                            .vacantesDisponibles(1)
                            .descripcion("Ejecución de pruebas manuales y automatizadas")
                            .ubicacion(U.apply("Valparaíso", "Viña del Mar"))
                            .rangoSalarial(R.apply(800000, 1100000))
                            .periodoVigencia(V.apply(hoy.minusDays(2), hoy.plusDays(20)))
                            .requiereExperiencia(true)
                            .requiereNivelEducacional(false)
                            .tipoContrato("PLAZO_FIJO")
                            .tipoJornada("POR_TURNOS")
                            .nivelCargo("Junior")
                            .origenOferta("BNE")
                            .practicaProfesional(false)
                            .ley21015(false)
                            .build(),

                    OfertaBNE.builder()
                            .empresa(report)
                            .nombre("Analista de Datos")
                            .vacantesDisponibles(2)
                            .descripcion("SQL, PowerBI, reporting y métricas")
                            .ubicacion(U.apply("Antofagasta", "Antofagasta"))
                            .rangoSalarial(R.apply(1100000, 1600000))
                            .periodoVigencia(V.apply(hoy.minusDays(3), hoy.plusDays(15)))
                            .requiereExperiencia(true)
                            .requiereNivelEducacional(true)
                            .tipoNivelEducacional("Profesional")
                            .tipoContrato("INDEFINIDO")
                            .tipoJornada("COMPLETA")
                            .nivelCargo("Senior")
                            .origenOferta("BNE")
                            .practicaProfesional(false)
                            .ley21015(false)
                            .build(),

                    OfertaBNE.builder()
                            .empresa(salud)
                            .nombre("Práctica Enfermería")
                            .vacantesDisponibles(4)
                            .descripcion("Práctica profesional en clínica")
                            .ubicacion(U.apply("Los Lagos", "Puerto Montt"))
                            .rangoSalarial(R.apply(200000, 250000))
                            .periodoVigencia(V.apply(hoy, hoy.plusDays(60)))
                            .requiereExperiencia(false)
                            .requiereNivelEducacional(true)
                            .tipoNivelEducacional("Técnico")
                            .tipoContrato("HONORARIOS")
                            .tipoJornada("COMPLETA")
                            .nivelCargo("Practicante")
                            .origenOferta("BNE")
                            .practicaProfesional(true)
                            .ley21015(false)
                            .build()
            );
            bne.forEach(ofertasBNE::save);

            // ====== Ofertas EXTERNAS (6) ======
            List<OfertaExterna> ext = List.of(
                    OfertaExterna.builder()
                            .empresa(acme)
                            .nombre("Frontend React")
                            .vacantesDisponibles(1)
                            .descripcion("React + Vite, consumo de APIs")
                            .ubicacion(U.apply("RM", "Santiago"))
                            .rangoSalarial(R.apply(1200000, 1700000))
                            .periodoVigencia(V.apply(hoy, hoy.plusDays(30)))
                            .nombrePublicador("Bolsa Externa")
                            .requiereExperiencia(true)
                            .requiereNivelEducacional(true)
                            .tipoNivelEducacional("Profesional")
                            .tipoContrato("INDEFINIDO")
                            .tipoJornada("COMPLETA")
                            .nivelCargo("PROFESIONAL")
                            .origenOferta("EXTERNA")
                            .urlFuente("https://acme.jobs/react-fe")
                            .practicaProfesional(false)
                            .build(),

                    OfertaExterna.builder()
                            .empresa(report)
                            .nombre("Soporte TI")
                            .vacantesDisponibles(2)
                            .descripcion("Soporte de primer nivel, turnos rotativos")
                            .ubicacion(U.apply("Valparaíso", "Valparaíso"))
                            .rangoSalarial(R.apply(700000, 900000))
                            .periodoVigencia(V.apply(hoy.minusDays(1), hoy.plusDays(20)))
                            .nombrePublicador("Portal Empleos")
                            .requiereExperiencia(false)
                            .requiereNivelEducacional(true)
                            .tipoNivelEducacional("Técnico")
                            .tipoContrato("PLAZO_FIJO")
                            .tipoJornada("POR_TURNOS")
                            .nivelCargo("TECNICO")
                            .origenOferta("EXTERNA")
                            .urlFuente("https://rrhh.example/soporte")
                            .practicaProfesional(false)
                            .build(),

                    OfertaExterna.builder()
                            .empresa(salud)
                            .nombre("Fonoaudiólogo/a")
                            .vacantesDisponibles(1)
                            .descripcion("Atención clínica y domiciliaria")
                            .ubicacion(U.apply("La Araucanía", "Temuco"))
                            .rangoSalarial(R.apply(1000000, 1400000))
                            .periodoVigencia(V.apply(hoy, hoy.plusDays(35)))
                            .nombrePublicador("Salud Empleos")
                            .requiereExperiencia(true)
                            .requiereNivelEducacional(true)
                            .tipoNivelEducacional("Profesional")
                            .tipoContrato("INDEFINIDO")
                            .tipoJornada("PART_TIME")
                            .nivelCargo("PROFESIONAL")
                            .origenOferta("EXTERNA")
                            .urlFuente("https://saludplus.cl/fono")
                            .practicaProfesional(false)
                            .build(),

                    OfertaExterna.builder()
                            .empresa(acme)
                            .nombre("Operario de Bodega")
                            .vacantesDisponibles(5)
                            .descripcion("Recepción y despacho, orden de bodega")
                            .ubicacion(U.apply("Biobío", "Concepción"))
                            .rangoSalarial(R.apply(600000, 750000))
                            .periodoVigencia(V.apply(hoy.minusDays(2), hoy.plusDays(18)))
                            .nombrePublicador("Portal Trabajos")
                            .requiereExperiencia(false)
                            .requiereNivelEducacional(false)
                            .tipoContrato("HONORARIOS")
                            .tipoJornada("COMPLETA")
                            .nivelCargo("OPERARIO")
                            .origenOferta("EXTERNA")
                            .urlFuente("https://jobs.example/operario-bodega")
                            .practicaProfesional(false)
                            .build(),

                    OfertaExterna.builder()
                            .empresa(report)
                            .nombre("Ejecutivo Comercial")
                            .vacantesDisponibles(2)
                            .descripcion("Venta de servicios B2B zona norte")
                            .ubicacion(U.apply("Antofagasta", "Antofagasta"))
                            .rangoSalarial(R.apply(1000000, 2000000))
                            .periodoVigencia(V.apply(hoy.minusDays(3), hoy.plusDays(22)))
                            .nombrePublicador("Portal Comercial")
                            .requiereExperiencia(true)
                            .requiereNivelEducacional(true)
                            .tipoNivelEducacional("Profesional")
                            .tipoContrato("INDEFINIDO")
                            .tipoJornada("COMPLETA")
                            .nivelCargo("EJECUTIVO")
                            .origenOferta("EXTERNA")
                            .urlFuente("https://comercial.example/ejecutivo")
                            .practicaProfesional(false)
                            .build(),

                    OfertaExterna.builder()
                            .empresa(salud)
                            .nombre("Técnico en Enfermería (Práctica)")
                            .vacantesDisponibles(3)
                            .descripcion("Apoyo en pabellón y hospitalizados")
                            .ubicacion(U.apply("Los Lagos", "Puerto Montt"))
                            .rangoSalarial(R.apply(180000, 220000))
                            .periodoVigencia(V.apply(hoy, hoy.plusDays(45)))
                            .nombrePublicador("Salud Empleos")
                            .requiereExperiencia(false)
                            .requiereNivelEducacional(true)
                            .tipoNivelEducacional("Técnico")
                            .tipoContrato("PLAZO_FIJO")
                            .tipoJornada("COMPLETA")
                            .nivelCargo("TECNICO")
                            .origenOferta("EXTERNA")
                            .urlFuente("https://saludplus.cl/ten-practica")
                            .practicaProfesional(true)
                            .build()
            );
            ext.forEach(ofertasEXT::save);

            // ====== Postulaciones de ejemplo ======
            // u1 postula a 3 ofertas
            OfertaBNE bne1 = bne.get(0);
            OfertaBNE bne2 = bne.get(1);
            OfertaExterna ex1 = ext.get(0);

            postulaciones.save(Postulacion.builder()
                    .ofertaId(new OfertaId(bne1.getId(), OfertaId.Origen.BNE))
                    .empresaId(bne1.getEmpresa().getId())
                    .usuarioId(u1.getId())
                    .fechaPostulacion(hoy)
                    .estado(Postulacion.EstadoPostulacion.ENVIADA)
                    .build());

            postulaciones.save(Postulacion.builder()
                    .ofertaId(new OfertaId(bne2.getId(), OfertaId.Origen.BNE))
                    .empresaId(bne2.getEmpresa().getId())
                    .usuarioId(u1.getId())
                    .fechaPostulacion(hoy.minusDays(1))
                    .estado(Postulacion.EstadoPostulacion.ACEPTADA)
                    .build());

            postulaciones.save(Postulacion.builder()
                    .ofertaId(new OfertaId(ex1.getId(), OfertaId.Origen.EXTERNA))
                    .empresaId(ex1.getEmpresa().getId())
                    .usuarioId(u1.getId())
                    .fechaPostulacion(hoy.minusDays(2))
                    .estado(Postulacion.EstadoPostulacion.RECHAZADA)
                    .build());

            System.out.println("Seed OK: usuarios=" + u1.getId() + "," + u2.getId()
                    + " | empresas=" + acme.getId() + "," + report.getId() + "," + salud.getId()
                    + " | ofertas BNE=" + bne.size() + " | ofertas EXTERNAS=" + ext.size());
        };
    }
}

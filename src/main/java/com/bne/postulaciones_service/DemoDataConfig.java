/* package com.bne.postulaciones_service;

import com.bne.postulaciones_service.domain.model.Postulacion;
import com.bne.postulaciones_service.domain.model.OfertaEmpleo;
import com.bne.postulaciones_service.domain.model.UsuarioPostulante;
import com.bne.postulaciones_service.infrastructure.repository.JpaPostulacionRepository;
import com.bne.postulaciones_service.infrastructure.repository.JpaOfertaRepository;
import com.bne.postulaciones_service.infrastructure.repository.JpaUsuarioPostulanteRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DemoDataConfig {

    @Bean
    CommandLineRunner testJpa(JpaUsuarioPostulanteRepository usuarioRepo,
                              JpaOfertaRepository ofertaRepo,
                              JpaPostulacionRepository postulacionRepo) {
        return args -> {
            System.out.println("===== INICIO PRUEBAS DE PERSISTENCIA =====\n");

            // ====== CREAR USUARIO ======
            UsuarioPostulante u = new UsuarioPostulante("Cristian", "cristian@mail.com");
            usuarioRepo.save(u);

            // ====== CREAR OFERTA ======
            OfertaEmpleo o = new OfertaEmpleo("Desarrollador Java", "Trabajo remoto, tiempo completo");
            ofertaRepo.save(o);

            // ====== CREAR POSTULACION ======
            Postulacion p = new Postulacion(u, o, "ENVIADA");
            postulacionRepo.save(p);

            // ================== SALIDA BONITA ==================
            System.out.println("===== DATOS DE PRUEBA =====\n");

            // Usuarios
            System.out.printf("%-20s | %-30s%n", "USUARIO", "EMAIL");
            usuarioRepo.findAll().forEach(
                    usr -> System.out.printf("%-20s | %-30s%n", usr.getNombre(), usr.getEmail())
            );
            System.out.println("-------------------------------");

            // Ofertas
            System.out.printf("%-25s | %-40s%n", "OFERTA", "DESCRIPCIÓN");
            ofertaRepo.findAll().forEach(
                    oferta -> System.out.printf("%-25s | %-40s%n", oferta.getTitulo(), oferta.getDescripcion())
            );
            System.out.println("-------------------------------");

            // Postulaciones
            System.out.printf("%-15s | %-25s | %-10s%n", "USUARIO", "OFERTA", "ESTADO");
            postulacionRepo.findAll().forEach(
                    post -> System.out.printf("%-15s | %-25s | %-10s%n",
                            post.getPostulante().getNombre(),
                            post.getOferta().getTitulo(),
                            post.getEstado()
                    )
            );

            System.out.println("\n===== FIN PRUEBAS DE PERSISTENCIA =====");
        };
    }
}*/
package com.bne.postulaciones_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication  // está en el paquete raíz, así escanea todo debajo
public class PostulacionesServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(PostulacionesServiceApplication.class, args);
    }
}

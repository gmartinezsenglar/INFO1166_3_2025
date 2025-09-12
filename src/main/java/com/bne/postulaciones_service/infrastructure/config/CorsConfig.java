package com.bne.postulaciones_service.infrastructure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                // Puertos comunes de Vite/Angular; ajusta si usas otros
                .allowedOrigins("http://localhost:5173", "http://localhost:4200")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
                .allowedHeaders("*")
                // Si el front necesita leer estas cabeceras de respuesta:
                .exposedHeaders("Authorization", "Location", "Content-Disposition")
                .allowCredentials(true)
                .maxAge(3600); // cache preflight 1h
    }
}

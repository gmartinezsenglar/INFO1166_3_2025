package com.bne.postulaciones_service.infrastructure.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.bne.postulaciones_service.infrastructure.config.jwt.JwtUtil;

@Configuration
public class JwtConfig {
    @Bean
    public JwtUtil jwtUtil(
            @Value("${security.jwt.secret}") String secret,
            @Value("${security.jwt.expirationMs}") long expirationMs) {
        return new JwtUtil(secret, expirationMs);
    }
}

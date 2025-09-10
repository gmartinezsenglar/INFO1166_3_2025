package com.bne.postulaciones_service.infrastructure.config;

import com.bne.postulaciones_service.infrastructure.config.jwt.JwtAuthFilter;
import com.bne.postulaciones_service.infrastructure.config.jwt.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final JwtUtil jwtUtil;

    public SecurityConfig(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/hello",
                                "/auth/**",
                                // Swagger / OpenAPI
                                "/swagger", "/swagger/**", "/swagger-ui/**", "/v3/api-docs/**",
                                // Infra abierta
                                "/h2-console/**",
                                "/actuator/health",
                                "/dev/**" //probar sin el tocen
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                // H2 console necesita frames (same origin)
                .headers(h -> h.frameOptions(f -> f.sameOrigin()))
                // 401 cuando falta/vence el token (en vez de 403)
                .exceptionHandling(e -> e
                        .authenticationEntryPoint((req, res, ex) ->
                                res.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                )
                // Evitar Basic/Form por defecto
                .httpBasic(b -> b.disable())
                .formLogin(f -> f.disable())
                // Filtro JWT antes del de username/password
                .addFilterBefore(new JwtAuthFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}

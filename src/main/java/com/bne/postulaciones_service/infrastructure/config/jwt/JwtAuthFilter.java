package com.bne.postulaciones_service.infrastructure.config.jwt;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtAuthFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    /** No aplicar el filtro en rutas públicas ni en preflights CORS */
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String p = request.getServletPath();
        String m = request.getMethod();
        // Preflight
        if ("OPTIONS".equalsIgnoreCase(m)) return true;

        // Rutas públicas (ajusta si agregas/quitas)
        return p.equals("/favicon.ico")
                || p.startsWith("/auth/")
                || p.equals("/hello")
                || p.startsWith("/h2-console")
                || p.startsWith("/actuator/health")
                || p.startsWith("/v3/api-docs")
                || p.startsWith("/swagger-ui")    // ojo: swagger UI real es /swagger-ui/**
                || p.equals("/swagger-ui.html")
                || p.startsWith("/api/ofertas");  // <-- listado/detalle de ofertas público
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        // Si shouldNotFilter() devolvió false, entonces sí filtramos (rutas protegidas)
        String header = request.getHeader("Authorization");

        // Si no viene Bearer, no autenticamos pero dejamos seguir; el SecurityConfig decidirá (401/permitAll)
        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = header.substring(7);
        try {
            Claims claims = jwtUtil.validateAndGetClaims(token);
            String subject = claims.getSubject(); // usuario en el "sub"
            if (subject != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                var auth = new UsernamePasswordAuthenticationToken(
                        subject, null, Collections.emptyList()
                );
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            // Token inválido/expirado -> 401 (no 500)
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            // opcional: un body simple
            // response.setContentType("application/json");
            // response.getWriter().write("{\"error\":\"unauthorized\"}");
        }
    }
}

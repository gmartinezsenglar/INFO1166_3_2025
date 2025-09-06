package com.bne.postulaciones_service.infrastructure.rest;

import com.bne.postulaciones_service.infrastructure.config.jwt.JwtUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
public class AuthController {
    private final JwtUtil jwtUtil;
    public AuthController(JwtUtil jwtUtil) { this.jwtUtil = jwtUtil; }

    @PostMapping("/auth/token")
    public Map<String, String> token(@RequestParam String user) {
        return Map.of("token", jwtUtil.generateToken(user));
    }
}

package com.bne.postulaciones_service.infrastructure.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrivatePingController {
    @GetMapping("/private/ping")
    public String ping() { return "pong (protegido)"; }
}

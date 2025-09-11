package com.bne.postulaciones.domain.model.vo;

public record NivelEducacional(String nivel) {

    public NivelEducacional {
        if (nivel == null || nivel.isBlank()) {
            throw new IllegalArgumentException("El nivel educacional no puede estar vacío.");
        }
        // Validar contra un catálogo permitido
        if (!nivel.matches("BASICA|MEDIA|TECNICO|UNIVERSITARIO|POSTGRADO")) {
            throw new IllegalArgumentException("Nivel educacional inválido: " + nivel);
        }
    }

    public boolean esSuperiorA(NivelEducacional otro) {
        return orden(this.nivel) > orden(otro.nivel);
    }

    private int orden(String nivel) {
        return switch (nivel) {
            case "BASICA" -> 1;
            case "MEDIA" -> 2;
            case "TECNICO" -> 3;
            case "UNIVERSITARIO" -> 4;
            case "POSTGRADO" -> 5;
            default -> 0;
        };
    }
}

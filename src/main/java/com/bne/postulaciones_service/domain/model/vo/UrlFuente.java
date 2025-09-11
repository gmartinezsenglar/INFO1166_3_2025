package com.bne.postulaciones.domain.model.vo;

import java.net.MalformedURLException;
import java.net.URL;

public record UrlFuente(String valor) {

    public UrlFuente {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException("La URL no puede ser vacía.");
        }
        try {
            URL url = new URL(valor);
            if (!url.getProtocol().equals("http") && !url.getProtocol().equals("https")) {
                throw new IllegalArgumentException("La URL debe usar http o https.");
            }
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Formato de URL inválido: " + valor);
        }
    }

    public boolean perteneceADominio(String dominio) {
        return valor.contains(dominio);
    }
}

package com.bne.postulaciones_service.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "empresas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String actividadEconomica;

    private String descripcion;

    private String logo;
    
    public void actualizarNombre(String nuevoNombre) {
        if (nuevoNombre == null || nuevoNombre.isBlank()) {
            throw new IllegalArgumentException("El nombre de la empresa no puede estar vacío.");
        }
        this.nombre=nuevoNombre;
    }

    public void actualizarActividadEconomica(String nuevaActividad) {
        if (nuevaActividad == null || nuevaActividad.isBlank()) {
            throw new IllegalArgumentException("La actividad económica no puede estar vacía.");
        }
        this.actividadEconomica=nuevaActividad;
    }

    public void actualizarDescripcion(String nuevaDescripcion) {
        if (nuevaDescripcion == null || nuevaDescripcion.isBlank()) {
            throw new IllegalArgumentException("La descripción no puede estar vacía.");
        }
        this.descripcion=nuevaDescripcion;
    }

    public void cambiarLogo(String nuevoLogo) {
        if (nuevoLogo == null || nuevoLogo.isBlank()) {
            throw new IllegalArgumentException("El logo no puede estar vacío.");
        }
        this.logo=nuevoLogo;
    }

    public boolean datosBasicosCompletos() {
        return nombre != null && !nombre.isBlank()
            && actividadEconomica != null && !actividadEconomica.isBlank()
            && descripcion != null && !descripcion.isBlank()
            && logo != null && !logo.isBlank();
    }
}

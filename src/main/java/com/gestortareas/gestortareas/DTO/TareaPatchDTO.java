package com.gestortareas.gestortareas.DTO;

import lombok.Data;

@Data
public class TareaPatchDTO {
    private String titulo;
    private String descripcion;
    private Boolean completada;
}

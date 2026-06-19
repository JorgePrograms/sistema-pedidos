package com.examen.pedidos.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ClienteRequest {
    private String nombre;
    private String apellido;
    private String dni;
    private String correo;
}

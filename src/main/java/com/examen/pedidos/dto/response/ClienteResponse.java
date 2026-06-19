package com.examen.pedidos.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ClienteResponse {
    private Long id;
    private String nombre;
    private String apellido;
    private String dni;
    private String correo;
}

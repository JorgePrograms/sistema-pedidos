package com.examen.pedidos.dto.request;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetallePedidoRequest {
    private Long productoId;

    private Integer cantidad;
}

package com.examen.pedidos.dto.request;
import java.util.List;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoRequest {
    private Long clienteId;
    private List<DetallePedidoRequest> items;
}

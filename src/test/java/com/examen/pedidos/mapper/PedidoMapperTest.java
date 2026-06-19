package com.examen.pedidos.mapper;

import com.examen.pedidos.dto.response.PedidoResponse;
import com.examen.pedidos.entity.Cliente;
import com.examen.pedidos.entity.Pedido;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PedidoMapperTest {

    private final PedidoMapper mapper =
            Mappers.getMapper(PedidoMapper.class);

    @Test
    void toResponse() {

        LocalDateTime fecha = LocalDateTime.now();

        Cliente cliente = Cliente.builder()
                .id(5L)
                .build();

        Pedido pedido = Pedido.builder()
                .id(1L)
                .cliente(cliente)
                .fechaPedido(fecha)
                .estado("PENDIENTE")
                .total(new BigDecimal("150.50"))
                .build();

        PedidoResponse response = mapper.toResponse(pedido);

        assertNotNull(response);

        assertEquals(1L, response.getId());
        assertEquals(5L, response.getClienteId());
        assertEquals(fecha, response.getFechaPedido());
        assertEquals("PENDIENTE", response.getEstado());
        assertEquals(new BigDecimal("150.50"), response.getTotal());
    }
}
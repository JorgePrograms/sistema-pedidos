package com.examen.pedidos.service;

import com.examen.pedidos.dto.request.PedidoRequest;
import com.examen.pedidos.dto.response.PedidoResponse;

import java.util.List;

public interface PedidoService {
    PedidoResponse crearPedido(PedidoRequest request);

    PedidoResponse obtenerPedido(Long id);

    List<PedidoResponse> listarPedidosPorCliente(Long clienteId);
}

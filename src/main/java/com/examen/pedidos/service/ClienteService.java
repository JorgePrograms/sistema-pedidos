package com.examen.pedidos.service;

import com.examen.pedidos.dto.request.ClienteRequest;
import com.examen.pedidos.dto.response.ClienteResponse;

public interface ClienteService {
    ClienteResponse crearCliente(ClienteRequest request);

    ClienteResponse obtenerCliente(Long id);
}

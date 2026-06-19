package com.examen.pedidos.service.impl;

import com.examen.pedidos.dto.request.ClienteRequest;
import com.examen.pedidos.dto.response.ClienteResponse;
import com.examen.pedidos.entity.Cliente;
import com.examen.pedidos.exception.ClienteNotFoundException;
import com.examen.pedidos.mapper.ClienteMapper;
import com.examen.pedidos.repository.ClienteRepository;
import com.examen.pedidos.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;


    @Override
    public ClienteResponse crearCliente(ClienteRequest request) {

        Cliente cliente = clienteMapper.toEntity(request);

        cliente.setFechaRegistro(LocalDateTime.now());

        Cliente guardado = clienteRepository.save(cliente);

        return clienteMapper.toResponse(guardado);
    }

    @Override
    public ClienteResponse obtenerCliente(Long id) {

        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() ->
                        new ClienteNotFoundException("Cliente no encontrado"));

        return clienteMapper.toResponse(cliente);
    }
}
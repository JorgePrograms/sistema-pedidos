package com.examen.pedidos.service.impl;

import com.examen.pedidos.dto.request.ClienteRequest;
import com.examen.pedidos.dto.response.ClienteResponse;
import com.examen.pedidos.entity.Cliente;
import com.examen.pedidos.exception.ClienteNotFoundException;
import com.examen.pedidos.mapper.ClienteMapper;
import com.examen.pedidos.repository.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteServiceImplTest {

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private ClienteMapper clienteMapper;

    @InjectMocks
    private ClienteServiceImpl clienteService;

    @Test
    void crearCliente_DeberiaGuardarYRetornarCliente() {

        ClienteRequest request = new ClienteRequest();

        Cliente cliente = new Cliente();

        Cliente clienteGuardado = new Cliente();
        clienteGuardado.setId(1L);

        ClienteResponse response = new ClienteResponse();

        when(clienteMapper.toEntity(request)).thenReturn(cliente);
        when(clienteRepository.save(any(Cliente.class))).thenReturn(clienteGuardado);
        when(clienteMapper.toResponse(clienteGuardado)).thenReturn(response);

        ClienteResponse resultado = clienteService.crearCliente(request);

        assertNotNull(resultado);
        verify(clienteMapper).toEntity(request);
        verify(clienteRepository).save(cliente);
        verify(clienteMapper).toResponse(clienteGuardado);

        assertNotNull(cliente.getFechaRegistro());
    }

    @Test
    void obtenerCliente_DeberiaRetornarClienteCuandoExiste() {

        Long id = 1L;

        Cliente cliente = new Cliente();
        cliente.setId(id);

        ClienteResponse response = new ClienteResponse();

        when(clienteRepository.findById(id))
                .thenReturn(Optional.of(cliente));

        when(clienteMapper.toResponse(cliente))
                .thenReturn(response);

        ClienteResponse resultado = clienteService.obtenerCliente(id);

        assertNotNull(resultado);

        verify(clienteRepository).findById(id);
        verify(clienteMapper).toResponse(cliente);
    }

    @Test
    void obtenerCliente_DeberiaLanzarExcepcionCuandoNoExiste() {

        Long id = 99L;

        when(clienteRepository.findById(id))
                .thenReturn(Optional.empty());

        ClienteNotFoundException exception =
                assertThrows(
                        ClienteNotFoundException.class,
                        () -> clienteService.obtenerCliente(id)
                );

        assertEquals("Cliente no encontrado", exception.getMessage());

        verify(clienteRepository).findById(id);
        verify(clienteMapper, never()).toResponse(any());
    }
}
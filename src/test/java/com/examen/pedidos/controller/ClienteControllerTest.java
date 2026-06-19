package com.examen.pedidos.controller;

import com.examen.pedidos.dto.request.ClienteRequest;
import com.examen.pedidos.dto.response.ClienteResponse;
import com.examen.pedidos.response.BaseResponse;
import com.examen.pedidos.service.ClienteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteControllerTest {

    @Mock
    private ClienteService clienteService;

    @InjectMocks
    private ClienteController clienteController;

    @Test
    void crearCliente() {

        ClienteRequest request = new ClienteRequest();
        ClienteResponse clienteResponse = new ClienteResponse();

        when(clienteService.crearCliente(request))
                .thenReturn(clienteResponse);

        ResponseEntity<BaseResponse<ClienteResponse>> response =
                clienteController.crear(request);

        assertEquals(200, response.getStatusCode().value());
        assertEquals(200, response.getBody().getCodigo());
        assertEquals("Cliente creado correctamente",
                response.getBody().getMensaje());

        verify(clienteService).crearCliente(request);
    }

    @Test
    void obtenerCliente() {

        ClienteResponse clienteResponse = new ClienteResponse();

        when(clienteService.obtenerCliente(1L))
                .thenReturn(clienteResponse);

        ResponseEntity<BaseResponse<ClienteResponse>> response =
                clienteController.obtener(1L);

        assertEquals(200, response.getStatusCode().value());
        assertEquals(200, response.getBody().getCodigo());
        assertEquals("Cliente encontrado",
                response.getBody().getMensaje());

        verify(clienteService).obtenerCliente(1L);
    }
}
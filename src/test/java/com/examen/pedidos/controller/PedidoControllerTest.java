package com.examen.pedidos.controller;

import com.examen.pedidos.dto.request.PedidoRequest;
import com.examen.pedidos.dto.response.PedidoResponse;
import com.examen.pedidos.response.BaseResponse;
import com.examen.pedidos.service.PedidoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PedidoControllerTest {

    @Mock
    private PedidoService pedidoService;

    @InjectMocks
    private PedidoController pedidoController;

    @Test
    void crearPedido() {

        PedidoRequest request = new PedidoRequest();
        PedidoResponse pedidoResponse = new PedidoResponse();

        when(pedidoService.crearPedido(request))
                .thenReturn(pedidoResponse);

        ResponseEntity<BaseResponse<PedidoResponse>> response =
                pedidoController.crear(request);

        assertEquals(200, response.getStatusCode().value());
        assertEquals(200, response.getBody().getCodigo());
        assertEquals("Pedido creado correctamente",
                response.getBody().getMensaje());

        verify(pedidoService).crearPedido(request);
    }

    @Test
    void obtenerPedido() {

        PedidoResponse pedidoResponse = new PedidoResponse();

        when(pedidoService.obtenerPedido(1L))
                .thenReturn(pedidoResponse);

        ResponseEntity<BaseResponse<PedidoResponse>> response =
                pedidoController.obtener(1L);

        assertEquals(200, response.getStatusCode().value());
        assertEquals(200, response.getBody().getCodigo());
        assertEquals("Pedido encontrado",
                response.getBody().getMensaje());

        verify(pedidoService).obtenerPedido(1L);
    }

    @Test
    void listarPedidosPorCliente() {

        PedidoResponse pedido = new PedidoResponse();

        when(pedidoService.listarPedidosPorCliente(1L))
                .thenReturn(List.of(pedido));

        ResponseEntity<BaseResponse<List<PedidoResponse>>> response =
                pedidoController.listarPorCliente(1L);

        assertEquals(200, response.getStatusCode().value());
        assertEquals(200, response.getBody().getCodigo());
        assertEquals("Pedidos del cliente",
                response.getBody().getMensaje());

        assertNotNull(response.getBody().getObjeto());
        assertEquals(1, response.getBody().getObjeto().size());

        verify(pedidoService).listarPedidosPorCliente(1L);
    }
}
package com.examen.pedidos.controller;

import com.examen.pedidos.dto.request.ProductoRequest;
import com.examen.pedidos.dto.response.ProductoResponse;
import com.examen.pedidos.response.BaseResponse;
import com.examen.pedidos.service.ProductoService;
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
class ProductoControllerTest {

    @Mock
    private ProductoService productoService;

    @InjectMocks
    private ProductoController productoController;

    @Test
    void crearProducto() {

        ProductoRequest request = new ProductoRequest();
        ProductoResponse productoResponse = new ProductoResponse();

        when(productoService.crearProducto(request))
                .thenReturn(productoResponse);

        ResponseEntity<BaseResponse<ProductoResponse>> response =
                productoController.crear(request);

        assertEquals(200, response.getStatusCode().value());
        assertEquals(200, response.getBody().getCodigo());
        assertEquals("Producto creado correctamente",
                response.getBody().getMensaje());

        verify(productoService).crearProducto(request);
    }

    @Test
    void listarProductos() {

        ProductoResponse producto = new ProductoResponse();

        when(productoService.listarProductos())
                .thenReturn(List.of(producto));

        ResponseEntity<BaseResponse<List<ProductoResponse>>> response =
                productoController.listar();

        assertEquals(200, response.getStatusCode().value());
        assertEquals(200, response.getBody().getCodigo());
        assertEquals("Lista de productos",
                response.getBody().getMensaje());

        assertNotNull(response.getBody().getObjeto());
        assertEquals(1, response.getBody().getObjeto().size());

        verify(productoService).listarProductos();
    }
}
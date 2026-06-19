package com.examen.pedidos.service.impl;

import com.examen.pedidos.dto.request.ProductoRequest;
import com.examen.pedidos.dto.response.ProductoResponse;
import com.examen.pedidos.entity.Producto;
import com.examen.pedidos.mapper.ProductoMapper;
import com.examen.pedidos.repository.ProductoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductoServiceImplTest {

    @Mock
    private ProductoRepository productoRepository;

    @Mock
    private ProductoMapper productoMapper;

    @InjectMocks
    private ProductoServiceImpl productoService;

    @Test
    void crearProducto_DeberiaGuardarYRetornarProducto() {

        ProductoRequest request = new ProductoRequest();

        Producto producto = new Producto();

        Producto productoGuardado = new Producto();
        productoGuardado.setId(1L);

        ProductoResponse response = new ProductoResponse();

        when(productoMapper.toEntity(request))
                .thenReturn(producto);

        when(productoRepository.save(any(Producto.class)))
                .thenReturn(productoGuardado);

        when(productoMapper.toResponse(productoGuardado))
                .thenReturn(response);

        ProductoResponse resultado =
                productoService.crearProducto(request);

        assertNotNull(resultado);

        verify(productoMapper).toEntity(request);
        verify(productoRepository).save(producto);
        verify(productoMapper).toResponse(productoGuardado);

        assertTrue(producto.getEstado());
    }

    @Test
    void listarProductos_DeberiaRetornarListaDeProductos() {

        Producto producto1 = new Producto();
        Producto producto2 = new Producto();

        ProductoResponse response1 = new ProductoResponse();
        ProductoResponse response2 = new ProductoResponse();

        when(productoRepository.findAll())
                .thenReturn(List.of(producto1, producto2));

        when(productoMapper.toResponse(producto1))
                .thenReturn(response1);

        when(productoMapper.toResponse(producto2))
                .thenReturn(response2);

        List<ProductoResponse> resultado =
                productoService.listarProductos();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());

        verify(productoRepository).findAll();
        verify(productoMapper).toResponse(producto1);
        verify(productoMapper).toResponse(producto2);
    }
}
package com.examen.pedidos.service.impl;

import com.examen.pedidos.dto.request.DetallePedidoRequest;
import com.examen.pedidos.dto.request.PedidoRequest;
import com.examen.pedidos.dto.response.PedidoResponse;
import com.examen.pedidos.entity.Cliente;
import com.examen.pedidos.entity.Pedido;
import com.examen.pedidos.entity.Producto;
import java.util.List;
import java.util.Optional;
import com.examen.pedidos.exception.PedidoNotFoundException;
import com.examen.pedidos.exception.StockInsuficienteException;
import com.examen.pedidos.mapper.PedidoMapper;
import com.examen.pedidos.repository.ClienteRepository;
import com.examen.pedidos.repository.PedidoRepository;
import com.examen.pedidos.repository.ProductoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PedidoServiceImplTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private ProductoRepository productoRepository;

    @Mock
    private PedidoMapper pedidoMapper;

    @InjectMocks
    private PedidoServiceImpl pedidoService;

    @Test
    void crearPedido_cuandoDatosSonValidos_retornaPedidoCreado() {
        Cliente cliente = Cliente.builder()
                .id(1L)
                .nombre("Juan")
                .build();

        Producto producto = Producto.builder()
                .id(1L)
                .nombre("Laptop")
                .precio(BigDecimal.valueOf(100))
                .stock(10)
                .build();

        DetallePedidoRequest detalle =
                DetallePedidoRequest.builder()
                        .productoId(1L)
                        .cantidad(2)
                        .build();

        PedidoRequest request =
                PedidoRequest.builder()
                        .clienteId(1L)
                        .items(List.of(detalle))
                        .build();

        Pedido pedidoGuardado = Pedido.builder()
                .id(1L)
                .total(BigDecimal.valueOf(200))
                .estado("CREADO")
                .build();

        PedidoResponse response =
                PedidoResponse.builder()
                        .id(1L)
                        .total(BigDecimal.valueOf(200))
                        .estado("CREADO")
                        .build();

        when(clienteRepository.findById(1L))
                .thenReturn(Optional.of(cliente));

        when(productoRepository.findById(1L))
                .thenReturn(Optional.of(producto));

        when(pedidoRepository.save(any(Pedido.class)))
                .thenReturn(pedidoGuardado);

        when(pedidoMapper.toResponse(pedidoGuardado))
                .thenReturn(response);

        PedidoResponse resultado =
                pedidoService.crearPedido(request);

        assertNotNull(resultado);
        assertEquals("CREADO", resultado.getEstado());
        assertEquals(
                BigDecimal.valueOf(200),
                resultado.getTotal());

        verify(pedidoRepository, times(1))
                .save(any(Pedido.class));
    }

    @Test
    void crearPedido_cuandoStockEsInsuficiente_lanzaStockInsuficienteException() {
        Cliente cliente = Cliente.builder()
                .id(1L)
                .build();

        Producto producto = Producto.builder()
                .id(1L)
                .stock(1)
                .precio(BigDecimal.valueOf(100))
                .build();

        DetallePedidoRequest detalle =
                DetallePedidoRequest.builder()
                        .productoId(1L)
                        .cantidad(5)
                        .build();

        PedidoRequest request =
                PedidoRequest.builder()
                        .clienteId(1L)
                        .items(List.of(detalle))
                        .build();

        when(clienteRepository.findById(1L))
                .thenReturn(Optional.of(cliente));

        when(productoRepository.findById(1L))
                .thenReturn(Optional.of(producto));

        assertThrows(
                StockInsuficienteException.class,
                () -> pedidoService.crearPedido(request)
        );

        verify(pedidoRepository, never())
                .save(any());
    }

    @Test
    void buscarPedido_cuandoNoExiste_lanzaPedidoNotFoundException() {
        when(pedidoRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                PedidoNotFoundException.class,
                () -> pedidoService.obtenerPedido(1L)
        );
    }
}
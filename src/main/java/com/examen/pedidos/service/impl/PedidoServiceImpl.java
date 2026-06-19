package com.examen.pedidos.service.impl;

import com.examen.pedidos.dto.request.DetallePedidoRequest;
import com.examen.pedidos.dto.request.PedidoRequest;
import com.examen.pedidos.dto.response.PedidoResponse;
import com.examen.pedidos.entity.Cliente;
import com.examen.pedidos.entity.DetallePedido;
import com.examen.pedidos.entity.Pedido;
import com.examen.pedidos.entity.Producto;
import com.examen.pedidos.exception.PedidoNotFoundException;
import com.examen.pedidos.exception.StockInsuficienteException;
import com.examen.pedidos.mapper.PedidoMapper;
import com.examen.pedidos.repository.ClienteRepository;
import com.examen.pedidos.repository.PedidoRepository;
import com.examen.pedidos.repository.ProductoRepository;
import com.examen.pedidos.service.PedidoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ProductoRepository productoRepository;
    private final PedidoMapper pedidoMapper;

    @Override
    @Transactional
    public PedidoResponse crearPedido(PedidoRequest request) {

        Cliente cliente = clienteRepository.findById(request.getClienteId())
                .orElseThrow(() ->
                        new RuntimeException("Cliente no encontrado"));

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setFechaPedido(LocalDateTime.now());
        pedido.setEstado("CREADO");

        List<DetallePedido> detalles = new ArrayList<>();

        BigDecimal total = BigDecimal.ZERO;

        for (DetallePedidoRequest item : request.getItems()) {
            if (item.getCantidad() <= 0) {
                throw new StockInsuficienteException(
                        "La cantidad debe ser mayor a cero");
            }

            Producto producto = productoRepository.findById(item.getProductoId())
                    .orElseThrow(() ->
                             new PedidoNotFoundException("Pedido no encontrado"));

            if (producto.getStock() < item.getCantidad()) {
                throw new StockInsuficienteException(
                        "Stock insuficiente para " + producto.getNombre());
            }

            BigDecimal subtotal =
                    producto.getPrecio()
                            .multiply(BigDecimal.valueOf(item.getCantidad()));

            DetallePedido detalle = new DetallePedido();

            detalle.setProductoId(producto.getId());
            detalle.setNombreProducto(producto.getNombre());
            detalle.setCantidad(item.getCantidad());
            detalle.setPrecioUnitario(producto.getPrecio());
            detalle.setSubtotal(subtotal);

            detalle.setPedido(pedido);

            detalles.add(detalle);

            producto.setStock(
                    producto.getStock() - item.getCantidad());

            productoRepository.save(producto);

            total = total.add(subtotal);
        }

        pedido.setDetalles(detalles);
        pedido.setTotal(total);

        Pedido guardado = pedidoRepository.save(pedido);

        return pedidoMapper.toResponse(guardado);
    }

    @Override
    public PedidoResponse obtenerPedido(Long id) {

        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() ->
                        new PedidoNotFoundException("Pedido no encontrado"));

        return pedidoMapper.toResponse(pedido);
    }

    @Override
    public List<PedidoResponse> listarPedidosPorCliente(Long clienteId) {

        return pedidoRepository.findByClienteId(clienteId)
                .stream()
                .map(pedidoMapper::toResponse)
                .toList();
    }
}
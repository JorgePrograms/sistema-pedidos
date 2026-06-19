package com.examen.pedidos.service.impl;

import com.examen.pedidos.dto.request.ProductoRequest;
import com.examen.pedidos.dto.response.ProductoResponse;
import com.examen.pedidos.entity.Producto;
import com.examen.pedidos.mapper.ProductoMapper;
import com.examen.pedidos.repository.ProductoRepository;
import com.examen.pedidos.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper;

    @Override
    public ProductoResponse crearProducto(ProductoRequest request) {

        Producto producto = productoMapper.toEntity(request);

        producto.setEstado(true);

        Producto guardado = productoRepository.save(producto);

        return productoMapper.toResponse(guardado);
    }

    @Override
    public List<ProductoResponse> listarProductos() {

        return productoRepository.findAll()
                .stream()
                .map(productoMapper::toResponse)
                .toList();
    }
}
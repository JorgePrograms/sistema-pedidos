package com.examen.pedidos.mapper;

import com.examen.pedidos.dto.request.ProductoRequest;
import com.examen.pedidos.dto.response.ProductoResponse;
import com.examen.pedidos.entity.Producto;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProductoMapperTest {

    private final ProductoMapper mapper =
            Mappers.getMapper(ProductoMapper.class);

    @Test
    void toEntity() {

        ProductoRequest request = ProductoRequest.builder()
                .nombre("Laptop")
                .descripcion("Laptop Gamer")
                .precio(new BigDecimal("3500.00"))
                .stock(10)
                .build();

        Producto producto = mapper.toEntity(request);

        assertNotNull(producto);
        assertEquals("Laptop", producto.getNombre());
        assertEquals("Laptop Gamer", producto.getDescripcion());
        assertEquals(new BigDecimal("3500.00"), producto.getPrecio());
        assertEquals(10, producto.getStock());
    }

    @Test
    void toResponse() {

        Producto producto = Producto.builder()
                .id(1L)
                .nombre("Laptop")
                .descripcion("Laptop Gamer")
                .precio(new BigDecimal("3500.00"))
                .stock(10)
                .estado(true)
                .build();

        ProductoResponse response = mapper.toResponse(producto);

        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("Laptop", response.getNombre());
        assertEquals("Laptop Gamer", response.getDescripcion());
        assertEquals(new BigDecimal("3500.00"), response.getPrecio());
        assertEquals(10, response.getStock());
        assertTrue(response.getEstado());
    }
}
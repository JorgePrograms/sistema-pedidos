package com.examen.pedidos.mapper;

import com.examen.pedidos.dto.request.ClienteRequest;
import com.examen.pedidos.dto.response.ClienteResponse;
import com.examen.pedidos.entity.Cliente;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class ClienteMapperTest {

    private final ClienteMapper mapper =
            Mappers.getMapper(ClienteMapper.class);

    @Test
    void toEntity() {

        ClienteRequest request = ClienteRequest.builder()
                .nombre("Juan")
                .apellido("Perez")
                .dni("12345678")
                .correo("juan@gmail.com")
                .build();

        Cliente cliente = mapper.toEntity(request);

        assertNotNull(cliente);
        assertEquals("Juan", cliente.getNombre());
        assertEquals("Perez", cliente.getApellido());
        assertEquals("12345678", cliente.getDni());
        assertEquals("juan@gmail.com", cliente.getCorreo());
    }

    @Test
    void toResponse() {

        Cliente cliente = Cliente.builder()
                .id(1L)
                .nombre("Juan")
                .apellido("Perez")
                .dni("12345678")
                .correo("juan@gmail.com")
                .build();

        ClienteResponse response = mapper.toResponse(cliente);

        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("Juan", response.getNombre());
        assertEquals("Perez", response.getApellido());
        assertEquals("12345678", response.getDni());
        assertEquals("juan@gmail.com", response.getCorreo());
    }
}
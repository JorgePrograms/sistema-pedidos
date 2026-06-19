package com.examen.pedidos.mapper;

import com.examen.pedidos.dto.response.PedidoResponse;
import com.examen.pedidos.entity.Pedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PedidoMapper {
    @Mapping(target = "clienteId", source = "cliente.id")
    PedidoResponse toResponse(Pedido pedido);
}

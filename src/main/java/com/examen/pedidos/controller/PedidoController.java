package com.examen.pedidos.controller;

import com.examen.pedidos.dto.request.PedidoRequest;
import com.examen.pedidos.dto.response.PedidoResponse;
import com.examen.pedidos.response.BaseResponse;
import com.examen.pedidos.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<BaseResponse<PedidoResponse>> crear(
            @RequestBody PedidoRequest request) {

        PedidoResponse response =
                pedidoService.crearPedido(request);

        return ResponseEntity.ok(
                BaseResponse.<PedidoResponse>builder()
                        .codigo(200)
                        .mensaje("Pedido creado correctamente")
                        .objeto(response)
                        .build()
        );
    }
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<PedidoResponse>> obtener(
            @PathVariable Long id) {

        PedidoResponse response =
                pedidoService.obtenerPedido(id);

        return ResponseEntity.ok(
                BaseResponse.<PedidoResponse>builder()
                        .codigo(200)
                        .mensaje("Pedido encontrado")
                        .objeto(response)
                        .build()
        );
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<BaseResponse<List<PedidoResponse>>> listarPorCliente(
            @PathVariable Long clienteId) {

        List<PedidoResponse> response =
                pedidoService.listarPedidosPorCliente(clienteId);

        return ResponseEntity.ok(
                BaseResponse.<List<PedidoResponse>>builder()
                        .codigo(200)
                        .mensaje("Pedidos del cliente")
                        .objeto(response)
                        .build()
        );
    }
}
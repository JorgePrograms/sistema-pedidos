package com.examen.pedidos.controller;

import com.examen.pedidos.dto.request.ClienteRequest;
import com.examen.pedidos.dto.response.ClienteResponse;
import com.examen.pedidos.response.BaseResponse;
import com.examen.pedidos.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping
    public ResponseEntity<BaseResponse<ClienteResponse>> crear(
            @RequestBody ClienteRequest request) {

        ClienteResponse response =
                clienteService.crearCliente(request);

        return ResponseEntity.ok(
                BaseResponse.<ClienteResponse>builder()
                        .codigo(200)
                        .mensaje("Cliente creado correctamente")
                        .objeto(response)
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<ClienteResponse>> obtener(
            @PathVariable Long id) {

        ClienteResponse response =
                clienteService.obtenerCliente(id);

        return ResponseEntity.ok(
                BaseResponse.<ClienteResponse>builder()
                        .codigo(200)
                        .mensaje("Cliente encontrado")
                        .objeto(response)
                        .build()
        );
    }
}
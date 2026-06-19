package com.examen.pedidos.controller;

import com.examen.pedidos.dto.request.ProductoRequest;
import com.examen.pedidos.dto.response.ProductoResponse;
import com.examen.pedidos.response.BaseResponse;
import com.examen.pedidos.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @PostMapping
    public ResponseEntity<BaseResponse<ProductoResponse>> crear(
            @RequestBody ProductoRequest request) {

        ProductoResponse response =
                productoService.crearProducto(request);

        return ResponseEntity.ok(
                BaseResponse.<ProductoResponse>builder()
                        .codigo(200)
                        .mensaje("Producto creado correctamente")
                        .objeto(response)
                        .build()
        );
    }

    @GetMapping
    public ResponseEntity<BaseResponse<List<ProductoResponse>>> listar() {

        List<ProductoResponse> response =
                productoService.listarProductos();

        return ResponseEntity.ok(
                BaseResponse.<List<ProductoResponse>>builder()
                        .codigo(200)
                        .mensaje("Lista de productos")
                        .objeto(response)
                        .build()
        );
    }
}
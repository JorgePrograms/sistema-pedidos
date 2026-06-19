package com.examen.pedidos.exception;

import com.examen.pedidos.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PedidoNotFoundException.class)
    public ResponseEntity<BaseResponse<Object>> handlePedidoNotFound(
            PedidoNotFoundException ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(
                        BaseResponse.builder()
                                .codigo(404)
                                .mensaje(ex.getMessage())
                                .objeto(null)
                                .build()
                );
    }

    @ExceptionHandler(StockInsuficienteException.class)
    public ResponseEntity<BaseResponse<Object>> handleStockInsuficiente(
            StockInsuficienteException ex) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(
                        BaseResponse.builder()
                                .codigo(400)
                                .mensaje(ex.getMessage())
                                .objeto(null)
                                .build()
                );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse<Object>> handleGeneral(
            Exception ex) {

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        BaseResponse.builder()
                                .codigo(500)
                                .mensaje(ex.getMessage())
                                .objeto(null)
                                .build()
                );
    }


    @ExceptionHandler(ClienteNotFoundException.class)
    public ResponseEntity<BaseResponse<Object>> handleClienteNotFound(
            ClienteNotFoundException ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(
                        BaseResponse.builder()
                                .codigo(404)
                                .mensaje(ex.getMessage())
                                .objeto(null)
                                .build()
                );
    }

}
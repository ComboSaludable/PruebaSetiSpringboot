package com.example.prueba.controller;

import com.example.prueba.dto.PedidoDTO;
import com.example.prueba.service.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoService orderService;

    public PedidoController(PedidoService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/process")
    public ResponseEntity<String> processOrder(@RequestBody PedidoDTO request) {
        return ResponseEntity.ok(orderService.processOrder(request));
    }
}
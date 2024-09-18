package com.spb.supermercado.carrinho_services.rabbitmq;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rabbitmq")
@CrossOrigin(origins = "http://localhost:3000")
public class RabbitMQController {

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestBody String message) {
        // Lógica para enviar mensagem
        return ResponseEntity.ok("Mensagem enviada com sucesso!");
    }
}

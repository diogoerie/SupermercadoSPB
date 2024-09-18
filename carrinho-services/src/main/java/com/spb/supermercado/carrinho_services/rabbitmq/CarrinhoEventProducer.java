package com.spb.supermercado.carrinho_services.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@CrossOrigin(origins = "http://localhost:3000")
public class CarrinhoEventProducer {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public CarrinhoEventProducer(RabbitTemplate rabbitTemplate) {this.rabbitTemplate = rabbitTemplate;}

    public void enviarMensagemFinalizacaoPedido(Double valor) {
        rabbitTemplate.convertAndSend("caixa.queue", valor);
        System.out.println("Evento enviado para a fila com valor: " + valor);
    }

}

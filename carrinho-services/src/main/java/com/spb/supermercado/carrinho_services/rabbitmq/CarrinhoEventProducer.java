package com.spb.supermercado.carrinho_services.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CarrinhoEventProducer {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public CarrinhoEventProducer(RabbitTemplate rabbitTemplate) {this.rabbitTemplate = rabbitTemplate;}

    public void enviarMensagemFinalizacaoPedido(Double valor) {
        rabbitTemplate.convertAndSend("caixa.queue", valor);
        System.out.println("Evento enviado para a fila com valor: " + valor);
    }

}

package com.spb.supermercado.users_services.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducer {

    private final AmqpTemplate amqpTemplate;

    private String exchange = "user.exchange";
    private String routingKey = "user.routingkey";

    @Autowired
    public RabbitMQProducer(AmqpTemplate amqpTemplate) {this.amqpTemplate = amqpTemplate;}

    public void sendMessage(String message) {
        amqpTemplate.convertAndSend(exchange, routingKey, message);
        System.out.println("Mensagem enviada ao RabbitMQ: " + message);
    }
}

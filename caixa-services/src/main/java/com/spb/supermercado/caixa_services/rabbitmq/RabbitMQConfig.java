package com.spb.supermercado.caixa_services.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;

@Configuration
@CrossOrigin(origins = "http://localhost:3000")
public class RabbitMQConfig {

    public static final String QUEUE_NAME = "caixa.queue";

    @Bean
    public Queue caixaQueue() {
        return new Queue(QUEUE_NAME, true); // true indica que a fila é durável
    }
}
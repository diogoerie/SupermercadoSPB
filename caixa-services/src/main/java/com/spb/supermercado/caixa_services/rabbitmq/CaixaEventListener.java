package com.spb.supermercado.caixa_services.rabbitmq;

import com.spb.supermercado.caixa_services.service.CaixaService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CaixaEventListener {

    private final CaixaService caixaService;

    @Autowired
    public CaixaEventListener(CaixaService caixaService) {this.caixaService = caixaService;}

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void processarPedidoFinalizado(Double valor) {
        caixaService.registrarEntrada(valor);
        System.out.println("Pedido finalizado processado. Valor: " + valor);
    }

}

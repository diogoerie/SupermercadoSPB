package com.spb.supermercado.carrinho_services.service;

import com.spb.supermercado.carrinho_services.model.Carrinho;
import com.spb.supermercado.carrinho_services.model.ItemCarrinho;
import com.spb.supermercado.carrinho_services.rabbitmq.CarrinhoEventProducer;
import com.spb.supermercado.carrinho_services.repository.CarrinhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarrinhoService {

    private final CarrinhoRepository carrinhoRepository;

    private final CarrinhoEventProducer eventProducer;

    @Autowired
    public CarrinhoService (CarrinhoRepository carrinhoRepository, CarrinhoEventProducer eventProducer) { this.carrinhoRepository = carrinhoRepository; this.eventProducer = eventProducer;}

    public Carrinho getCarrinhoById(Long id) {
        return carrinhoRepository.findById(id).orElse(null);
    }

    public Carrinho getCarrinhoByUsuarioId(Long usuarioId) {
        return carrinhoRepository.findByUsuarioIdAndStatus(usuarioId, "EM PROCESSO")
                .orElse(null);
    }

    public List<Carrinho> getTodosCarrinhos() {
        return carrinhoRepository.findAll();
    }

    public Carrinho criarCarrinho(Long usuarioId) {
        Carrinho carrinho = new Carrinho(usuarioId);
        carrinho.setStatus("EM PROCESSO");
        return carrinhoRepository.save(carrinho);
    }

    public Carrinho addItemToCarrinho(Long carrinhoId, ItemCarrinho item) {
        Carrinho carrinho = getCarrinhoById(carrinhoId);
        if (carrinho == null || "FINALIZADO".equals(carrinho.getStatus())) {
            throw new IllegalStateException("Carrinho não encontrado ou já finalizado");
        }

        carrinho.getItens().add(item);

        double novoValorTotal = carrinho.getValorTotal() + item.getPreco() * item.getQuantidade();
        carrinho.setValorTotal(novoValorTotal);

        return carrinhoRepository.save(carrinho);
    }

    public void deleteCarrinho(Long id) {
        carrinhoRepository.deleteById(id);
    }

    public Carrinho finalizarPedido(Long carrinhoId) {
        Carrinho carrinho = getCarrinhoById(carrinhoId);
        if (carrinho == null || carrinho.getItens().isEmpty()) {
            throw new IllegalStateException("Carrinho vazio ou não encontrado");
        }

        carrinho.setStatus("FINALIZADO");

        eventProducer.enviarMensagemFinalizacaoPedido(carrinho.getValorTotal());

        return carrinhoRepository.save(carrinho);
    }
}

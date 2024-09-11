package com.spb.supermercado.carrinho_service.service;

import com.spb.supermercado.carrinho_service.model.Carrinho;
import com.spb.supermercado.carrinho_service.model.ItemCarrinho;
import com.spb.supermercado.carrinho_service.repository.CarrinhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarrinhoService {

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    public Carrinho criarCarrinho(Long clienteId) {
        Carrinho carrinho = new Carrinho();
        carrinho.setClienteId(clienteId);
        return carrinhoRepository.save(carrinho);
    }

    public Optional<Carrinho> buscarCarrinhoPorClienteId(Long clienteId) {
        return carrinhoRepository.findByClienteId(clienteId);
    }

    public Carrinho adicionarItemAoCarrinho(Long clienteId, ItemCarrinho itemCarrinho) {
        Optional<Carrinho> optionalCarrinho = carrinhoRepository.findByClienteId(clienteId);

        Carrinho carrinho;
        if (optionalCarrinho.isPresent()) {
            carrinho = optionalCarrinho.get();
        } else {
            carrinho = criarCarrinho(clienteId);
        }

        carrinho.getItens().add(itemCarrinho);
        return carrinhoRepository.save(carrinho);
    }

    public void removerItemDoCarrinho(Long clienteId, Long itemId) {
        Optional<Carrinho> optionalCarrinho = carrinhoRepository.findByClienteId(clienteId);
        if (optionalCarrinho.isPresent()) {
            Carrinho carrinho = optionalCarrinho.get();
            carrinho.getItens().removeIf(item -> item.getId().equals(itemId));
            carrinhoRepository.save(carrinho);
        }
    }
}

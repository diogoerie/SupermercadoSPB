package com.spb.supermercado.carrinho_services.service;

import com.spb.supermercado.carrinho_services.model.Carrinho;
import com.spb.supermercado.carrinho_services.model.ItemCarrinho;
import com.spb.supermercado.carrinho_services.repository.CarrinhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarrinhoService {

    private final CarrinhoRepository carrinhoRepository;

    @Autowired
    public CarrinhoService (CarrinhoRepository carrinhoRepository) { this.carrinhoRepository = carrinhoRepository; }

    public Carrinho getCarrinhoById(Long id) {
        return carrinhoRepository.findById(id).orElse(null);
    }

    public Carrinho addItemToCarrinho(Long carrinhoId, ItemCarrinho item) {
        Carrinho carrinho = getCarrinhoById(carrinhoId);
        if (carrinho == null) {
            carrinho = new Carrinho();
        }
        carrinho.getItens().add(item);
        carrinho.setValorTotal(carrinho.getValorTotal() + item.getPreco() * item.getQuantidade());
        return carrinhoRepository.save(carrinho);
    }

    public void deleteCarrinho(Long id) {
        carrinhoRepository.deleteById(id);
    }
}

package com.spb.supermercado.carrinho_service.controller;

import com.spb.supermercado.carrinho_service.model.Carrinho;
import com.spb.supermercado.carrinho_service.model.ItemCarrinho;
import com.spb.supermercado.carrinho_service.service.CarrinhoService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carrinho")
public class CarrinhoController {

    private final CarrinhoService carrinhoService;

    public CarrinhoController(CarrinhoService carrinhoService) {
        this.carrinhoService = carrinhoService;
    }

    // Endpoint para criar um novo carrinho para um cliente
    @PostMapping("/criar/{clienteId}")
    public Carrinho criarCarrinho(@PathVariable Long clienteId) {
        return carrinhoService.criarCarrinho(clienteId);
    }

    @PostMapping("/{carrinhoId}/adicionar")
    public Carrinho adicionarProduto(@PathVariable Long carrinhoId, @RequestBody ItemCarrinho item) {
        return carrinhoService.adicionarProduto(carrinhoId, item);
    }

    @DeleteMapping("/{carrinhoId}/remover/{produtoId}")
    public Carrinho removerProduto(@PathVariable Long carrinhoId, @PathVariable Long produtoId) {
        return carrinhoService.removerProduto(carrinhoId, produtoId);
    }

    @PostMapping("/{carrinhoId}/finalizar")
    public Carrinho finalizarCompra(@PathVariable Long carrinhoId) {
        return carrinhoService.finalizarCompra(carrinhoId);
    }

    // Endpoint para obter o carrinho por ID
    @GetMapping("/{carrinhoId}")
    public Carrinho getCarrinho(@PathVariable Long carrinhoId) {
        return carrinhoService.findById(carrinhoId);
    }

}

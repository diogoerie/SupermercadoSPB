package com.spb.supermercado.carrinho_service.controller;

import com.spb.supermercado.carrinho_service.model.Carrinho;
import com.spb.supermercado.carrinho_service.model.ItemCarrinho;
import com.spb.supermercado.carrinho_service.service.CarrinhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/carrinho")
public class CarrinhoController {

    @Autowired
    private CarrinhoService carrinhoService;

    // Adicionar um item ao carrinho
    @PostMapping("/{clienteId}/adicionar")
    public ResponseEntity<Carrinho> adicionarItem(@PathVariable Long clienteId, @RequestBody ItemCarrinho itemCarrinho) {
        return ResponseEntity.ok(carrinhoService.adicionarItemAoCarrinho(clienteId, itemCarrinho));
    }

    // Ver carrinho por cliente ID
    @GetMapping("/{clienteId}")
    public ResponseEntity<Carrinho> verCarrinho(@PathVariable Long clienteId) {
        Optional<Carrinho> carrinho = carrinhoService.buscarCarrinhoPorClienteId(clienteId);
        return carrinho.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Remover item do carrinho
    @DeleteMapping("/{clienteId}/remover/{itemId}")
    public ResponseEntity<Void> removerItem(@PathVariable Long clienteId, @PathVariable Long itemId) {
        carrinhoService.removerItemDoCarrinho(clienteId, itemId);
        return ResponseEntity.noContent().build();
    }
}
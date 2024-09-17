package com.spb.supermercado.carrinho_services.controller;

import com.spb.supermercado.carrinho_services.model.Carrinho;
import com.spb.supermercado.carrinho_services.model.ItemCarrinho;
import com.spb.supermercado.carrinho_services.service.CarrinhoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carrinhos")
public class CarrinhoController {

    private final CarrinhoService carrinhoService;

    @Autowired
    public CarrinhoController(CarrinhoService carrinhoService) {this.carrinhoService = carrinhoService;}

    @GetMapping("/{id}")
    public ResponseEntity<Carrinho> getCarrinhoById(@PathVariable Long id) {
        Carrinho carrinho = carrinhoService.getCarrinhoById(id);
        return carrinho != null ? ResponseEntity.ok(carrinho) : ResponseEntity.notFound().build();
    }

    @PostMapping("/{carrinhoId}/itens")
    public ResponseEntity<Carrinho> addItemToCarrinho(@PathVariable Long carrinhoId, @Valid @RequestBody ItemCarrinho item) {
        Carrinho updatedCarrinho = carrinhoService.addItemToCarrinho(carrinhoId, item);
        return ResponseEntity.ok(updatedCarrinho);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarrinho(@PathVariable Long id) {
        carrinhoService.deleteCarrinho(id);
        return ResponseEntity.noContent().build();
    }

}

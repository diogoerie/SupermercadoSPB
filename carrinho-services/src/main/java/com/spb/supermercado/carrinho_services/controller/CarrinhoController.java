package com.spb.supermercado.carrinho_services.controller;

import com.spb.supermercado.carrinho_services.model.Carrinho;
import com.spb.supermercado.carrinho_services.model.ItemCarrinho;
import com.spb.supermercado.carrinho_services.service.CarrinhoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carrinhos")
@CrossOrigin(origins = "http://localhost:3000")
public class CarrinhoController {

    private final CarrinhoService carrinhoService;

    @Autowired
    public CarrinhoController(CarrinhoService carrinhoService) {
        this.carrinhoService = carrinhoService;
    }

    @PostMapping("/usuario/{usuarioId}/novo")
    public ResponseEntity<Carrinho> criarCarrinho(@PathVariable Long usuarioId) {
        Carrinho novoCarrinho = carrinhoService.criarCarrinho(usuarioId);
        return ResponseEntity.ok(novoCarrinho);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carrinho> getCarrinhoById(@PathVariable Long id) {
        Carrinho carrinho = carrinhoService.getCarrinhoById(id);
        return carrinho != null ? ResponseEntity.ok(carrinho) : ResponseEntity.notFound().build();
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<Carrinho> getCarrinhoByUsuarioId(@PathVariable Long usuarioId) {
        Carrinho carrinho = carrinhoService.getCarrinhoByUsuarioId(usuarioId);
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

    @PutMapping("/{carrinhoId}/finalizar")
    public ResponseEntity<Carrinho> finalizarCarrinho(@PathVariable Long carrinhoId) {
        Carrinho carrinho = carrinhoService.finalizarPedido(carrinhoId);
        return ResponseEntity.ok(carrinho);
    }

    @GetMapping
    public ResponseEntity<List<Carrinho>> getTodosCarrinhos() {
        List<Carrinho> carrinhos = carrinhoService.getTodosCarrinhos();
        return ResponseEntity.ok(carrinhos);
    }



}
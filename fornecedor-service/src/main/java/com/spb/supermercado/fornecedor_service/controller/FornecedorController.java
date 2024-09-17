package com.spb.supermercado.fornecedor_service.controller;

import com.spb.supermercado.fornecedor_service.feign.ProdutoClient;
import com.spb.supermercado.fornecedor_service.model.Fornecedor;
import com.spb.supermercado.fornecedor_service.service.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fornecedores")
public class FornecedorController {

    private final FornecedorService fornecedorService;

    private final ProdutoClient produtoClient;

    @Autowired
    public FornecedorController(FornecedorService fornecedorService, ProdutoClient produtoClient) {
        this.fornecedorService = fornecedorService;
        this.produtoClient = produtoClient;
    }

    @GetMapping
    public ResponseEntity<List<Fornecedor>> getTodosFornecedores() {
        List<Fornecedor> fornecedores = fornecedorService.getTodosFornecedores();
        return ResponseEntity.ok(fornecedores);
    }

    @PostMapping
    public ResponseEntity<Fornecedor> criarFornecedor(@RequestBody Fornecedor fornecedor) {
        Fornecedor novoFornecedor = fornecedorService.criarFornecedor(fornecedor);
        return ResponseEntity.ok(novoFornecedor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFornecedor(@PathVariable Long id) {
        fornecedorService.deletarFornecedor(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/solicitar/{idProduto}/{quantidade}")
    public ResponseEntity<Void> solicitarProduto(@PathVariable Long idProduto, @PathVariable Integer quantidade) {
        // Atualiza o estoque no Produto-service usando Feign
        produtoClient.atualizarEstoque(idProduto, quantidade);
        return ResponseEntity.ok().build();
    }

}

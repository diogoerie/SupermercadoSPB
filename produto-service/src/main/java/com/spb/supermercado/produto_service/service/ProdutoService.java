package com.spb.supermercado.produto_service.service;

import com.spb.supermercado.produto_service.model.Produto;
import com.spb.supermercado.produto_service.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoService (ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> getAllProdutos() {
        return produtoRepository.findAll();
    }

    public Produto getProdutoById(Long id) {
        return produtoRepository.findById(id).orElse(null);
    }

    public Produto saveProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    public void deleteProduto(Long id) {
        produtoRepository.deleteById(id);
    }

}

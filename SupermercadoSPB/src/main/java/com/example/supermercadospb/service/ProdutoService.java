package com.example.supermercadospb.service;

import com.example.supermercadospb.entity.Produto;
import com.example.supermercadospb.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> getAllProdutos() {
        return produtoRepository.findAll();
    }

    public Optional<Produto> getProdutoById(int id) {
        return produtoRepository.findById(id);
    }

    public Produto addProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Produto updateProduto(int id, Produto produto) {
        return produtoRepository.findById(id)
                .map(existingProduto -> {
                    produto.setId(existingProduto.getId());
                    return produtoRepository.save(produto);
                })
                .orElseGet(() -> {
                    produto.setId(id);
                    return produtoRepository.save(produto);
                });
    }

    public boolean deleteProduto(int id) {
        if (produtoRepository.existsById(id)) {
            produtoRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
package com.example.supermercadospb.config;

import com.example.supermercadospb.entity.Produto;
import com.example.supermercadospb.repository.ProdutoRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataInitializer {

    @Autowired
    private ProdutoRepository produtoRepository;

    @PostConstruct
    public void init() {
        if (produtoRepository.count() > 0) {
            return;
        }

        Produto produto1 = new Produto();
        produto1.setNome("Arroz");
        produto1.setCodigo("AR123");
        produto1.setPreco(20.0);

        Produto produto2 = new Produto();
        produto2.setNome("Feijão");
        produto2.setCodigo("FE456");
        produto2.setPreco(15.0);

        Produto produto3 = new Produto();
        produto3.setNome("Macarrão");
        produto3.setCodigo("MA789");
        produto3.setPreco(10.0);

        produtoRepository.saveAll(Arrays.asList(produto1, produto2, produto3));
        System.out.println("Produtos adicionados:");
        System.out.println(produto1);
        System.out.println(produto2);
        System.out.println(produto3);
    }
}

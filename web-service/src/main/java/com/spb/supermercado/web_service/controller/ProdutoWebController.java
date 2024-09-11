package com.spb.supermercado.web_service.controller;

import com.spb.supermercado.web_service.model.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/produtos")
public class ProdutoWebController {

    @Autowired
    private RestTemplate restTemplate;

    private final String produtoServiceUrl = "http://produto-service/produtos";

    @GetMapping
    public String listarProdutos(Model model) {
        List<Produto> produtos = Arrays.asList(restTemplate.getForObject(produtoServiceUrl, Produto[].class));
        model.addAttribute("produtos", produtos);
        return "produtos";
    }

    @PostMapping("/adicionar/{produtoId}")
    public String adicionarAoCarrinho(@PathVariable Long produtoId, @RequestParam int quantidade) {
        // Lógica para adicionar ao carrinho via Carrinho Service
        return "redirect:/produtos";
    }

}

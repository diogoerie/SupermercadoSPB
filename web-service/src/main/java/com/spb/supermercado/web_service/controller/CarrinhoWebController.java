package com.spb.supermercado.web_service.controller;

import com.spb.supermercado.web_service.model.Carrinho;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/carrinho")
public class CarrinhoWebController {

    @Autowired
    private RestTemplate restTemplate;

    private final String carrinhoServiceUrl = "http://carrinho-service/carrinho";

    @GetMapping
    public String verCarrinho(Model model) {
        Carrinho carrinho = restTemplate.getForObject(carrinhoServiceUrl + "/{clienteId}", Carrinho.class, 1L);
        model.addAttribute("carrinho", carrinho);
        return "carrinho";
    }

    @PostMapping("/finalizar")
    public String finalizarCompra() {
        restTemplate.postForObject(carrinhoServiceUrl + "/finalizar/{clienteId}", null, Void.class, 1L);
        return "redirect:/carrinho?finalizado";
    }

}
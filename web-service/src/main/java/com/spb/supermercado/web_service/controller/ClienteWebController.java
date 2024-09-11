package com.spb.supermercado.web_service.controller;

import com.spb.supermercado.web_service.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/clientes")
public class ClienteWebController {

    @Autowired
    private RestTemplate restTemplate;

    private final String clienteServiceUrl = "http://cliente-service/clientes";

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@ModelAttribute Cliente cliente, Model model) {
        Cliente clienteExistente = restTemplate.getForObject(clienteServiceUrl + "/email/{email}",
                Cliente.class, cliente.getEmail());

        if (clienteExistente != null && clienteExistente.getSenha().equals(cliente.getSenha())) {
            return "redirect:/produtos";
        } else {
            model.addAttribute("error", "Credenciais inválidas!");
            return "login";
        }
    }

    @PostMapping("/register")
    public String processRegister(@ModelAttribute Cliente cliente) {
        restTemplate.postForObject(clienteServiceUrl, cliente, Cliente.class);
        return "redirect:/login";
    }

}

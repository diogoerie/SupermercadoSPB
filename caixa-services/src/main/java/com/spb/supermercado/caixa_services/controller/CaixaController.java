package com.spb.supermercado.caixa_services.controller;

import com.spb.supermercado.caixa_services.DTO.ValorDTO;
import com.spb.supermercado.caixa_services.model.Transacao;
import com.spb.supermercado.caixa_services.service.CaixaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transacoes")
public class CaixaController {

    private final CaixaService caixaService;

    @Autowired
    public CaixaController(CaixaService caixaService) {this.caixaService = caixaService;}

    @GetMapping
    public ResponseEntity<List<Transacao>> getTodasTransacoes() {
        List<Transacao> transacoes = caixaService.getTodasTransacoes();
        return ResponseEntity.ok(transacoes);
    }

    @PostMapping("/entrada")
    public ResponseEntity<Transacao> registrarEntrada(@RequestBody ValorDTO valorDTO) {
        Transacao transacao = caixaService.registrarEntrada(valorDTO.getValor());
        return ResponseEntity.ok(transacao);
    }

    @PostMapping("/saida")
    public ResponseEntity<Transacao> registrarSaida(@RequestBody ValorDTO valorDTO) {
        Transacao transacao = caixaService.registrarSaida(valorDTO.getValor());
        return ResponseEntity.ok(transacao);
    }

}

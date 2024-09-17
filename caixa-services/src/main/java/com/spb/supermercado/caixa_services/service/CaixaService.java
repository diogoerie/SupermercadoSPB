package com.spb.supermercado.caixa_services.service;

import com.spb.supermercado.caixa_services.model.Transacao;
import com.spb.supermercado.caixa_services.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CaixaService {

    private final TransacaoRepository transacaoRepository;

    @Autowired
    public CaixaService(TransacaoRepository transacaoRepository) {this.transacaoRepository = transacaoRepository;}

    public Transacao registrarEntrada(Double valor) {
        Transacao transacao = new Transacao(valor, LocalDateTime.now(), "ENTRADA");
        return transacaoRepository.save(transacao);
    }

    public Transacao registrarSaida(Double valor) {
        Transacao transacao = new Transacao(valor, LocalDateTime.now(), "SAÍDA");
        return transacaoRepository.save(transacao);
    }

    public List<Transacao> getTodasTransacoes() {
        return transacaoRepository.findAll();
    }
}

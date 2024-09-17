package com.spb.supermercado.caixa_services.repository;

import com.spb.supermercado.caixa_services.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
}
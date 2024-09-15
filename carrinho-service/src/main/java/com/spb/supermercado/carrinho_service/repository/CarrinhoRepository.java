package com.spb.supermercado.carrinho_service.repository;

import com.spb.supermercado.carrinho_service.model.Carrinho;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {
}
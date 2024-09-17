package com.spb.supermercado.carrinho_services.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.spb.supermercado.carrinho_services.model.Carrinho;

import java.util.List;

public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {
    List<Carrinho> findByUsuarioId(Long usuarioId);
}
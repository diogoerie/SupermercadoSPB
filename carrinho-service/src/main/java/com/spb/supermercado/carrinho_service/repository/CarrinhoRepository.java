package com.spb.supermercado.carrinho_service.repository;

import com.spb.supermercado.carrinho_service.model.Carrinho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {
    Optional<Carrinho> findByClienteId(Long clienteId);
}
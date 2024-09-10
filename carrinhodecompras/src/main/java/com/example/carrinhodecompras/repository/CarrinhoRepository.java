package com.example.carrinhodecompras.repository;

import com.example.carrinhodecompras.entity.Carrinho;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {
}

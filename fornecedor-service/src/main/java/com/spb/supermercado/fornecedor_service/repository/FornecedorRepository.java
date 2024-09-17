package com.spb.supermercado.fornecedor_service.repository;

import com.spb.supermercado.fornecedor_service.model.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
}

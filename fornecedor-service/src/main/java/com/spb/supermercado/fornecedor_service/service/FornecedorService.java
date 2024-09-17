package com.spb.supermercado.fornecedor_service.service;

import com.spb.supermercado.fornecedor_service.model.Fornecedor;
import com.spb.supermercado.fornecedor_service.repository.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FornecedorService {

    private final FornecedorRepository fornecedorRepository;

    @Autowired
    public FornecedorService(FornecedorRepository fornecedorRepository) {this.fornecedorRepository = fornecedorRepository;}

    public List<Fornecedor> getTodosFornecedores() {
        return fornecedorRepository.findAll();
    }

    public Fornecedor getFornecedorById(Long id) {
        return fornecedorRepository.findById(id).orElse(null);
    }

    public Fornecedor criarFornecedor(Fornecedor fornecedor) {
        return fornecedorRepository.save(fornecedor);
    }

    public void deletarFornecedor(Long id) {
        fornecedorRepository.deleteById(id);
    }
}

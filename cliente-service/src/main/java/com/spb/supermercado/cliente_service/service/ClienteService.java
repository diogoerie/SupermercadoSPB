package com.spb.supermercado.cliente_service.service;

import com.spb.supermercado.cliente_service.model.Cliente;
import com.spb.supermercado.cliente_service.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    public Cliente salvarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    public void deletarCliente(Long id) {
        clienteRepository.deleteById(id);
    }

    public Cliente buscarPorEmail(String email) {
        return clienteRepository.findByEmail(email);
    }
}
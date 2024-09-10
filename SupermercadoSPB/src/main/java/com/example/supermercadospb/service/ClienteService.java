package com.example.supermercadospb.service;

import com.example.supermercadospb.entity.Cliente;
import com.example.supermercadospb.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> getClienteById(int id) {
        return clienteRepository.findById(id);
    }

    public Cliente addCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente updateCliente(int id, Cliente cliente) {
        return clienteRepository.findById(id)
                .map(existingCliente -> {
                    cliente.setId(existingCliente.getId());
                    return clienteRepository.save(cliente);
                })
                .orElseGet(() -> {
                    cliente.setId(id);
                    return clienteRepository.save(cliente);
                });
    }

    public boolean deleteCliente(int id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}

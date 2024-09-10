package com.example.supermercadospb.controller;


import com.example.supermercadospb.entity.Cliente;
import com.example.supermercadospb.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Operation(summary = "Listar todos os clientes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Clientes listados com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno")
    })
    @GetMapping("/clientes") // mostrar todos clientes
    public ResponseEntity<List<Cliente>> getAllClientes() {
        List<Cliente> clientes = clienteService.getAllClientes();
        return ResponseEntity.ok(clientes);
    }

    @Operation(summary = "Obter um cliente específico pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    @GetMapping("/clientes/{id}") // mostrar cliente específico
    public ResponseEntity<Cliente> getClienteById(@PathVariable int id) {
        return clienteService.getClienteById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @Operation(summary = "Cadastrar um novo cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente adicionado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    @PostMapping("/clientes") // cadastrar novo cliente
    public ResponseEntity<String> addCliente(@RequestBody Cliente cliente) {
        Cliente savedCliente = clienteService.addCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body("Cliente adicionado com sucesso: " + savedCliente.getNome());
    }

    @Operation(summary = "Editar um cliente existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    @PutMapping("/clientes/{id}") // editar cliente existente
    public ResponseEntity<String> updateCliente(@PathVariable int id, @RequestBody Cliente cliente) {
        Cliente updatedCliente = clienteService.updateCliente(id, cliente);
        if (updatedCliente.getId() == id) {
            return ResponseEntity.ok("Cliente atualizado com sucesso: " + updatedCliente.getNome());
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body("Novo cliente criado com sucesso: " + updatedCliente.getNome());
        }
    }

    @Operation(summary = "Deletar um cliente existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cliente deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    @DeleteMapping("/clientes/{id}") // deletar cliente existente
    public ResponseEntity<String> deleteCliente(@PathVariable int id) {
        if (clienteService.deleteCliente(id)) {
            return ResponseEntity.noContent().header("Message", "Cliente deletado com sucesso.").build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado.");
        }
    }
}

package com.spb.supermercado.users_services.controller;

import com.spb.supermercado.users_services.model.User;
import com.spb.supermercado.users_services.service.UserService;
import com.spb.supermercado.users_services.rabbitmq.RabbitMQProducer;
import com.spb.supermercado.users_services.rabbitmq.RabbitMQConfig;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    private RabbitMQProducer rabbitMQProducer;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginRequest request) {
        User user = userService.findByNome(request.getNome());

        if (user != null && user.getSenha().equals(request.getSenha())) {
            rabbitMQProducer.sendMessage("Usuario ID " + user.getId() + " acessou o sistema.");

            Map<String, Object> response = new HashMap<>();
            response.put("autenticado", true);
            response.put("id", user.getId());
            response.put("nome", user.getNome());

            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Collections.singletonMap("autenticado", false));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @Valid @RequestBody User userDetails) {
        try {

            User user = userService.getUserById(id);
            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
            }

            user.setNome(userDetails.getNome());
            user.setSenha(userDetails.getSenha());
            userService.saveUser(user);

            rabbitMQProducer.sendMessage("Usuário ID " + user.getId() + " alterou seus dados.");

            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao atualizar os dados do usuário");
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> saveUser(@Valid @RequestBody User user) {
        try {
            User savedUser = userService.saveUser(user);

            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(savedUser.getId())
                    .toUri();

            rabbitMQProducer.sendMessage("Usuário ID " + savedUser.getId() + " foi criado.");

            return ResponseEntity.created(location).body(savedUser);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao criar o usuário: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}

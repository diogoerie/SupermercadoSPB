package com.spb.supermercado.web_service.service;

import com.spb.supermercado.web_service.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

    private final RestTemplate restTemplate;
    private final String userServiceUrl;

    public UserService(RestTemplate restTemplate, @Value("${user.service.url}") String userServiceUrl) {
        this.restTemplate = restTemplate;
        this.userServiceUrl = userServiceUrl;
    }

    public void registrarUsuario(User user) {
        String url = userServiceUrl + "/users";
        restTemplate.postForObject(url, user, User.class);
    }

    public User autenticarUsuario(String nome, String senha) {
        String url = userServiceUrl + "/users/authenticate";
        AuthRequest authRequest = new AuthRequest(nome, senha);

        try {
            ResponseEntity<User> response = restTemplate.postForEntity(url, authRequest, User.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                return response.getBody();
            } else {
                throw new RuntimeException("Falha na autenticação: " + response.getBody());
            }
        } catch (HttpClientErrorException e) {

            if (e.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                throw new RuntimeException("Nome de usuário ou senha incorretos.");
            } else {
                throw new RuntimeException("Falha na autenticação: " + e.getStatusCode() + " : " + e.getResponseBodyAsString());
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao conectar ao serviço de usuários: " + e.getMessage());
        }
    }

    private static class AuthRequest {
        private String nome;
        private String senha;

        public AuthRequest(String nome, String senha) {
            this.nome = nome;
            this.senha = senha;
        }

        public String getNome() {
            return nome;
        }
        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getSenha() {
            return senha;
        }
        public void setSenha(String senha) {
            this.senha = senha;
        }
    }

}
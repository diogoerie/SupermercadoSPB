package com.spb.supermercado.produto_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "PRODUTO")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do produto é obrigatório")
    private String nome;

    @NotNull(message = "A quantidade em estoque é obrigatória")
    @Min(value = 0, message = "A quantidade em estoque deve ser maior ou igual a 0")
    private Integer quantidadeEstoque;

    @NotNull(message = "O preço é obrigatório")
    @Min(value = 0, message = "O preço deve ser positivo")
    private double preco;

    public Produto() {
    }

    public Produto(String nome, Double preco, Integer quantidadeEstoque) {
        this.nome = nome;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "O nome do produto é obrigatório") String getNome() {
        return nome;
    }

    public void setNome(@NotBlank(message = "O nome do produto é obrigatório") String nome) {
        this.nome = nome;
    }

    public @NotNull(message = "A quantidade em estoque é obrigatória") @Min(value = 0, message = "A quantidade em estoque deve ser maior ou igual a 0") Integer getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(@NotNull(message = "A quantidade em estoque é obrigatória") @Min(value = 0, message = "A quantidade em estoque deve ser maior ou igual a 0") Integer quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    @NotNull(message = "O preço é obrigatório")
    @Min(value = 0, message = "O preço deve ser positivo")
    public double getPreco() {
        return preco;
    }

    public void setPreco(@NotNull(message = "O preço é obrigatório") @Min(value = 0, message = "O preço deve ser positivo") double preco) {
        this.preco = preco;
    }
}
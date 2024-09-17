package com.spb.supermercado.carrinho_services.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Carrinho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double valorTotal = 0.0;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "carrinho_id")
    private List<ItemCarrinho> itens = new ArrayList<>();

    private String status;

    private Long usuarioId;

    public Carrinho() {
        this.valorTotal = 0.0;
    }

    public Carrinho(Double valorTotal, List<ItemCarrinho> itens) {
        this.valorTotal = valorTotal;
        this.itens = itens;
    }

    public Carrinho(Long usuarioId) {
        this.usuarioId = usuarioId;
        this.status = "EM PROCESSO";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<ItemCarrinho> getItens() {
        return itens;
    }

    public void setItens(List<ItemCarrinho> itens) {
        this.itens = itens;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
}

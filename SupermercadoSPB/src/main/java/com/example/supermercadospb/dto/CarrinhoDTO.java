package com.example.supermercadospb.dto;

import java.util.List;

public class CarrinhoDTO {

    private String id;
    private List<CarrinhoItemDTO> itens;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<CarrinhoItemDTO> getItens() {
        return itens;
    }

    public void setItens(List<CarrinhoItemDTO> itens) {
        this.itens = itens;
    }
}

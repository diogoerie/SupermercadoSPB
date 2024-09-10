package com.example.carrinhodecompras.dto;

import java.util.List;

public class CarrinhoDTO {

    private Long id;
    private List<CarrinhoItemDTO> itens;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CarrinhoItemDTO> getItens() {
        return itens;
    }

    public void setItens(List<CarrinhoItemDTO> itens) {
        this.itens = itens;
    }
}

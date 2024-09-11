package com.spb.supermercado.carrinho_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Carrinho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long clienteId;  // Associação com o cliente

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ItemCarrinho> itens;

}

package com.example.carrinhodecompras.service;

import com.example.carrinhodecompras.dto.CarrinhoDTO;
import com.example.carrinhodecompras.dto.CarrinhoItemDTO;
import com.example.carrinhodecompras.entity.Carrinho;
import com.example.carrinhodecompras.entity.CarrinhoItem;
import com.example.carrinhodecompras.repository.CarrinhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarrinhoService {

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    public CarrinhoDTO addItemToCarrinho(Long carrinhoId, CarrinhoItemDTO itemDTO) {
        Carrinho carrinho = carrinhoRepository.findById(carrinhoId)
                .orElse(new Carrinho());

        CarrinhoItem item = convertToEntity(itemDTO);
        carrinho.addItem(item);

        Carrinho updatedCarrinho = carrinhoRepository.save(carrinho);
        return convertToDTO(updatedCarrinho);
    }

    public CarrinhoDTO getCarrinho(Long carrinhoId) {
        Carrinho carrinho = carrinhoRepository.findById(carrinhoId)
                .orElse(new Carrinho());
        return convertToDTO(carrinho);
    }

    private CarrinhoItem convertToEntity(CarrinhoItemDTO dto) {
        CarrinhoItem item = new CarrinhoItem();
        item.setId(dto.getId());
        item.setProdutoNome(dto.getProdutoNome());
        item.setQuantidade(dto.getQuantidade());
        item.setPreco(dto.getPreco());
        return item;
    }

    private CarrinhoItemDTO convertToDTO(CarrinhoItem entity) {
        CarrinhoItemDTO dto = new CarrinhoItemDTO();
        dto.setId(entity.getId());
        dto.setProdutoNome(entity.getProdutoNome());
        dto.setQuantidade(entity.getQuantidade());
        dto.setPreco(entity.getPreco());
        return dto;
    }

    private CarrinhoDTO convertToDTO(Carrinho carrinho) {
        CarrinhoDTO dto = new CarrinhoDTO();
        dto.setId(carrinho.getId());
        dto.setItens(carrinho.getItens().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList()));
        return dto;
    }
}

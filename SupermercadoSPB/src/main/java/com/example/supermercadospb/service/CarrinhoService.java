package com.example.supermercadospb.service;

import com.example.supermercadospb.dto.CarrinhoDTO;
import com.example.supermercadospb.dto.CarrinhoItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CarrinhoService {

    @Autowired
    private RestTemplate restTemplate;

    private final String CARRINHO_SERVICE_URL = "http://carrinhodecompras/carrinhos";

    public String adicionarItemAoCarrinho(String carrinhoId, CarrinhoItemDTO item) {
        ResponseEntity<CarrinhoDTO> response = restTemplate.postForEntity(CARRINHO_SERVICE_URL + "/" + carrinhoId + "/itens", item, CarrinhoDTO.class);
        return response.getBody() != null ? response.getBody().toString() : "Erro ao adicionar item";
    }

    public CarrinhoDTO getCarrinhoInfo(Long carrinhoId) {
        return restTemplate.getForObject(CARRINHO_SERVICE_URL + "/" + carrinhoId, CarrinhoDTO.class);
    }

}

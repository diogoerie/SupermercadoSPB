package com.spb.supermercado.fornecedor_service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "produto-service")
public interface ProdutoClient {

    @PutMapping("/produtos/{id}/estoque/{quantidade}")
    void atualizarEstoque(@PathVariable Long id, @PathVariable Integer quantidade);

}
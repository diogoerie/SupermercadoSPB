package com.spb.supermercado.carrinho_service.service;

import com.spb.supermercado.carrinho_service.model.Carrinho;
import com.spb.supermercado.carrinho_service.model.Cliente;
import com.spb.supermercado.carrinho_service.model.ItemCarrinho;
import com.spb.supermercado.carrinho_service.model.Produto;
import com.spb.supermercado.carrinho_service.repository.CarrinhoRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList; // Adicione esta linha
import java.util.List;

@Service
public class CarrinhoService {

    private final CarrinhoRepository carrinhoRepository;
    private final RestTemplate restTemplate;
    private final String clienteServiceUrl;
    private final String produtoServiceUrl;

    public CarrinhoService(
            CarrinhoRepository carrinhoRepository,
            RestTemplate restTemplate,
            @Value("${cliente.service.url}") String clienteServiceUrl,
            @Value("${produto.service.url}") String produtoServiceUrl) {
        this.carrinhoRepository = carrinhoRepository;
        this.restTemplate = restTemplate;
        this.clienteServiceUrl = clienteServiceUrl;
        this.produtoServiceUrl = produtoServiceUrl;
    }

    // Método adicionado para encontrar o carrinho por ID
    public Carrinho findById(Long carrinhoId) {
        return carrinhoRepository.findById(carrinhoId)
                .orElseThrow(() -> new RuntimeException("Carrinho não encontrado com o ID: " + carrinhoId));
    }

    public Carrinho criarCarrinho(Long clienteId) {
        Carrinho carrinho = new Carrinho();
        carrinho.setClienteId(clienteId);
        carrinho.setTotal(0.0);
        carrinho.setItens(new ArrayList<>());
        return carrinhoRepository.save(carrinho);
    }

    public Carrinho adicionarProduto(Long carrinhoId, ItemCarrinho item) {
        Carrinho carrinho = findById(carrinhoId);

        // Obter detalhes do produto do serviço de produtos
        String produtoUrl = produtoServiceUrl + "/produtos/" + item.getProdutoId();
        Produto produto = restTemplate.getForObject(produtoUrl, Produto.class);

        if (produto != null) {
            item.setPrecoUnitario(produto.getPreco());
            item.setPrecoTotal(produto.getPreco() * item.getQuantidade());

            List<ItemCarrinho> itens = carrinho.getItens();
            itens.add(item);
            carrinho.setTotal(carrinho.getTotal() + item.getPrecoTotal());

            return carrinhoRepository.save(carrinho);
        } else {
            throw new RuntimeException("Produto não encontrado");
        }
    }

    public Carrinho removerProduto(Long carrinhoId, Long produtoId) {
        Carrinho carrinho = findById(carrinhoId);

        List<ItemCarrinho> itens = carrinho.getItens();
        ItemCarrinho itemRemover = itens.stream()
                .filter(i -> i.getProdutoId().equals(produtoId))
                .findFirst().orElse(null);

        if (itemRemover != null) {
            itens.remove(itemRemover);
            carrinho.setTotal(carrinho.getTotal() - itemRemover.getPrecoTotal());
            return carrinhoRepository.save(carrinho);
        } else {
            throw new RuntimeException("Produto não encontrado no carrinho");
        }
    }

    public Carrinho finalizarCompra(Long carrinhoId) {
        Carrinho carrinho = findById(carrinhoId);

        // Obter detalhes do cliente
        String clienteUrl = clienteServiceUrl + "/users/" + carrinho.getClienteId();
        Cliente cliente = restTemplate.getForObject(clienteUrl, Cliente.class);

        if (cliente == null) {
            throw new RuntimeException("Cliente não encontrado");
        }

        // Aqui você pode adicionar lógica de finalização de compra, como integração com pagamento

        // Limpar o carrinho após a compra
        carrinho.setItens(new ArrayList<>());
        carrinho.setTotal(0.0);
        return carrinhoRepository.save(carrinho);
    }

}

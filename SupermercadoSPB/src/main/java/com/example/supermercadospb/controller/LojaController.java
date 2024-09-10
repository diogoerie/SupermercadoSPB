package com.example.supermercadospb.controller;

import com.example.supermercadospb.dto.CarrinhoItemDTO;
import com.example.supermercadospb.entity.Produto;
import com.example.supermercadospb.service.CarrinhoService;
import com.example.supermercadospb.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class LojaController {

    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private CarrinhoService carrinhoService;


    @PostMapping("/carrinhos/{carrinhoId}/adicionar")
    public ResponseEntity<String> addProdutoToCarrinho(@PathVariable Long carrinhoId, @RequestBody Produto produto) {
        CarrinhoItemDTO item = new CarrinhoItemDTO();
        item.setProdutoNome(produto.getNome());
        item.setQuantidade(1);
        item.setPreco(produto.getPreco());

        String result = carrinhoService.adicionarItemAoCarrinho(carrinhoId.toString(), item);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/compras")
    public String mostrarPaginaDeCompras(Model model) {
        List<Produto> produtos = produtoService.getAllProdutos();
        if (produtos != null && !produtos.isEmpty()) {
            System.out.println("Produtos carregados: " + produtos);
        } else {
            System.out.println("Nenhum produto encontrado!");
        }
        model.addAttribute("produtos", produtos);
        return "pagina-de-compras"; // Nome do arquivo sem a extensão
    }




    @GetMapping("/produtos")
    @ResponseBody
    public List<Produto> getAllProdutos() {
        return produtoService.getAllProdutos();
    }

    @Operation(summary = "Obter um produto específico pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto encontrado"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    @GetMapping("/selecionarproduto/{id}")// mostrar produto específico
    public ResponseEntity<Produto> getProdutoById(@PathVariable int id) {
        return produtoService.getProdutoById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @Operation(summary = "Cadastrar um novo produto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Produto adicionado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    @PostMapping("/adicionarproduto")// cadastrar novo produto
    public ResponseEntity<String> addProduto(@RequestBody Produto produto) {
        Produto savedProduto = produtoService.addProduto(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Produto adicionado com sucesso: " + savedProduto.getNome());
    }

    @Operation(summary = "Editar um produto existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    @PutMapping("/updateproduto/{id}")// editar produto existente
    public ResponseEntity<String> updateProduto(@PathVariable int id, @RequestBody Produto produto) {
        Produto updatedProduto = produtoService.updateProduto(id, produto);
        if (updatedProduto.getId() == id) {
            return ResponseEntity.ok("Produto atualizado com sucesso: " + updatedProduto.getNome());
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body("Novo produto criado com sucesso: " + updatedProduto.getNome());
        }
    }

    @Operation(summary = "Deletar um produto existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Produto deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    @DeleteMapping("/deleteproduto/{id}")// deletar produto existente
    public ResponseEntity<String> deleteProduto(@PathVariable int id) {
        if (produtoService.deleteProduto(id)) {
            return ResponseEntity.noContent().header("Message", "Produto deletado com sucesso.").build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado.");
        }
    }
}
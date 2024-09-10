package com.example.carrinhodecompras.controller;



import com.example.carrinhodecompras.dto.CarrinhoDTO;
import com.example.carrinhodecompras.dto.CarrinhoItemDTO;
import com.example.carrinhodecompras.entity.Carrinho;
import com.example.carrinhodecompras.entity.CarrinhoItem;
import com.example.carrinhodecompras.service.CarrinhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carrinhos")
public class CarrinhoController {

    @Autowired
    private CarrinhoService carrinhoService;

    @PostMapping("/{carrinhoId}/itens")
    public ResponseEntity<CarrinhoDTO> addItemToCarrinho(@PathVariable Long carrinhoId, @RequestBody CarrinhoItemDTO itemDTO) {
        CarrinhoDTO updatedCarrinho = carrinhoService.addItemToCarrinho(carrinhoId, itemDTO);
        return ResponseEntity.ok(updatedCarrinho);
    }

    @GetMapping("/{carrinhoId}")
    public ResponseEntity<CarrinhoDTO> getCarrinho(@PathVariable Long carrinhoId) {
        CarrinhoDTO carrinhoDTO = carrinhoService.getCarrinho(carrinhoId);
        return ResponseEntity.ok(carrinhoDTO);
    }
}

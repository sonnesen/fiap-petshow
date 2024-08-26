package com.petshow.petshow.controllers;

import com.petshow.petshow.dto.CartResponse;
import com.petshow.petshow.mapper.CartMapper;
import com.petshow.petshow.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("petshow/api/v1/cart")
@Tag(name = "petshow/api/v1/cart", description = "Controller responsável por gerenciar o carrinho de compras do usuário.")
public class CartController {

    @Autowired
    private CartService service;
    @Autowired
    private CartMapper mapper;

    @PostMapping("/{username}/create")
    @Operation(summary = "Criar um novo carrinho", description = "Endpoint responsável por criar um carrinho.", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "carrinho criado com sucesso.")
    })
    public ResponseEntity<CartResponse> createCart(@PathVariable String username){
        CartResponse cartResponse = mapper.toCartResponse(service.createCart(username));

        return ResponseEntity.ok(cartResponse);
    }

    @PutMapping("/{username}/add")
    @Operation(summary = "Adicionar um produto ao carrinho", description = "Endpoint responsável por Adicionar um produto a um carrinho existente.", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto adicionado ao carrinho com sucesso.")
    })
    public ResponseEntity<CartResponse> addProductToCart(@PathVariable String username,
                                           @RequestParam Long productId,
                                           @RequestParam int quantity) {
        CartResponse cartResponse = mapper.toCartResponse(service.addItemToCart(username, productId, quantity));

        return ResponseEntity.ok().body(cartResponse);
    }

    @PutMapping("/{username}/remove")
    @Operation(summary = "Remover um produto do carrinho", description = "Endpoint responsável por remover um produto de um carrinho existente.", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto removido do carrinho com sucesso.")
    })
    public ResponseEntity<CartResponse> removeProductFromCart(@PathVariable String username,
                                                              @RequestParam Long productId) {
        CartResponse cartResponse = mapper.toCartResponse(service.removeItemFromCart(username, productId));

        return ResponseEntity.ok().body(cartResponse);
    }
}

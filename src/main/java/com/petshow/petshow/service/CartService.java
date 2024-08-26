package com.petshow.petshow.service;

import com.petshow.petshow.entity.CartEntity;
import com.petshow.petshow.exception.ProductNotFoundException;
import com.petshow.petshow.mapper.CartMapper;
import com.petshow.petshow.repository.CartItemRepository;
import com.petshow.petshow.repository.CartRepository;
import com.petshow.petshow.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.math.BigDecimal.ZERO;

@Service
@RequiredArgsConstructor
public class CartService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartMapper mapper;

    public CartEntity getCartByUsername(String username) {
        return cartRepository.findByUsername(username);
    }

    @Transactional
    public CartEntity createCart(String username) {
        var cartEntity = new CartEntity();
        cartEntity.setUsername(username);
        cartEntity.setTotalPrice(ZERO);
        cartEntity.setItems(List.of());
        return cartRepository.save(cartEntity);
    }

    @Transactional
    public CartEntity addItemToCart(String username, Long productId, int quantity) {
        var cartEntity = getCartByUsername(username);
        var productEntity = productRepository.findById(productId).orElseThrow(() ->
                new ProductNotFoundException(String.format("Product '%s' not found.", productId)));
        var cartItemEntity = mapper.toCartItemEntity(productEntity);
        cartItemEntity.setQuantity(quantity);
        cartItemEntity.setCart(cartEntity);

        cartEntity.getItems().add(cartItemEntity);
        cartEntity.calculateTotalPrice();
        cartItemRepository.save(cartItemEntity);
        return cartRepository.save(cartEntity);
    }

    public CartEntity removeItemFromCart(String username, Long productId) {
        var cartEntity = getCartByUsername(username);
        var cartItemEntity = cartItemRepository.getByProductId(productId);

        if (cartEntity.getItems().remove(cartItemEntity)) {
            cartItemRepository.delete(cartItemEntity);
            cartEntity.calculateTotalPrice();
            cartRepository.save(cartEntity);
        }

        return cartEntity;
    }

}
package com.petshow.petshow.mapper;

import com.petshow.petshow.dto.CartItem;
import com.petshow.petshow.dto.CartResponse;
import com.petshow.petshow.entity.CartEntity;
import com.petshow.petshow.entity.CartItemEntity;
import com.petshow.petshow.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper {

    @Mapping(source = "name", target = "productName")
    @Mapping(source = "price", target = "productPrice")
    @Mapping(source = "idProduct", target = "productId")
    CartItemEntity toCartItemEntity(ProductEntity productEntity);

    @Mapping(source = "id", target = "cartId")
    @Mapping(source = "username", target = "username")
    @Mapping(source = "totalPrice", target = "totalPrice")
    CartResponse toCartResponse(CartEntity cartEntity);

    @Mapping(source = "productId", target = "productId")
    @Mapping(source = "productName", target = "productName")
    @Mapping(source = "productPrice", target = "price")
    @Mapping(source = "quantity", target = "quantity")
    CartItem toCartItem(CartItemEntity cartItemEntity);
}

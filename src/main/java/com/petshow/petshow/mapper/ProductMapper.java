package com.petshow.petshow.mapper;

import com.petshow.petshow.dto.ProductRequest;
import com.petshow.petshow.dto.ProductResponse;
import com.petshow.petshow.entity.ProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductEntity toProductEntity(ProductRequest productRequest);

    ProductResponse toProductResponse(ProductEntity productEntity);
}

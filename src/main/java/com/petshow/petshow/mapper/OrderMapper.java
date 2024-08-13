package com.petshow.petshow.mapper;

import com.petshow.petshow.dto.OrderRequest;
import com.petshow.petshow.dto.OrderResponse;
import com.petshow.petshow.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(source = "name", target = "name")
    OrderResponse toOrderResponse(OrderEntity orderEntity);

    OrderEntity toOrderEntity(OrderRequest orderRequest);
}

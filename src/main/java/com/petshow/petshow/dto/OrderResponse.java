package com.petshow.petshow.dto;

import com.petshow.petshow.entity.ProductEntity;
import com.petshow.petshow.entity.StatusOrderEntity;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Builder
public record OrderResponse(

        UUID idOrder,
        Date orderDate,
        String paymentMethod,
        BigDecimal totalValue,
        Date deliveryDate

) {

}
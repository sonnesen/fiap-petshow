package com.petshow.petshow.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.Date;

@Builder
public record OrderResponse(
        Long idOrder,
        Date orderDate,
        String paymentMethod,
        BigDecimal totalValue,
        Date deliveryDate
) {

}
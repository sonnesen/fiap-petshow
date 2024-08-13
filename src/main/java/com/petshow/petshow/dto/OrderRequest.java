package com.petshow.petshow.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.Date;

@Builder
public record OrderRequest(
        Date orderDate,
        String paymentMethod,
        BigDecimal valorTotal,
        Date deliveryDate){
}
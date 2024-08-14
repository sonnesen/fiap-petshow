package com.petshow.petshow.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

@Builder
@Getter
public record OrderRequest(
        Date orderDate,
        String paymentMethod,
        BigDecimal totalValue,
        Date deliveryDate){
}
package com.petshow.petshow.dto;

import java.math.BigDecimal;
import java.util.Date;

public record OrderRecordDto(
        Date orderDate,
        String paymentMethod,
        BigDecimal valorTotal,
        Date deliveryDate){
}
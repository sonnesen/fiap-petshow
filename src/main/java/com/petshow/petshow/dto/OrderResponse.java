package com.petshow.petshow.dto;

import com.petshow.petshow.entity.ProductEntity;
import com.petshow.petshow.entity.StatusOrderEntity;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record OrderResponse(

        UUID idOrder,
        String name,
        Double quantidade,
        BigDecimal valorTotalServico,
        BigDecimal valorPago,
        BigDecimal troco,
        LocalDateTime dataEmissao,
        LocalDateTime dataFinalizacao,
        ProductEntity productEntity,
        StatusOrderEntity statusOrderEntity

) {


}

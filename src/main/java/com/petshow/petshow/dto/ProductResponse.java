package com.petshow.petshow.dto;

import java.math.BigDecimal;

public record ProductResponse(
        Long idProduct,
        String name,
        String description,
        BigDecimal price
) {
}

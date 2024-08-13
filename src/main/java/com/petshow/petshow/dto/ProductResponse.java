package com.petshow.petshow.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductResponse(
        UUID idProduct,
        String name,
        String description,
        BigDecimal value,
        BigDecimal valorProduct
) {
}

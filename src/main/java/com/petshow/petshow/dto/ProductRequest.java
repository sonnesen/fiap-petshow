package com.petshow.petshow.dto;

import java.math.BigDecimal;

public record ProductRequest(
        String name,
        String description,
        String quantity,
        BigDecimal price) {
}
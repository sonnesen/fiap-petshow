package com.petshow.petshow.dto;

import java.math.BigDecimal;

public record ProductRecordDto(
        String name,
        String description,
        String quantity,
        BigDecimal value) {
}
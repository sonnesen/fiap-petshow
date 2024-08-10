package com.petshow.petshow.entity;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record OrderRegistrerEntity(



            @NotBlank
            String name,

            @NotNull
            @Positive
            UUID productId,

            @NotNull
            @Digits(integer=4, fraction=0)
            double quantidade


)

{}





package com.petshow.petshow.dto;

import lombok.Builder;

@Builder
public record CostumerRecordDto(
        String customerName,
        String phoneNumber,
        String email,
        String primaryPet,
        String primaryPetSpecies) {
}
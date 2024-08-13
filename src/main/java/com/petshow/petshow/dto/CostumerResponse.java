package com.petshow.petshow.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record CostumerResponse (

        UUID idCostumer,
        String costumerName,
        String phoneNumber,
        String email,
        String primaryPet,
        String primaryPetSpecies

) {
}

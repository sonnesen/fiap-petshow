package com.petshow.petshow.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public record CostumerResponse (

        UUID idCostumer,
        String costumerName,
        String phoneNumber,
        String email,
        String primaryPet,
        String primaryPetSpecies

) {
}

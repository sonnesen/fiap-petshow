package com.petshow.petshow.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public record CostumerResponse (

        Long idCostumer,
        String costumerName,
        String phoneNumber,
        String email,
        String primaryPet,
        String primaryPetSpecies

) {
}

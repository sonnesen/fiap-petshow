package com.petshow.petshow.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Table(name = "tb_costumer")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class CostumerEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idCostumer;
    private String costumerName;
    private String phoneNumber;
    private String email;
    private String primaryPet;
    private String primaryPetSpecies;

}
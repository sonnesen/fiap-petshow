package com.petshow.petshow.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Table(name = "tb_costumer")
@Builder
@Data
@Getter
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
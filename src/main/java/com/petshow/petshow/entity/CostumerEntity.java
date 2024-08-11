package com.petshow.petshow.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "tb_costumer")
@Builder
public class CostumerEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idCostumer;
    private String costumerName;
    private String phoneNumber;
    private String email;
    private String primaryPet;
    private String primaryPetSpecies;

}
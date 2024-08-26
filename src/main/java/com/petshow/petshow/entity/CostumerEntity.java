package com.petshow.petshow.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Table(name = "costumer")
@Builder
@Data
@Getter
@Entity(name = "costumer")
public class CostumerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCostumer;
    @Column
    private String costumerName;
    @Column
    private String phoneNumber;
    @Column
    private String email;
    @Column
    private String primaryPet;
    @Column
    private String primaryPetSpecies;

}
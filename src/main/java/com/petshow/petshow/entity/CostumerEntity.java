package com.petshow.petshow.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.UUID;




@Entity
@Table(name = "tb_costumer")
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

    public UUID getIdCostumer() {
        return idCostumer;
    }

    public void setIdCostumer(UUID idCostumer) {
        this.idCostumer = idCostumer;
    }

    public String getCostumerName() {
        return costumerName;
    }

    public void setCostumerName(String customerName) {
        this.costumerName = customerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPrimaryPet() {
        return primaryPet;
    }

    public void setPrimaryPet(String primaryPet) {
        this.primaryPet = primaryPet;
    }

    public String getPrimaryPetSpecies() {
        return primaryPetSpecies;
    }

    public void setPrimaryPetSpecies(String primaryPetSpecies) {
        this.primaryPetSpecies = primaryPetSpecies;
    }
}





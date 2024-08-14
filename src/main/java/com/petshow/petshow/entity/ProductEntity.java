package com.petshow.petshow.entity;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "tb_products")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
public class ProductEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idProduct;
    private String name;
    private String description;
    private BigDecimal value;
    private BigDecimal valorProduct;

}
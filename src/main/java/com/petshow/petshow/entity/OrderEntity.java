package com.petshow.petshow.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;


@Entity(name = "orders")
@Table(name = "orders")
@Builder
@Data
@Getter
@Setter
public class OrderEntity {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idOrder;
    @Column
    private Date orderDate;
    @Column
    private String paymentMethod;
    @Column
    private BigDecimal totalValue;
    @Column
    private Date deliveryDate;
    @ManyToOne
    private ProductEntity productList;
    @Enumerated(EnumType.STRING)
    private StatusOrderEntity Entity;

}
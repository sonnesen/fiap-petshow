package com.petshow.petshow.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;




@Entity
@Table(name = "tb_orders")
public class OrderEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idOrder;

    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;

    @Temporal(TemporalType.TIMESTAMP)
    private
    Date deliveryDate;

    private String paymentMethod;
    private BigDecimal valorTotal;

//    @Enumerated(EnumType.STRING)
//    private OrderStatus status;
//
//    @ManyToOne
//    @JoinColumn(name = "customer_id")
//    private CustomerDomain
//            customer;


}






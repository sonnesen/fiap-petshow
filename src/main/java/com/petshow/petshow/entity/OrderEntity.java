package com.petshow.petshow.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;


@Entity
@Table(name = "tb_orders")
@Builder
@Data
@Getter
public class OrderEntity{

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idOrder;
    private Date orderDate;
    private String paymentMethod;
    private BigDecimal totalValue;
    private Date deliveryDate;
    @ManyToOne
    private ProductEntity productList;
    @Enumerated(EnumType.STRING)
    private StatusOrderEntity Entity;

    public UUID getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(UUID idOrder) {
        this.idOrder = idOrder;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public ProductEntity getProductList() {
        return productList;
    }

    public void setProductList(ProductEntity productList) {
        this.productList = productList;
    }

    public StatusOrderEntity getEntity() {
        return Entity;
    }

    public void setEntity(StatusOrderEntity entity) {
        Entity = entity;
    }
}
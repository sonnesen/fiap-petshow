package com.petshow.petshow.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseEntity {

    private Long id;
    private String nomeCliente;
    private String nomeProduto;
    private double quantidade;
    private BigDecimal valorTotal;
    private LocalDateTime dataEmissao;
    private LocalDateTime dataFinalizacao;
    private StatusOrderEntity statusPedido;

    // Construtor
    public OrderResponseEntity(OrderEntity orderEntity) {
        this.id = orderEntity.getId();
        this.nomeCliente = orderEntity.getNomeCliente();
        this.nomeProduto = orderEntity.getNomeProduto();//encontre onde o nome dogetNomeProduto está sendo setado;
        this.quantidade = orderEntity.getQuantidade();
        this.valorTotal = orderEntity.getValorTotalServico();
        this.dataEmissao = orderEntity.getDataEmissao();
        this.dataFinalizacao = orderEntity.getDataFinalizacao();
        this.statusPedido = orderEntity.getStatusPedido();

    }

    // Métodos estáticos para conversão
    public static Page<OrderResponseEntity> converter(Page<OrderEntity> orderEntities) {
        return orderEntities.map(OrderResponseEntity::new);
    }

    public static OrderResponseEntity converterUmProduto(OrderEntity orderEntity) {
        return new OrderResponseEntity(orderEntity);
    }
}
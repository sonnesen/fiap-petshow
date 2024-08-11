package com.petshow.petshow.entity;

import com.petshow.petshow.infra.exeption.ValorPagoInsuficienteException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;


@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "tb_orders")
public class OrderEntity implements Serializable {
    private static final long serialVersionUID = 1L;
@Column
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idOrder;
    private String name;
    private Double quantidade;

    private BigDecimal valorTotalServico = BigDecimal.ZERO;
    private BigDecimal valorPago = BigDecimal.ZERO;
    private BigDecimal troco = BigDecimal.ZERO;

    private LocalDateTime dataEmissao; // gerada automatico ao ser criado
    private LocalDateTime dataFinalizacao; // geraado automatico ao ser fechado


    @ManyToOne
    private ProductEntity productEntity;

    @Enumerated(EnumType.STRING)
    private StatusOrderEntity statusOrderEntity; // gerado automático em outros pontos


    public OrderEntity (String name,
                   ProductEntity productEntity,
                   Double quantidade) {
        this.name = name;
        this.productEntity = productEntity;
        this.quantidade = quantidade;
         this.calculaTotal();
    }

    public OrderEntity() {

    }

    public static Object getOrderEntity() {
        return null;
    }

    public void calculaTotal() {
       //this.valorTotalServico = pro.multiply(BigDecimal.valueOf(quantidade));

    }

     public BigDecimal calcularTroco(BigDecimal valorPago) throws ValorPagoInsuficienteException {
        if (valorPago.compareTo(this.valorTotalServico) == -1) {
            this.troco = BigDecimal.ZERO;
            throw new ValorPagoInsuficienteException("O valor pago R$: " + valorPago + " é menor que o total do serviço R$: " + this.valorTotalServico);
        }  else if (valorPago.compareTo(this.valorTotalServico) == 0) {
            this.troco = BigDecimal.ZERO;
            this.valorPago = valorPago;
            this.statusOrderEntity = StatusOrderEntity.PAGOFINALIZADO;
        } else if (valorPago.compareTo(this.valorTotalServico) == 1) {
            this.troco = valorPago.subtract(this.valorTotalServico);
            this.valorPago = valorPago;
            this.statusOrderEntity = StatusOrderEntity.PAGOFINALIZADO;
        }
        System.out.println(troco);
        return this.troco;
    }

    public Object map(Object aNew) {
        return null;
    }

    public Long getId() {
        return null;
    }

    public String getNomeCliente() {
        return null;
    }

    public Object getProduto() {
        return null;
    }

    public BigDecimal getValorTotalServico() {
        return null;
    }


    public LocalDateTime getDataEmissao() {
        return LocalDateTime.now();
    }


    public StatusOrderEntity getStatusPedido() {
        return StatusOrderEntity.PAGOFINALIZADO;
    }

    public LocalDateTime getDataFinalizacao() {
        return null;
    }

    public double getQuantidade() {
        return 0;
    }


       public Object getname() {
        return null;
    }

    public String getNomeProduto() {
        return null;
    }
}


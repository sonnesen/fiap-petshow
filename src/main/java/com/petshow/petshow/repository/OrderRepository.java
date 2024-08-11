package com.petshow.petshow.repository;

import com.petshow.petshow.entity.OrderEntity;
import com.petshow.petshow.entity.StatusOrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, UUID> {

    @Query("SELECT u FROM OrderEntity u WHERE u.name = :nomeCliente ")
    Page<OrderEntity> findByNomeClienteIgnoreCase(@Param("nomeCliente") String nomeCliente, Pageable paginacao);

    @Query("SELECT u FROM OrderEntity u WHERE u.statusOrderEntity = :status ")
    List<OrderEntity> findByStatusPedidoFila(@Param("status") StatusOrderEntity statusPedido);

    @Query("SELECT u FROM OrderEntity u WHERE u.statusOrderEntity = :status ")
    Page<OrderEntity> findByStatusPedido(@Param("status") StatusOrderEntity statusPedido, Pageable paginacao);

    @Query("SELECT u FROM OrderEntity u WHERE u.productEntity.name = :nomeProduto ")
    Page<OrderEntity> findByProdutoNameIgnoreCase(@Param("nomeProduto") String nomeProduto, Pageable paginacao);

    @Query("SELECT u FROM OrderEntity u WHERE u.productEntity.name = :nomeProduto AND u.statusOrderEntity=:status")
    Page<OrderEntity> findByProdutoNameIgnoreCaseAndStatusPedido(
            @Param("nomeProduto") String nomeProduto, @Param("status") StatusOrderEntity statusPedido, Pageable paginacao);

    @Query("SELECT u FROM OrderEntity u WHERE u.valorTotalServico >= :valorPedido")
    Page<OrderEntity> findMaiorQue(@Param("valorPedido") BigDecimal valorPedido, Pageable paginacao);






}

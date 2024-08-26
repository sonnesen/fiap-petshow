package com.petshow.petshow.service;

import com.petshow.petshow.dto.OrderRequest;
import com.petshow.petshow.entity.OrderEntity;
import com.petshow.petshow.exception.OrderNotFoundException;
import com.petshow.petshow.mapper.OrderMapper;
import com.petshow.petshow.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;
    @Autowired
    private OrderMapper mapper;

    public OrderEntity saveOrder(OrderRequest request){

        return repository.save(mapper.toOrderEntity(request));

    }

    public List<OrderEntity> getAllOrders(){

        return repository.findAll();

    }

    public OrderEntity getOrder(Long id) {

        return repository.findById(id).orElseThrow(() -> new OrderNotFoundException(String.format("Order '%s' not found.", id)));

    }

    public OrderEntity updateOrder(Long id, OrderRequest request){

        var order = getOrder(id);
        order.setOrderDate(request.orderDate());
        order.setDeliveryDate(request.deliveryDate());
        order.setPaymentMethod(request.paymentMethod());
        order.setTotalValue(request.totalValue());
        return repository.save(order);

    }

    public void deleteOrder(Long id) {

        OrderEntity order = this.getOrder(id);
        repository.delete(order);

    }

}
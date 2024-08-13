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
import java.util.UUID;

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

    public OrderEntity getOrder(UUID id) {

        return repository.findById(id).orElseThrow(() -> new OrderNotFoundException(String.format("Order '%s' not found.", id)));

    }

    public OrderEntity updateOrder(UUID id, OrderRequest request){

        getOrder(id);
        return saveOrder(request);

    }

    public void deleteOrder(UUID id) {

        OrderEntity order = this.getOrder(id);
        repository.delete(order);

    }

}
package com.petshow.petshow.controllers;

import com.petshow.petshow.dto.OrderRecordDto;
import com.petshow.petshow.entity.OrderEntity;
import com.petshow.petshow.repository.OrderRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController()
@RequestMapping("/petshow/api/v1/order")
public class OrderController {
    @Autowired
    OrderRepository OrderRepository;

    @PostMapping
    public ResponseEntity<OrderEntity> saveOrder(@RequestBody @Valid OrderRecordDto OrderRecordDto) {

        //var OrderEntity = new OrderEntity();
        var OrderEntity = new OrderEntity();
        BeanUtils.copyProperties(OrderRecordDto, OrderEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(OrderRepository.save(OrderEntity));
        //return ResponseEntity.status(HttpStatus.CREATED).body(OrderRepository.save(OrderEntity));

    }

    @GetMapping
    public ResponseEntity<List<OrderEntity>> getAllOrders() {

        return ResponseEntity.status(HttpStatus.OK).body(OrderRepository.findAll());

    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneOrder(@PathVariable(value="id") UUID id){

        Optional<OrderEntity> OrdersO = OrderRepository.findById(id);
        if(OrdersO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(OrdersO.get());

    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateOrder(@PathVariable(value="id") UUID id,
                                              @RequestBody @Valid OrderRecordDto OrderRecordDto) {
        Optional<OrderEntity> OrdersO = OrderRepository.findById(id);
        if(OrdersO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("orders not found.");
        }
        var OrderEntity = OrdersO.get();
        BeanUtils.copyProperties(OrderRecordDto, OrderEntity);
        return ResponseEntity.status(HttpStatus.OK).body(OrderRepository.save(OrderEntity));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteOrder(@PathVariable(value="id") UUID id) {

        Optional<OrderEntity> OrdersO = OrderRepository.findById(id);
        if(OrdersO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found.");
        }
        OrderRepository.delete(OrdersO.get());
        return ResponseEntity.status(HttpStatus.OK).body("Order deleted.");

    }

}
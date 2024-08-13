package com.petshow.petshow.controllers;

import com.petshow.petshow.dto.OrderRequest;
import com.petshow.petshow.dto.OrderResponse;
import com.petshow.petshow.exception.OrderNotFoundException;
import com.petshow.petshow.mapper.OrderMapper;
import com.petshow.petshow.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/petshow/api/v1/order")
public class OrderController {

    @Autowired
    private OrderService service;
    @Autowired
    private OrderMapper mapper;

    @PostMapping
    public ResponseEntity<OrderResponse> saveOrder(@RequestBody @Valid OrderRequest orderRequest) {

        var orderResponse = mapper.toOrderResponse(service.saveOrder(orderRequest));
        return ok(orderResponse);

    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrders() {

        var orderEntityList = service.getAllOrders();
        var orderResponseList = orderEntityList.stream().map(mapper::toOrderResponse).toList();

        return status(OK).body(orderResponseList);

    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable(value = "id") UUID id) {

        var order = service.getOrder(id);
        return ok(mapper.toOrderResponse(order));

    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponse> updateOrder(@PathVariable(value = "id") UUID id,
                                                     @RequestBody @Valid OrderRequest orderRequest) {

        var order = service.updateOrder(id, orderRequest);
        return ok(mapper.toOrderResponse(order));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable(value = "id") UUID id) {

        try {
            service.deleteOrder(id);
        } catch (Exception exception) {
            return notFound().build();
        }

        return noContent().build();

    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<String> handleOrderNotFoundException(OrderNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

}
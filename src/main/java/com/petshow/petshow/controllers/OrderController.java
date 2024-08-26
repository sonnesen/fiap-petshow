package com.petshow.petshow.controllers;

import com.petshow.petshow.dto.OrderRequest;
import com.petshow.petshow.dto.OrderResponse;
import com.petshow.petshow.exception.OrderNotFoundException;
import com.petshow.petshow.mapper.OrderMapper;
import com.petshow.petshow.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/petshow/api/v1/order")
@Tag(name = "petshow/api/v1/order", description = "Controller responsável por gerenciar os pedidos de um usuário.")
public class OrderController {

    @Autowired
    private OrderService service;
    @Autowired
    private OrderMapper mapper;

    @PostMapping
    @Operation(summary = "Criar um pedido", description = "Endpoint responsável por criar um novo pedido.", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido criado com sucesso.")
    })
    public ResponseEntity<OrderResponse> saveOrder(@RequestBody @Valid OrderRequest orderRequest) {

        var orderResponse = mapper.toOrderResponse(service.saveOrder(orderRequest));
        return ok(orderResponse);

    }

    @GetMapping
    @Operation(summary = "Obter todos pedidos", description = "Endpoint responsável por obter uma lista de pedidos.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedidos obtidos com sucesso.")
    })
    public ResponseEntity<List<OrderResponse>> getAllOrders() {

        var orderEntityList = service.getAllOrders();
        var orderResponseList = orderEntityList.stream().map(mapper::toOrderResponse).toList();

        return status(OK).body(orderResponseList);

    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter um pedido", description = "Endpoint responsável por obter um pedido.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido obtido com sucesso.")
    })
    public ResponseEntity<OrderResponse> getOrder(@PathVariable(value = "id") Long id) {

        var order = service.getOrder(id);
        return ok(mapper.toOrderResponse(order));

    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um pedido", description = "Endpoint responsável por atualizar um pedido existente.", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido atualizado com sucesso.")
    })
    public ResponseEntity<OrderResponse> updateOrder(@PathVariable(value = "id") Long id,
                                                     @RequestBody @Valid OrderRequest orderRequest) {

        var order = service.updateOrder(id, orderRequest);
        return ok(mapper.toOrderResponse(order));

    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar um pedido", description = "Endpoint responsável por deletar um pedido existente.", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido deletado com sucesso.")
    })
    public ResponseEntity<Void> deleteOrder(@PathVariable(value = "id") Long id) {

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
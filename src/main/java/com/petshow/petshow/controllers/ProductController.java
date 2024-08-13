package com.petshow.petshow.controllers;


import com.petshow.petshow.dto.ProductRequest;
import com.petshow.petshow.dto.ProductResponse;
import com.petshow.petshow.entity.ProductEntity;
import com.petshow.petshow.exception.ProductNotFoundException;
import com.petshow.petshow.mapper.ProductMapper;
import com.petshow.petshow.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.*;

@RestController()
@RequestMapping("/petshow/api/v1/product")
public class ProductController {

    @Autowired
    private ProductService service;
    @Autowired
    ProductMapper mapper;

    @PostMapping
    public ResponseEntity<ProductResponse> saveProduct(@RequestBody @Valid ProductRequest productRequest) {

        var productEntity = service.saveProduct(productRequest);
        return ok(mapper.toProductResponse(productEntity));

    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {

        List<ProductResponse> productResponseList = service.getAllProducts()
                .stream().map(mapper::toProductResponse).toList();
        return ok(productResponseList);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable(value = "id") UUID id) {

        ProductEntity product = service.getProduct(id);
        return ok(mapper.toProductResponse(product));

    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable(value = "id") UUID id,
                                                @RequestBody @Valid ProductRequest productRequest) {

        ProductEntity productEntity = service.updateProduct(id, productRequest);
        return ok(mapper.toProductResponse(productEntity));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable(value = "id") UUID id) {

        try {
            service.deleteProduct(id);
        } catch (Exception exception) {
            return notFound().build();
        }

        return noContent().build();

    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleProductNotFoundException(ProductNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

}
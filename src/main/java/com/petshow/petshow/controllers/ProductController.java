package com.petshow.petshow.controllers;


import com.petshow.petshow.dto.ProductRequest;
import com.petshow.petshow.dto.ProductResponse;
import com.petshow.petshow.entity.ProductEntity;
import com.petshow.petshow.exception.ProductNotFoundException;
import com.petshow.petshow.mapper.ProductMapper;
import com.petshow.petshow.service.ProductService;
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

import static org.springframework.http.ResponseEntity.*;

@RestController()
@RequestMapping("/petshow/api/v1/product")
@Tag(name = "petshow/api/v1/product", description = "Controller responsável por gerenciar os produtos de uma compra.")
public class ProductController {

    @Autowired
    private ProductService service;
    @Autowired
    private ProductMapper mapper;

    @PostMapping
    @Operation(summary = "Criar um produto", description = "Endpoint responsável por criar um novo produto.", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto criado com sucesso.")
    })
    public ResponseEntity<ProductResponse> saveProduct(@RequestBody @Valid ProductRequest productRequest) {

        var productEntity = service.saveProduct(productRequest);
        return ok(mapper.toProductResponse(productEntity));

    }

    @GetMapping
    @Operation(summary = "Obter todos produtos", description = "Endpoint responsável por obter uma lista de produtos.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produtos obtidos com sucesso.")
    })
    public ResponseEntity<List<ProductResponse>> getAllProducts() {

        List<ProductResponse> productResponseList = service.getAllProducts()
                .stream().map(mapper::toProductResponse).toList();
        return ok(productResponseList);

    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter um produto", description = "Endpoint responsável por obter um produto.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto obtido com sucesso.")
    })
    public ResponseEntity<ProductResponse> getProduct(@PathVariable(value = "id") Long id) {

        ProductEntity product = service.getProduct(id);
        return ok(mapper.toProductResponse(product));

    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um produto", description = "Endpoint responsável por atualizar um produto existente.", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso.")
    })
    public ResponseEntity<Object> updateProduct(@PathVariable(value = "id") Long id,
                                                @RequestBody @Valid ProductRequest productRequest) {

        ProductEntity productEntity = service.updateProduct(id, productRequest);
        return ok(mapper.toProductResponse(productEntity));

    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar um produto", description = "Endpoint responsável por deletar um produto existente.", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto deletado com sucesso.")
    })
    public ResponseEntity<Void> deleteProduct(@PathVariable(value = "id") Long id) {

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
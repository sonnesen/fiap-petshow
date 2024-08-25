package com.petshow.petshow.controllers;

import com.petshow.petshow.dto.CostumerRequest;
import com.petshow.petshow.dto.CostumerResponse;
import com.petshow.petshow.exception.CostumerNotFoundException;
import com.petshow.petshow.mapper.CostumerMapper;
import com.petshow.petshow.service.CostumerService;
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

@RestController
@RequestMapping("/petshow/api/v1/costumer")
@Tag(name = "petshow/api/v1/costumer", description = "Controller responsável por gerenciar os dados do cliente.")
public class CostumerController {

    @Autowired
    private CostumerService service;
    @Autowired
    private CostumerMapper mapper;


    @PostMapping
    @Operation(summary = "Criar cliente", description = "Endpoint responsável por criar um novo cliente.", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente criado com sucesso.")
    })
    public ResponseEntity<CostumerResponse> saveCostumer(@RequestBody @Valid CostumerRequest costumerRequest) {

        var costumerResponse = mapper.toCostumerResponse(service.saveCostumer(costumerRequest));
        return status(HttpStatus.CREATED).body(costumerResponse);

    }

    @GetMapping
    @Operation(summary = "Obter todos clientes", description = "Endpoint responsável por obter uma lista de clientes.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Clientes obtidos com sucesso.")
    })
    public ResponseEntity<List<CostumerResponse>> getAllCostumer() {

        var allCostumer = service.getAllCostumer()
                .stream().map(mapper::toCostumerResponse).toList();
        return ok(allCostumer);

    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter um cliente", description = "Endpoint responsável por obter cliente.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente obtido com sucesso.")
    })
    public ResponseEntity<CostumerResponse> getOneCostumer(@PathVariable(value = "id") Long id) {

        var costumer = service.getCostumer(id);
        return ok(mapper.toCostumerResponse(costumer));

    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um cliente", description = "Endpoint responsável por atualizar um cliente existente.", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso.")
    })
    public ResponseEntity<Object> updateCostumer(@PathVariable(value = "id") Long id,
                                                 @RequestBody @Valid CostumerRequest costumerRequest) {

        var costumer = service.updateCostumer(id, costumerRequest);
        return ok(mapper.toCostumerResponse(costumer));

    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar um cliente", description = "Endpoint responsável por deletar um cliente existente.", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente deletado com sucesso.")
    })
    public ResponseEntity<Void> deleteCostumer(@PathVariable(value = "id") Long id) {

        try {
            service.deleteCostumer(id);
        } catch (Exception exception) {
            return notFound().build();
        }
        return noContent().build();

    }

    @ExceptionHandler(CostumerNotFoundException.class)
    public ResponseEntity<String> handleCostumerNotFoundException(CostumerNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

}
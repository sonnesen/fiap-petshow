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
import java.util.UUID;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/petshow/api/v1/costumer")
@Tag(name = "petshow/api/v1/costumer", description = "Api for costumer")
public class CostumerController {

    @Autowired
    private CostumerService service;
    @Autowired
    private CostumerMapper mapper;


    @PostMapping
    @Operation(summary = "Summary", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Criado com sucesso.")
    })
    public ResponseEntity<CostumerResponse> saveCostumer(@RequestBody @Valid CostumerRequest costumerRequest) {

        var costumerResponse = mapper.toCostumerResponse(service.saveCostumer(costumerRequest));
        return status(HttpStatus.CREATED).body(costumerResponse);

    }

    @GetMapping
    public ResponseEntity<List<CostumerResponse>> getAllCostumer() {

        var allCostumer = service.getAllCostumer()
                .stream().map(mapper::toCostumerResponse).toList();
        return ok(allCostumer);

    }

    @GetMapping("/{id}")
    public ResponseEntity<CostumerResponse> getOneCostumer(@PathVariable(value = "id") UUID id) {

        var costumer = service.getCostumer(id);
        return ok(mapper.toCostumerResponse(costumer));

    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCostumer(@PathVariable(value = "id") UUID id,
                                                 @RequestBody @Valid CostumerRequest costumerRequest) {

        var costumer = service.updateCostumer(id, costumerRequest);
        return ok(mapper.toCostumerResponse(costumer));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCostumer(@PathVariable(value = "id") UUID id) {

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
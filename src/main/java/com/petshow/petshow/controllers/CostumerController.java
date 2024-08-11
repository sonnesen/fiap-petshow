package com.petshow.petshow.controllers;

import com.petshow.petshow.entity.CostumerEntity;
import com.petshow.petshow.dto.CostumerRecordDto;
import com.petshow.petshow.repository.CostumerRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/petshow/api/v1")
public class CostumerController {

    @Autowired
    private final CostumerRepository costumerRepository;

    @PostMapping("/costumer")
    public ResponseEntity<CostumerEntity> saveCostumer(@RequestBody @Valid CostumerRecordDto costumerRecordDto) {

        var CostumerEntity = com.petshow.petshow.entity.CostumerEntity.builder().build();
        BeanUtils.copyProperties(costumerRecordDto, CostumerEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(costumerRepository.save(CostumerEntity));

    }

    @GetMapping("/costumer")
    public ResponseEntity<List<CostumerEntity>> getAllCostumer() {

        return ResponseEntity.status(HttpStatus.OK).body(costumerRepository.findAll());

    }

    @GetMapping("/costumer/{id}")
    public ResponseEntity<Object> getOneCostumer(@PathVariable(value="id") UUID id){

        Optional<CostumerEntity> costumerO = costumerRepository.findById(id);
        if(costumerO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Costumer not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(costumerO.get());

    }

    @PutMapping("/costumer/{id}")
    public ResponseEntity<Object> updateCostumer(@PathVariable(value="id") UUID id,
                                                 @RequestBody @Valid CostumerRecordDto productRecordDto) {
        Optional<CostumerEntity> costumerO = costumerRepository.findById(id);
        if(costumerO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        var productModel = costumerO.get();
        BeanUtils.copyProperties(productRecordDto, productModel);
        return ResponseEntity.status(HttpStatus.OK).body(costumerRepository.save(productModel));

    }

    @DeleteMapping("/costumer/{id}")
    public ResponseEntity<Object> deleteCostumer(@PathVariable(value="id") UUID id) {
        Optional<CostumerEntity> costumerO = costumerRepository.findById(id);
        if(costumerO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Costumer not found.");
        }
        costumerRepository.delete(costumerO.get());
        return ResponseEntity.status(HttpStatus.OK).body("Costumer deleted.");

    }

}
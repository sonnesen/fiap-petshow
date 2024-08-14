package com.petshow.petshow.service;

import com.petshow.petshow.dto.CostumerRequest;
import com.petshow.petshow.entity.CostumerEntity;
import com.petshow.petshow.exception.CostumerNotFoundException;
import com.petshow.petshow.mapper.CostumerMapper;
import com.petshow.petshow.repository.CostumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CostumerService {

    @Autowired
    private CostumerRepository repository;
    @Autowired
    private CostumerMapper mapper;

    public CostumerEntity saveCostumer(CostumerRequest request) {

        return repository.save(mapper.toCostumerEntity(request));

    }

    public List<CostumerEntity> getAllCostumer() {
        return repository.findAll();
    }

    public CostumerEntity getCostumer(UUID id) {

        return repository.findById(id)
                .orElseThrow(() ->  new CostumerNotFoundException(String.format("Costumer '%s' not found.", id)));

    }

    public CostumerEntity updateCostumer(UUID id, CostumerRequest request) {

        getCostumer(id);
        return saveCostumer(request);

    }

    public void deleteCostumer(UUID id) {
        CostumerEntity costumer = getCostumer(id);
        repository.delete(costumer);
    }

}

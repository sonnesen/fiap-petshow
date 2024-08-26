package com.petshow.petshow.service;

import com.petshow.petshow.dto.ProductRequest;
import com.petshow.petshow.entity.ProductEntity;
import com.petshow.petshow.exception.ProductNotFoundException;
import com.petshow.petshow.mapper.ProductMapper;
import com.petshow.petshow.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;
    @Autowired
    private ProductMapper mapper;

    public ProductEntity saveProduct(ProductRequest request) {
        return repository.save(mapper.toProductEntity(request));
    }

    public List<ProductEntity> getAllProducts() {
        return repository.findAll();
    }

    public ProductEntity getProduct(Long id) {
        return repository.findById(id).orElseThrow(() -> new ProductNotFoundException(String.format("Product '%s' not found.", id)));
    }

    public ProductEntity updateProduct(Long id, ProductRequest productRequest) {
        var product = getProduct(id);
        product.setName(productRequest.name());
        product.setPrice(productRequest.price());
        product.setDescription(productRequest.description());
        return repository.save(product);
    }

    public void deleteProduct(Long id) {
        ProductEntity product = getProduct(id);
        repository.delete(product);
    }
}

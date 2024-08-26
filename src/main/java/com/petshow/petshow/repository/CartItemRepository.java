package com.petshow.petshow.repository;

import com.petshow.petshow.entity.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItemEntity, Long> {

    CartItemEntity getByProductId(Long productId);
}
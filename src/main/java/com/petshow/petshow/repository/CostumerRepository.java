package com.petshow.petshow.repository;

import com.petshow.petshow.entity.CostumerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CostumerRepository extends JpaRepository<CostumerEntity, Long> {

}
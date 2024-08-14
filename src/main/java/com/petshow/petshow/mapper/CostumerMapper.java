package com.petshow.petshow.mapper;

import com.petshow.petshow.dto.CostumerRequest;
import com.petshow.petshow.dto.CostumerResponse;
import com.petshow.petshow.entity.CostumerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CostumerMapper {

    CostumerEntity toCostumerEntity(CostumerRequest request);

    CostumerResponse toCostumerResponse(CostumerEntity costumerEntity);

}
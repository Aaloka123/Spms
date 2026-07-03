package com.spms.mapper;

import com.spms.dto.request.ProductRequestDTO;
import com.spms.dto.response.ProductResponseDTO;
import com.spms.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

//Mapper for converting between Product Entity and DTOs.
@Mapper(componentModel = "spring")
public interface ProductMapper {

    // Convert ProductRequestDTO to Product Entity
    Product toEntity(ProductRequestDTO requestDTO);

    // Convert Product Entity to ProductResponseDTO
    ProductResponseDTO toResponseDTO(Product product);

    // Update existing Product Entity from RequestDTO
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntityFromDTO(ProductRequestDTO requestDTO,
                             @MappingTarget Product product);

}
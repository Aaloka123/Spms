package com.spms.mapper;

import com.spms.dto.request.RoleRequestDTO;
import com.spms.dto.response.RoleResponseDTO;
import com.spms.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

//Mapper for converting between Role Entity and DTOs.

@Mapper(componentModel = "spring")
public interface RoleMapper {

    // Convert RoleRequestDTO to Role Entity
    Role toEntity(RoleRequestDTO requestDTO);

    // Convert Role Entity to RoleResponseDTO
    RoleResponseDTO toResponseDTO(Role role);

    // Update an existing Role Entity using RoleRequestDTO
    void updateEntityFromDTO(RoleRequestDTO requestDTO,
                             @MappingTarget Role role);

}
package com.spms.mapper;

import com.spms.dto.request.RoleRequestDTO;
import com.spms.dto.response.RoleResponseDTO;
import com.spms.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

//Mapper for converting between Role Entity and DTOs.

@Mapper(componentModel = "spring")
public interface RoleMapper {

    // Convert RoleRequestDTO to Role Entity
    @Mapping(target = "roleId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Role toEntity(RoleRequestDTO requestDTO);

    // Convert Role Entity to RoleResponseDTO
    RoleResponseDTO toResponseDTO(Role role);

    // Convert list of Role entities to list of RoleResponseDTO
    List<RoleResponseDTO> toResponseDTOList(List<Role> roles);

    // Update an existing Role Entity using RoleRequestDTO
    @Mapping(target = "roleId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    void updateEntityFromDTO(RoleRequestDTO requestDTO,
                             @MappingTarget Role role);

}
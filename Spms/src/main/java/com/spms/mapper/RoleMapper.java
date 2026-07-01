package com.spms.mapper;

import com.spms.dto.request.RoleRequestDTO;
import com.spms.dto.response.RoleResponseDTO;
import com.spms.entity.Role;
import org.springframework.stereotype.Component;

/**
 * Mapper class for converting between Role Entity and DTOs.
 */
@Component
public class RoleMapper {

    // Convert RoleRequestDTO to Role Entity
    public Role toEntity(RoleRequestDTO requestDTO) {

        Role role = new Role();

        role.setRoleName(requestDTO.getRoleName());
        role.setDescription(requestDTO.getDescription());

        return role;
    }

    // Convert Role Entity to RoleResponseDTO
    public RoleResponseDTO toResponseDTO(Role role) {

        RoleResponseDTO responseDTO = new RoleResponseDTO();

        responseDTO.setRoleId(role.getRoleId());
        responseDTO.setRoleName(role.getRoleName());
        responseDTO.setDescription(role.getDescription());
        responseDTO.setCreatedAt(role.getCreatedAt());

        return responseDTO;
    }

    // Update existing entity using DTO
    public void updateEntityFromDTO(RoleRequestDTO requestDTO, Role role) {

        role.setRoleName(requestDTO.getRoleName());
        role.setDescription(requestDTO.getDescription());
    }
}
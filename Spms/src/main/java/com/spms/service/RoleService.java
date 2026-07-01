package com.spms.service;

import com.spms.dto.request.RoleRequestDTO;
import com.spms.dto.response.RoleResponseDTO;

import java.util.List;

public interface RoleService {

    // Create a new role
    RoleResponseDTO saveRole(RoleRequestDTO roleRequestDTO);

    // Retrieve all roles


    List<RoleResponseDTO> getAllRoles();

    // Retrieve a role by its ID
    RoleResponseDTO getRoleById(Long id);

    // Update an existing role
    RoleResponseDTO updateRole(Long id, RoleRequestDTO roleRequestDTO);

    // Delete a role
    void deleteRole(Long id);
}


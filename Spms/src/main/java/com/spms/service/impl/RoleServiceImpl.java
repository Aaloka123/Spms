package com.spms.service.impl;

import com.spms.dto.request.RoleRequestDTO;
import com.spms.dto.response.RoleResponseDTO;
import com.spms.entity.Role;
import com.spms.mapper.RoleMapper;
import com.spms.repository.RoleRepository;
import com.spms.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

//Service implementation for Role operations
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    // Constructor Injection
    public RoleServiceImpl(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    //create a new role
    @Override
    public RoleResponseDTO saveRole(RoleRequestDTO roleRequestDTO) {

        // Convert DTO to Entity
        Role role = roleMapper.toEntity(roleRequestDTO);

        // Ensure a new record is created
        role.setRoleId(null);

        // Save Entity
        Role savedRole = roleRepository.save(role);

        // Convert Entity to Response DTO
        return roleMapper.toResponseDTO(savedRole);
    }

    //Retrieve all roles
    @Override
    public List<RoleResponseDTO> getAllRoles() {

        return roleRepository.findAll()
                .stream()
                .map(roleMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retrieve a role by ID.
     */
    @Override
    public RoleResponseDTO getRoleById(Long id) {

        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found with ID: " + id));

        return roleMapper.toResponseDTO(role);
    }

    /**
     * Update an existing role.
     */
    @Override
    public RoleResponseDTO updateRole(Long id, RoleRequestDTO roleRequestDTO) {

        Role existingRole = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found with ID: " + id));

        // Update entity using mapper
        roleMapper.updateEntityFromDTO(roleRequestDTO, existingRole);

        // Save updated entity
        Role updatedRole = roleRepository.save(existingRole);

        // Convert Entity to Response DTO
        return roleMapper.toResponseDTO(updatedRole);
    }

    /**
     * Delete a role.
     */
    @Override
    public void deleteRole(Long id) {

        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found with ID: " + id));

        roleRepository.delete(role);
    }
}
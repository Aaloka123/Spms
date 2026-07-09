package com.spms.service.impl;

import com.spms.constants.Roles;
import com.spms.dto.request.RoleRequestDTO;
import com.spms.dto.response.RoleResponseDTO;
import com.spms.entity.Role;
import com.spms.exception.InvalidRoleNameException;
import com.spms.exception.RoleAlreadyExistsException;
import com.spms.exception.RoleInUseException;
import com.spms.exception.RoleNotFoundException;
import com.spms.mapper.RoleMapper;
import com.spms.repository.RoleRepository;
import com.spms.repository.UserRepository;
import com.spms.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

// Service implementation for Role operations.
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;
    private final UserRepository userRepository;

    // Create a new role.
    @Override
    public RoleResponseDTO saveRole(RoleRequestDTO roleRequestDTO) {

        validateAllowedRoleName(roleRequestDTO.getRoleName());

        // Check role name
        if (roleRepository.existsByRoleName(roleRequestDTO.getRoleName())) {
            throw new RoleAlreadyExistsException(roleRequestDTO.getRoleName());
        }

        // Convert DTO to Entity using MapStruct
        Role role = roleMapper.toEntity(roleRequestDTO);

        // Save Entity
        Role savedRole = roleRepository.save(role);

        // Convert Entity to Response DTO
        return roleMapper.toResponseDTO(savedRole);
    }

    // Retrieve all roles.
    @Override
    public List<RoleResponseDTO> getAllRoles() {

        return roleMapper.toResponseDTOList(roleRepository.findAll());
    }

    // Retrieve a role by ID.
    @Override
    public RoleResponseDTO getRoleById(Long id) {

        Role role = roleRepository.findById(id)
                .orElseThrow(() ->
                        new RoleNotFoundException(
                                "Role not found with id: " + id));

        return roleMapper.toResponseDTO(role);
    }

    // Update an existing role.
    @Override
    public RoleResponseDTO updateRole(Long id, RoleRequestDTO roleRequestDTO) {

        Role existingRole = roleRepository.findById(id)
                .orElseThrow(() ->
                        new RoleNotFoundException(
                                "Role not found with id: " + id));

        validateAllowedRoleName(roleRequestDTO.getRoleName());

        // Check role name
        if (roleRepository.existsByRoleNameAndRoleIdNot(roleRequestDTO.getRoleName(), id)) {
            throw new RoleAlreadyExistsException(roleRequestDTO.getRoleName());
        }

        // Update entity using mapper
        roleMapper.updateEntityFromDTO(roleRequestDTO, existingRole);

        // Save updated entity
        Role updatedRole = roleRepository.save(existingRole);

        // Convert Entity to Response DTO
        return roleMapper.toResponseDTO(updatedRole);
    }

    // Delete a role.
    @Override
    public void deleteRole(Long id) {

        Role role = roleRepository.findById(id)
                .orElseThrow(() ->
                        new RoleNotFoundException(
                                "Role not found with id: " + id));

        // Check if role is assigned to users
        if (userRepository.existsByRole_RoleId(id)) {
            throw new RoleInUseException(id);
        }

        roleRepository.delete(role);
    }

    private void validateAllowedRoleName(String roleName) {

        if (!Roles.ADMIN.equals(roleName)
                && !Roles.PHARMACIST.equals(roleName)
                && !Roles.USER.equals(roleName)) {
            throw new InvalidRoleNameException(roleName);
        }
    }
}

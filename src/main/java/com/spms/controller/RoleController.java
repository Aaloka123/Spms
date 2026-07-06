package com.spms.controller;

import com.spms.constants.ApiPath;
import com.spms.dto.request.RoleRequestDTO;
import com.spms.dto.response.RoleResponseDTO;
import com.spms.service.RoleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPath.ROLES)
public class RoleController {

    private final RoleService roleService;

    // Constructor injection
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    // Create a new role
    @PostMapping
    public ResponseEntity<RoleResponseDTO> createRole(
            @Valid @RequestBody RoleRequestDTO roleRequestDTO) {

        RoleResponseDTO savedRole = roleService.saveRole(roleRequestDTO);

        return new ResponseEntity<>(savedRole, HttpStatus.CREATED);
    }

    // Retrieve all roles
    @GetMapping
    public ResponseEntity<List<RoleResponseDTO>> getAllRoles() {

        List<RoleResponseDTO> roles = roleService.getAllRoles();

        return ResponseEntity.ok(roles);
    }

    // Retrieve a role by its ID
    @GetMapping("/{id}")
    public ResponseEntity<RoleResponseDTO> getRoleById(@PathVariable Long id) {

        RoleResponseDTO role = roleService.getRoleById(id);

        return ResponseEntity.ok(role);
    }

    // Update an existing role
    @PutMapping("/{id}")
    public ResponseEntity<RoleResponseDTO> updateRole(
            @PathVariable Long id,
            @Valid @RequestBody RoleRequestDTO roleRequestDTO) {

        RoleResponseDTO updatedRole = roleService.updateRole(id, roleRequestDTO);

        return ResponseEntity.ok(updatedRole);
    }

    // Delete a role by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRole(@PathVariable Long id) {

        roleService.deleteRole(id);

        return ResponseEntity.ok("Role deleted successfully.");
    }
}
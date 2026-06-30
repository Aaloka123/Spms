package com.spms.controller;

import com.spms.entity.Role;
import com.spms.service.RoleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for Role Management.
 */
@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService roleService;

    // Constructor Injection
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    // Create Role
    @PostMapping
    public ResponseEntity<Role> createRole(@Valid @RequestBody Role role) {

        Role savedRole = roleService.saveRole(role);

        return new ResponseEntity<>(savedRole, HttpStatus.CREATED);
    }

    // Get All Roles
    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() {

        List<Role> roles = roleService.getAllRoles();

        return ResponseEntity.ok(roles);
    }

    // Get Role by ID
    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable Long id) {

        return roleService.getRoleById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update Role
    @PutMapping("/{id}")
    public ResponseEntity<Role> updateRole(
            @PathVariable Long id,
            @Valid @RequestBody Role role) {

        return roleService.updateRole(id, role)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete Role
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRole(@PathVariable Long id) {

        roleService.deleteRole(id);

        return ResponseEntity.ok("Role deleted successfully.");
    }
}
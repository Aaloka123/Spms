package com.spms.service;

import com.spms.entity.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    Role saveRole(Role role);

    List<Role> getAllRoles();

    Optional<Role> getRoleById(Long id);

    Optional<Role> updateRole(Long id, Role role);

    void deleteRole(Long id);
}
package com.spms.service.impl;

import com.spms.entity.Role;
import com.spms.repository.RoleRepository;
import com.spms.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role saveRole(Role role) {
        role.setRoleId(null);
        return roleRepository.save(role);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> getRoleById(Long id) {
        return roleRepository.findById(id);
    }

    @Override
    public Optional<Role> updateRole(Long id, Role role) {
        return roleRepository.findById(id).map(existingRole -> {
            existingRole.setRoleName(role.getRoleName());
            existingRole.setDescription(role.getDescription());
            return roleRepository.save(existingRole);
        });
    }

    @Override
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }
}

package com.spms.config;

import com.spms.constants.Roles;
import com.spms.entity.Role;
import com.spms.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

// Seeds fixed system roles when the application starts.
@Component
@RequiredArgsConstructor
public class RoleDataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) {

        seedRole(Roles.ADMIN, "System Administrator");
        seedRole(Roles.PHARMACIST, "Pharmacist role");
        seedRole(Roles.USER, "Normal system user");
    }

    private void seedRole(String roleName, String description) {

        if (!roleRepository.existsByRoleName(roleName)) {
            Role role = new Role();
            role.setRoleName(roleName);
            role.setDescription(description);
            roleRepository.save(role);
        }
    }
}

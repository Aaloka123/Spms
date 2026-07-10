package com.spms.config;

import com.spms.constants.Roles;
import com.spms.entity.Role;
import com.spms.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

// Seeds fixed system roles when the application starts.
@Component
@RequiredArgsConstructor
public class RoleDataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    //overrides the run method from the command line runner
    @Override
    public void run(String @NonNull ... args) { //Variable-length command-line arguments.

        seedRole(Roles.ADMIN, "System Administrator");
        seedRole(Roles.PHARMACIST, "Pharmacist role");
        seedRole(Roles.USER, "Normal system user");
    }

    //Helper method
    private void seedRole(String roleName, String description) {

        if (!roleRepository.existsByRoleName(roleName)) {
            Role role = new Role();
            role.setRoleName(roleName);
            role.setDescription(description);
            roleRepository.save(role);
        }
    }
}

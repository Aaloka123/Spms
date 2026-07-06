package com.spms.repository;

import com.spms.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRoleName(String roleName);

    boolean existsByRoleName(String roleName);

    // Check role name excluding current role
    boolean existsByRoleNameAndRoleIdNot(String roleName, Long roleId);

}
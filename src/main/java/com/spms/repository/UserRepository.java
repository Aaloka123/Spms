package com.spms.repository;

import com.spms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Check if username already exists
    boolean existsByUsername(String username);

    // Check if email already exists
    boolean existsByEmail(String email);

    // Check if phone number already exists
    boolean existsByPhoneNumber(String phoneNumber);

    // Check username excluding current user
    boolean existsByUsernameAndIdNot(String username, Long id);

    // Check email excluding current user
    boolean existsByEmailAndIdNot(String email, Long id);

    // Check phone number excluding current user
    boolean existsByPhoneNumberAndIdNot(String phoneNumber, Long id);

    // Finds a user by username for login authentication
    @Query("SELECT u FROM User u JOIN FETCH u.role WHERE u.username = :username")
    Optional<User> findByUsername(@Param("username") String username);

    // Check if any user is assigned to a role
    boolean existsByRole_RoleId(Long roleId);
}
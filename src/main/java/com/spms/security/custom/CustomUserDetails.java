package com.spms.security.custom;

import com.spms.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

// Represents the logged-in user for Spring Security
public class CustomUserDetails implements UserDetails {

    // Stores the application's User entity
    private final User user;

    // Initializes the authenticated user
    public CustomUserDetails(User user) {
        this.user = user;
    }

    // Returns the user's role as a GrantedAuthority
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(
                new SimpleGrantedAuthority(user.getRole().getRoleName())
        );
    }

    // Returns the encoded password
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    // Returns the username used for login
    @Override
    public String getUsername() {
        return user.getUsername();
    }

    // Returns true if the account has not expired
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // Returns true if the account is not locked
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // Returns true if the password has not expired
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // Returns whether the user account is enabled
    @Override
    public boolean isEnabled() {
        return Boolean.TRUE.equals(user.getEnabled());
    }

    // Returns the complete User entity
    public User getUser() {
        return user;
    }
}
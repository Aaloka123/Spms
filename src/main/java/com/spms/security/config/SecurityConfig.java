package com.spms.security.config;

import com.spms.constants.ApiPath;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// Marks this class as a Spring Security configuration
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    // Loads user details from the database
    private final UserDetailsService userDetailsService;

    // Encodes and verifies passwords
    private final PasswordEncoder passwordEncoder;

    // Configures Spring Security filter chain
    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            DaoAuthenticationProvider authenticationProvider) throws Exception {

        http

                // Disable CSRF for REST APIs
                .csrf(AbstractHttpConfigurer::disable)

                // Configure endpoint authorization
                .authorizeHttpRequests(auth -> auth

                        // Authentication endpoint
                        .requestMatchers(ApiPath.AUTH + "/login").permitAll()

                        // Public Role APIs
                        .requestMatchers(HttpMethod.GET, ApiPath.ROLES + "/**").permitAll()

                        // Public User Registration API
                        .requestMatchers(HttpMethod.POST, ApiPath.USERS).permitAll()

                        // All remaining endpoints require authentication
                        .anyRequest().authenticated()
                )

                // Disable Spring Security's default login page
                .formLogin(AbstractHttpConfigurer::disable)

                // Enable HTTP Basic Authentication
                .httpBasic(httpBasic -> {
                })

                // Register the custom authentication provider
                .authenticationProvider(authenticationProvider);

        return http.build();
    }

    // Configures DaoAuthenticationProvider
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider(userDetailsService);

        // Sets the password encoder
        provider.setPasswordEncoder(passwordEncoder);

        return provider;
    }

    // Exposes AuthenticationManager as a Spring Bean
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration) throws Exception {

        return configuration.getAuthenticationManager();
    }
}
package com.spms.service.impl;

import com.spms.dto.request.LoginRequestDTO;
import com.spms.dto.response.LoginResponseDTO;
import com.spms.security.custom.CustomUserDetails;
import com.spms.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;

    // Authenticates a user and returns the login response
    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {

        Authentication authentication = authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken.unauthenticated(
                        loginRequestDTO.getUsername(),
                        loginRequestDTO.getPassword()
                )
        );

        CustomUserDetails userDetails =
                (CustomUserDetails) authentication.getPrincipal();

        assert userDetails != null;
        return new LoginResponseDTO(
                "Login successful",
                userDetails.getUsername(),
                userDetails.getUser().getRole().getRoleName()
        );
        
    }
}
package com.spms.service.impl;

import com.spms.dto.request.LoginRequestDTO;
import com.spms.dto.response.LoginResponseDTO;
import com.spms.exception.InvalidCredentialsException;
import com.spms.security.custom.CustomUserDetails;
import com.spms.security.jwt.JwtService;
import com.spms.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {

        try {
            Authentication authentication = authenticationManager.authenticate(
                    UsernamePasswordAuthenticationToken.unauthenticated(
                            loginRequestDTO.getUsername(),
                            loginRequestDTO.getPassword()
                    )
            );

            CustomUserDetails userDetails =
                    (CustomUserDetails) authentication.getPrincipal();

            String accessToken = jwtService.generateToken(userDetails);

            return new LoginResponseDTO(
                    "Login successful",
                    userDetails.getUsername(),
                    userDetails.getUser().getRole().getRoleName(),
                    accessToken
            );

        } catch (BadCredentialsException ex) {
            throw new InvalidCredentialsException();
        }
    }
}

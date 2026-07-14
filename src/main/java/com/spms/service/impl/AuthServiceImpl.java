package com.spms.service.impl;

import com.spms.dto.request.LoginRequestDTO;
import com.spms.dto.request.RefreshTokenRequestDTO;
import com.spms.dto.response.LoginResponseDTO;
import com.spms.entity.RefreshToken;
import com.spms.entity.User;
import com.spms.exception.InvalidCredentialsException;
import com.spms.exception.InvalidRefreshTokenException;
import com.spms.security.custom.CustomUserDetails;
import com.spms.security.jwt.JwtService;
import com.spms.security.service.UserDetailsServiceImpl;
import com.spms.service.AuthService;
import com.spms.service.RefreshTokenService;
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
    private final RefreshTokenService refreshTokenService;
    private final UserDetailsServiceImpl userDetailsService;

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {

        try {
            // Step 1: Validate username and password
            Authentication authentication = authenticationManager.authenticate(
                    UsernamePasswordAuthenticationToken.unauthenticated(
                            loginRequestDTO.getUsername(),
                            loginRequestDTO.getPassword()
                    )
            );

            CustomUserDetails userDetails =
                    (CustomUserDetails) authentication.getPrincipal();

            // Step 2: Generate access + refresh tokens
            String accessToken = jwtService.generateAccessToken(userDetails);
            String refreshToken = jwtService.generateRefreshToken(userDetails);

            // Step 3: Save refresh token in database
            refreshTokenService.saveRefreshTokenService(userDetails.getUser(), refreshToken);

            // Step 4: Return both tokens
            return new LoginResponseDTO(
                    "Login successful",
                    userDetails.getUsername(),
                    userDetails.getUser().getRole().getRoleName(),
                    accessToken,
                    refreshToken
            );

        } catch (BadCredentialsException ex) {
            throw new InvalidCredentialsException();
        }
    }

    @Override
    public LoginResponseDTO refreshToken(RefreshTokenRequestDTO request) {

        // Step 1: Validate refresh token in database
        RefreshToken storedToken =
                refreshTokenService.validateRefreshToken(request.getRefreshToken());

        User user = storedToken.getUser();

        // Step 2: Load user details
        CustomUserDetails userDetails =
                (CustomUserDetails) userDetailsService.loadUserByUsername(user.getUsername());

        // Step 3: Validate JWT refresh token
        if (!jwtService.isRefreshTokenValid(request.getRefreshToken(), userDetails)) {
            throw new InvalidRefreshTokenException();
        }

        // Step 4: Generate new access token
        String newAccessToken = jwtService.generateAccessToken(userDetails);

        // Step 5: Return new access token (same refresh token)
        return new LoginResponseDTO(
                "Token refreshed successfully",
                userDetails.getUsername(),
                userDetails.getUser().getRole().getRoleName(),
                newAccessToken,
                request.getRefreshToken()
        );
    }

    @Override
    public void logout(RefreshTokenRequestDTO request) {
        refreshTokenService.revokeRefreshToken(request.getRefreshToken());
    }
}
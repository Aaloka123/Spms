package com.spms.service;

import com.spms.dto.request.LoginRequestDTO;
import com.spms.dto.request.RefreshTokenRequestDTO;
import com.spms.dto.response.LoginResponseDTO;

public interface AuthService {

    LoginResponseDTO login(LoginRequestDTO loginRequestDTO);

    LoginResponseDTO refreshToken(RefreshTokenRequestDTO request);

    void logout(RefreshTokenRequestDTO request);
}
package com.spms.service;

import com.spms.dto.request.LoginRequestDTO;
import com.spms.dto.response.LoginResponseDTO;

public interface AuthService {

    LoginResponseDTO login(LoginRequestDTO loginRequestDTO);
}

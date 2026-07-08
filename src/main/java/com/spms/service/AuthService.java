package com.spms.service;

import com.spms.dto.request.LoginRequestDTO;
import com.spms.dto.response.LoginResponseDTO;

public interface AuthService {

    //Authenticates user and returns the login response
    LoginResponseDTO login(LoginRequestDTO loginRequestDTO);
}

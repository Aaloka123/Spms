package com.spms.controller;

import com.spms.constants.ApiPath;
import com.spms.dto.request.LoginRequestDTO;
import com.spms.dto.response.LoginResponseDTO;
import com.spms.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiPath.AUTH)
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(
            @Valid @RequestBody LoginRequestDTO loginRequestDTO) {

        LoginResponseDTO response = authService.login(loginRequestDTO);

        return ResponseEntity.ok(response);
    }
}

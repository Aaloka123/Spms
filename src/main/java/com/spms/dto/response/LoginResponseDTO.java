package com.spms.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDTO {

    private String message;

    private String username;

    private String role;

    private String accessToken; //short lives

    private String refreshToken;//long lived
}

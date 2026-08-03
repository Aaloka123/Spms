package com.spms.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserResponseDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private String username;

    private String email;

    private String phoneNumber;

    private String address;

    private Boolean enabled;

    private String roleName;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
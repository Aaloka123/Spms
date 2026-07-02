package com.spms.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//incoming request data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleRequestDTO {

    // Role name is mandatory and cannot exceed 100 characters
    @NotBlank(message = "Role name is required.")
    @Size(max = 100, message = "Role name must not exceed 100 characters.")
    private String roleName;

    // Optional description with maximum length of 255 characters
    @Size(max = 255, message = "Description must not exceed 255 characters.")
    private String description;
}
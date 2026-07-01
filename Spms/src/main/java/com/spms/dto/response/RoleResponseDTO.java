package com.spms.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleResponseDTO {

    // Unique identifier of the role
    private Long roleId;

    // Name of the role
    private String roleName;

    // Description of the role
    private String description;

    // Date and time when the role was created
    private LocalDateTime createdAt;
}
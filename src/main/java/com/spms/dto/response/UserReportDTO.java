package com.spms.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// User report data shown to ADMIN
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserReportDTO {

    private Long userId;
    private String username;
    private String fullName;
    private String email;
    private String role;
    private Boolean enabled;
}
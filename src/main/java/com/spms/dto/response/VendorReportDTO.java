package com.spms.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Vendor report data shown to ADMIN
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VendorReportDTO {

    private Long vendorId;
    private String vendorName;
    private String contactPerson;
    private String email;
    private String phoneNumber;
    private Boolean isActive;
    private Long createdBy;
}
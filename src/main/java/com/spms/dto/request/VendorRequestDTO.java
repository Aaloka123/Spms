package com.spms.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Incoming vendor data from client
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VendorRequestDTO {

    @NotBlank(message = "Vendor name is required.")
    @Size(max = 100, message = "Vendor name cannot exceed 100 characters.")
    private String vendorName;

    @NotBlank(message = "Contact person is required.")
    @Size(max = 100, message = "Contact person cannot exceed 100 characters.")
    private String contactPerson;

    @NotBlank(message = "Email is required.")
    @Email(message = "Email must be valid.")
    private String email;

    @NotBlank(message = "Phone number is required.")
    @Size(max = 20, message = "Phone number cannot exceed 20 characters.")
    private String phoneNumber;

    @NotBlank(message = "Address is required.")
    @Size(max = 255, message = "Address cannot exceed 255 characters.")
    private String address;

    @Size(max = 500, message = "Description cannot exceed 500 characters.")
    private String description;
}
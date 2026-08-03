package com.spms.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Vendor send data to the client
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VendorResponseDTO {

    private Long id ;
    private String vendorName;
    private String contactPerson;
    private String email;
    private String phoneNumber;
    private String address;
    private String description;
    private String isActive;
    private Long createdBy;


}

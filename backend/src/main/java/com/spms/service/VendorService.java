package com.spms.service;

import com.spms.dto.request.VendorRequestDTO;
import com.spms.dto.response.VendorResponseDTO;

import java.util.List;

// Service methods for Vendor CRUD
public interface VendorService {

    // Create a new vendor
    VendorResponseDTO createVendor(VendorRequestDTO requestDTO);

    // Get all vendors
    List<VendorResponseDTO> getAllVendors();

    // Get one vendor by id
    VendorResponseDTO getVendorById(Long id);

    // Update vendor by id
    VendorResponseDTO updateVendor(Long id, VendorRequestDTO requestDTO);

    // Delete vendor by id
    void deleteVendor(Long id);
}
package com.spms.controller;

import com.spms.constants.ApiPath;
import com.spms.dto.request.VendorRequestDTO;
import com.spms.dto.response.VendorResponseDTO;
import com.spms.service.VendorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// REST APIs for Vendor module
@RestController
@RequestMapping(ApiPath.VENDORS) // /api/vendors
@RequiredArgsConstructor
public class VendorController {

    private final VendorService vendorService;

    // Create vendor
    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','PHARMACIST')")
    public ResponseEntity<VendorResponseDTO> createVendor(
            @Valid @RequestBody VendorRequestDTO requestDTO) {

        VendorResponseDTO response = vendorService.createVendor(requestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Get all vendors
    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','PHARMACIST')")
    public ResponseEntity<List<VendorResponseDTO>> getAllVendors() {
        return ResponseEntity.ok(vendorService.getAllVendors());
    }

    // Get vendor by id
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','PHARMACIST')")
    public ResponseEntity<VendorResponseDTO> getVendorById(@PathVariable Long id) {
        return ResponseEntity.ok(vendorService.getVendorById(id));
    }

    // Update vendor
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<VendorResponseDTO> updateVendor(
            @PathVariable Long id,
            @Valid @RequestBody VendorRequestDTO requestDTO) {

        return ResponseEntity.ok(vendorService.updateVendor(id, requestDTO));
    }

    // Delete vendor
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> deleteVendor(@PathVariable Long id) {
        vendorService.deleteVendor(id);
        return ResponseEntity.ok("Vendor deleted successfully.");
    }
}
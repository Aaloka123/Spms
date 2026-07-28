package com.spms.service.impl;

import com.spms.app.entity.Vendor;
import com.spms.app.repository.VendorRepository;
import com.spms.dto.request.VendorRequestDTO;
import com.spms.dto.response.VendorResponseDTO;
import com.spms.exception.VendorAlreadyExistsException;
import com.spms.exception.VendorNotFoundException;
import com.spms.mapper.VendorMapper;
import com.spms.security.custom.CustomUserDetails;
import com.spms.service.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// Implements vendor business logic
@Service
@RequiredArgsConstructor
@Transactional(transactionManager = "appTransactionManager")
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;
    private final VendorMapper vendorMapper;

    @Override
    public VendorResponseDTO createVendor(VendorRequestDTO requestDTO) {

        // Check duplicates
        if (vendorRepository.existsByVendorName(requestDTO.getVendorName())) {
            throw new VendorAlreadyExistsException(
                    "Vendor already exists with name: " + requestDTO.getVendorName());
        }
        if (vendorRepository.existsByEmail(requestDTO.getEmail())) {
            throw new VendorAlreadyExistsException(
                    "Vendor already exists with email: " + requestDTO.getEmail());
        }
        if (vendorRepository.existsByPhoneNumber(requestDTO.getPhoneNumber())) {
            throw new VendorAlreadyExistsException(
                    "Vendor already exists with phone: " + requestDTO.getPhoneNumber());
        }

        // Convert DTO to entity
        Vendor vendor = vendorMapper.toEntity(requestDTO);
        vendor.setIsActive(true);
        vendor.setCreatedBy(getCurrentUserId()); // save auth user id as Long

        // Save and return
        Vendor saved = vendorRepository.save(vendor);
        return vendorMapper.toResponseDTO(saved);
    }

    @Override
    public List<VendorResponseDTO> getAllVendors() {
        return vendorMapper.toResponseDTOList(vendorRepository.findAll());
    }

    @Override
    public VendorResponseDTO getVendorById(Long id) {
        Vendor vendor = vendorRepository.findById(id)
                .orElseThrow(() -> new VendorNotFoundException(
                        "Vendor not found with id: " + id));
        return vendorMapper.toResponseDTO(vendor);
    }

    @Override
    public VendorResponseDTO updateVendor(Long id, VendorRequestDTO requestDTO) {
        Vendor existing = vendorRepository.findById(id)
                .orElseThrow(() -> new VendorNotFoundException(
                        "Vendor not found with id: " + id));

        // Check duplicates for other vendors
        if (vendorRepository.existsByVendorNameAndIdNot(requestDTO.getVendorName(), id)) {
            throw new VendorAlreadyExistsException(
                    "Vendor already exists with name: " + requestDTO.getVendorName());
        }
        if (vendorRepository.existsByEmailAndIdNot(requestDTO.getEmail(), id)) {
            throw new VendorAlreadyExistsException(
                    "Vendor already exists with email: " + requestDTO.getEmail());
        }
        if (vendorRepository.existsByPhoneNumberAndIdNot(requestDTO.getPhoneNumber(), id)) {
            throw new VendorAlreadyExistsException(
                    "Vendor already exists with phone: " + requestDTO.getPhoneNumber());
        }

        // Update fields
        vendorMapper.updateEntityFromDTO(requestDTO, existing);
        Vendor updated = vendorRepository.save(existing);
        return vendorMapper.toResponseDTO(updated);
    }

    @Override
    public void deleteVendor(Long id) {
        Vendor vendor = vendorRepository.findById(id)
                .orElseThrow(() -> new VendorNotFoundException(
                        "Vendor not found with id: " + id));
        vendorRepository.delete(vendor);
    }

    // Get logged-in user id from JWT/security context
    private Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null
                && authentication.getPrincipal() instanceof CustomUserDetails userDetails) {
            return userDetails.getUserId();
        }
        return null;
    }
}
package com.spms.app.repository;

import com.spms.app.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Handles database operations for Vendor
@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {

    // Check if vendor name already exists (for create)
    boolean existsByVendorName(String vendorName);

    // Check if vendor name exists for another vendor (for update)
    boolean existsByVendorNameAndIdNot(String vendorName, Long id);

    // Check if email already exists (for create)
    boolean existsByEmail(String email);

    // Check if email exists for another vendor (for update)
    boolean existsByEmailAndIdNot(String email, Long id);

    // Check if phone number already exists (for create)
    boolean existsByPhoneNumber(String phoneNumber);

    // Check if phone number exists for another vendor (for update)
    boolean existsByPhoneNumberAndIdNot(String phoneNumber, Long id);
}
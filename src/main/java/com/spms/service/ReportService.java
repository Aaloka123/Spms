package com.spms.service;

import com.spms.dto.response.UserReportDTO;
import com.spms.dto.response.VendorReportDTO;

import java.util.List;

// Service methods for Admin reports
public interface ReportService {

    // Get report for all users
    List<UserReportDTO> getUserReports();

    // Get report for one user
    UserReportDTO getUserReportById(Long userId);

    // Get report for all vendors
    List<VendorReportDTO> getVendorReports();

    // Get report for one vendor
    VendorReportDTO getVendorReportById(Long vendorId);
}
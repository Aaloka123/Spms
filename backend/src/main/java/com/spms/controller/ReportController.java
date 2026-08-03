package com.spms.controller;

import com.spms.constants.ApiPath;
import com.spms.dto.response.UserReportDTO;
import com.spms.dto.response.VendorReportDTO;
import com.spms.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// Admin report APIs for users and vendors
@RestController
@RequestMapping(ApiPath.REPORTS) // /api/reports
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')") // only ADMIN can access
public class ReportController {

    private final ReportService reportService;

    // Get all user reports
    @GetMapping("/users")
    public ResponseEntity<List<UserReportDTO>> getUserReports() {
        return ResponseEntity.ok(reportService.getUserReports());
    }

    // Get one user report by id
    @GetMapping("/users/{id}")
    public ResponseEntity<UserReportDTO> getUserReportById(@PathVariable Long id) {
        return ResponseEntity.ok(reportService.getUserReportById(id));
    }

    // Get all vendor reports
    @GetMapping("/vendors")
    public ResponseEntity<List<VendorReportDTO>> getVendorReports() {
        return ResponseEntity.ok(reportService.getVendorReports());
    }

    // Get one vendor report by id
    @GetMapping("/vendors/{id}")
    public ResponseEntity<VendorReportDTO> getVendorReportById(@PathVariable Long id) {
        return ResponseEntity.ok(reportService.getVendorReportById(id));
    }
}
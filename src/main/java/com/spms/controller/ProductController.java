package com.spms.controller;

import com.spms.constants.ApiPath;
import com.spms.dto.request.ProductRequestDTO;
import com.spms.dto.response.ProductResponseDTO;
import com.spms.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPath.PRODUCTS)
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // Create a new product
    //Admin and pharmacist can create product
    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','PHARMACIST')")
    public ResponseEntity<ProductResponseDTO> createProduct(
            @Valid @RequestBody ProductRequestDTO requestDTO) {

        ProductResponseDTO response = productService.createProduct(requestDTO);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Get all products
    //Anyone can view the product
    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','PHARMACIST','USER')")
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {

        return ResponseEntity.ok(productService.getAllProducts());
    }

    // Get product by ID
    //anyone can view product details
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','PHARMACIST','USER')")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable Long id) {

        return ResponseEntity.ok(productService.getProductById(id));
    }

    // Update product
    //Admin and pharmacist can update product
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','PHARMACIST')")
    public ResponseEntity<ProductResponseDTO> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequestDTO requestDTO) {

        return ResponseEntity.ok(productService.updateProduct(id, requestDTO));
    }

    // Delete product
    //Admin and pharmacist can delete product
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','PHARMACIST')")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {

        productService.deleteProduct(id);

        return ResponseEntity.ok("Product deleted successfully.");
    }

}
package com.spms.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter // Generates getters for all fields
@Setter // Generates setters for all fields
@NoArgsConstructor // Generates a no-argument constructor
@AllArgsConstructor // Generates a constructor with all fields
@Entity // Marks this class as a JPA entity
@Table(name = "products") // Maps this entity to the "products" table
public class Product {

    @Id // Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment ID
    private Long id;

    @NotBlank(message = "Product name is required.")
    @Column(name = "product_name", nullable = false, unique = true, length = 100)
    private String productName;

    @NotBlank(message = "Generic name is required.")
    @Column(name = "generic_name", nullable = false, length = 100)
    private String genericName;

    @NotBlank(message = "Brand name is required.")
    @Column(nullable = false, length = 100)
    private String brand;

    @NotBlank(message = "Dosage form is required.")
    @Column(name = "dosage_form", nullable = false, length = 50)
    private String dosageForm;

    @NotBlank(message = "Strength is required.")
    @Column(nullable = false, length = 50)
    private String strength;

    @DecimalMin(value = "0.0", inclusive = false, message = "Purchase price must be greater than zero.")
    @Column(name = "purchase_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal purchasePrice;

    @DecimalMin(value = "0.0", inclusive = false, message = "Selling price must be greater than zero.")
    @Column(name = "selling_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal sellingPrice;

    @Min(value = 0, message = "Stock quantity cannot be negative.")
    @Column(name = "stock_quantity", nullable = false)
    private Integer stockQuantity;

    @Min(value = 0, message = "Reorder level cannot be negative.")
    @Column(name = "reorder_level", nullable = false)
    private Integer reorderLevel;

    @Column(name = "expiry_date", nullable = false)
    private LocalDate expiryDate;

    @Column(length = 500)
    private String description;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true; // Product is active by default

    @CreationTimestamp // Automatically stores record creation time
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp // Automatically updates record modification time
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
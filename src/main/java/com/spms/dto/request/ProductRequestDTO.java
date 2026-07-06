package com.spms.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDTO {

    @NotBlank(message = "Product name is required.")
    private String productName;

    @NotBlank(message = "Generic name is required.")
    private String genericName;

    @NotBlank(message = "Brand name is required.")
    private String brand;

    @NotBlank(message = "Dosage form is required.")
    private String dosageForm;

    @NotBlank(message = "Strength is required.")
    private String strength;

    @NotNull(message = "Purchase price is required.")
    @DecimalMin(value = "0.01", message = "Purchase price must be greater than zero.")
    private BigDecimal purchasePrice;

    @NotNull(message = "Selling price is required.")
    @DecimalMin(value = "0.01", message = "Selling price must be greater than zero.")
    private BigDecimal sellingPrice;

    @NotNull(message = "Stock quantity is required.")
    @Min(value = 0, message = "Stock quantity cannot be negative.")
    private Integer stockQuantity;

    @NotNull(message = "Reorder level is required.")
    @Min(value = 0, message = "Reorder level cannot be negative.")
    private Integer reorderLevel;

    @NotNull(message = "Expiry date is required.")
    @Future(message = "Expiry date must be a future date.")
    private LocalDate expiryDate;

    private String description;
}
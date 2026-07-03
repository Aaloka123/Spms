package com.spms.repository;

import com.spms.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//Repository interface for Product entity.
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Check if a product exists by product name
    boolean existsByProductName(String productName);

    // Check product name excluding current product
    boolean existsByProductNameAndIdNot(String productName, Long id);

    // Find product by product name
    Optional<Product> findByProductName(String productName);

}
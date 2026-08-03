package com.spms.exception;

//Exception thrown when a product with the same name already exists.
public class ProductAlreadyExistsException extends RuntimeException {

    public ProductAlreadyExistsException(String productName) {
        super("Product already exists with name: " + productName);
    }

}
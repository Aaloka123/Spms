package com.spms.exception;

// Thrown when vendor id is not found
public class VendorNotFoundException extends RuntimeException {

    public VendorNotFoundException(String message) {
        super(message);
    }
}
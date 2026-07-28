package com.spms.exception;

// Thrown when vendor name/email/phone already exists
public class VendorAlreadyExistsException extends RuntimeException {

    public VendorAlreadyExistsException(String message) {
        super(message);
    }
}
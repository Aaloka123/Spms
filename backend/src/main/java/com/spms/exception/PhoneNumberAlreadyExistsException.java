package com.spms.exception;

public class PhoneNumberAlreadyExistsException extends RuntimeException {

    public PhoneNumberAlreadyExistsException(String phoneNumber) {
        super("Phone number '" + phoneNumber + "' already exists.");
    }
}
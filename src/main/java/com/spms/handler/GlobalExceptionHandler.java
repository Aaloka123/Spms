package com.spms.handler;

import com.spms.exception.EmailAlreadyExistsException;
import com.spms.exception.PhoneNumberAlreadyExistsException;
import com.spms.exception.RoleNotFoundException;
import com.spms.exception.UserNotFoundException;
import com.spms.exception.UsernameAlreadyExistsException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice // Handles exceptions globally for all controllers
public class GlobalExceptionHandler {

    // Handles UserNotFoundException
    @ExceptionHandler(UserNotFoundException.class)
    public ProblemDetail handleUserNotFound(UserNotFoundException ex,
                                            HttpServletRequest request) {

        // Create ProblemDetail object with HTTP 404 status
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);

        // Set short title of the error
        problemDetail.setTitle("User Not Found");

        // Set detailed error message
        problemDetail.setDetail(ex.getMessage());

        // Set request URI where the error occurred
        problemDetail.setInstance(java.net.URI.create(request.getRequestURI()));

        // Add custom properties
        problemDetail.setProperty("timestamp", LocalDateTime.now());

        // Return ProblemDetail
        return problemDetail;
    }

    // Handles RoleNotFoundException
    @ExceptionHandler(RoleNotFoundException.class)
    public ProblemDetail handleRoleNotFound(RoleNotFoundException ex,
                                            HttpServletRequest request) {

        // Create ProblemDetail object with HTTP 404 status
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);

        // Set short title of the error
        problemDetail.setTitle("Role Not Found");

        // Set detailed error message
        problemDetail.setDetail(ex.getMessage());

        // Set request URI where the error occurred
        problemDetail.setInstance(java.net.URI.create(request.getRequestURI()));

        // Add custom properties
        problemDetail.setProperty("timestamp", LocalDateTime.now());

        // Return ProblemDetail
        return problemDetail;
    }

    // Handles UsernameAlreadyExistsException
    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ProblemDetail handleUsernameAlreadyExists(UsernameAlreadyExistsException ex,
                                                     HttpServletRequest request) {

        // Create ProblemDetail object with HTTP 409 status
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.CONFLICT);

        // Set short title of the error
        problemDetail.setTitle("Username Already Exists");

        // Set detailed error message
        problemDetail.setDetail(ex.getMessage());

        // Set request URI where the error occurred
        problemDetail.setInstance(java.net.URI.create(request.getRequestURI()));

        // Add custom properties
        problemDetail.setProperty("timestamp", LocalDateTime.now());

        // Return ProblemDetail
        return problemDetail;
    }

    // Handles EmailAlreadyExistsException
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ProblemDetail handleEmailAlreadyExists(EmailAlreadyExistsException ex,
                                                  HttpServletRequest request) {

        // Create ProblemDetail object with HTTP 409 status
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.CONFLICT);

        // Set short title of the error
        problemDetail.setTitle("Email Already Exists");

        // Set detailed error message
        problemDetail.setDetail(ex.getMessage());

        // Set request URI where the error occurred
        problemDetail.setInstance(java.net.URI.create(request.getRequestURI()));

        // Add custom properties
        problemDetail.setProperty("timestamp", LocalDateTime.now());

        // Return ProblemDetail
        return problemDetail;
    }

    // Handles PhoneNumberAlreadyExistsException
    @ExceptionHandler(PhoneNumberAlreadyExistsException.class)
    public ProblemDetail handlePhoneNumberAlreadyExists(PhoneNumberAlreadyExistsException ex,
                                                        HttpServletRequest request) {

        // Create ProblemDetail object with HTTP 409 status
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.CONFLICT);

        // Set short title of the error
        problemDetail.setTitle("Phone Number Already Exists");

        // Set detailed error message
        problemDetail.setDetail(ex.getMessage());

        // Set request URI where the error occurred
        problemDetail.setInstance(java.net.URI.create(request.getRequestURI()));

        // Add custom properties
        problemDetail.setProperty("timestamp", LocalDateTime.now());

        // Return ProblemDetail
        return problemDetail;
    }

    // Handles validation errors (@Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidationErrors(MethodArgumentNotValidException ex,
                                                HttpServletRequest request) {

        // Create ProblemDetail object with HTTP 400 status
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);

        // Set short title of the error
        problemDetail.setTitle("Validation Failed");

        // Set general error message
        problemDetail.setDetail("One or more fields contain invalid values.");

        // Set request URI where the error occurred
        problemDetail.setInstance(java.net.URI.create(request.getRequestURI()));

        // Store validation errors
        Map<String, String> errors = new HashMap<>();

        // Extract field name + error message
        ex.getBindingResult().getAllErrors().forEach(error -> {

            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();

            errors.put(fieldName, message);
        });

        // Add validation errors as custom property
        problemDetail.setProperty("errors", errors);

        // Add timestamp
        problemDetail.setProperty("timestamp", LocalDateTime.now());

        // Return ProblemDetail
        return problemDetail;
    }

    // Handles all unexpected exceptions (Fallback)
    @ExceptionHandler(Exception.class)
    public ProblemDetail handleGenericException(Exception ex,
                                                HttpServletRequest request) {

        // Create ProblemDetail object with HTTP 500 status
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        // Set short title of the error
        problemDetail.setTitle("Internal Server Error");

        // Set detailed error message
        problemDetail.setDetail(ex.getMessage());

        // Set request URI where the error occurred
        problemDetail.setInstance(java.net.URI.create(request.getRequestURI()));

        // Add timestamp
        problemDetail.setProperty("timestamp", LocalDateTime.now());

        // Return ProblemDetail
        return problemDetail;
    }
}
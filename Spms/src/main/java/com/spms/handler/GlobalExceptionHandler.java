package com.spms.handler;

import com.spms.dto.response.ErrorResponse;
import com.spms.exception.RoleNotFoundException;
import com.spms.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.validation.FieldError;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice // Handles exceptions globally for all controllers
public class GlobalExceptionHandler {

    // Handles UserNotFoundException
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFound(UserNotFoundException ex) {

        // Create standard error response
        ErrorResponse error = new ErrorResponse(
                ex.getMessage(),                 // Error message
                HttpStatus.NOT_FOUND.value(),   // 404 status code
                LocalDateTime.now()             // Timestamp
        );

        // Return response with HTTP 404
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // Handles RoleNotFoundException
    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleRoleNotFound(RoleNotFoundException ex) {

        // Create standard error response
        ErrorResponse error = new ErrorResponse(
                ex.getMessage(),                 // Error message
                HttpStatus.NOT_FOUND.value(),   // 404 status code
                LocalDateTime.now()             // Timestamp
        );

        // Return response with HTTP 404
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // Handles validation errors (@Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {

        // Store field errors in map
        Map<String, String> errors = new HashMap<>();

        // Extract field name + error message
        ex.getBindingResult().getAllErrors().forEach(error -> {

            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();

            errors.put(fieldName, message);
        });

        // Return 400 Bad Request with validation errors
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    // Handles all unexpected exceptions (Fallback)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {

        // Create generic error response
        ErrorResponse error = new ErrorResponse(
                ex.getMessage(),                              // Error message
                HttpStatus.INTERNAL_SERVER_ERROR.value(),     // 500 status code
                LocalDateTime.now()                           // Timestamp
        );

        // Return response with HTTP 500
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
package com.employee.exception;

/**
 * Custom Exception for Resource Not Found
 */
public class ResourceNotFoundException extends RuntimeException {
    
    /**
     * Constructor with message
     * @param message the error message
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructor with message and cause
     * @param message the error message
     * @param cause the cause of the exception
     */
    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

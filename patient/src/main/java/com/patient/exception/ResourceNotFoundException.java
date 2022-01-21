package com.patient.exception;

/**
 * The Class ResourceNotFoundException.
 */
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Instantiates a new resource not found exception.
     *
     * @param message the message
     */
    public ResourceNotFoundException(
    		final String message) {
        super(message);
    }
}

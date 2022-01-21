package com.patient.exception;

/**
 * The Class DataAlreadyRegisteredException.
 */
public class DataAlreadyRegisteredException extends RuntimeException {

    /**
     * Instantiates a new data already registered exception.
     *
     * @param message the message
     */
    public DataAlreadyRegisteredException(
    		final String message) {
        super(message);
    }
}

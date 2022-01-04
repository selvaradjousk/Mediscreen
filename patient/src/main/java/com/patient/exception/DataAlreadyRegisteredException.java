package com.patient.exception;

public class DataAlreadyRegisteredException extends RuntimeException {

    public DataAlreadyRegisteredException(
    		final String message) {
        super(message);
    }
}

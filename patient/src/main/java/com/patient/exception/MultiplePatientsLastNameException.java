package com.patient.exception;

/**
 * The Class MultiplePatientsLastNameException.
 */
public class MultiplePatientsLastNameException extends RuntimeException {

	    /**
    	 * Instantiates a new multiple patients last name exception.
    	 *
    	 * @param message the message
    	 */
    	public MultiplePatientsLastNameException(
	    		final String message) {
	        super(message);
	    }
}

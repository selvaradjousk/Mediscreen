package com.patientHistory.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * The Class GlobalExceptionHandler.
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    /**
     * Handle not found.
     *
     * @param ex the ex
     * @param request the request
     * @return the response entity
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity handleNotFound(
    		final ResourceNotFoundException ex,
    		final WebRequest request) {

    	ErrorDetails errorDetails = new ErrorDetails(
    			LocalDateTime.now(),
    			ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(
        		errorDetails, HttpStatus.NOT_FOUND);
    }
}

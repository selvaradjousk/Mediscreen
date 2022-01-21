package com.patient.exception;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * The Class GlobalExceptionHandler.
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    /**
     * Handle type mismatch.
     *
     * @param ex the ex
     * @param request the request
     * @return the response entity
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity handleTypeMismatch(
    		final MethodArgumentTypeMismatchException ex, final WebRequest request) {


        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),
                request.getDescription(false));
        errorDetails.setMessage("Invalid id");

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }


    /**
     * Handle not found.
     *
     * @param ex the ex
     * @param request the request
     * @return the response entity
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity handleNotFound(
    		final ResourceNotFoundException ex, final WebRequest request) {

        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    /**
     * Handle method argument not valid.
     *
     * @param ex the ex
     * @param header the header
     * @param status the status
     * @param request the request
     * @return the response entity
     */
    @Override
    public ResponseEntity handleMethodArgumentNotValid(
    		final MethodArgumentNotValidException ex,
    		final HttpHeaders header,
            final HttpStatus status, final WebRequest request) {

    	// Get the error messages for invalid fields
        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(", "));

        // Create ValidationErrorResponse object using error messages and request details
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), errorMessage, request.getDescription(false));

        return new ResponseEntity<Object>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}

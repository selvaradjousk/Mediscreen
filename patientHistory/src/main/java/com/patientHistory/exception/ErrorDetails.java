package com.patientHistory.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * The Class ErrorDetails.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {

    /** The timestamp. */
    private LocalDateTime timestamp;

    /** The message. */
    private String message;

    /** The details. */
    private String details;
}
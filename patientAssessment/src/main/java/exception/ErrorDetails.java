package exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
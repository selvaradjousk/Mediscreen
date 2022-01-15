package exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity handleTypeMismatch(
    		final MethodArgumentTypeMismatchException ex,
    		final WebRequest request) {

        ErrorDetails errorDetails = new ErrorDetails(
        		LocalDateTime.now(),
        		ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(
        		errorDetails,
        		HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity handleNotFound(
    		final ResourceNotFoundException ex,
    		final WebRequest request) {

        ErrorDetails errorDetails = new ErrorDetails(
        		LocalDateTime.now(),
        		ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(
        		errorDetails,
        		HttpStatus.NOT_FOUND);
    }
}
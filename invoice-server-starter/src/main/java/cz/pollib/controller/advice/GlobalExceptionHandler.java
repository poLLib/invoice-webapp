package cz.pollib.controller.advice;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.webjars.NotFoundException;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Global exception handler to handle validation and runtime exceptions.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles validation errors.
     *
     * @param ex the MethodArgumentNotValidException thrown when validation fails
     * @return ResponseEntity containing a map of error details and 400 status
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
        return new ResponseEntity<>(errors, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles entity not found exceptions and returns 404 status.
     */
    @ExceptionHandler({NotFoundException.class, EntityNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleEntityNotFoundException() {
    }

    /**
     * Handles runtime exceptions.
     *
     * @param ex the RuntimeException thrown when error occurred
     * @return ResponseEntity with the error message and 500 status
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        return new ResponseEntity<>("An unexpected error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handles when attempt to create an invoice with already existing invoiceNumber.
     *
     * @return ResponseEntity containing error message and 400 status
     */
    @ExceptionHandler(DuplicateInvoiceNumberException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleDuplicateInvoiceNumberException() {
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("invoiceNumber", "Faktura s tímto číslem již v databázi existuje");
        return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles when attempt to create a person with already existing identificationNumber.
     *
     * @return ResponseEntity containing error message and 400 status
     */
    @ExceptionHandler(DuplicateIdentificationNumberException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleDuplicateIdentificationNumberException() {
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("identificationNumber", "IČO již v databázi existuje");
        return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
    }
}
//TODO: rozdel response podle toho jestli jej posila person nebo invoice. nechcem v tele obe hlasky.


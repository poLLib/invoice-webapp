package cz.pollib.controller.advice;

import cz.pollib.constant.ErrorCode;
import cz.pollib.service.common.model.ErrorResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

import static cz.pollib.constant.ErrorCode.*;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * Global exception handler to handle validation and runtime exceptions.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Handles entity not found exceptions and returns 404 statuses.
     */
    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(
            EntityNotFoundException ex,
            HttpServletRequest request
                                                                      ) {
        ErrorResponse errorResponse = new ErrorResponse(
                ENTITY_NOT_FOUND,
                request.getRequestURI(),
                ex.getMessage() != null ? ex.getMessage() : "Entity not found"
        );
        logger.info(
                "Entity not found: {}",
                ex.getMessage()
                   );

        return ResponseEntity.status(NOT_FOUND)
                             .body(errorResponse);
    }

    /**
     * Handles runtime exceptions - fallback for unexpected errors.
     */
    @ExceptionHandler({RuntimeException.class, Exception.class})
    public ResponseEntity<ErrorResponse> handleRuntimeException(
            RuntimeException ex,
            HttpServletRequest request
                                                               ) {
        ErrorResponse errorResponse = new ErrorResponse(
                ErrorCode.INTERNAL_SERVER_ERROR,
                request.getRequestURI(),
                "An unexpected error occurred" + ex.getMessage()
        );
        logger.error(
                "Unexpected error: {}",
                ex.getMessage(),
                ex
                    );

        return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                             .body(errorResponse);
    }

    /**
     * Handles constraint validation.
     */
    @ExceptionHandler({ConstraintViolationException.class, DataIntegrityViolationException.class})
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(
            ConstraintViolationException ex,
            HttpServletRequest request
                                                                           ) {
        List<String> validationErrors = ex.getConstraintViolations()
                                          .stream()
                                          .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                                          .toList();

        logger.error(
                "Constraint validation failed for URI: {} - Errors: {}",
                request.getRequestURI(),
                validationErrors
                    );

        ErrorResponse errorResponse = new ErrorResponse(
                CONSTRAINT_VIOLATION,
                request.getRequestURI(),
                validationErrors
        );
        return ResponseEntity.status(BAD_REQUEST)
                             .body(errorResponse);
    }

    /**
     * Handles request body validation errors.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex,
            HttpServletRequest request
                                                                              ) {
        List<String> validationErrors = ex.getBindingResult()
                                          .getAllErrors()
                                          .stream()
                                          .map(error -> {
                                              if (error instanceof FieldError fieldError) {
                                                  return fieldError.getField() + ": " + fieldError.getDefaultMessage();
                                              }
                                              return error.getDefaultMessage();
                                          })
                                          .toList();

        logger.error(
                "Request body validation failed for URI: {} - Errors: {}",
                request.getRequestURI(),
                validationErrors
                    );

        ErrorResponse errorResponse = new ErrorResponse(
                VALIDATION_FAILED,
                request.getRequestURI(),
                validationErrors
        );
        return ResponseEntity.status(BAD_REQUEST)
                             .body(errorResponse);
    }
}

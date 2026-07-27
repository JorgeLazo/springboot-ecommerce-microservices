package com.jorgelazo.product.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jorgelazo.common.dto.ApiError;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleProductNotFoundException(MethodArgumentNotValidException ex, HttpServletRequest request) {


        Map<String, String> errors = new HashMap<>();
        
        ex.getBindingResult()
            .getFieldErrors()
            .forEach(error ->
                    errors.put(error.getField(), error.getDefaultMessage()));

        ApiError apiError = new ApiError(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                "Validation failed",
                request.getRequestURI()
        );

        apiError.setValidationErrors(errors);

        return ResponseEntity.badRequest().body(apiError);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ApiError> handleProductNotFoundException( ProductNotFoundException ex, HttpServletRequest request) {
    
        ApiError apiError = new ApiError(
            LocalDateTime.now(),
            HttpStatus.NOT_FOUND.value(),
            HttpStatus.NOT_FOUND.getReasonPhrase(),
            ex.getMessage(),
            request.getRequestURI()
        );
    
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
        
    }
    
}

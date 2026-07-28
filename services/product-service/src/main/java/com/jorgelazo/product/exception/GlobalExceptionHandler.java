package com.jorgelazo.product.exception;

import com.jorgelazo.common.dto.ApiError;
import com.jorgelazo.common.exception.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;




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

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleProductNotFoundException( ResourceNotFoundException ex, HttpServletRequest request) {
    
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

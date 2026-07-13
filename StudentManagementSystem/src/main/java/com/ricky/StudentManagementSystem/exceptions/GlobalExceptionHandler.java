package com.ricky.StudentManagementSystem.exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ricky.StudentManagementSystem.dtos.response_dtos.ExceptionResponseDTO;
import com.ricky.StudentManagementSystem.dtos.response_dtos.ValidationExceptionResponseDTO;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // For handling resource not found exception
    // this comes under runtime exception but there is no built in exception for it
    // so whaetever we created ResourceNotFoundException.java will use that here
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponseDTO> handleResourceNotFoundException(
            ResourceNotFoundException ex,
            HttpServletRequest request) {

        ExceptionResponseDTO exceptionResponse = new ExceptionResponseDTO(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exceptionResponse);
    }

    // For handling duplicate resources
    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ExceptionResponseDTO> handleDuplicateResourceException(DuplicateResourceException ex,
            HttpServletRequest request) {

        ExceptionResponseDTO exceptionResponse = new ExceptionResponseDTO(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI());

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(exceptionResponse);
    }

    // For handling input validation error thrown by spring boot validation package
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationExceptionResponseDTO> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {

        Map<String, String> fieldErrors = new HashMap<>();
        
        // this gives list of map of error of each field
        // on that we ran loop
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> fieldErrors.put(error.getField(), error.getDefaultMessage()));

        ValidationExceptionResponseDTO exceptionResponse = new ValidationExceptionResponseDTO(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                "Validation Failed",
                request.getRequestURI(),
                fieldErrors);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exceptionResponse);
    }

    // for any runtime exception which may occur
    // Null point exception will also get handled here
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionResponseDTO> handleRuntimException(
            RuntimeException ex,
            HttpServletRequest request) {

        ExceptionResponseDTO exceptionResponse = new ExceptionResponseDTO(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI());

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exceptionResponse);
    }

    // Last Fallback
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponseDTO> handleGenericException(
            Exception ex,
            HttpServletRequest request) {

        ExceptionResponseDTO exceptionResponse = new ExceptionResponseDTO(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                "Something went wrong. Please try again later.",
                request.getRequestURI());

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exceptionResponse);
    }
}

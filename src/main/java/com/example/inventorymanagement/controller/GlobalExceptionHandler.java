package com.example.inventorymanagement.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        logger.error("An unexpected error occurred: {}", ex.getMessage(), ex);
        return new ResponseEntity<>("An unexpected error occurred. Please try again later.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        logger.warn("A bad request error occurred: {}", ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<String> handleException(Exception ex) {
//        logger.error("An unexpected error occurred: {}", ex.getMessage(), ex);
//        return ResponseEntity
//                .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .body("An unexpected error occurred. Please try again later.");
//    }
//
//    @ExceptionHandler(IllegalArgumentException.class)
//    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
//        logger.warn("A bad request error occurred: {}", ex.getMessage());
//        return ResponseEntity
//                .status(HttpStatus.BAD_REQUEST)
//                .body(ex.getMessage());
//    }
//
//    @ExceptionHandler(ConstraintViolationException.class)
//    public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException ex) {
//        logger.warn("Validation error: {}", ex.getMessage());
//        return ResponseEntity
//                .status(HttpStatus.BAD_REQUEST)
//                .body("Validation error: " + ex.getMessage());
//    }
//}

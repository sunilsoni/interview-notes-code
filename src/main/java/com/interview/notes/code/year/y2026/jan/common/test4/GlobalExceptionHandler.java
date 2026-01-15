package com.interview.notes.code.year.y2026.jan.common.test4;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> handleInsufficientFunds(IllegalStateException ex) {
        // Returns 409 Conflict or 400 Bad Request
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleInvalidInput(IllegalArgumentException ex) {
        // Returns 400 Bad Request
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
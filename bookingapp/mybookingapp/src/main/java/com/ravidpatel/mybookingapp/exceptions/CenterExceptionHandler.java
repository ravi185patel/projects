package com.ravidpatel.mybookingapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class CenterExceptionHandler {

    @ExceptionHandler(SeatAlreadyBookedException.class)
    public ResponseEntity<?> handleSeatAlreadyBooked(SeatAlreadyBookedException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT) // 409
                .body(Map.of(
                        "status", "FAILED",
                        "message", ex.getMessage()
                ));
    }

    @ExceptionHandler(SeatException.class)
    public ResponseEntity<String> handleSeatIssues(RuntimeException ex) {
        System.out.println(" in handleSeatIssues ");
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleBookingIssue(RuntimeException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_GATEWAY)
                .body(ex.getMessage());
    }
}

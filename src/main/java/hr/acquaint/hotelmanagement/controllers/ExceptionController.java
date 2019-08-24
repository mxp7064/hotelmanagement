package hr.acquaint.hotelmanagement.controllers;

import hr.acquaint.hotelmanagement.exceptions.BadArgumentIdException;
import hr.acquaint.hotelmanagement.exceptions.ErrorResponse;
import hr.acquaint.hotelmanagement.exceptions.HotelNotFoundException;
import hr.acquaint.hotelmanagement.exceptions.InvalidAvailableRoomsNumberException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(HotelNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(HotelNotFoundException ex) {
        return generateErrorResponse(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({BadArgumentIdException.class, InvalidAvailableRoomsNumberException.class})
    public ResponseEntity<ErrorResponse> handleBadArgumentId(RuntimeException ex) {
        return generateErrorResponse(ex, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<ErrorResponse> generateErrorResponse(Exception ex, HttpStatus status) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setError(ex.getMessage());
        errorResponse.setStatus(status.value());
        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.BAD_REQUEST.value());

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach((error) -> {
            String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        body.put("errors", errors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
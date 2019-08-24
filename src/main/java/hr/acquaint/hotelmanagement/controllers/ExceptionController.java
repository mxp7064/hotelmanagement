package hr.acquaint.hotelmanagement.controllers;

import hr.acquaint.hotelmanagement.exceptions.ErrorResponse;
import hr.acquaint.hotelmanagement.exceptions.HotelNotFoundException;
import hr.acquaint.hotelmanagement.exceptions.InvalidArgumentIdException;
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

/**
 * Global Exception controller which handles custom exceptions
 */
@ControllerAdvice
public class ExceptionController {

    /**
     * Handle HotelNotFoundException
     *
     * @param ex HotelNotFoundException to be handled
     * @return generated error response with http status 401
     */
    @ExceptionHandler(HotelNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(HotelNotFoundException ex) {
        return generateErrorResponse(ex, HttpStatus.NOT_FOUND);
    }

    /**
     * Handle InvalidArgumentIdException and InvalidAvailableRoomsNumberException
     *
     * @param ex exception to be handled
     * @return generated error response with http status 400
     */
    @ExceptionHandler({InvalidArgumentIdException.class, InvalidAvailableRoomsNumberException.class})
    public ResponseEntity<ErrorResponse> handleInvalidInput(RuntimeException ex) {
        return generateErrorResponse(ex, HttpStatus.BAD_REQUEST);
    }

    /**
     * Generate error response with timestamp, error message and status based on an exception
     *
     * @param ex     exception based on which to generate error response
     * @param status http status based on which to generate error response
     * @return generated error response
     */
    private ResponseEntity<ErrorResponse> generateErrorResponse(Exception ex, HttpStatus status) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setError(ex.getMessage());
        errorResponse.setStatus(status.value());
        return new ResponseEntity<>(errorResponse, status);
    }

    /**
     * Handle MethodArgumentNotValidException which is thrown when the http request input data doesn't pass validation
     *
     * @param ex MethodArgumentNotValidException to be handled
     * @return generated error response with timestamp, http status 400 and validation error messages for each field
     */
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
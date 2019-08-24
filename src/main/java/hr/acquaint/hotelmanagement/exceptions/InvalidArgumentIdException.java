package hr.acquaint.hotelmanagement.exceptions;

/**
 * Exception that is thrown when the client provides invalid argument id
 */
public class InvalidArgumentIdException extends RuntimeException {
    public InvalidArgumentIdException() {
        super("Provide valid id");
    }
}
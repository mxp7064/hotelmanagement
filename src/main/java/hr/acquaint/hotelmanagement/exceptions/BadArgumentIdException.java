package hr.acquaint.hotelmanagement.exceptions;

public class BadArgumentIdException extends RuntimeException {
    public BadArgumentIdException() {
        super("Provide correct id");
    }
}
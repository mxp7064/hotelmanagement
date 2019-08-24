package hr.acquaint.hotelmanagement.exceptions;

/**
 * Exception that is thrown when hotel is not found by its id
 */
public class HotelNotFoundException extends RuntimeException {
    public HotelNotFoundException(Long id) {
        super("Hotel id not found: " + id);
    }
}
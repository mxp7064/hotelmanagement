package hr.acquaint.hotelmanagement.exceptions;

public class HotelNotFoundException extends RuntimeException {
    public HotelNotFoundException(Long id) {
        super("Hotel id not found: " + id);
    }
}
package hr.acquaint.hotelmanagement.exceptions;

/**
 * Exception that is thrown when the client tries to set number of available rooms that is higher than total room number of the hotel
 */
public class InvalidAvailableRoomsNumberException extends RuntimeException {
    public InvalidAvailableRoomsNumberException(Long numRooms) {
        super("Number of available rooms must be less than or equal to total room number (" + numRooms + ")");
    }
}

package hr.acquaint.hotelmanagement.exceptions;

public class InvalidAvailableRoomsNumberException extends RuntimeException {
    public InvalidAvailableRoomsNumberException(Long numRooms) {
        super("Number of available rooms must be less than total room number (" + numRooms + ")");
    }
}

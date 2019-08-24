package hr.acquaint.hotelmanagement.datatransferobjects;

import javax.validation.constraints.*;
import java.io.Serializable;

public class HotelData implements Serializable {

    private long id;

    @NotBlank
    @Size(min = 1, max = 40)
    @Pattern(regexp = "^[A-Za-z0-9\\s\\&']*$")
    private String name;

    @NotBlank
    @Size(min = 1, max = 50)
    private String address;

    @Email
    private String email;

    @Min(10)
    @Max(5000)
    private long numRooms;

    @Min(0)
    @Max(5000)
    private long numAvailableRooms;

    @Min(1)
    @Max(5)
    private long numStars;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getNumRooms() {
        return numRooms;
    }

    public void setNumRooms(long numRooms) {
        this.numRooms = numRooms;
    }

    public long getNumAvailableRooms() {
        return numAvailableRooms;
    }

    public void setNumAvailableRooms(long numAvailableRooms) {
        this.numAvailableRooms = numAvailableRooms;
    }

    public long getNumStars() {
        return numStars;
    }

    public void setNumStars(long numStars) {
        this.numStars = numStars;
    }
}

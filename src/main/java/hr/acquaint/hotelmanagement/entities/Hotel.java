package hr.acquaint.hotelmanagement.entities;


import javax.persistence.*;

@Entity
@Table(name = "hotel")
public class Hotel extends BaseEntity {

    @Id
    @Column(name = "hotel_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "email")
    private String email;

    @Column(name = "num_rooms")
    private Long numRooms;

    @Column(name = "num_available_rooms")
    private Long numAvailableRooms;

    @Column(name = "num_stars")
    private Long numStars;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Long getNumRooms() {
        return numRooms;
    }

    public void setNumRooms(Long numRooms) {
        this.numRooms = numRooms;
    }

    public Long getNumAvailableRooms() {
        return numAvailableRooms;
    }

    public void setNumAvailableRooms(Long numAvailableRooms) {
        this.numAvailableRooms = numAvailableRooms;
    }

    public Long getNumStars() {
        return numStars;
    }

    public void setNumStars(Long numStars) {
        this.numStars = numStars;
    }
}

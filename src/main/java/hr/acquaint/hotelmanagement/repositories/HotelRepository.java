package hr.acquaint.hotelmanagement.repositories;

import hr.acquaint.hotelmanagement.entities.Hotel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HotelRepository extends CrudRepository<Hotel, Long>, HotelCustomRepository {
    Optional<Hotel> findByIdAndActive(Long id, boolean active);
}

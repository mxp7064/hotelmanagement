package hr.acquaint.hotelmanagement.repositories;

import hr.acquaint.hotelmanagement.entities.Hotel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Main hotel crud repository
 */
@Repository
public interface HotelRepository extends CrudRepository<Hotel, Long>, HotelCustomRepository {
    /**
     * Find hotel by id and active state
     *
     * @param id     hotel id
     * @param active active state
     * @return hotel optional object
     */
    Optional<Hotel> findByIdAndActive(Long id, boolean active);
}

package hr.acquaint.hotelmanagement.services;

import hr.acquaint.hotelmanagement.datatransferobjects.HotelData;
import hr.acquaint.hotelmanagement.datatransferobjects.SearchResult;
import hr.acquaint.hotelmanagement.entities.Hotel;
import hr.acquaint.hotelmanagement.exceptions.HotelNotFoundException;
import hr.acquaint.hotelmanagement.exceptions.InvalidArgumentIdException;
import hr.acquaint.hotelmanagement.exceptions.InvalidAvailableRoomsNumberException;
import hr.acquaint.hotelmanagement.repositories.HotelRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

/**
 * Hotel service class that handles hotel business logic and manipulates hotel data
 */
@Service
public class HotelService implements IHotelService {

    private final HotelRepository hotelRepository;
    private static final Long maxPageSize = 20L;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    /**
     * Get hotels by page number, page size and name
     *
     * @param page     page number
     * @param pageSize page size
     * @param name     hotel name
     * @return hotel data objects search result
     */
    @Override
    @Cacheable(value = "allHotelsCache", unless = "#result.data.size() == 0")
    public SearchResult<HotelData> getHotels(Long page, Long pageSize, String name) {
        if (pageSize == null || pageSize > maxPageSize || pageSize < 1) {
            pageSize = maxPageSize;
        }
        if (name == null) {
            name = "";
        }
        return hotelRepository.findAll(page, pageSize, name);
    }

    /**
     * Get hotel by id
     *
     * @param id hotel id
     * @return hotel data object requested
     * @throws HotelNotFoundException if hotel not found
     */
    @Override
    @Cacheable(value = "hotelCache", key = "#id")
    public HotelData getHotelById(Long id) {
        Hotel hotel = hotelRepository.findByIdAndActive(id, true).orElseThrow(() -> new HotelNotFoundException(id));
        return createHotelDataFromEntity(hotel);
    }

    /**
     * Create new hotel
     *
     * @param hotelData hotel data object for the new hotel
     * @return hotel data object of the newly created hotel
     */
    @Override
    @Caching(
            put = {@CachePut(value = "hotelCache", key = "#hotelData.id")},
            evict = {@CacheEvict(value = "allHotelsCache", allEntries = true)}
    )
    public HotelData createHotel(HotelData hotelData) {
        Hotel hotel = new Hotel();
        hotel.setActive(true);
        return saveHotel(hotel, hotelData);
    }

    /**
     * Update hotel
     *
     * @param hotelData hotel data object with new field values to be updated
     * @param id        hotel id of the hotel to be updated
     * @return updated hotel data object
     */
    @Override
    @Caching(
            put = {@CachePut(value = "hotelCache", key = "#id")},
            evict = {@CacheEvict(value = "allHotelsCache", allEntries = true)}
    )
    public HotelData updateHotel(HotelData hotelData, Long id) {
        Hotel hotel = hotelRepository.findByIdAndActive(id, true).orElseThrow(InvalidArgumentIdException::new);
        return saveHotel(hotel, hotelData);
    }

    /**
     * Delete hotel, note that this doesn't remove the record from the database, it's just a soft delete
     *
     * @param id hotel id
     */
    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = "hotelCache", key = "#id"),
                    @CacheEvict(value = "allHotelsCache", allEntries = true)
            }
    )
    public void deleteHotel(Long id) {
        Hotel hotel = hotelRepository.findByIdAndActive(id, true).orElseThrow(InvalidArgumentIdException::new);
        hotel.setActive(false);
        hotelRepository.save(hotel);
    }

    /**
     * Create hotel data transfer object from entity model object
     *
     * @param hotel hotel entity
     * @return hotel data object created from hotel entity model object
     */
    private HotelData createHotelDataFromEntity(Hotel hotel) {
        HotelData hotelData = new HotelData();
        hotelData.setId(hotel.getId());
        hotelData.setName(hotel.getName());
        hotelData.setAddress(hotel.getAddress());
        hotelData.setEmail(hotel.getEmail());
        hotelData.setNumRooms(hotel.getNumRooms());
        hotelData.setNumAvailableRooms(hotel.getNumAvailableRooms());
        hotelData.setNumStars(hotel.getNumStars());
        return hotelData;
    }

    /**
     * Save hotel entity mapped from hotel data transfer object
     *
     * @param hotel     hotel entity to be saved
     * @param hotelData hotel data object from which to populate hotel entity
     * @return new hotel data object mapped from saved hotel entity
     */
    private HotelData saveHotel(Hotel hotel, HotelData hotelData) {
        validateHotelData(hotelData);
        updateHotelEntity(hotel, hotelData);
        return createHotelDataFromEntity(hotelRepository.save(hotel));
    }

    /**
     * Validate hotel data transfer object
     *
     * @param hotelData hotel data object to be validated
     */
    private void validateHotelData(HotelData hotelData) {
        if (hotelData.getNumAvailableRooms() > hotelData.getNumRooms()) {
            throw new InvalidAvailableRoomsNumberException(hotelData.getNumRooms());
        }
    }

    /**
     * Update hotel entity by mapping from hotel data transfer object
     *
     * @param hotel     hotel entity to be updated
     * @param hotelData hotel data object from which to update hotel entity
     */
    private void updateHotelEntity(Hotel hotel, HotelData hotelData) {
        hotel.setName(hotelData.getName());
        hotel.setAddress(hotelData.getAddress());
        hotel.setEmail(hotelData.getEmail());
        hotel.setNumRooms(hotelData.getNumRooms());
        hotel.setNumAvailableRooms(hotelData.getNumAvailableRooms());
        hotel.setNumStars(hotelData.getNumStars());
    }
}

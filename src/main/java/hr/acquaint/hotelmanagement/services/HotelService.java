package hr.acquaint.hotelmanagement.services;

import hr.acquaint.hotelmanagement.datatransferobjects.HotelData;
import hr.acquaint.hotelmanagement.entities.Hotel;
import hr.acquaint.hotelmanagement.exceptions.BadArgumentIdException;
import hr.acquaint.hotelmanagement.exceptions.HotelNotFoundException;
import hr.acquaint.hotelmanagement.exceptions.InvalidAvailableRoomsNumberException;
import hr.acquaint.hotelmanagement.repositories.HotelRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService implements IHotelService {

    private final HotelRepository hotelRepository;
    private static final Long maxItemsPerPage = 20L;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    @Cacheable(value = "allHotelsCache", unless = "#result.size() == 0")
    public List<HotelData> getHotelsByPage(Long itemsPerPage, Long page) {
        if (itemsPerPage == null || itemsPerPage > maxItemsPerPage || itemsPerPage < 1) {
            itemsPerPage = maxItemsPerPage;
        }
        return hotelRepository.findAllByPage(itemsPerPage, page);
    }

    @Override
    @Cacheable(value = "hotelCache", key = "#id")
    public HotelData getHotelById(Long id) {
        Hotel hotel = hotelRepository.findByIdAndActive(id, true).orElseThrow(() -> new HotelNotFoundException(id));
        return createHotelDataFromEntity(hotel);
    }

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

    @Override
    @Caching(
            put = {@CachePut(value = "hotelCache", key = "#id")},
            evict = {@CacheEvict(value = "allHotelsCache", allEntries = true)}
    )
    public HotelData updateHotel(HotelData hotelData, Long id) {
        Hotel hotel = hotelRepository.findByIdAndActive(id, true).orElseThrow(BadArgumentIdException::new);
        return saveHotel(hotel, hotelData);
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = "hotelCache", key = "#id"),
                    @CacheEvict(value = "allHotelsCache", allEntries = true)
            }
    )
    public void deleteHotel(Long id) {
        Hotel hotel = hotelRepository.findByIdAndActive(id, true).orElseThrow(BadArgumentIdException::new);
        hotel.setActive(false);
        hotelRepository.save(hotel);
    }

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


    private HotelData saveHotel(Hotel hotel, HotelData hotelData) {
        validateHotelData(hotelData);
        updateHotelEntity(hotel, hotelData);
        return createHotelDataFromEntity(hotelRepository.save(hotel));
    }

    private void validateHotelData(HotelData hotelData) {
        if (hotelData.getNumAvailableRooms() > hotelData.getNumRooms()) {
            throw new InvalidAvailableRoomsNumberException(hotelData.getNumRooms());
        }
    }

    private void updateHotelEntity(Hotel hotel, HotelData hotelData) {
        hotel.setName(hotelData.getName());
        hotel.setAddress(hotelData.getAddress());
        hotel.setEmail(hotelData.getEmail());
        hotel.setNumRooms(hotelData.getNumRooms());
        hotel.setNumAvailableRooms(hotelData.getNumAvailableRooms());
        hotel.setNumStars(hotelData.getNumStars());
    }
}

package hr.acquaint.hotelmanagement.services;

import hr.acquaint.hotelmanagement.datatransferobjects.HotelData;
import hr.acquaint.hotelmanagement.entities.Hotel;
import hr.acquaint.hotelmanagement.exceptions.BadArgumentIdException;
import hr.acquaint.hotelmanagement.exceptions.HotelNotFoundException;
import hr.acquaint.hotelmanagement.exceptions.InvalidAvailableRoomsNumberException;
import hr.acquaint.hotelmanagement.repositories.HotelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {

    private final HotelRepository hotelRepository;
    private static final Long maxItemsPerPage = 20L;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public List<HotelData> getHotelsByPage(Long itemsPerPage, Long page) {
        if (itemsPerPage == null || itemsPerPage > maxItemsPerPage || itemsPerPage < 1) {
            itemsPerPage = maxItemsPerPage;
        }
        return hotelRepository.findAllByPage(itemsPerPage, page);
    }

    public HotelData getHotelById(Long id) {
        Hotel hotel = hotelRepository.findByIdAndActive(id, true).orElseThrow(() -> new HotelNotFoundException(id));
        return createHotelDataFromEntity(hotel);
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

    public HotelData createHotel(HotelData newHotel) {
        Hotel hotel = new Hotel();
        hotel.setActive(true);
        return saveHotel(hotel, newHotel);
    }

    public HotelData updateHotel(HotelData hotelData, Long id) {
        Hotel hotel = hotelRepository.findByIdAndActive(id, true).orElseThrow(BadArgumentIdException::new);
        return saveHotel(hotel, hotelData);
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

    private void updateHotelEntity(Hotel hotel, HotelData newHotel) {
        hotel.setName(newHotel.getName());
        hotel.setAddress(newHotel.getAddress());
        hotel.setEmail(newHotel.getEmail());
        hotel.setNumRooms(newHotel.getNumRooms());
        hotel.setNumAvailableRooms(newHotel.getNumAvailableRooms());
        hotel.setNumStars(newHotel.getNumStars());
    }

    public void deleteHotel(Long id) {
        Hotel hotel = hotelRepository.findByIdAndActive(id, true).orElseThrow(BadArgumentIdException::new);
        hotel.setActive(false);
        hotelRepository.save(hotel);
    }
}

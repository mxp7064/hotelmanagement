package hr.acquaint.hotelmanagement.services;

import hr.acquaint.hotelmanagement.datatransferobjects.HotelData;
import hr.acquaint.hotelmanagement.datatransferobjects.SearchResult;

/**
 * Hotel service interface abstraction
 */
public interface IHotelService {
    SearchResult<HotelData> getHotels(Long page, Long pageSize, String name);

    HotelData getHotelById(Long id);

    HotelData createHotel(HotelData hotelData);

    HotelData updateHotel(HotelData hotelData, Long id);

    void deleteHotel(Long id);
}

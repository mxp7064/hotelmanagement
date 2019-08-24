package hr.acquaint.hotelmanagement.services;

import hr.acquaint.hotelmanagement.datatransferobjects.HotelData;

import java.util.List;

/**
 * Hotel service interface abstraction
 */
public interface IHotelService {
    List<HotelData> getHotelsByPage(Long pageSize, Long page);

    HotelData getHotelById(Long id);

    HotelData createHotel(HotelData hotelData);

    HotelData updateHotel(HotelData hotelData, Long id);

    void deleteHotel(Long id);
}

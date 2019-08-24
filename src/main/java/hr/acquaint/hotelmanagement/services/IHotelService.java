package hr.acquaint.hotelmanagement.services;

import hr.acquaint.hotelmanagement.datatransferobjects.HotelData;

import java.util.List;

public interface IHotelService {
    List<HotelData> getHotelsByPage(Long itemsPerPage, Long page);
    HotelData getHotelById(Long id);
    HotelData createHotel(HotelData hotelData);
    HotelData updateHotel(HotelData hotelData, Long id);
    void deleteHotel(Long id);
}

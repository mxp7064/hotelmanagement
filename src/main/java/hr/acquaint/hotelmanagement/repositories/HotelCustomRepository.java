package hr.acquaint.hotelmanagement.repositories;

import hr.acquaint.hotelmanagement.datatransferobjects.HotelData;

import java.util.List;

public interface HotelCustomRepository {
    List<HotelData> findAllByPage(Long itemsPerPage, Long page);
}

package hr.acquaint.hotelmanagement.repositories;

import hr.acquaint.hotelmanagement.datatransferobjects.HotelData;

import java.util.List;

/**
 * Hotel custom repository, class can implement it to provide custom query implementation
 */
public interface HotelCustomRepository {
    /**
     * Find all hotels by page
     *
     * @param pageSize page size
     * @param page     page number
     * @return list of hotel data objects based on page size and page number
     */
    List<HotelData> findAllByPage(Long pageSize, Long page);
}

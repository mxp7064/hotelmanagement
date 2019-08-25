package hr.acquaint.hotelmanagement.repositories;

import hr.acquaint.hotelmanagement.datatransferobjects.HotelData;
import hr.acquaint.hotelmanagement.datatransferobjects.SearchResult;

/**
 * Hotel custom repository, class can implement it to provide custom query implementation
 */
public interface HotelCustomRepository {
    /**
     * Find all hotels by page number, page size and name
     *
     * @param page     page number
     * @param pageSize page size
     * @param name     hotel name
     * @return hotel data objects search result
     */
    SearchResult<HotelData> findAll(Long page, Long pageSize, String name);
}

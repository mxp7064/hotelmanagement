package hr.acquaint.hotelmanagement.repositories;

import hr.acquaint.hotelmanagement.datatransferobjects.HotelData;
import hr.acquaint.hotelmanagement.datatransferobjects.SearchResult;
import hr.acquaint.hotelmanagement.util.RepositoryUtil;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;

/**
 * Class that implements custom hotel queries
 */
public class HotelCustomRepositoryImpl implements HotelCustomRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public HotelCustomRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    /**
     * Implementation of find all hotels by page number, page size and name
     *
     * @param page     page number
     * @param pageSize page size
     * @param name     hotel name
     * @return hotel data objects search result
     */
    @Override
    public SearchResult<HotelData> findAll(Long page, Long pageSize, String name) {
        long offset = (page - 1) * pageSize;
        name = RepositoryUtil.prepareLikeContainsParameter(name);

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("name", name);
        parameters.addValue("limit", pageSize);
        parameters.addValue("offset", offset);

        SearchResult<HotelData> searchResult = new SearchResult<>();

        String sql = "WITH hotel_data\n" +
                "AS\n" +
                "(\n" +
                "    SELECT hotel_id,\n" +
                "           name,\n" +
                "           address,\n" +
                "           email,\n" +
                "           num_rooms,\n" +
                "           num_available_rooms,\n" +
                "           num_stars\n" +
                "    FROM hotel\n" +
                "    WHERE LOWER(name) LIKE :name\n" +
                "      AND active = true\n" +
                "),\n" +
                "hotel_count\n" +
                "AS\n" +
                "(\n" +
                "    SELECT COUNT(*) AS total_count\n" +
                "    FROM hotel_data\n" +
                ")\n" +
                "SELECT *\n" +
                "FROM hotel_data\n" +
                "         CROSS JOIN hotel_count\n" +
                "ORDER BY name\n" +
                "LIMIT :limit OFFSET :offset;";

        List<HotelData> hotelDataList = namedParameterJdbcTemplate.query(
                sql,
                parameters,
                (rs, rn) -> {
                    HotelData hotelData = new HotelData();
                    hotelData.setId(rs.getInt("hotel_id"));
                    hotelData.setName(rs.getString("name"));
                    hotelData.setAddress(rs.getString("address"));
                    hotelData.setEmail(rs.getString("email"));
                    hotelData.setNumRooms(rs.getInt("num_rooms"));
                    hotelData.setNumAvailableRooms(rs.getInt("num_available_rooms"));
                    hotelData.setNumStars(rs.getInt("num_stars"));
                    searchResult.setTotalCount(rs.getLong("total_count"));
                    return hotelData;
                });
        searchResult.setData(hotelDataList);
        return searchResult;
    }
}

package hr.acquaint.hotelmanagement.repositories;

import hr.acquaint.hotelmanagement.datatransferobjects.HotelData;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

/**
 * Class that implements custom hotel queries
 */
public class HotelCustomRepositoryImpl implements HotelCustomRepository {

    private final JdbcTemplate jdbcTemplate;

    public HotelCustomRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Implementation of the findAllByPage method that returns hotel objects based on page size and page number
     *
     * @param pageSize page size
     * @param page     page number
     * @return list of hotel data objects based on page size and page number
     */
    @Override
    public List<HotelData> findAllByPage(Long pageSize, Long page) {
        long offset = (page - 1) * pageSize;
        String sql = "SELECT hotel_id,\n" +
                "       name,\n" +
                "       address,\n" +
                "       email,\n" +
                "       num_rooms,\n" +
                "       num_available_rooms,\n" +
                "       num_stars\n" +
                "FROM hotel\n" +
                "WHERE active = true\n" +
                "ORDER BY name\n" +
                "LIMIT ? OFFSET ?;";
        return jdbcTemplate.query(
                sql,
                new Object[]{pageSize, offset},
                hotelViewModelMapper());
    }

    /**
     * Map result set to hotel data object
     *
     * @return hotel data row mapper
     */
    private RowMapper<HotelData> hotelViewModelMapper() {
        return (rs, rn) -> {
            HotelData hotelData = new HotelData();

            hotelData.setId(rs.getInt("hotel_id"));
            hotelData.setName(rs.getString("name"));
            hotelData.setAddress(rs.getString("address"));
            hotelData.setEmail(rs.getString("email"));
            hotelData.setNumRooms(rs.getInt("num_rooms"));
            hotelData.setNumAvailableRooms(rs.getInt("num_available_rooms"));
            hotelData.setNumStars(rs.getInt("num_stars"));

            return hotelData;
        };
    }
}

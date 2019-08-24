package hr.acquaint.hotelmanagement.repositories;

import hr.acquaint.hotelmanagement.datatransferobjects.HotelData;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

public class HotelCustomRepositoryImpl implements HotelCustomRepository {

    private final JdbcTemplate jdbcTemplate;

    public HotelCustomRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<HotelData> findAllByPage(Long itemsPerPage, Long page) {
        long offset = (page - 1) * itemsPerPage;
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
                new Object[]{itemsPerPage, offset},
                hotelViewModelMapper());
    }

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

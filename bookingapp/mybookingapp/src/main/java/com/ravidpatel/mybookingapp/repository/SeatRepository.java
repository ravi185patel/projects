package com.ravidpatel.mybookingapp.repository;

import com.ravidpatel.mybookingapp.entity.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SeatRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;


    public String save(Seat entity) {

        String sql = """
            INSERT INTO seat
            VALUES (:id, :screenId, :no, :type)
        """;

        Map<String, Object> params = Map.of(
                "id", entity.getSeatId(),
                "screenId", entity.getScreenId(),
                "no", entity.getSeatNo(),
                "type", entity.getSeatType()
        );

        int success = jdbcTemplate.update(sql, params);
        if (success > 0) {
            System.out.println("successfully seat added...!");
        } else {
            throw new RuntimeException(" Problem with seat addition...!");
        }
        return entity.getScreenId();
    }

    public Seat getById(String seatId) {
        String sql = "SELECT * FROM seat WHERE seat_id = :id";
        return jdbcTemplate.queryForObject(sql,Map.of("id", seatId),Seat.class);
    }
}

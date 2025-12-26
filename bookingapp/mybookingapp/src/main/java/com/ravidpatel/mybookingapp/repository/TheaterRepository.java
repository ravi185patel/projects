package com.ravidpatel.mybookingapp.repository;

import com.ravidpatel.mybookingapp.entity.Theater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class TheaterRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public void save(Theater entity) {

        String sql = """
            INSERT INTO theater
            VALUES (:id, :name, :city, :state, :pin)
        """;

        Map<String, Object> params = Map.of(
                "id", entity.getTheaterId(),
                "name", entity.getTheaterName(),
                "city", entity.getTheaterCity(),
                "state", entity.getTheaterState(),
                "pin", entity.getTheaterPinCode()
        );

        jdbcTemplate.update(sql, params);
    }

    public Theater getById(String theaterId) {
        String sql = "SELECT * FROM theater WHERE theater_id = :id";
        return jdbcTemplate.queryForObject(sql,Map.of("id", theaterId),Theater.class);
    }
}

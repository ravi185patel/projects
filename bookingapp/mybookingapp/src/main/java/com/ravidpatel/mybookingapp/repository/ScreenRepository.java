package com.ravidpatel.mybookingapp.repository;

import com.ravidpatel.mybookingapp.entity.Screen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Objects;

@Repository
public class ScreenRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public String save(Screen entity) {

        String sql = """
            INSERT INTO screen
            VALUES (:id, :theaterId, :no)
        """;

        Map<String, Object> params = Map.of(
                "id", entity.getScreenId(),
                "theaterId", entity.getTheaterId(),
                "no", entity.getScreenNo()
        );

        int success = jdbcTemplate.update(sql, params);
        if (success > 0) {
            System.out.println("successfully screen added...!");
        } else {
            throw new RuntimeException(" Problem with screen addition...!");
        }
        return entity.getScreenId();
    }

    public Screen getById(String screenId) {
        String sql = "SELECT * FROM screen WHERE screen_id = :id";
        Screen screen = jdbcTemplate.queryForObject(sql,Map.of("id", screenId),Screen.class);
        if(Objects.nonNull(screen)){
            throw new RuntimeException(" Screen not found ...!");
        }
        return screen;
    }
}

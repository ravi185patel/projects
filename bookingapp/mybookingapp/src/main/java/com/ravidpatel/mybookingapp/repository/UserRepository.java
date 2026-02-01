package com.ravidpatel.mybookingapp.repository;

import com.ravidpatel.mybookingapp.constant.UserSql;
import com.ravidpatel.mybookingapp.entity.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRepository {

    private final NamedParameterJdbcTemplate jdbc;

    public UserRepository(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void insertUser(User user) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", user.getUserId());
        params.put("userName", user.getUserName());
        params.put("email", user.getEmail());
        params.put("createdAt", Timestamp.valueOf(user.getCreatedAt()));

        jdbc.update(UserSql.INSERT_USER, params);
    }

    public User getUserById(String userId) {
        return jdbc.queryForObject(
                UserSql.FIND_BY_ID,
                Map.of("userId", userId),
                USER_ROW_MAPPER
        );
    }

    private static final RowMapper<User> USER_ROW_MAPPER = (rs, i) -> {
        User user = new User();
        user.setUserId(rs.getString("user_id"));
        user.setUserName(rs.getString("user_name"));
        user.setEmail(rs.getString("email"));
        user.setCreatedAt(
                rs.getTimestamp("created_at").toLocalDateTime()
        );
        return user;
    };
}

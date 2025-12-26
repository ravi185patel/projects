package com.ravidpatel.mybookingapp.repository;

import com.ravidpatel.mybookingapp.entity.Consumer;
import com.ravidpatel.mybookingapp.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
public class ConsumerRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    // SAVE
    public String saveConsumer(Consumer entity) {

        String sql = """
            INSERT INTO consumer
            (consumer_id, consumer_name, consumer_password, consumer_identity_no)
            VALUES (:id, :name, :password, :identity)
        """;

        Map<String, Object> params = new HashMap<>();
        params.put("id", entity.getConsumerId());
        params.put("name", entity.getConsumerName());
        params.put("password", entity.getConsumerPassword());
        params.put("identity", entity.getConsumerIdentityNo());

        int success = jdbcTemplate.update(sql, params);
        if (success > 0) {
            System.out.println("successfully user registered...!");
        } else {
            throw new RuntimeException(" Problem with user registration...!");
        }
        return entity.getConsumerId();
    }

    // GET BY ID
    public Consumer getConsumer(String consumerId) {

        String sql = """
            SELECT consumer_id, consumer_name, consumer_identity_no
            FROM consumer
            WHERE consumer_id = :id
        """;
        return jdbcTemplate.queryForObject(sql,Map.of("id", consumerId),Consumer.class);
    }

//    // AUTHENTICATE
//    public boolean authenticate(ConsumerAuthRequestDTO dto) {
//
//        String sql = """
//            SELECT consumer_password
//            FROM consumer
//            WHERE consumer_id = :id
//        """;
//
//        String hashedPassword = jdbcTemplate.queryForObject(
//                sql,
//                Map.of("id", dto.getConsumerId()),
//                String.class
//        );
//
//        return passwordEncoder.matches(dto.getPassword(), hashedPassword);
//    }
}

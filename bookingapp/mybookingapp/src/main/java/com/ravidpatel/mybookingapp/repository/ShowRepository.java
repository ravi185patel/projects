package com.ravidpatel.mybookingapp.repository;

import com.ravidpatel.mybookingapp.entity.Show;
import com.ravidpatel.mybookingapp.exceptions.SeatAlreadyBookedException;
import com.ravidpatel.mybookingapp.mapper.ShowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ShowRepository {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    static String sql = """
        INSERT INTO show
        (:show_id,movie_id, screen_id, show_date, from_time, to_time)
        VALUES (:show_id,:movieId, :screenId, :showDate, :fromTime, :toTime)
    """;


    static String sqlBatch = """
            INSERT INTO show
            (:show_id,movie_id, screen_id, show_date, start_time, end_time)
            VALUES (:show_id,:movieId, :screenId, :showDate, :startTime, :endTime, :price)
        """;
    // --------------------
    // Insert single show
    // --------------------
    public String save(Show show) {

        Map<String, Object> params = new HashMap<>();
        params.put("showId", show.getShowId());
        params.put("movieId", show.getMovieId());
        params.put("screenId", show.getScreenId());
        params.put("showDate", show.getShowDate());
        params.put("fromTime", show.getFromTime());
        params.put("toTime", show.getToTime());


        int success = namedParameterJdbcTemplate.update(
                sql,
                new MapSqlParameterSource(params));
        if(success > 0){
            System.out.println(" Show successfully saved...!");
        }else{
            throw new RuntimeException(" Issue in show save...!");
        }

        return show.getShowId();
    }

//    // --------------------
//    // Bulk insert
//    // --------------------
    public void saveAll(List<Show> shows) {

        List<Map<String, Object>> listParams = shows.stream()
                .map(show -> {
                    Map<String, Object> params = new HashMap<>();
                    params.put("showId", show.getShowId());
                    params.put("movieId", show.getMovieId());
                    params.put("screenId", show.getScreenId());
                    params.put("showDate", show.getShowDate());
                    params.put("fromTime", show.getFromTime());
                    params.put("toTime", show.getToTime());
                    return params;
                })
                .toList();

        // payment
        try {
            int[] successList = namedParameterJdbcTemplate.batchUpdate(sql, listParams.toArray(new Map[0]));
            int sum = Arrays.stream(successList).sum();
            if(sum == successList.length -1){
                System.out.println("successfully show added...!");
            }else{
                throw new RuntimeException(" Problem show added...!");
            }
        }catch (DuplicateKeyException ex) {
            throw new SeatAlreadyBookedException("Show already added");
        }
    }

    public Show getShowById(String showId) {

        String sql = """
        SELECT show_id, movie_id, screen_id,
               show_date, from_time, to_time
        FROM show
        WHERE show_id = :showId
    """;

        Map<String, Object> params = Map.of(
                "showId", showId
        );

        return namedParameterJdbcTemplate.queryForObject(sql,params,Show.class);
    }

    // --------------------
    // Search with optional filters
    // --------------------
    public List<Show> search(
            Long movieId,
            Long screenId,
            LocalDate showDate
    ) {

        StringBuilder sql = new StringBuilder("""
            SELECT *
            FROM show
            WHERE 1=1
        """);

        Map<String, Object> params = new HashMap<>();

        if (movieId != null) {
            sql.append(" AND movie_id = :movieId");
            params.put("movieId", movieId);
        }

        if (screenId != null) {
            sql.append(" AND screen_id = :screenId");
            params.put("screenId", screenId);
        }

        if (showDate != null) {
            sql.append(" AND show_date = :showDate");
            params.put("showDate", showDate);
        }

        ShowMapper showMapper =  namedParameterJdbcTemplate.queryForObject(sql.toString(), params,ShowMapper.class);
        return showMapper.getShows();
    }
}

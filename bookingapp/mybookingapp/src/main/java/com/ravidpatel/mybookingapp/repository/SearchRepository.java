package com.ravidpatel.mybookingapp.repository;

import com.ravidpatel.mybookingapp.entity.Movie;
import com.ravidpatel.mybookingapp.mapper.MovieMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class SearchRepository {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static String moviesByTheater = """
    select m.* from theater th
    inner join screen sc on (th.theaterId = sc.theaterId)
    inner join show sh on (sc.screenId = sh.screenId)
    inner join movie m on (sh.movieId = m.movieId)
    where th.theaterCity like '%:city%'
""";

    public List<Movie> moviesByTheater(String theaterId){

        return new ArrayList<>();
    }

    public List<Movie> moviesByCity(String city){
        Map<String, Object> params = new HashMap<>();
        params.put("city",city);
        MovieMapper movieMapper = namedParameterJdbcTemplate.queryForObject(moviesByTheater, params, MovieMapper.class);
        if(movieMapper != null && movieMapper.getMovies() != null && !movieMapper.getMovies().isEmpty()){
            System.out.println("All movie Data...!");
            return movieMapper.getMovies();
        }else{
            return new ArrayList<>();
        }
    }
}

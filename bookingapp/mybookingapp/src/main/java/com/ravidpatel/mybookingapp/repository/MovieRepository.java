package com.ravidpatel.mybookingapp.repository;

import com.ravidpatel.mybookingapp.constant.BookingStatus;
import com.ravidpatel.mybookingapp.dto.BookingRequestDto;
import com.ravidpatel.mybookingapp.dto.MovieDto;
import com.ravidpatel.mybookingapp.entity.Booking;
import com.ravidpatel.mybookingapp.entity.Movie;
import com.ravidpatel.mybookingapp.exceptions.SeatAlreadyBookedException;
import com.ravidpatel.mybookingapp.exceptions.SeatException;
import com.ravidpatel.mybookingapp.mapper.MovieMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class MovieRepository {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private String movieQuery = """
            INSERT INTO movie (:movie_id,:movie_name)
        """;

    private String moviesSearchQuery = """
            select * from movie where 
            (movie_id = null or movie_id = :movie_id)
            and ( movie_name is null or movie_name = :movie:name)
        """;
    private String moviesQueryByMovieId = """
            select * from movie where movie_id = null(:movie_id,movie_id)
        """;
    // use status for active movie

    private String moviesQueryByMovieName = """
            select * from movie where movie_name = :movie_name;
        """;

    private String movieQuerys = """
            INSERT INTO movie (movie_id,movie_name)
            VALUES (:movie_id,:movie_name)
        """;

    public String addMovie(MovieDto movieDto) {
        Map<String, Object> params = new HashMap<>();
        Movie movie = convertIntoEntity(movieDto);
        movie.setMovieId(UUID.randomUUID().toString());
        params.put("movie_id",movieDto.getMovieId());
        params.put("movie_name",movieDto.getMovieName());
        int success = namedParameterJdbcTemplate.update(movieQuery, params);
        if (success > 0) {
            System.out.println("successfully booking done...!");
        } else {
            throw new RuntimeException(" Problem with booking...!");
        }
        return movie.getMovieId();
    }

    public List<MovieDto> getAllMovie(MovieDto movieDto){
        Map<String, Object> params = new HashMap<>();
        params.put("movie_id",movieDto.getMovieId());
        params.put("movie_name",movieDto.getMovieName());
        MovieMapper movieMapper = namedParameterJdbcTemplate.queryForObject(moviesSearchQuery, params, MovieMapper.class);
        if(movieMapper != null && movieMapper.getMovies() != null && !movieMapper.getMovies().isEmpty()){
            System.out.println("All movie Data...!");
            return movieMapper.getMovies().stream().map(this::convertIntoDto).collect(Collectors.toList());
        }else{
            return new ArrayList<>();
        }
    }

    public Movie convertIntoEntity(MovieDto movieDto){
        Movie movie = new Movie();
        movie.setMovieId(movieDto.getMovieId());
        movie.setMovieName(movieDto.getMovieName());
        return movie;
    }

    public MovieDto convertIntoDto(Movie movie){
        MovieDto movieDto = new MovieDto();
        movieDto.setMovieId(movie.getMovieId());
        movieDto.setMovieName(movie.getMovieName());
        return movieDto;
    }
}

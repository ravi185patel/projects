package com.ravidpatel.mybookingapp.service;

import com.ravidpatel.mybookingapp.dto.MovieDto;
import com.ravidpatel.mybookingapp.entity.Movie;
import com.ravidpatel.mybookingapp.mapper.MovieMapper;
import com.ravidpatel.mybookingapp.repository.SearchRepository;
import com.ravidpatel.mybookingapp.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SearchService {


    @Autowired
    private SearchRepository searchRepository;

    private List<Movie> moviesByTheater(String theaterId){

        return new ArrayList<>();
    }

    public List<MovieDto> moviesByCity(String city){
        return searchRepository.moviesByCity(city).stream().map(movie -> convertIntoDto(movie)).toList();
    }

    public MovieDto convertIntoDto(Movie movie){
        MovieDto movieDto = new MovieDto();
        movieDto.setMovieId(movie.getMovieId());
        movieDto.setMovieName(movie.getMovieName());
        return movieDto;
    }
}

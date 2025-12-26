package com.ravidpatel.mybookingapp.service;

import com.ravidpatel.mybookingapp.dto.MovieDto;
import com.ravidpatel.mybookingapp.mapper.MovieMapper;
import com.ravidpatel.mybookingapp.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public String addMovie(MovieDto movieDto) {
       return movieRepository.addMovie(movieDto);
    }

    public List<MovieDto> getAllMovie(MovieDto movieDto){
        return movieRepository.getAllMovie(movieDto);
    }
}

package com.ravidpatel.mybookingapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

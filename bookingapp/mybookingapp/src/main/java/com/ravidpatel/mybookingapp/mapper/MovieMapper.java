package com.ravidpatel.mybookingapp.mapper;

import com.ravidpatel.mybookingapp.entity.Movie;

import java.util.List;

public class MovieMapper {

    List<Movie> movies;

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}

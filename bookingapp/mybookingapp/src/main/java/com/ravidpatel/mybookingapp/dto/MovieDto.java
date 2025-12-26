package com.ravidpatel.mybookingapp.dto;

public class MovieDto {
    private String movieId;
    private String movieName;

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public MovieDto(){

    }
    public MovieDto(String movieId, String movieName) {
        this.movieId = movieId;
        this.movieName = movieName;
    }
}

package com.ravidpatel.mybookingapp.controller;

import com.ravidpatel.mybookingapp.dto.MovieDto;
import com.ravidpatel.mybookingapp.dto.ResponseDto;
import com.ravidpatel.mybookingapp.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/{movie_name}")
    public List<MovieDto> getAllMovie(@PathVariable("movie_name") String movieName){
        return movieService.getAllMovie(new MovieDto(null,movieName));
    }

    @PostMapping("/")
    public ResponseEntity<ResponseDto> getAllMovie(@RequestBody MovieDto movieDto){
        String movieId= movieService.addMovie(movieDto);
        return ResponseEntity.ok(new ResponseDto.ResponseDtoBuilder<>().setStatus("Success").setData(movieId).build());
    }
}

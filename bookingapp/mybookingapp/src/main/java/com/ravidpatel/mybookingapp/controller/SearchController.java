package com.ravidpatel.mybookingapp.controller;

import com.ravidpatel.mybookingapp.dto.MovieDto;
import com.ravidpatel.mybookingapp.dto.ShowDto;
import com.ravidpatel.mybookingapp.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/search/")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping("{theaterId")
    private List<MovieDto> moviesByTheater(@RequestParam String theaterId){
        return new ArrayList<>();
    }

    @GetMapping("{city}")
    private List<MovieDto> moviesByCity(@RequestParam String city){
        return searchService.moviesByCity(city);
    }

}

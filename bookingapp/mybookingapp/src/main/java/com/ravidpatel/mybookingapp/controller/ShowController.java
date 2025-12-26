package com.ravidpatel.mybookingapp.controller;

import com.ravidpatel.mybookingapp.dto.RequestDto;
import com.ravidpatel.mybookingapp.dto.ResponseDto;
import com.ravidpatel.mybookingapp.dto.ShowDto;
import com.ravidpatel.mybookingapp.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/shows")
public class ShowController {

    @Autowired
    private ShowService showService;
    @PostMapping
    public ResponseEntity<ResponseDto<String>> createShow(@RequestBody RequestDto<ShowDto> dto
    ) {
        String id = showService.createShow(dto.getData());
        return ResponseEntity.ok(new ResponseDto.ResponseDtoBuilder().setStatus("OK").setData(id).build());
    }

    @PostMapping("/bulk")
    public ResponseEntity<ResponseDto<List<String>>> createShows(
            @RequestBody RequestDto<List<ShowDto>> dtos
    ) {
        List<String> ids = showService.createShows(dtos.getData());
        return ResponseEntity.ok(new ResponseDto.ResponseDtoBuilder().setStatus("OK").setData(ids).build());
    }

    @GetMapping("/{showId}")
    public ResponseEntity<ResponseDto<ShowDto>> getShowById(@PathVariable String showId) {
        ShowDto showDto = showService.getShowById(showId);
        return ResponseEntity.ok(new ResponseDto.ResponseDtoBuilder().setStatus("OK").setData(showDto).build());
    }

    @GetMapping
    public ResponseEntity<ResponseDto<List<ShowDto>>> searchShows(
            @RequestParam(required = false) Long movieId,
            @RequestParam(required = false) Long screenId,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate showDate
    ) {
        List<ShowDto>  shows= showService.searchShows(movieId, screenId, showDate);
        return ResponseEntity.ok(new ResponseDto.ResponseDtoBuilder().setStatus("OK").setData(shows).build());
    }
}

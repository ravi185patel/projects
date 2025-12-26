package com.ravidpatel.mybookingapp.controller;

import com.ravidpatel.mybookingapp.dto.ConsumerDto;
import com.ravidpatel.mybookingapp.dto.RequestDto;
import com.ravidpatel.mybookingapp.dto.ResponseDto;
import com.ravidpatel.mybookingapp.dto.TheaterDto;
import com.ravidpatel.mybookingapp.service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/theaters")
public class TheaterController {

    @Autowired
    private TheaterService service;

    @PostMapping
    public ResponseEntity<ResponseDto<String>> create(@RequestBody RequestDto<TheaterDto> dto) {
        String id = service.save(dto.getData());
        return ResponseEntity.ok(new ResponseDto.ResponseDtoBuilder<String>().setStatus("Ok").setData(id).build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<TheaterDto>> get(@PathVariable String id) {
        TheaterDto theaterDto = service.getById(id);
        return ResponseEntity.ok(new ResponseDto.ResponseDtoBuilder<TheaterDto>().setStatus("Ok").setData(theaterDto).build());
    }
}

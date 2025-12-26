package com.ravidpatel.mybookingapp.controller;

import com.ravidpatel.mybookingapp.dto.RequestDto;
import com.ravidpatel.mybookingapp.dto.ResponseDto;
import com.ravidpatel.mybookingapp.dto.ScreenDto;
import com.ravidpatel.mybookingapp.service.ScreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/screens")
public class ScreenController {

    @Autowired
    private ScreenService service;

    @PostMapping
    public ResponseEntity<ResponseDto<String>> create(@RequestBody RequestDto<ScreenDto> dto) {
        String id = service.save(dto.getData());
        return ResponseEntity.ok(new ResponseDto.ResponseDtoBuilder<String>().setStatus("OK").setData(id).build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<ScreenDto>> get(@PathVariable String id) {
        ScreenDto screenDto = service.getById(id);
        return ResponseEntity.ok(new ResponseDto.ResponseDtoBuilder<ScreenDto>().setStatus("OK").setData(screenDto).build());
    }
}

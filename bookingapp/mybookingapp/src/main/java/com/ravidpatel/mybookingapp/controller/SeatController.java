package com.ravidpatel.mybookingapp.controller;

import com.ravidpatel.mybookingapp.dto.RequestDto;
import com.ravidpatel.mybookingapp.dto.ResponseDto;
import com.ravidpatel.mybookingapp.dto.SeatDto;
import com.ravidpatel.mybookingapp.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/seats")
public class SeatController {

    @Autowired
    private SeatService service;

    public SeatController(SeatService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ResponseDto<String>> create(@RequestBody RequestDto<SeatDto> dto) {
        String id = service.save(dto.getData());
        return ResponseEntity.ok(new ResponseDto.ResponseDtoBuilder<String>().setStatus("OK").setData(id).build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<SeatDto>> get(@PathVariable String id) {
        SeatDto seatDto = service.getById(id);
        return ResponseEntity.ok(new ResponseDto.ResponseDtoBuilder<SeatDto>().setStatus("OK").setData(seatDto).build());
    }
}

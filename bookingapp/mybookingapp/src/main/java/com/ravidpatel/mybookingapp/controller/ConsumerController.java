package com.ravidpatel.mybookingapp.controller;

import com.ravidpatel.mybookingapp.dto.ConsumerDto;
import com.ravidpatel.mybookingapp.dto.ResponseDto;
import com.ravidpatel.mybookingapp.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/consumers")
public class ConsumerController {

    @Autowired
    private ConsumerService consumerService;

    public ConsumerController(ConsumerService consumerService) {
        this.consumerService = consumerService;
    }

    // CREATE USER
    @PostMapping
    public ResponseEntity<ResponseDto<String>> createConsumer(@RequestBody ConsumerDto dto) {
        String id = consumerService.saveConsumer(dto);
        return ResponseEntity.ok(new ResponseDto.ResponseDtoBuilder<String>().setStatus("Ok").setData(id).build());
    }

    // GET USER
    @GetMapping("/{consumerId}")
    public ResponseEntity<ResponseDto<ConsumerDto>> getConsumer(@PathVariable String consumerId) {
        ConsumerDto consumerDto = consumerService.getConsumer(consumerId);
        return ResponseEntity.ok(new ResponseDto.ResponseDtoBuilder<ConsumerDto>().setStatus("Ok").setData(consumerDto).build());
    }

//    // LOGIN
//    @PostMapping("/authenticate")
//    public ResponseEntity<String> authenticate(
//            @RequestBody ConsumerDto dto) {
//
//        boolean authenticated = consumerService.authenticate(dto);
//
//        if (authenticated) {
//            return ResponseEntity.ok("Authentication successful");
//        }
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                .body("Invalid credentials");
//    }
}

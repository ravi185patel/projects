package com.ravidpatel.mybookingapp.controller;

import com.ravidpatel.mybookingapp.dto.BookingRequestDto;
import com.ravidpatel.mybookingapp.dto.RequestDto;
import com.ravidpatel.mybookingapp.dto.ResponseDto;
import com.ravidpatel.mybookingapp.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("mybookingapp")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/show/{showId}")
    private String getShows(@PathVariable String showId){
        return bookingService.getShows(showId);
    }

    @PostMapping("/book/show")
    private ResponseEntity createBooking(@RequestBody RequestDto<BookingRequestDto> bookingRequestDto){
        String bookingId = bookingService.createBooking(bookingRequestDto.getData());
        ResponseDto<String> responseDto = new ResponseDto<String>(bookingId,"successs");
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/book/show/{bookId}")
    private String getShowBooking(@PathVariable String bookId){
        return bookingService.getShowBooking(bookId);
    }

}

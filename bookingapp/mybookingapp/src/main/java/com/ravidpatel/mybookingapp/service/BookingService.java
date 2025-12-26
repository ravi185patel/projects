package com.ravidpatel.mybookingapp.service;

import com.ravidpatel.mybookingapp.dto.BookingRequestDto;
import com.ravidpatel.mybookingapp.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public String getShows(String showId){
        return "Available";
    }

    public String createBooking(BookingRequestDto bookingRequestDto){
        return bookingRepository.createBooking(bookingRequestDto);
    }

    public String getShowBooking(String bookId){
        return "seat-1";
    }

}

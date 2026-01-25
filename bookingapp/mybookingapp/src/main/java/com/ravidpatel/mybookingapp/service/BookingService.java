package com.ravidpatel.mybookingapp.service;

import com.ravidpatel.mybookingapp.constant.BookingStatus;
import com.ravidpatel.mybookingapp.dto.BookingRequestDto;
import com.ravidpatel.mybookingapp.dto.BookingResponseDto;
import com.ravidpatel.mybookingapp.entity.Booking;
import com.ravidpatel.mybookingapp.entity.BookingSeat;
import com.ravidpatel.mybookingapp.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class BookingService {

    private BookingRepository bookingRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository){
        this.bookingRepository = bookingRepository;
    }

    @Transactional
    public BookingResponseDto createBooking(BookingRequestDto dto, String userId){
        String bookingId = UUID.randomUUID().toString();

        // 1️⃣ Create Booking entity
        Booking booking = new Booking();
        booking.setBookingId(bookingId);
        booking.setScheduleId(dto.getScheduleId());
        booking.setUserId(userId);
        booking.setStatus(BookingStatus.CREATED);
        booking.setCreatedAt(LocalDateTime.now());

        bookingRepository.insertBooking(booking);

        List<BookingSeat> seats = dto.getSeatIds().stream()
                .map(seatId -> {
                    BookingSeat bs = new BookingSeat();
                    bs.setBookingId(bookingId);
                    bs.setScheduleId(dto.getScheduleId());
                    bs.setSeatId(seatId);
                    return bs;
                })
                .toList();

        // 3️⃣ Batch insert seats
        bookingRepository.insertBookingSeat(seats);
        // 🔥 if ANY seat already booked → UNIQUE constraint fails → rollback

        // 4️⃣ Build response
        BookingResponseDto response = new BookingResponseDto();
        response.setBookingId(bookingId);
        response.setScheduleId(dto.getScheduleId());
        response.setSeatIds(dto.getSeatIds());
        response.setStatus(BookingStatus.CREATED);
        response.setCreatedAt(booking.getCreatedAt());

        return response;
    }

    @Transactional
    public BookingResponseDto getBookingById(String bookingId){
        Booking booking = bookingRepository.getBookingById(bookingId);
        List<String> seats = bookingRepository.getSeatIdsByBookingId(bookingId);
        BookingResponseDto response = new BookingResponseDto();
        response.setBookingId(bookingId);
        response.setScheduleId(booking.getScheduleId());
        response.setSeatIds(seats);
        response.setStatus(booking.getStatus());
        response.setCreatedAt(booking.getCreatedAt());

        return response;
    }

}

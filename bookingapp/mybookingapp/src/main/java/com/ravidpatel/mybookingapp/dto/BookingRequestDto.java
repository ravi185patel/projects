package com.ravidpatel.mybookingapp.dto;

import com.ravidpatel.mybookingapp.constant.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

public class BookingRequestDto {
    private String bookingId;
    private String showId;
    private String userId;
    private BookingStatus bookingStatus;
    private LocalDateTime bookingDateTime;
    private List<String> seatId; // mapped with screen and user

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getShowId() {
        return showId;
    }

    public void setShowId(String showId) {
        this.showId = showId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public LocalDateTime getBookingDateTime() {
        return bookingDateTime;
    }

    public void setBookingDateTime(LocalDateTime bookingDateTime) {
        this.bookingDateTime = bookingDateTime;
    }

    public List<String> getSeatId() {
        return seatId;
    }

    public void setSeatId(List<String> seatId) {
        this.seatId = seatId;
    }
}

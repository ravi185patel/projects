package com.ravidpatel.mybookingapp.dto;

import com.ravidpatel.mybookingapp.constant.BookingStatus;

import java.time.LocalDateTime;
import java.util.List;

public class BookingResponseDto {

    private String bookingId;
    private String scheduleId;
    private List<String> seatIds;
    private BookingStatus status;
    private LocalDateTime createdAt;

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public List<String> getSeatIds() {
        return seatIds;
    }

    public void setSeatIds(List<String> seatIds) {
        this.seatIds = seatIds;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

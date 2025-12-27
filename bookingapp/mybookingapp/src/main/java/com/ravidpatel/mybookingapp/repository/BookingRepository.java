package com.ravidpatel.mybookingapp.repository;

import com.ravidpatel.mybookingapp.config.DbContextHolder;
import com.ravidpatel.mybookingapp.constant.BookingStatus;
import com.ravidpatel.mybookingapp.dto.BookingRequestDto;
import com.ravidpatel.mybookingapp.entity.Booking;
import com.ravidpatel.mybookingapp.exceptions.SeatAlreadyBookedException;
import com.ravidpatel.mybookingapp.exceptions.SeatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public class BookingRepository{

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private String bookingQuery = """
            INSERT INTO booking (booking_id,show_id,consumer_id,booking_date,booking_status)
            VALUES (:booking_id,:show_id,:consumer_id,:booking_date,:booking_status)
        """;
    private String validateSeats = """
                SELECT count(seat_id) SEATS FROM BOOKING B
                    INNER JOIN BOOKING_SEAT BS ON ( B.BOOKING_ID = BS.BOOKING_ID)
                    WHERE B.SHOW_ID = :SHOW_ID
                    AND BS.SEAT_ID IN( :SEAT_ID)
            """;
    private String seatQuery = """
            INSERT INTO booking_seat (booking_id,seat_id,show_id)
            VALUES (:booking_id,:seat_id,:show_id)
        """;

//    @Transactional
    public String createBooking(BookingRequestDto bookingRequestDto) {
        validateSelectedSeats(bookingRequestDto);
        return bookSeats(bookingRequestDto);
    }

    @Transactional(readOnly = true)
    public void validateSelectedSeats(BookingRequestDto bookingRequestDto){
        DbContextHolder.useSlave();
        Map<String, Object> params = new HashMap<>();
        params.put("SHOW_ID",bookingRequestDto.getShowId());
        params.put("SEAT_ID",bookingRequestDto.getSeatId());
        int noOfSeatsOccupied = namedParameterJdbcTemplate.queryForObject(validateSeats, params,Integer.class);
        if(noOfSeatsOccupied == 0){
            System.out.println("All seat are free to book...!");
            DbContextHolder.clear();
        }else{
            DbContextHolder.clear();
            throw new SeatException(" Few of selected seats got occupied..! ");
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public String bookSeats(BookingRequestDto bookingRequestDto){
        DbContextHolder.useMaster();
        Booking booking = convertIntoEntity(bookingRequestDto);
        Map<String,Object> params = new HashMap<>();
        params.put("booking_id", booking.getBookingId());
        params.put("show_id", booking.getShowId());
        params.put("consumer_id", booking.getUserId());
        params.put("booking_date", LocalDateTime.now());
        params.put("booking_status", BookingStatus.CONFIRMED.getName());

        int success = namedParameterJdbcTemplate.update(bookingQuery, params);
        if (success > 0) {
            System.out.println("successfully booking done...!");
        } else {
            DbContextHolder.clear();
            throw new RuntimeException(" Problem with booking...!");
        }
        List<Map<String, Object>> listParams = bookingRequestDto.getSeatId().stream()
                .map(seatId -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("booking_id", booking.getBookingId());
                    map.put("show_id", booking.getShowId());
                    map.put("seat_id", seatId);
                    return map;
                })
                .toList();

        // payment
        try {
            int[] successList = namedParameterJdbcTemplate.batchUpdate(seatQuery, listParams.toArray(new Map[0]));
            if(successList.length > 0){
                System.out.println("successfully seat reserved...!");
            }else{
                throw new RuntimeException(" Problem seat reserved...!");
            }
        }catch (DuplicateKeyException ex) {
            throw new SeatAlreadyBookedException("Seat already booked");
        }finally {
            DbContextHolder.clear();
        }


        // payment process
        return booking.getBookingId();
    }
    public Booking convertIntoEntity(BookingRequestDto bookingRequestDto){
        Booking booking = new Booking();
        booking.setBookingId(UUID.randomUUID().toString());
        booking.setUserId(bookingRequestDto.getUserId());
        booking.setShowId(bookingRequestDto.getShowId());
        booking.setBookingDateTime(bookingRequestDto.getBookingDateTime());
        booking.setBookingStatus(bookingRequestDto.getBookingStatus());
        return booking;
    }
}

package com.ravidpatel.mybookingapp.repository;

import com.ravidpatel.mybookingapp.config.DbContextHolder;
import com.ravidpatel.mybookingapp.constant.BookingSql;
import com.ravidpatel.mybookingapp.constant.BookingStatus;
import com.ravidpatel.mybookingapp.dto.BookingRequestDto;
import com.ravidpatel.mybookingapp.entity.Booking;
import com.ravidpatel.mybookingapp.entity.BookingSeat;
import com.ravidpatel.mybookingapp.exceptions.SeatAlreadyBookedException;
import com.ravidpatel.mybookingapp.exceptions.SeatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BookingRepository{

    @Autowired
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate;
//
//    public BookingRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
//        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
//    }

//    public static String INSERT_BOOKING = """
//        INSERT INTO booking (booking_id, schedule_id, user_id, status, created_at) VALUES (:booking_id, :schedule_id, :user_id, :status, :created_at)
//    """;

    public static String INSERT_BOOKING_SEAT = """
        INSERT INTO booking_seat
        (booking_id, schedule_id, seat_id)
        VALUES (:booking_id, :schedule_id, :seat_id)
    """;
    public void validateSelectedSeats(BookingRequestDto bookingRequestDto){ // create race condition
        DbContextHolder.useSlave();
        Map<String, Object> params = new HashMap<>();
        params.put("SCHEDULE_ID",bookingRequestDto.getScheduleId());
        params.put("SEAT_ID",bookingRequestDto.getSeatIds());
        int noOfSeatsOccupied = namedParameterJdbcTemplate.queryForObject(BookingSql.VALIDATE_SEAT_BY_SEAT_AND_SHOW_ID, params,Integer.class);
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

    public void insertBooking(Booking booking){
        DbContextHolder.useMaster();
        Map<String,Object> params = new HashMap<>();
        params.put("booking_id", booking.getBookingId());
        params.put("schedule_id", Long.valueOf(booking.getScheduleId()));
        params.put("user_id",booking.getUserId());
        params.put("created_at", Timestamp.valueOf(LocalDateTime.now()));
        params.put("status", BookingStatus.CONFIRMED.name());

        int success = namedParameterJdbcTemplate.update("INSERT INTO booking (booking_id, schedule_id, user_id, status, created_at) VALUES (:booking_id, :schedule_id, :user_id, :status, :created_at)", params);
        if (success > 0) {
            System.out.println("successfully booking done...!");
        } else {
            DbContextHolder.clear();
            throw new RuntimeException(" Problem with booking...!");
        }
    }

    public void insertBookingSeat(List<BookingSeat> bookingSeats){
        List<Map<String, Object>> listParams = bookingSeats.stream()
                .map(bookingSeat -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("booking_id", bookingSeat.getBookingId());
                    map.put("schedule_id", Long.valueOf(bookingSeat.getScheduleId()));
                    map.put("seat_id", Long.valueOf(bookingSeat.getSeatId()));
                    return map;
                })
                .toList();

        // payment
        try {
            int[] successList = namedParameterJdbcTemplate.batchUpdate(INSERT_BOOKING_SEAT, listParams.toArray(new Map[0]));
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
    }


    public Booking getBookingById(String bookingId){
        Map<String, Object> params = Map.of("bookingId", bookingId);
        return namedParameterJdbcTemplate.queryForObject(
                BookingSql.FIND_BOOKING_BY_ID,
                params,
                BOOKING_ROW_MAPPER
        );
    }

    public List<String> getSeatIdsByBookingId(String bookingId) {

        String sql = """
            SELECT seat_id
            FROM booking_seat
            WHERE booking_id = :bookingId
        """;

        return namedParameterJdbcTemplate.queryForList(
                sql,
                Map.of("bookingId", bookingId),
                String.class
        );
    }

    private static final RowMapper<Booking> BOOKING_ROW_MAPPER =
            (rs, rowNum) -> {
                Booking booking = new Booking();
                booking.setBookingId(rs.getString("booking_id"));
                booking.setScheduleId(rs.getString("schedule_id"));
                booking.setUserId(rs.getString("user_id"));
                booking.setStatus(
                        BookingStatus.valueOf(rs.getString("status"))
                );
                booking.setCreatedAt(
                        rs.getTimestamp("created_at").toLocalDateTime()
                );
                return booking;
            };
}

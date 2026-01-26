package com.ravidpatel.mybookingapp.constant;

public final class BookingSql {

    private BookingSql() {}

    public static final String INSERT_BOOKING = """
        INSERT INTO booking
        (booking_id, schedule_id, user_id, status, created_at)
        VALUES (:booking_id, :schedule_id, :user_id, :status, :created_at)
    """;

    public static final String INSERT_BOOKING_SEAT = """
        INSERT INTO booking_seat
        (booking_id, schedule_id, seat_id)
        VALUES (:booking_id, :schedule_id, :user_id)
    """;

    public static final String FIND_BOOKING_BY_ID = """
        SELECT *
        FROM booking
        WHERE booking_id = :booking_id
    """;

    public static final String VALIDATE_SEAT_BY_SEAT_AND_SHOW_ID = """
          SELECT count(seat_id) SEATS FROM BOOKING B
                    INNER JOIN BOOKING_SEAT BS ON ( B.BOOKING_ID = BS.BOOKING_ID)
                    WHERE B.SHOW_ID = :SHOW_ID
                    AND BS.SEAT_ID IN( :SEAT_ID)
    """;

    String sql = """
            INSERT INTO venue (venue_name, city, venue_type)
            VALUES (:name, :city, :type)
        """;

}

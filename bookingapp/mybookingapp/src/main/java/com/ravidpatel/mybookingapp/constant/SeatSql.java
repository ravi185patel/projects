package com.ravidpatel.mybookingapp.constant;

public final class SeatSql {

    private SeatSql() {}

    public static final String INSERT = """
        INSERT INTO seat (space_id, row_label, seat_no, seat_type)
        VALUES (:spaceId, :row, :seatNo, :type)
    """;

    public static final String FIND_BY_SPACE =
        "SELECT * FROM seat WHERE space_id = :spaceId";
}

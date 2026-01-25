package com.ravidpatel.mybookingapp.constant;

public final class ScheduleSql {

    private ScheduleSql() {}

    public static final String INSERT = """
        INSERT INTO schedule
        (content_id, space_id, schedule_date, from_time, to_time, base_price)
        VALUES (:contentId, :spaceId, :date, :fromTime, :toTime, :price)
    """;

    public static final String UPDATE_PRICE = """
        UPDATE schedule
        SET base_price = :price
        WHERE schedule_id = :scheduleId
    """;

    public static final String FIND_BY_ID =
        "SELECT * FROM schedule WHERE schedule_id = :scheduleId";
}

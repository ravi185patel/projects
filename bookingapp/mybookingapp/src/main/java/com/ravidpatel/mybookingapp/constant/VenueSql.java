package com.ravidpatel.mybookingapp.constant;

public final class VenueSql {

    private VenueSql() {}

    public static final String INSERT = """
        INSERT INTO venue (venue_name, city, venue_type)
        VALUES (:name, :city, :type)
    """;

    public static final String UPDATE = """
        UPDATE venue
        SET venue_name = :name, city = :city
        WHERE venue_id = :venueId
    """;

    public static final String FIND_BY_ID =
        "SELECT * FROM venue WHERE venue_id = :venueId";
}

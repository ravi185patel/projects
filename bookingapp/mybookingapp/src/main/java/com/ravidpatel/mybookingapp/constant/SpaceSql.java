package com.ravidpatel.mybookingapp.constant;

public final class SpaceSql {

    private SpaceSql() {}

    public static final String INSERT = """
        INSERT INTO space (venue_id, space_name, capacity)
        VALUES (:venueId, :name, :capacity)
    """;

    public static final String UPDATE = """
        UPDATE space
        SET capacity = :capacity
        WHERE space_id = :spaceId
    """;

    public static final String FIND_BY_ID =
        "SELECT * FROM space WHERE space_id = :spaceId";
}

package com.ravidpatel.mybookingapp.constant;

public final class ContentSql {

    private ContentSql() {}

    public static final String INSERT = """
        INSERT INTO content (content_type, title, language, duration_minutes)
        VALUES (:type, :title, :language, :duration)
    """;

    public static final String UPDATE = """
        UPDATE content
        SET title = :title, language = :language
        WHERE content_id = :contentId
    """;

    public static final String FIND_BY_ID =
        "SELECT * FROM content WHERE content_id = :contentId";
}

package com.ravidpatel.mybookingapp.constant;

public final class UserSql {

    private UserSql() {}

    public static final String INSERT_USER = """
        INSERT INTO app_user
        (user_id, user_name, email, created_at)
        VALUES (:userId, :userName, :email, :createdAt)
    """;

    public static final String FIND_BY_ID = """
        SELECT user_id, user_name, email, created_at
        FROM app_user
        WHERE user_id = :userId
    """;
}
